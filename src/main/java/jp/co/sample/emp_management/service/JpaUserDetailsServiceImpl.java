package jp.co.sample.emp_management.service;

import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import jp.co.sample.emp_management.domain.Administrator;
import jp.co.sample.emp_management.repository.AdministratorRepository;

/**
 * ログイン認証処理を行うクラス.
 * 
 * @author yumi takahashi
 *
 */
@Component
public class JpaUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AdministratorRepository administratorRepository;
	
	@Autowired
	private HttpSession session;

	@Override
	public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException {

		Administrator administrator = administratorRepository.findByMailAddress(mailAddress);
		
		if(Objects.isNull(administrator)) {
			return new Administrator();
		}
		
		session.setAttribute("administratorName", administrator.getName());
		return administrator;
	}
}
