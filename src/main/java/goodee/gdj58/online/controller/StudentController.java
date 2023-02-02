package goodee.gdj58.online.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.EmployeeService;
import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.service.StudentService;
import goodee.gdj58.online.vo.Employee;
import goodee.gdj58.online.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller 
public class StudentController {
	@Autowired StudentService studentService;
	@Autowired EmployeeService employeeService;
	@Autowired IdService idService;
	
	// 학생 삭제
	@GetMapping("/employee/student/removeStudent")
	public String removeStudent(@RequestParam("studentNo") int studentNo) {
		
		studentService.removeStudent(studentNo);
		return "redirect:/employee/student/studentList"; // 리스트로 리다이렉트
	}
		
	// 학생 입력
	@GetMapping("/employee/student/addStudent")
	public String addstudent() {
		
		return "student/addStudent"; // forword
	}
	@PostMapping("/employee/student/addStudent")
	public String addStudent(Model model, Student student) {
		
		String idCheck = idService.getIdCheck(student.getStudentId());
		if(idCheck != null) {
			model.addAttribute("errorMsg", "중복된ID");
			return "student/addStudent";
		}
		
		int row = studentService.addStudent(student);
		if(row == 0) {
			model.addAttribute("errorMsg", "시스템에러로 등록실패");
			return "student/addStudent";
		}
		
		return "redirect:/employee/student/studentList"; // sendRedirect , CM -> C
	}
		
	// 학생 리스트
	@GetMapping("/employee/student/studentList")
	public String studentList(Model model
							, @RequestParam(value="currentPage", defaultValue = "1") int currentPage
							, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
							, @RequestParam(value="searchWord", defaultValue="") String searchWord) { 
							// int currentPage = reuqest.getParamenter("currentPage");
		log.debug("\u001B[31m"+currentPage+" <-- currentPage");
		log.debug("\u001B[31m"+rowPerPage+" <-- rowPerPage");
		log.debug("\u001B[31m"+searchWord+" <-- searchWord");
		List<Student> list = studentService.getStudentList(currentPage, rowPerPage, searchWord);
		int count = studentService.getStudentCount(searchWord);
		int page = 10; // 페이징 목록 개수
		int beginPage = ((currentPage - 1)/page) * page + 1; // 시작 페이지
		int endPage = beginPage + page - 1; // 페이징 목록 끝
		int lastPage = (int)Math.ceil((double)count / (double)rowPerPage); // 마지막 페이지
		if(endPage > lastPage) {
			endPage = lastPage;
		}
		
		
		model.addAttribute("list", list); // request.setAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("beginPage", beginPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("lastPage", lastPage);
		return "student/studentList";
	}
}
