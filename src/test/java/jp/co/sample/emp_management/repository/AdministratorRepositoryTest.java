package jp.co.sample.emp_management.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.sample.emp_management.domain.Administrator;

@RunWith(SpringRunner.class)
@SpringBootTest
class AdministratorRepositoryTest {

	@Autowired
	private AdministratorRepository administratorRepository;

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	// 付与されたメソッドは各テスト/インナーテストクラスが実行される前に実行される
	@BeforeEach
	public void testInsert() {
		System.out.println("DB初期化処理開始");
		Administrator administrator = new Administrator();
		administrator.setName("伊賀将之");
		administrator.setMailAddress("igaiga@sample.com");
		administrator.setPassword(passwordEncoder.encode("testtest"));
		administratorRepository.insert(administrator);
		System.out.println("インサートが完了しました。");

		System.out.println("DB初期化処理終了");
	}

	@Test
	public void testLoad() {
		System.out.println("主キー検索するテスト開始");

		Integer maxId = template.queryForObject("select max(id) from administrators;", new MapSqlParameterSource(),
				Integer.class);
		Administrator resultAdministrator = administratorRepository.load(maxId);

		assertEquals("伊賀将之", resultAdministrator.getName(), "名前が登録されていません");
		assertEquals("igaiga@sample.com", resultAdministrator.getMailAddress(), "メールアドレスが登録されていません");
		assertTrue(passwordEncoder.matches("testtest", resultAdministrator.getPassword()), "パスワードが登録されていません");
		

		System.out.println("主キー検索するテスト終了");
	}

	@Test
	public void testFindByMailAddress() {
		System.out.println("メールアドレスから従業員情報を取得するテスト開始");
		
		Administrator resultAdministrator = administratorRepository.findByMailAddress("igaiga@sample.com");
		
		assertEquals("伊賀将之", resultAdministrator.getName(), "名前が登録されていません");
		assertEquals("igaiga@sample.com", resultAdministrator.getMailAddress(), "メールアドレスが登録されていません");
		assertTrue(passwordEncoder.matches("testtest", resultAdministrator.getPassword()), "パスワードが登録されていません");
		
		System.out.println("メールアドレスから従業員情報を取得するテスト終了");
	}

	// 付与されたメソッドは各テスト/インナーテストクラスが実行された後に実行される
	@AfterEach
	public void tearDownAfterClass() throws Exception {
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", "igaiga@sample.com");
		template.update("delete from administrators where mail_address = :mailAddress", param);
		System.out.println("入れたデータを削除しました。");
	}

}
