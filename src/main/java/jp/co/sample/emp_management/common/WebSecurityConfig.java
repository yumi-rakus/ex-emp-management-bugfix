package jp.co.sample.emp_management.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jp.co.sample.emp_management.service.JpaUserDetailsServiceImpl;

/**
 * Spring Securityの設定クラス.
 * 
 * @author yumi takahashi
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring().antMatchers("/favicon.ico", "/css/**", "/js/**", "/img/**", "/fonts/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests() // 認証が必要となるURLを設定
				.antMatchers("/toInsert").permitAll() // 管理者登録画面は認証不要
				.antMatchers("/insert").permitAll() // 管理者登録は認証不要
				.antMatchers("/login").permitAll().antMatchers("/").permitAll() // ログインページは認証不要
				.anyRequest().authenticated() // それ以外はすべて認証された状態でないと閲覧不可
				.and().formLogin() // ログインページに飛ばす
				.loginProcessingUrl("/login") // ログイン処理をするURL
				.loginPage("/"); // ログインページのURL

		http.formLogin().loginProcessingUrl("/login") // 認証処理を起動させるパス
				.loginPage("/") // ログインフォームのパス
				.failureUrl("/login") // ログイン処理失敗時の遷移先
				.defaultSuccessUrl("/employee/showList", true) // 認証成功時の遷移先
				.usernameParameter("mailAddress").passwordParameter("password"); // ユーザ名（今回はメールアドレス）とパスワードのパラメータ

		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // ログイン処理を起動させるパス
				.logoutSuccessUrl("/"); // ログアウト完了時のパス
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Configuration
	protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

		@Autowired
		JpaUserDetailsServiceImpl userDetailsService;

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		}

	}
}
