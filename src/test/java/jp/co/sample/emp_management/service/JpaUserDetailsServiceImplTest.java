package jp.co.sample.emp_management.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.sample.emp_management.domain.Administrator;

@RunWith(SpringRunner.class)
@SpringBootTest
class JpaUserDetailsServiceImplTest {

	@Autowired
	private JpaUserDetailsServiceImpl jpaUserDetailsServiceImpl;

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@BeforeEach
	public void testInsert() {
		System.out.println("DB初期化処理開始");
		Administrator administrator = new Administrator();
		administrator.setName("伊賀将之");
		administrator.setMailAddress("igaiga@sample.com");
		administrator.setPassword("testtest");
		administratorService.insert(administrator, administrator.getPassword());
		System.out.println("インサートが完了しました。");

		System.out.println("DB初期化処理終了");
	}

	@Test
	public void loadUserByUsernameTest() {
		System.out.println("メールアドレスから管理者情報を取得するテスト開始");

		UserDetails resultAdministrator = jpaUserDetailsServiceImpl.loadUserByUsername("igaiga@sample.com");

		assertEquals("igaiga@sample.com", resultAdministrator.getUsername(), "メールアドレスが登録されていません");
		assertTrue(passwordEncoder.matches("testtest", resultAdministrator.getPassword()), "パスワードが登録されていません");

		System.out.println("メールアドレスから管理者情報を取得するテスト終了");
	}

	@AfterEach
	public void tearDownAfterClass() throws Exception {
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", "igaiga@sample.com");
		template.update("delete from administrators where mail_address = :mailAddress", param);
		System.out.println("入れたデータを削除しました。");
	}

}
