package jp.co.sample.emp_management.form;

import javax.validation.constraints.*;

/**
 * 管理者情報登録時に使用するフォーム.
 * 
 * @author yumi takahashi
 * 
 */
public class InsertAdministratorForm {
	/** 名前 */
	@NotBlank(message = "名前を入力してください")
	@Size(min = 0, max = 50, message = "名前は50文字以内で入力してください")
	private String name;

	/** メールアドレス */
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "Emailの形式が不正です")
	private String mailAddress;

	/** パスワード */
	@Size(min = 4, max = 8, message = "パスワードは4文字以上8文字以下で入力してください")
	private String password;

	// getter setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// toString
	@Override
	public String toString() {
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ "]";
	}

}
