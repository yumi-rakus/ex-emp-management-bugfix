package jp.co.sample.emp_management.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sample.emp_management.domain.Employee;
import jp.co.sample.emp_management.form.InsertEmployeeForm;
import jp.co.sample.emp_management.form.UpdateEmployeeForm;
import jp.co.sample.emp_management.service.EmployeeService;

/**
 * 従業員情報を操作するコントローラー.
 * 
 * @author yumi takahashi
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private HttpSession employeeSession;

	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpForm() {
		return new UpdateEmployeeForm();
	}

	@ModelAttribute
	public InsertEmployeeForm setUpInsertEmployeeForm() {
		return new InsertEmployeeForm();
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員一覧を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員一覧(10件ずつ)画面を出力します.
	 * 
	 * @param model モデル
	 * @return 従業員一覧画面
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {

		Integer offset = 0;
		List<Employee> employeeList = employeeService.showTenList(offset);

		model.addAttribute("employeeList", employeeList);

		employeeSession.setAttribute("offset", offset);
		employeeSession.setAttribute("count", employeeService.employeeCount());

		List<Employee> employeeListAll = employeeService.showList();
		String employeesName = "";

		for (Employee employee : employeeListAll) {
			employeesName = employeesName + employee.getName() + ",";
		}

		employeeSession.setAttribute("employeesName", employeesName);

		return "employee/list";
	}

	/**
	 * 従業員一覧画面で次の10件へ
	 * 
	 * @param model モデル
	 * @return 従業員一覧画面
	 */
	@RequestMapping("/showNext")
	public String showNext(Model model) {

		Integer count = employeeService.employeeCount();
		Integer offset = (Integer) employeeSession.getAttribute("offset");
		offset += 10;

		if (offset > count) {
			offset = count - 1;
			Integer num = offset % 10;

			offset = num * 10;
		}

		List<Employee> employeeList = employeeService.showTenList(offset);

		model.addAttribute("employeeList", employeeList);
		employeeSession.setAttribute("offset", offset);

		return "employee/list";
	}

	/**
	 * 従業員一覧画面で前の10件へ
	 * 
	 * @param model モデル
	 * @return 従業員一覧画面
	 */
	@RequestMapping("/showBack")
	public String showBack(Model model) {

		Integer offset = (Integer) employeeSession.getAttribute("offset");
		offset -= 10;

		if (offset < 0) {
			offset = 0;
		}

		List<Employee> employeeList = employeeService.showTenList(offset);

		model.addAttribute("employeeList", employeeList);
		employeeSession.setAttribute("offset", offset);

		return "employee/list";
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員詳細を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員詳細画面を出力します.
	 * 
	 * @param id    リクエストパラメータで送られてくる従業員ID
	 * @param model モデル
	 * @return 従業員詳細画面
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		return "employee/detail";
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員詳細を更新する
	/////////////////////////////////////////////////////
	/**
	 * 従業員詳細(ここでは扶養人数のみ)を更新します.
	 * 
	 * @param form 従業員情報用フォーム
	 * @return 従業員一覧画面へリダクレクト
	 */
	@RequestMapping("/update")
	public String update(@Validated UpdateEmployeeForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return showDetail(form.getId(), model);
		}
		Employee employee = new Employee();
		employee.setId(form.getIntId());
		employee.setDependentsCount(form.getIntDependentsCount());
		employeeService.update(employee);
		return "redirect:/employee/showList";
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員を検索する
	/////////////////////////////////////////////////////
	/**
	 * 検索された従業員を表示します.
	 * 
	 * @param keyName            曖昧検索キー
	 * @param redirectAttributes Flashスコープ
	 * @return 検索された従業員一覧画面へリダイレクト
	 */
	@RequestMapping("/search")
	public String search(String keyName, RedirectAttributes redirectAttributes) {

		List<Employee> employeeList = employeeService.search(keyName);

		// 空文字で検索した場合全件検索結果を表示
		if (keyName.trim().equals("")) {

			return "redirect:/employee/showList";
		}

		// 指定した文字列が存在しなかった場合「１件もありませんでした」というメッセージと共に全件検索結果を表示
		if (employeeList.isEmpty()) {

			redirectAttributes.addFlashAttribute("result", "お探しの従業員は1件も存在しませんでした");

			return "redirect:/employee/showList";
		}

		redirectAttributes.addFlashAttribute("employeeList", employeeList);
		employeeSession.setAttribute("count", employeeList.size());
		employeeSession.setAttribute("offset", 0);

		return "redirect:/employee/showSearchResult";
	}

	/**
	 * 検索された従業員一覧画面を出力します.
	 * 
	 * @return 検索された従業員一覧画面
	 */
	@RequestMapping("/showSearchResult")
	public String showSearchResult() {
		return "employee/list";
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員を新規登録する
	/////////////////////////////////////////////////////
	/**
	 * 従業員新規登録画面を出力します.
	 * 
	 * @param model モデル
	 * @return 従業員新規登録画面
	 */
	@RequestMapping("/toRegister")
	public String toRegister(Model model) {

		// hireYear(1900~現在の西暦)
		List<Object> hireYearList = new ArrayList<>();

		LocalDate ldt = LocalDate.now();
		Integer currentYear = ldt.getYear();

		hireYearList.add("----");

		for (Integer i = currentYear; i >= 1900; i--) {
			hireYearList.add(i);
		}

		model.addAttribute("hireYearList", hireYearList);

		// hireMonth(1~12)
		List<Object> hireMonthList = new ArrayList<>();

		hireMonthList.add("----");

		for (Integer i = 1; i <= 12; i++) {
			hireMonthList.add(i);
		}

		model.addAttribute("hireMonthList", hireMonthList);

		// hireDay(1~31)
		List<Object> hireDayList = new ArrayList<>();

		hireDayList.add("----");

		for (Integer i = 1; i <= 31; i++) {
			hireDayList.add(i);
		}

		model.addAttribute("hireDayList", hireDayList);

		return "employee/register";
	}

	/**
	 * 従業員を新規登録します.
	 * 
	 * @param form   フォーム
	 * @param result BindingResultオブジェクト
	 * @param model  モデル
	 * @return 従業員一覧画面
	 * @throws Exception
	 */
	@RequestMapping("/register")
	synchronized String register(@Validated InsertEmployeeForm form, BindingResult result, Model model)
			throws Exception {

		boolean exist = employeeService.findByMailAddress(form.getMailAddress());

		if (exist) {
			model.addAttribute("mailAddressResult", "既に登録されているメールアドレスです。");
			return toRegister(model);
		}

		if (form.getImage().getOriginalFilename().equals("")) {
			model.addAttribute("imageResult", "画像を選択してください");
			return toRegister(model);
		}

		if (result.hasErrors()) {
			return toRegister(model);
		}

		System.out.println(form.getImage().getSize());

		StringBuffer data = new StringBuffer();

		String base64 = new String(Base64.encodeBase64(form.getImage().getBytes()), "ASCII");

		data.append("data:image/jpeg;base64,");
		data.append(base64);

		String imageData = data.toString();

		Employee employee = new Employee();

		employee.setName(form.getName());
		employee.setKanaName(form.getKanaName());
		employee.setImage(imageData);
		employee.setGender(form.getGender());

		LocalDate hireDateL = LocalDate.of(Integer.parseInt(form.getHireYear()), Integer.parseInt(form.getHireMonth()),
				Integer.parseInt(form.getHireDay()));

		Date hireDate = Date.valueOf(hireDateL);

		employee.setHireDate(hireDate);

		employee.setMailAddress(form.getMailAddress());
		employee.setZipCode(form.getZipCodeFirst() + "-" + form.getZipCodeLast());
		employee.setAddress(form.getAddressFirst() + form.getAddressLast());
		employee.setTelephone(
				form.getTelephoneFirst() + "-" + form.getTelephoneMiddle() + "-" + form.getTelephoneLast());
		employee.setSalary(form.getSalary());
		employee.setCharacteristics(form.getCharacteristics());
		employee.setDependentsCount(form.getDependentsCount());

		employeeService.register(employee);

		return "redirect:/employee/showList";
	}

}
