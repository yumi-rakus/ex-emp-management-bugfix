package jp.co.sample.emp_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.emp_management.domain.Employee;
import jp.co.sample.emp_management.repository.EmployeeRepository;

/**
 * 従業員情報を操作するサービス.
 * 
 * @author yumi takahashi
 *
 */
@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * 従業員情報を全件取得します.
	 * 
	 * @return 従業員情報一覧
	 */
	public List<Employee> showList() {
		List<Employee> employeeList = employeeRepository.findAll();
		return employeeList;
	}

	/**
	 * 従業員情報を取得します.
	 * 
	 * @param id ID
	 * @return 従業員情報
	 * @throws 検索されない場合は例外が発生します
	 */
	public Employee showDetail(Integer id) {
		Employee employee = employeeRepository.load(id);
		return employee;
	}

	/**
	 * 従業員情報を更新します.
	 * 
	 * @param employee 更新した従業員情報
	 */
	public void update(Employee employee) {
		employeeRepository.update(employee);
	}

	/**
	 * 従業員情報を曖昧検索します.
	 * 
	 * @param keyName 検索キー
	 * @return 検索された従業員情報一覧
	 */
	public List<Employee> search(String keyName) {
		return employeeRepository.findByKeyName(keyName);
	}

	/**
	 * 従業員情報を10件取得します.
	 * 
	 * @param offset オフセット
	 * @return 10件の従業員情報
	 */
	public List<Employee> showTenList(Integer offset) {
		return employeeRepository.findTenEmployee(offset);
	}

	/**
	 * 従業員数を取得します.
	 * 
	 * @return 従業員数
	 */
	public Integer employeeCount() {
		return employeeRepository.count();
	}

	/**
	 * 従業員情報を新規登録します.
	 * 
	 * @param employee 従業員情報
	 */
	public void register(Employee employee) {
		employeeRepository.insert(employee);
	}
}
