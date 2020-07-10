package jp.co.sample.emp_management.repository;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.sample.emp_management.domain.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Before
	public void testInsert() {
		System.out.println("DB初期化処理開始");

		Employee employee1 = new Employee();

		employee1.setName("山田太郎");
		employee1.setKanaName("やまだたろう");
		employee1.setImage("profile1.jpg");
		employee1.setGender("男性");
		LocalDate hireDateL1 = LocalDate.of(2000, 1, 1);
		employee1.setHireDate(Date.valueOf(hireDateL1));
		employee1.setMailAddress("yamada@sample.com");
		employee1.setZipCode("111-1111");
		employee1.setAddress("東京都新宿区1-1-1");
		employee1.setTelephone("090-1111-1111");
		employee1.setSalary(100000);
		employee1.setCharacteristics("明るい性格です。");
		employee1.setDependentsCount(1);

		employeeRepository.insert(employee1);

		System.out.println("employee1のインサートが完了しました。");

		Employee employee2 = new Employee();

		employee2.setName("田中花子");
		employee2.setKanaName("たなかはなこ");
		employee2.setImage("profile2.jpg");
		employee2.setGender("女性");
		LocalDate hireDateL2 = LocalDate.of(2005, 3, 30);
		employee2.setHireDate(Date.valueOf(hireDateL2));
		employee2.setMailAddress("tanaka@sample.com");
		employee2.setZipCode("222-2222");
		employee2.setAddress("東京都世田谷区2-2-2");
		employee2.setTelephone("080-2222-2222");
		employee2.setSalary(200000);
		employee2.setCharacteristics("まじめな性格です。");
		employee2.setDependentsCount(0);

		employeeRepository.insert(employee2);

		System.out.println("employee2のインサートが完了しました。");

		Employee employee3 = new Employee();

		employee3.setName("渡辺三郎");
		employee3.setKanaName("わたなべさぶろう");
		employee3.setImage("profile3.jpg");
		employee3.setGender("男性");
		LocalDate hireDateL3 = LocalDate.of(2020, 4, 15);
		employee3.setHireDate(Date.valueOf(hireDateL3));
		employee3.setMailAddress("watanabe@sample.com");
		employee3.setZipCode("333-3333");
		employee3.setAddress("東京都墨田区3-3-3");
		employee3.setTelephone("070-3333-3333");
		employee3.setSalary(300000);
		employee3.setCharacteristics("おだやかな性格です。");
		employee3.setDependentsCount(2);

		employeeRepository.insert(employee3);

		System.out.println("employee3のインサートが完了しました。");

		System.out.println("DB初期化処理終了");
	}

	@Test
	public void loadTest1() {
		System.out.println("主キー検索するテスト1開始");

		Employee employee = employeeRepository.load(1);

		assertEquals(1, employee.getId(), "IDが期待される結果と異なります。");
		assertEquals("山田太郎", employee.getName(), "名前が期待される結果と異なります。");
		assertEquals("やまだたろう", employee.getKanaName(), "名前（ふりがな）が期待される結果と異なります。");
		assertEquals("profile1.jpg", employee.getImage(), "画像が期待される結果と異なります。");
		assertEquals("男性", employee.getGender(), "性別が期待される結果と異なります。");
		LocalDate hireDateL = LocalDate.of(2000, 1, 1);
		assertEquals(Date.valueOf(hireDateL), employee.getHireDate(), "入社日が期待される結果と異なります。");
		assertEquals("yamada@sample.com", employee.getMailAddress(), "メールアドレスが期待される結果と異なります。");
		assertEquals("111-1111", employee.getZipCode(), "郵便番号が期待される結果と異なります。");
		assertEquals("東京都新宿区1-1-1", employee.getAddress(), "住所が期待される結果と異なります。");
		assertEquals("090-1111-1111", employee.getTelephone(), "電話番号が期待される結果と異なります。");
		assertEquals(100000, employee.getSalary(), "給料が期待される結果と異なります。");
		assertEquals("明るい性格です。", employee.getCharacteristics(), "特性が期待される結果と異なります。");
		assertEquals(1, employee.getDependentsCount(), "扶養人数が期待される結果と異なります。");

		System.out.println("主キー検索するテスト1終了");
	}

	@Test
	public void loadTest2() {
		System.out.println("主キー検索するテスト2開始");

		Employee employee = employeeRepository.load(2);

		assertEquals(2, employee.getId(), "IDが期待される結果と異なります。");
		assertEquals("田中花子", employee.getName(), "名前が期待される結果と異なります。");
		assertEquals("たなかはなこ", employee.getKanaName(), "名前（ふりがな）が期待される結果と異なります。");
		assertEquals("profile2.jpg", employee.getImage(), "画像が期待される結果と異なります。");
		assertEquals("女性", employee.getGender(), "性別が期待される結果と異なります。");
		LocalDate hireDateL = LocalDate.of(2005, 3, 30);
		assertEquals(Date.valueOf(hireDateL), employee.getHireDate(), "入社日が期待される結果と異なります。");
		assertEquals("tanaka@sample.com", employee.getMailAddress(), "メールアドレスが期待される結果と異なります。");
		assertEquals("222-2222", employee.getZipCode(), "郵便番号が期待される結果と異なります。");
		assertEquals("東京都世田谷区2-2-2", employee.getAddress(), "住所が期待される結果と異なります。");
		assertEquals("080-2222-2222", employee.getTelephone(), "電話番号が期待される結果と異なります。");
		assertEquals(200000, employee.getSalary(), "給料が期待される結果と異なります。");
		assertEquals("まじめな性格です。", employee.getCharacteristics(), "特性が期待される結果と異なります。");
		assertEquals(0, employee.getDependentsCount(), "扶養人数が期待される結果と異なります。");

		System.out.println("主キー検索するテスト2終了");
	}

	@Test
	public void loadTest3() {
		System.out.println("主キー検索するテスト3開始");

		Employee employee = employeeRepository.load(3);

		assertEquals(3, employee.getId(), "IDが期待される結果と異なります。");
		assertEquals("渡辺三郎", employee.getName(), "名前が期待される結果と異なります。");
		assertEquals("わたなべさぶろう", employee.getKanaName(), "名前（ふりがな）が期待される結果と異なります。");
		assertEquals("profile3.jpg", employee.getImage(), "画像が期待される結果と異なります。");
		assertEquals("男性", employee.getGender(), "性別が期待される結果と異なります。");
		LocalDate hireDateL = LocalDate.of(2020, 4, 15);
		assertEquals(Date.valueOf(hireDateL), employee.getHireDate(), "入社日が期待される結果と異なります。");
		assertEquals("watanabe@sample.com", employee.getMailAddress(), "メールアドレスが期待される結果と異なります。");
		assertEquals("333-3333", employee.getZipCode(), "郵便番号が期待される結果と異なります。");
		assertEquals("東京都墨田区3-3-3", employee.getAddress(), "住所が期待される結果と異なります。");
		assertEquals("070-3333-3333", employee.getTelephone(), "電話番号が期待される結果と異なります。");
		assertEquals(300000, employee.getSalary(), "給料が期待される結果と異なります。");
		assertEquals("おだやかな性格です。", employee.getCharacteristics(), "特性が期待される結果と異なります。");
		assertEquals(2, employee.getDependentsCount(), "扶養人数が期待される結果と異なります。");

		System.out.println("主キー検索するテスト3終了");
	}

	@Test
	public void updateTest() {

		System.out.println("従業員の扶養人数を更新するテスト開始");

		Employee employee = employeeRepository.load(1);
		assertEquals(1, employee.getDependentsCount(), "扶養人数が期待される結果と異なります。");

		employee.setDependentsCount(3);
		employeeRepository.update(employee);

		employee = employeeRepository.load(1);

		assertEquals(3, employee.getDependentsCount(), "扶養人数が期待される結果と異なります。");

		System.out.println("従業員の扶養人数を更新するテスト終了");
	}

	@Test
	public void findByKeyNameTest() {

		System.out.println("従業員検索するテスト開始");

		List<Employee> searchEmployeeList = employeeRepository.findByKeyName("郎");

		Employee employee1 = searchEmployeeList.get(0);

		assertEquals(1, employee1.getId(), "IDが期待される結果と異なります。");
		assertEquals("山田太郎", employee1.getName(), "名前が期待される結果と異なります。");
		assertEquals("やまだたろう", employee1.getKanaName(), "名前（ふりがな）が期待される結果と異なります。");
		assertEquals("profile1.jpg", employee1.getImage(), "画像が期待される結果と異なります。");
		assertEquals("男性", employee1.getGender(), "性別が期待される結果と異なります。");
		LocalDate hireDateL1 = LocalDate.of(2000, 1, 1);
		assertEquals(Date.valueOf(hireDateL1), employee1.getHireDate(), "入社日が期待される結果と異なります。");
		assertEquals("yamada@sample.com", employee1.getMailAddress(), "メールアドレスが期待される結果と異なります。");
		assertEquals("111-1111", employee1.getZipCode(), "郵便番号が期待される結果と異なります。");
		assertEquals("東京都新宿区1-1-1", employee1.getAddress(), "住所が期待される結果と異なります。");
		assertEquals("090-1111-1111", employee1.getTelephone(), "電話番号が期待される結果と異なります。");
		assertEquals(100000, employee1.getSalary(), "給料が期待される結果と異なります。");
		assertEquals("明るい性格です。", employee1.getCharacteristics(), "特性が期待される結果と異なります。");
		assertEquals(1, employee1.getDependentsCount(), "扶養人数が期待される結果と異なります。");

		Employee employee2 = searchEmployeeList.get(1);

		assertEquals(3, employee2.getId(), "IDが期待される結果と異なります。");
		assertEquals("渡辺三郎", employee2.getName(), "名前が期待される結果と異なります。");
		assertEquals("わたなべさぶろう", employee2.getKanaName(), "名前（ふりがな）が期待される結果と異なります。");
		assertEquals("profile3.jpg", employee2.getImage(), "画像が期待される結果と異なります。");
		assertEquals("男性", employee2.getGender(), "性別が期待される結果と異なります。");
		LocalDate hireDateL2 = LocalDate.of(2020, 4, 15);
		assertEquals(Date.valueOf(hireDateL2), employee2.getHireDate(), "入社日が期待される結果と異なります。");
		assertEquals("watanabe@sample.com", employee2.getMailAddress(), "メールアドレスが期待される結果と異なります。");
		assertEquals("333-3333", employee2.getZipCode(), "郵便番号が期待される結果と異なります。");
		assertEquals("東京都墨田区3-3-3", employee2.getAddress(), "住所が期待される結果と異なります。");
		assertEquals("070-3333-3333", employee2.getTelephone(), "電話番号が期待される結果と異なります。");
		assertEquals(300000, employee2.getSalary(), "給料が期待される結果と異なります。");
		assertEquals("おだやかな性格です。", employee2.getCharacteristics(), "特性が期待される結果と異なります。");
		assertEquals(2, employee2.getDependentsCount(), "扶養人数が期待される結果と異なります。");

		System.out.println("従業員検索するテスト終了");
	}

	@Test
	public void findAllTest() {

		System.out.println("従業員情報をふりがな順で全件取得するテスト開始");

		List<Employee> employeeList = employeeRepository.findAll();

		Employee employee1 = employeeList.get(0);

		assertEquals(2, employee1.getId(), "IDが期待される結果と異なります。");
		assertEquals("田中花子", employee1.getName(), "名前が期待される結果と異なります。");
		assertEquals("たなかはなこ", employee1.getKanaName(), "名前（ふりがな）が期待される結果と異なります。");
		assertEquals("profile2.jpg", employee1.getImage(), "画像が期待される結果と異なります。");
		assertEquals("女性", employee1.getGender(), "性別が期待される結果と異なります。");
		LocalDate hireDateL1 = LocalDate.of(2005, 3, 30);
		assertEquals(Date.valueOf(hireDateL1), employee1.getHireDate(), "入社日が期待される結果と異なります。");
		assertEquals("tanaka@sample.com", employee1.getMailAddress(), "メールアドレスが期待される結果と異なります。");
		assertEquals("222-2222", employee1.getZipCode(), "郵便番号が期待される結果と異なります。");
		assertEquals("東京都世田谷区2-2-2", employee1.getAddress(), "住所が期待される結果と異なります。");
		assertEquals("080-2222-2222", employee1.getTelephone(), "電話番号が期待される結果と異なります。");
		assertEquals(200000, employee1.getSalary(), "給料が期待される結果と異なります。");
		assertEquals("まじめな性格です。", employee1.getCharacteristics(), "特性が期待される結果と異なります。");
		assertEquals(0, employee1.getDependentsCount(), "扶養人数が期待される結果と異なります。");

		Employee employee2 = employeeList.get(1);

		assertEquals(1, employee2.getId(), "IDが期待される結果と異なります。");
		assertEquals("山田太郎", employee2.getName(), "名前が期待される結果と異なります。");
		assertEquals("やまだたろう", employee2.getKanaName(), "名前（ふりがな）が期待される結果と異なります。");
		assertEquals("profile1.jpg", employee2.getImage(), "画像が期待される結果と異なります。");
		assertEquals("男性", employee2.getGender(), "性別が期待される結果と異なります。");
		LocalDate hireDateL2 = LocalDate.of(2000, 1, 1);
		assertEquals(Date.valueOf(hireDateL2), employee2.getHireDate(), "入社日が期待される結果と異なります。");
		assertEquals("yamada@sample.com", employee2.getMailAddress(), "メールアドレスが期待される結果と異なります。");
		assertEquals("111-1111", employee2.getZipCode(), "郵便番号が期待される結果と異なります。");
		assertEquals("東京都新宿区1-1-1", employee2.getAddress(), "住所が期待される結果と異なります。");
		assertEquals("090-1111-1111", employee2.getTelephone(), "電話番号が期待される結果と異なります。");
		assertEquals(100000, employee2.getSalary(), "給料が期待される結果と異なります。");
		assertEquals("明るい性格です。", employee2.getCharacteristics(), "特性が期待される結果と異なります。");
		assertEquals(1, employee2.getDependentsCount(), "扶養人数が期待される結果と異なります。");

		Employee employee3 = employeeList.get(2);

		assertEquals(3, employee3.getId(), "IDが期待される結果と異なります。");
		assertEquals("渡辺三郎", employee3.getName(), "名前が期待される結果と異なります。");
		assertEquals("わたなべさぶろう", employee3.getKanaName(), "名前（ふりがな）が期待される結果と異なります。");
		assertEquals("profile3.jpg", employee3.getImage(), "画像が期待される結果と異なります。");
		assertEquals("男性", employee3.getGender(), "性別が期待される結果と異なります。");
		LocalDate hireDateL3 = LocalDate.of(2020, 4, 15);
		assertEquals(Date.valueOf(hireDateL3), employee3.getHireDate(), "入社日が期待される結果と異なります。");
		assertEquals("watanabe@sample.com", employee3.getMailAddress(), "メールアドレスが期待される結果と異なります。");
		assertEquals("333-3333", employee3.getZipCode(), "郵便番号が期待される結果と異なります。");
		assertEquals("東京都墨田区3-3-3", employee3.getAddress(), "住所が期待される結果と異なります。");
		assertEquals("070-3333-3333", employee3.getTelephone(), "電話番号が期待される結果と異なります。");
		assertEquals(300000, employee3.getSalary(), "給料が期待される結果と異なります。");
		assertEquals("おだやかな性格です。", employee3.getCharacteristics(), "特性が期待される結果と異なります。");
		assertEquals(2, employee3.getDependentsCount(), "扶養人数が期待される結果と異なります。");

		System.out.println("従業員情報をふりがな順で全件取得するテスト終了");
	}

	@Test
	public void countTest() {

		System.out.println("従業員数を取得するテスト開始");

		Integer count = employeeRepository.count();

		assertEquals(3, count, "従業員数が結果と異なります。");

		System.out.println("従業員数を取得するテスト終了");
	}

	@Test
	public void findByMailAddressTest1() {

		System.out.println("メールアドレスから従業員が存在するかを判定するテスト開始:メールアドレスが存在する場合");

		assertTrue(employeeRepository.findByMailAddress("watanabe@sample.com"), "メールアドレスが存在していません。");

		System.out.println("メールアドレスから従業員が存在するかを判定するテスト終了:メールアドレスが存在する場合");
	}

	@Test
	public void findByMailAddressTest2() {

		System.out.println("メールアドレスから従業員が存在するかを判定するテスト開始:メールアドレスが存在しない場合");

		assertFalse(employeeRepository.findByMailAddress("aioeo@sample.com"), "メールアドレスが存在しています。");

		System.out.println("メールアドレスから従業員が存在するかを判定するテスト終了:メールアドレスが存在しない場合");
	}

	@After
	public void afterEach() {
		String sql1 = "TRUNCATE TABLE employees RESTART IDENTITY;";
		SqlParameterSource param = new MapSqlParameterSource();

		template.update(sql1, param);

		System.out.println("DBにいれたデータを削除しました");

	}

}
