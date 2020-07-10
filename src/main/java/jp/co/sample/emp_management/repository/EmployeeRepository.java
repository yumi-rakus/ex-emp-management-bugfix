package jp.co.sample.emp_management.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.emp_management.domain.Employee;

/**
 * employeesテーブルを操作するリポジトリー.
 * 
 * @author yumi takahashi
 * 
 */
@Repository
public class EmployeeRepository {

	/**
	 * Employeeオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setKanaName(rs.getString("kananame"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 従業員一覧情報を従業員名の五十音順で取得します.
	 * 
	 * @return 全従業員一覧 従業員が存在しない場合はサイズ0件の従業員一覧を返します
	 */
	public List<Employee> findAll() {
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count, kananame FROM employees ORDER BY kananame";

		List<Employee> developmentList = template.query(sql, EMPLOYEE_ROW_MAPPER);

		return developmentList;
	}

	/**
	 * 主キーから従業員情報を取得します.
	 * 
	 * @param id 検索したい従業員ID
	 * @return 検索された従業員情報
	 * @exception 従業員が存在しない場合は例外を発生します
	 */
	public Employee load(Integer id) {
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count, kananame FROM employees WHERE id=:id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);

		return employee;
	}

	/**
	 * 従業員情報を変更します.
	 */
	public void update(Employee employee) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

		String updateSql = "UPDATE employees SET dependents_count=:dependentsCount WHERE id=:id";
		template.update(updateSql, param);
	}

	/**
	 * 検索キーを含む従業員情報を取得します.
	 * 
	 * @param keyName 検索キー
	 * @return 検索キーを含む従業員情報一覧
	 */
	public List<Employee> findByKeyName(String keyName) {

		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count, kananame FROM employees WHERE name LIKE :keyName";

		SqlParameterSource param = new MapSqlParameterSource().addValue("keyName", "%" + keyName + "%");

		List<Employee> employeeList = template.query(sql, param, EMPLOYEE_ROW_MAPPER);

		return employeeList;
	}

	/**
	 * 従業員情報を10件ずつ取得します.
	 * 
	 * @param offset オフセット
	 * @return 10件の従業員情報
	 */
	public List<Employee> findTenEmployee(Integer offset) {

		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count, kananame FROM employees ORDER BY kananame LIMIT 10 OFFSET :offset";

		SqlParameterSource param = new MapSqlParameterSource().addValue("offset", offset);

		List<Employee> tenEmployeeList = template.query(sql, param, EMPLOYEE_ROW_MAPPER);

		return tenEmployeeList;
	}

	/**
	 * 従業員数を取得します.
	 * 
	 * @return 従業員数
	 */
	public Integer count() {

		String sql = "SELECT count(*) FROM employees";

		SqlParameterSource param = new MapSqlParameterSource();

		Integer count = template.queryForObject(sql, param, Integer.class);

		return count;
	}

	/**
	 * 従業員情報を挿入します.
	 * 
	 * @param employee 従業員情報
	 */
	public void insert(Employee employee) {

		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO employees ");
		sql.append(
				"(name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count, kananame) ");
		sql.append(" VALUES");
		sql.append(
				"(:name, :image, :gender, :hireDate, :mailAddress, :zipCode, :address, :telephone, :salary, :characteristics, :dependentsCount, :kanaName)");

		SqlParameterSource param = new MapSqlParameterSource().addValue("name", employee.getName())
				.addValue("image", employee.getImage()).addValue("gender", employee.getGender())
				.addValue("hireDate", employee.getHireDate()).addValue("mailAddress", employee.getMailAddress())
				.addValue("zipCode", employee.getZipCode()).addValue("address", employee.getAddress())
				.addValue("telephone", employee.getTelephone()).addValue("salary", employee.getSalary())
				.addValue("characteristics", employee.getCharacteristics())
				.addValue("dependentsCount", employee.getDependentsCount())
				.addValue("kanaName", employee.getKanaName());

		template.update(sql.toString(), param);
	}

	/**
	 * メールアドレスから従業員が存在するかを判定します.
	 * 
	 * @param mailAddress メールアドレス
	 * @return 従業員が存在していたらtrue、存在していなかったらfalseを返す
	 */
	public boolean findByMailAddress(String mailAddress) {

		String sql = "SELECT count(*) FROM employees WHERE mail_address = :mailAddress";

		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress);

		Integer count = template.queryForObject(sql, param, Integer.class);

		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}
}
