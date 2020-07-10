package jp.co.sample.emp_management.form;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class InsertEmployeeFormTest {

	private Validator validator;

	private InsertEmployeeForm form = new InsertEmployeeForm();

	@BeforeEach
	public void 事前処理() throws Exception {
		validator = Validation.buildDefaultValidatorFactory().getValidator();

		form.setName("山田太郎");
		form.setKanaName("やまだたろう");
		form.setGender("男性");
		form.setHireYear("2000");
		form.setHireMonth("10");
		form.setHireDay("15");
		form.setMailAddress("yamada@sample.com");
		form.setZipCodeFirst("111");
		form.setZipCodeLast("1111");
		form.setAddressFirst("東京都新宿区");
		form.setAddressLast("1-1-1");
		form.setTelephoneFirst("090");
		form.setTelephoneMiddle("1111");
		form.setTelephoneLast("1111");
		form.setSalary(100000);
		form.setCharacteristics("明るい人柄です。");
		form.setDependentsCount(1);
	}

	@Test
	public void 成功Test() {

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	// name
	@Test
	public void 名前successTest1() {

		form.setName("あ");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 名前successTest2() {

		form.setName("あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 名前failureTest1() {

		form.setName("あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおか");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 名前failureTest2() {

		form.setName("あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおかきくけこさしすせそ");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 名前failureTest3() {

		form.setName("");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotBlank, "エラーの種類が異なります。");
		}
	}

	// kanaName
	@Test
	public void 名前ふりがなsuccessTest1() {

		form.setKanaName("あ");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 名前ふりがなsuccessTest2() {

		form.setKanaName("あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 名前ふりがなfailureTest1() {

		form.setKanaName("あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおか");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 名前ふりがなfailureTest2() {

		form.setKanaName("あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおかきくけこさしすせそ");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 名前ふりがなfailureTest3() {

		form.setKanaName("");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotBlank, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 名前ふりがなfailureTest4() {

		form.setKanaName("山田太郎");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	// gender
	@Test
	public void 性別failureTest() {

		form.setGender("");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotBlank, "エラーの種類が異なります。");
		}
	}

	// hireYear
	@Test
	public void 入社年failureTest() {

		form.setHireYear("----");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	// hireMonth
	@Test
	public void 入社月failureTest() {

		form.setHireMonth("----");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	// hireDay
	@Test
	public void 入社日failureTest() {

		form.setHireDay("----");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	// mailAddress
	@Test
	public void メールアドレスfailureTest1() {

		form.setMailAddress("");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotBlank, "エラーの種類が異なります。");
		}
	}

	@Test
	public void メールアドレスfailureTest2() {

		form.setMailAddress("yamada");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Email, "エラーの種類が異なります。");
		}
	}

	// zipCodeFirst
	@Test
	public void 郵便番号上3桁failureTest1() {

		form.setZipCodeFirst("");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 郵便番号上3桁failureTest2() {

		form.setZipCodeFirst("1");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 郵便番号上3桁failureTest3() {

		form.setZipCodeFirst("11");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 郵便番号上3桁failureTest4() {

		form.setZipCodeFirst("1111");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 郵便番号上3桁failureTest5() {

		form.setZipCodeFirst("111111");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	// zipCodeLast
	@Test
	public void 郵便番号下4桁failureTest1() {

		form.setZipCodeLast("");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 郵便番号下4桁failureTest2() {

		form.setZipCodeLast("1");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 郵便番号下4桁failureTest3() {

		form.setZipCodeLast("11");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 郵便番号下4桁failureTest4() {

		form.setZipCodeLast("11111");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 郵便番号下4桁failureTest5() {

		form.setZipCodeLast("1111111");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	// addressFirst
	@Test
	public void 住所都道府県市区町村failureTest() {

		form.setAddressFirst("");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotBlank, "エラーの種類が異なります。");
		}
	}

	// addressLast
	@Test
	public void 住所番地以降failureTest() {

		form.setAddressLast("");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotBlank, "エラーの種類が異なります。");
		}
	}

	// telephoneFirst
	@Test
	public void 電話番号上3桁successTest1() {
		form.setTelephoneFirst("080");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 電話番号上3桁successTest2() {
		form.setTelephoneFirst("070");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 電話番号上3桁failureTest1() {
		form.setTelephoneFirst("");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 電話番号上3桁failureTest2() {
		form.setTelephoneFirst("000");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 電話番号上3桁failureTest3() {
		form.setTelephoneFirst("0");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	// telephoneMiddle
	@Test
	public void 電話番号中4桁failureTest1() {

		form.setTelephoneMiddle("");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 電話番号中4桁failureTest2() {

		form.setTelephoneMiddle("1");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 電話番号中4桁failureTest3() {

		form.setTelephoneMiddle("11");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 電話番号中4桁failureTest4() {

		form.setTelephoneMiddle("11111");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 電話番号中4桁failureTest5() {

		form.setTelephoneMiddle("111111");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	// telephoneLast
	@Test
	public void 電話番号下4桁failureTest1() {

		form.setTelephoneLast("");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 電話番号下4桁failureTest2() {

		form.setTelephoneLast("1");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 電話番号下4桁failureTest3() {

		form.setTelephoneLast("11");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 電話番号下4桁failureTest4() {

		form.setTelephoneLast("11111");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 電話番号下4桁failureTest5() {

		form.setTelephoneLast("111111");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	// salary
	@Test
	public void 給料failureTest1() {

		form.setSalary(null);

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotNull, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 給料failureTest2() {

		form.setSalary(-1);

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Range, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 給料failureTest3() {

		form.setSalary(-1000);

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Range, "エラーの種類が異なります。");
		}
	}

	// characteristics
	@Test
	public void 特性failureTest() {
		form.setCharacteristics("");

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotBlank, "エラーの種類が異なります。");
		}
	}

	// dependentsCount
	@Test
	public void 扶養人数successTest1() {

		form.setDependentsCount(0);

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 扶養人数successTest2() {

		form.setDependentsCount(10);

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 扶養人数failureTest1() {

		form.setDependentsCount(null);

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotNull, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 扶養人数failureTest2() {

		form.setDependentsCount(-1);

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Range, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 扶養人数failureTest3() {

		form.setDependentsCount(-100);

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Range, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 扶養人数failureTest4() {

		form.setDependentsCount(11);

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Range, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 扶養人数failureTest5() {

		form.setDependentsCount(15);

		Set<ConstraintViolation<InsertEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Range, "エラーの種類が異なります。");
		}
	}

}
