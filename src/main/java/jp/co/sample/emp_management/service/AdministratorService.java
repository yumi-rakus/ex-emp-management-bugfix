package jp.co.sample.emp_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.emp_management.domain.Administrator;
import jp.co.sample.emp_management.repository.AdministratorRepository;

/**
 * 管理者情報を操作するサービス.
 * 
 * @author yumi takahashi
 *
 */
@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository administratorRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * 管理者情報を登録します.
	 * 
	 * @param administrator 管理者情報
	 */
	public void insert(Administrator administrator, String rawPassword) {

		String encodedPassword = passwordEncoder.encode(rawPassword);

		administrator.setPassword(encodedPassword);

		administratorRepository.insert(administrator);
	}

	/**
	 * メールアドレスが既に登録されているかを確認します.
	 * 
	 * @param mailAddress メールアドレス
	 * @return 管理者情報 存在しない場合はnullが返ります
	 */
	public Administrator findByMailAddress(String mailAddress) {
		return administratorRepository.findByMailAddress(mailAddress);
	}
}
