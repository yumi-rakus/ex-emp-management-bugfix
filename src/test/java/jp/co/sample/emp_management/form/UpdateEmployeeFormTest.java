package jp.co.sample.emp_management.form;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class UpdateEmployeeFormTest {

	private Validator validator;

	@BeforeEach
	public void 事前処理() throws Exception {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Test
	public void 成功Test1() {

		UpdateEmployeeForm form = new UpdateEmployeeForm();

		form.setDependentsCount("0");

		Set<ConstraintViolation<UpdateEmployeeForm>> violations = validator.validate(form);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 成功Test2() {

		UpdateEmployeeForm form = new UpdateEmployeeForm();

		form.setDependentsCount("1");

		Set<ConstraintViolation<UpdateEmployeeForm>> violations = validator.validate(form);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 成功Test3() {

		UpdateEmployeeForm form = new UpdateEmployeeForm();

		form.setDependentsCount("5");

		Set<ConstraintViolation<UpdateEmployeeForm>> violations = validator.validate(form);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 失敗Test1() {

		UpdateEmployeeForm form = new UpdateEmployeeForm();

		form.setDependentsCount("");

		Set<ConstraintViolation<UpdateEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UpdateEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 失敗Test2() {

		UpdateEmployeeForm form = new UpdateEmployeeForm();

		form.setDependentsCount("a");

		Set<ConstraintViolation<UpdateEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UpdateEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 失敗Test3() {

		UpdateEmployeeForm form = new UpdateEmployeeForm();

		form.setDependentsCount("あ");

		Set<ConstraintViolation<UpdateEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UpdateEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 失敗Test4() {

		UpdateEmployeeForm form = new UpdateEmployeeForm();

		form.setDependentsCount(" ");

		Set<ConstraintViolation<UpdateEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UpdateEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}
	
	@Test
	public void 失敗Test5() {

		UpdateEmployeeForm form = new UpdateEmployeeForm();

		form.setDependentsCount("-1");

		Set<ConstraintViolation<UpdateEmployeeForm>> violations = validator.validate(form);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UpdateEmployeeForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}
}
