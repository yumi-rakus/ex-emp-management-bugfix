package jp.co.sample.emp_management.controller;

import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.emp_management.domain.Administrator;
import jp.co.sample.emp_management.form.InsertAdministratorForm;
import jp.co.sample.emp_management.service.AdministratorService;

/**
 * 管理者情報を操作するコントローラー.
 * 
 * @author yumi takahashi
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private HttpSession session;

	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}

	/////////////////////////////////////////////////////
	// ユースケース：管理者を登録する
	/////////////////////////////////////////////////////
	/**
	 * 管理者登録画面を出力します.
	 * 
	 * @return 管理者登録画面
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}

	/**
	 * 管理者情報を登録します.
	 * 
	 * @param form 管理者情報用フォーム
	 * @return ログイン画面へリダイレクト
	 */
	@RequestMapping("/insert")
	public String insert(@Validated InsertAdministratorForm form, BindingResult result, String confirmPassword,
			Model model) {

		Administrator exist = administratorService.findByMailAddress(form.getMailAddress());

		if ((form.getPassword().length() >= 4 && form.getPassword().length() <= 8) || form.getPassword().equals("")) {

			if (!form.getPassword().equals(confirmPassword)) {

				model.addAttribute("confirmPassword", "入力されたパスワードと一致していません");
				return "administrator/insert";
			}
		}

		if (Objects.nonNull(exist)) {

			model.addAttribute("exist", "そのメールアドレスは既に登録されています");
			return "administrator/insert";
		}

		if (result.hasErrors()) {

			return "administrator/insert";
		}

		Administrator administrator = new Administrator();

		administrator.setName(form.getName());
		administrator.setMailAddress(form.getMailAddress());
		administratorService.insert(administrator, form.getPassword());

		return "redirect:/";
	}

	/////////////////////////////////////////////////////
	// ユースケース：ログインをする
	/////////////////////////////////////////////////////
	/**
	 * ログイン画面を出力します.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}

	/**
	 * ログイン失敗における処理をします.
	 * 
	 * @param model モデル
	 * @return ログイン画面
	 */
	@RequestMapping("/login")
	public String login(Model model) {

		model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です。");

		return toLogin();
	}

	/////////////////////////////////////////////////////
	// ユースケース：ログアウトをする
	/////////////////////////////////////////////////////
	/**
	 * ログアウトをします. (SpringSecurityに任せるためコメントアウトしました)
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping(value = "/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}

}
