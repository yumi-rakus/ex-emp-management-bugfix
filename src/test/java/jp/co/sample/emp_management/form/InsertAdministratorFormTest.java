package jp.co.sample.emp_management.form;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class InsertAdministratorFormTest {

	private Validator validator;

	@BeforeEach
	public void 事前処理() throws Exception {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Test
	public void 成功Test() {

		InsertAdministratorForm form = new InsertAdministratorForm();

		form.setName("管理太郎");
		form.setMailAddress("kanri@sample.com");
		form.setPassword("kanri");

		Set<ConstraintViolation<InsertAdministratorForm>> violations = validator.validate(form);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 名前successTest1() {

		InsertAdministratorForm form = new InsertAdministratorForm();

		form.setName("あ");
		form.setMailAddress("kanri@sample.com");
		form.setPassword("kanri");

		Set<ConstraintViolation<InsertAdministratorForm>> violations = validator.validate(form);

		assertEquals(0, violations.size(), "エラーの数が異なります。");

	}

	@Test
	public void 名前successTest2() {

		InsertAdministratorForm form = new InsertAdministratorForm();

		form.setName("あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお");
		form.setMailAddress("kanri@sample.com");
		form.setPassword("kanri");

		Set<ConstraintViolation<InsertAdministratorForm>> violations = validator.validate(form);

		assertEquals(0, violations.size(), "エラーの数が異なります。");

	}

	@Test
	public void 名前failureTest1() {

		InsertAdministratorForm form = new InsertAdministratorForm();

		form.setName("あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおか");
		form.setMailAddress("kanri@sample.com");
		form.setPassword("kanri");

		Set<ConstraintViolation<InsertAdministratorForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertAdministratorForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
		}

	}

	@Test
	public void 名前failureTest2() {

		InsertAdministratorForm form = new InsertAdministratorForm();

		form.setName("あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおかきくけこさしすせそ");
		form.setMailAddress("kanri@sample.com");
		form.setPassword("kanri");

		Set<ConstraintViolation<InsertAdministratorForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertAdministratorForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
		}

	}

	@Test
	public void 名前failureTest3() {

		InsertAdministratorForm form = new InsertAdministratorForm();

		form.setName("");
		form.setMailAddress("kanri@sample.com");
		form.setPassword("kanri");

		Set<ConstraintViolation<InsertAdministratorForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertAdministratorForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotBlank, "エラーの種類が異なります。");
		}
	}

	@Test
	public void メールアドレスfailureTest1() {
		InsertAdministratorForm form = new InsertAdministratorForm();

		form.setName("山田太郎");
		form.setMailAddress("");
		form.setPassword("kanri");

		Set<ConstraintViolation<InsertAdministratorForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertAdministratorForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotBlank, "エラーの種類が異なります。");
		}
	}

	@Test
	public void メールアドレスfailureTest2() {
		InsertAdministratorForm form = new InsertAdministratorForm();

		form.setName("山田太郎");
		form.setMailAddress("kanri");
		form.setPassword("kanri");

		Set<ConstraintViolation<InsertAdministratorForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertAdministratorForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Email, "エラーの種類が異なります。");
		}
	}

	@Test
	public void パスワードsuccessTest1() {
		InsertAdministratorForm form = new InsertAdministratorForm();

		form.setName("山田太郎");
		form.setMailAddress("kanri@sample.com");
		form.setPassword("kanr");

		Set<ConstraintViolation<InsertAdministratorForm>> violations = validator.validate(form);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void パスワードsuccessTest2() {
		InsertAdministratorForm form = new InsertAdministratorForm();

		form.setName("山田太郎");
		form.setMailAddress("kanri@sample.com");
		form.setPassword("kanri");

		Set<ConstraintViolation<InsertAdministratorForm>> violations = validator.validate(form);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void パスワードsuccessTest3() {
		InsertAdministratorForm form = new InsertAdministratorForm();

		form.setName("山田太郎");
		form.setMailAddress("kanri@sample.com");
		form.setPassword("kanrikan");

		Set<ConstraintViolation<InsertAdministratorForm>> violations = validator.validate(form);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void パスワードfailureTest1() {
		InsertAdministratorForm form = new InsertAdministratorForm();

		form.setName("山田太郎");
		form.setMailAddress("kanri@sample.com");
		form.setPassword("");

		Set<ConstraintViolation<InsertAdministratorForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertAdministratorForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
		}
	}

	@Test
	public void パスワードfailureTest2() {
		InsertAdministratorForm form = new InsertAdministratorForm();

		form.setName("山田太郎");
		form.setMailAddress("kanri@sample.com");
		form.setPassword("kan");

		Set<ConstraintViolation<InsertAdministratorForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertAdministratorForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
		}
	}

	@Test
	public void パスワードfailureTest3() {
		InsertAdministratorForm form = new InsertAdministratorForm();

		form.setName("山田太郎");
		form.setMailAddress("kanri@sample.com");
		form.setPassword("kanrikanr");

		Set<ConstraintViolation<InsertAdministratorForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertAdministratorForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
		}
	}

	@Test
	public void パスワードfailureTest4() {
		InsertAdministratorForm form = new InsertAdministratorForm();

		form.setName("山田太郎");
		form.setMailAddress("kanri@sample.com");
		form.setPassword("kanrikanrikanri");

		Set<ConstraintViolation<InsertAdministratorForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<InsertAdministratorForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
		}
	}
	
	@Test
	public void 失敗Test1() {
		InsertAdministratorForm form = new InsertAdministratorForm();

		form.setName("");
		form.setMailAddress("kanri@sample.com");
		form.setPassword("ka");

		Set<ConstraintViolation<InsertAdministratorForm>> violations = validator.validate(form);

		assertEquals(2, violations.size(), "エラーの数が異なります。");
	}
	
	@Test
	public void 失敗Test2() {
		InsertAdministratorForm form = new InsertAdministratorForm();

		form.setName("");
		form.setMailAddress("kanri");
		form.setPassword("kanri");

		Set<ConstraintViolation<InsertAdministratorForm>> violations = validator.validate(form);

		assertEquals(2, violations.size(), "エラーの数が異なります。");
	}
	
	@Test
	public void 失敗Test3() {
		InsertAdministratorForm form = new InsertAdministratorForm();

		form.setName("山田太郎");
		form.setMailAddress("kanri");
		form.setPassword("ka");

		Set<ConstraintViolation<InsertAdministratorForm>> violations = validator.validate(form);

		assertEquals(2, violations.size(), "エラーの数が異なります。");
	}
	
	@Test
	public void 失敗Test4() {
		InsertAdministratorForm form = new InsertAdministratorForm();

		form.setName("");
		form.setMailAddress("kanri");
		form.setPassword("ka");

		Set<ConstraintViolation<InsertAdministratorForm>> violations = validator.validate(form);

		assertEquals(3, violations.size(), "エラーの数が異なります。");
	}

}
