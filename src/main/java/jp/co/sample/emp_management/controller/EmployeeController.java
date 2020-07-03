package jp.co.sample.emp_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sample.emp_management.domain.Employee;
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

	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpForm() {
		return new UpdateEmployeeForm();
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員一覧を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員一覧画面を出力します.
	 * 
	 * @param model モデル
	 * @return 従業員一覧画面
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
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
			employeeList = employeeService.showList();
			redirectAttributes.addFlashAttribute("employeeList", employeeList);

			return "redirect:/employee/showSearchResult";
		}

		// 指定した文字列が存在しなかった場合「１件もありませんでした」というメッセージと共に全件検索結果を表示
		if (employeeList.isEmpty()) {

			employeeList = employeeService.showList();

			redirectAttributes.addFlashAttribute("result", "お探しの従業員は1件も存在しませんでした");
			redirectAttributes.addFlashAttribute("employeeList", employeeList);

			return "redirect:/employee/showSearchResult";
		}

		redirectAttributes.addFlashAttribute("employeeList", employeeList);

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
}
