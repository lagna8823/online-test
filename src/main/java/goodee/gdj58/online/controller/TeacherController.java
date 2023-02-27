package goodee.gdj58.online.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.service.TeacherService;
import goodee.gdj58.online.vo.Employee;
import goodee.gdj58.online.vo.Teacher;
import goodee.gdj58.online.vo.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller 
public class TeacherController {
	@Autowired TeacherService teacherService;
	@Autowired IdService idService;
	
	// ==================== 시험( Test)====================
	
	// Test 수정
	@GetMapping("/teacher/modifyTest")
	public String modifyTest() {
		
		return "teacher/addTest"; // forword
	}
	@PostMapping("/teacher/modifyTest")
	public String modifyTest(Model model, Test test) {
		
		int row = teacherService.modifyTest(test);
		if(row == 0) {
			model.addAttribute("errorMsg", "시스템에러로 등록실패");
			return "teacher/modifyTest";
		}
		
		return "redirect:/testList"; // sendRedirect , CM -> C
	}
	
	// Test 삭제
	@GetMapping("teacher/removeTest")
	public String removeTest(@RequestParam("testNo") int testNo) {
		
		teacherService.removeTest(testNo);
		return "redirect:/testList"; // 리스트로 리다이렉트
	}
	
	// Test 입력
	@GetMapping("/teacher/addTest")
	public String addTest() {
		
		return "teacher/addTest"; // forword
	}
	@PostMapping("/teacher/addTest")
	public String addTest(Model model, Test test) {
		
		int row = teacherService.addTest(test);
		if(row == 0) {
			model.addAttribute("errorMsg", "시스템에러로 등록실패");
			return "teacher/addTest";
		}
		
		return "redirect:/testList"; // sendRedirect , CM -> C
	}

	// Test 리스트
	@GetMapping("/testList")
	public String testList(Model model
							, @RequestParam(value="currentPage", defaultValue = "1") int currentPage
							, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
							, @RequestParam(value="searchWord", defaultValue="") String searchWord) { 
		
		log.debug("\u001B[31m"+currentPage+" <-- currentPage");
		log.debug("\u001B[31m"+rowPerPage+" <-- rowPerPage");
		log.debug("\u001B[31m"+searchWord+" <-- searchWord");
		List<Test> list = teacherService.getTestList(currentPage, rowPerPage, searchWord);
		int count = teacherService.getTestCount(searchWord);
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
		return "testList";
	}
	
	// ==================== 강사 UI ====================
	// 강사 pw 수정
	@GetMapping("/teacher/modifyTeacherPw")
	public String modifyTeacherPw() {
		return "teacher/modifyTeacherPw";
	}
	@PostMapping("/teacher/modifyTeacherPw")
	public String modifyTeacherPw(HttpSession session
							, @RequestParam(value="oldPw") String oldPw
							, @RequestParam(value="newPw") String newPw) {
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		System.out.println(loginTeacher.getTeacherNo()+oldPw+newPw);
		teacherService.updateTeacherPw(loginTeacher.getTeacherNo(), oldPw, newPw);
		return "redirect:/Home";
	}
	
	// 강사 로그인
	@GetMapping("/loginTeacher")
	public String loginTeacher(Model model
								, @RequestParam(value="teacherMsg", defaultValue="") String teacherMsg
								, @RequestParam(value="failLoginTea",defaultValue="") String failLoginTea) {
		if(!teacherMsg.equals("")) { // 필터에서 걸러져 teacherMsg를 받은 경우
			model.addAttribute("teacherMsg", teacherMsg);
		}
		if(!failLoginTea.equals("")) {
			log.debug("\u001B[31m 로그인실패 : "+failLoginTea);
			model.addAttribute("failLoginTea",failLoginTea);
		}
		
		return "teacher/loginTeacher";
	}
	@PostMapping("/loginTeacher")
	public String loginTeacher(HttpSession session, Teacher teacher) {
		Teacher resultTeacher = teacherService.login(teacher);
		if(resultTeacher == null) {
			String failLoginTea = "failLoginTea";
			return "redirect:teacher/loginTeacher?failLoginTea="+failLoginTea;
		}
		log.debug("\u001B[33m"+"Teacher 로그인시도");
		session.setAttribute("loginTeacher", resultTeacher);
		return "redirect:/Home";
	}
		
	// 강사 삭제
	@GetMapping("/employee/teacher/removeTeacher")
	public String removeTeacher(@RequestParam("teacherNo") int teacherNo) {
		
		teacherService.removeTeacher(teacherNo);
		return "redirect:/employee/teacher/teacherList"; // 리스트로 리다이렉트
	}
	
	// 강사 입력
	@GetMapping("/employee/teacher/addTeacher")
	public String addTeacher() {
		
		return "employee/addTeacher"; // forword
	}
	@PostMapping("/employee/teacher/addTeacher")
	public String addTeacher(Model model, Teacher teacher) {
		
		String idCheck = idService.getIdCheck(teacher.getTeacherId());
		if(idCheck != null) {
			model.addAttribute("errorMsg", "중복된ID");
			return "employee/addTeacher";
		}
		
		int row = teacherService.addTeacher(teacher);
		if(row == 0) {
			model.addAttribute("errorMsg", "시스템에러로 등록실패");
			return "employee/addTeacher";
		}
		
		return "redirect:/employee/teacher/teacherList"; // sendRedirect , CM -> C
	}
		
	// 강사 리스트
	@GetMapping("/employee/teacher/teacherList")
	public String teacherList(Model model
							, @RequestParam(value="currentPage", defaultValue = "1") int currentPage
							, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
							, @RequestParam(value="searchWord", defaultValue="") String searchWord) { 
		
							// int currentPage = reuqest.getParamenter("currentPage");
		log.debug("\u001B[31m"+currentPage+" <-- currentPage");
		log.debug("\u001B[31m"+rowPerPage+" <-- rowPerPage");
		log.debug("\u001B[31m"+searchWord+" <-- searchWord");
		List<Teacher> list = teacherService.getTeacherList(currentPage, rowPerPage, searchWord);
		int count = teacherService.getTeacherCount(searchWord);
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
		return "teacher/teacherList";
	}
}
