package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.EmployeeMapper;
import goodee.gdj58.online.vo.Employee;

@Service
@Transactional
public class EmployeeService {
	// DI = new EmployeeMapper()
	@Autowired private EmployeeMapper employeeMapper;
	
	// 검색후 카운트
	public int getEmployeeCount(String searchWord) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("searchWord", searchWord);
		return employeeMapper.selectEmployeeCount(paramMap);
	}
	
	
	public int updateEmployeePw(int empNo, String oldPw, String newPw) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("empNo", empNo);
		paramMap.put("oldPw", oldPw);
		paramMap.put("newPw", newPw);
		return employeeMapper.updateEmployeePw(paramMap);
	}
	
	public Employee login(Employee emp) {
		return employeeMapper.login(emp);
	}
	
	
	public int removeEmployee(int empNo) {
		return employeeMapper.deleteEmployee(empNo);
	}	
	
	public int addEmployee(Employee employee) {
		return employeeMapper.insertEmployee(employee);
	}
	
	public List<Employee> getEmployeeList(int currentPage, int rowPerPage, String searchWord) {
		int beginRow = (currentPage-1)*rowPerPage;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		return employeeMapper.selectEmployeeList(paramMap);
	}

	
}
