package jp.co.sample.emp_management.form;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

/**
 * 従業員情報登録時に使用するフォーム.
 * 
 * @author yumi takahashi
 *
 */
public class InsertEmployeeForm {

	/**
	 * 従業員名
	 */
	@NotBlank(message = "従業員名を入力してください")
	@Size(min = 0, max = 50, message = "従業員名は50文字以内で入力してください")
	private String name;

	/**
	 * 従業員名ふりがな
	 */
	@NotBlank(message = "従業員名（ふりがな）を入力してください")
	@Pattern(regexp = "^[ぁ-ん|ー]*$", message = "ひらがなを入力してください")
	@Size(min = 0, max = 50, message = "従業員名（ふりがな）は50文字以内で入力してください")
	private String kanaName;

	/**
	 * 画像
	 */
	private MultipartFile image;

	/**
	 * 性別
	 */
	@NotBlank(message = "性別を選択してください")
	private String gender;

	/**
	 * 入社日（年）
	 */
	@Pattern(regexp = "^(?!.*----).*$", message = "入社年を選択してください")
	private String hireYear;

	/**
	 * 入社日（月）
	 */
	@Pattern(regexp = "^(?!.*----).*$", message = "入社月を選択してください")
	private String hireMonth;

	/**
	 * 入社日（日）
	 */
	@Pattern(regexp = "^(?!.*----).*$", message = "入社日を選択してください")
	private String hireDay;

	/**
	 * メールアドレス
	 */
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "Emailの形式が不正です")
	private String mailAddress;

	/**
	 * 郵便番号（上3桁）
	 */
	@Pattern(regexp = "[0-9]{3}", message = "3桁で入力してください")
	private String zipCodeFirst;

	/**
	 * 郵便番号（下4桁）
	 */
	@Pattern(regexp = "[0-9]{4}", message = "4桁で入力してください")
	private String zipCodeLast;

	/**
	 * 住所（都道府県市区町村）
	 */
	@NotBlank(message = "住所（都道府県市区町村）を入力してください")
	private String addressFirst;

	/**
	 * 住所（番地以降）
	 */
	@NotBlank(message = "住所（番地以降）を入力してください")
	private String addressLast;

	/**
	 * 電話番号(上3桁)
	 */
	@Pattern(regexp = "0[789]0", message = "070、080、090のいずれかを入力してください")
	private String telephoneFirst;

	/**
	 * 電話番号（中4桁）
	 */
	@Pattern(regexp = "[0-9]{4}", message = "4桁の数字を入力してください")
	private String telephoneMiddle;

	/**
	 * 電話番号（下4桁）
	 */
	@Pattern(regexp = "[0-9]{4}", message = "4桁の数字を入力してください")
	private String telephoneLast;

	/**
	 * 給料
	 */
	@NotNull(message = "給料を入力してください")
	@Range(min = 0, message = "0以上の値を入力してください")
	private Integer salary;

	/**
	 * 特性
	 */
	@NotBlank(message = "特性を入力してください")
	private String characteristics;

	/**
	 * 扶養人数
	 */
	@NotNull(message = "扶養人数を入力してください")
	@Range(min = 0, message = "0以上の値を入力してください")
	private Integer dependentsCount;

	// getter setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKanaName() {
		return kanaName;
	}

	public void setKanaName(String kanaName) {
		this.kanaName = kanaName;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHireYear() {
		return hireYear;
	}

	public void setHireYear(String hireYear) {
		this.hireYear = hireYear;
	}

	public String getHireMonth() {
		return hireMonth;
	}

	public void setHireMonth(String hireMonth) {
		this.hireMonth = hireMonth;
	}

	public String getHireDay() {
		return hireDay;
	}

	public void setHireDay(String hireDay) {
		this.hireDay = hireDay;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getZipCodeFirst() {
		return zipCodeFirst;
	}

	public void setZipCodeFirst(String zipCodeFirst) {
		this.zipCodeFirst = zipCodeFirst;
	}

	public String getZipCodeLast() {
		return zipCodeLast;
	}

	public void setZipCodeLast(String zipCodeLast) {
		this.zipCodeLast = zipCodeLast;
	}

	public String getAddressFirst() {
		return addressFirst;
	}

	public void setAddressFirst(String addressFirst) {
		this.addressFirst = addressFirst;
	}

	public String getAddressLast() {
		return addressLast;
	}

	public void setAddressLast(String addressLast) {
		this.addressLast = addressLast;
	}

	public String getTelephoneFirst() {
		return telephoneFirst;
	}

	public void setTelephoneFirst(String telephoneFirst) {
		this.telephoneFirst = telephoneFirst;
	}

	public String getTelephoneMiddle() {
		return telephoneMiddle;
	}

	public void setTelephoneMiddle(String telephoneMiddle) {
		this.telephoneMiddle = telephoneMiddle;
	}

	public String getTelephoneLast() {
		return telephoneLast;
	}

	public void setTelephoneLast(String telephoneLast) {
		this.telephoneLast = telephoneLast;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}

	public Integer getDependentsCount() {
		return dependentsCount;
	}

	public void setDependentsCount(Integer dependentsCount) {
		this.dependentsCount = dependentsCount;
	}

}
