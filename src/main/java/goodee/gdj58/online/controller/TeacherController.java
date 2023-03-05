package goodee.gdj58.online.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.service.TeacherService;
import goodee.gdj58.online.vo.Employee;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
import goodee.gdj58.online.vo.Teacher;
import goodee.gdj58.online.vo.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller 
public class TeacherController {
	@Autowired TeacherService teacherService;
	@Autowired IdService idService;
	
	
	// ==================== 문제(TestOne)====================
	
	// Test 상세보기 (문제 수정)
	@GetMapping("/teacher/modifyQuestion")
	public String modifyQuestionExample(Model model
											, @RequestParam(value="questionNo") int questionNo
											, @RequestParam(value="testNo") int testNo){
		List<Map<String,Object>> list = teacherService.getOneQuestion(testNo, questionNo);	
		model.addAttribute("list",list);
		
		return "teacher/modifyQuestion";
	}
	@PostMapping("/teacher/modifyQuestion")
	public String modifyQuestionExample(Question question
										, @RequestParam(value="exampleNo") int[] exampleNo
										, @RequestParam(value="exampleTitle") String[] exampleTitle
										, @RequestParam(value="exampleOx") int exampleOx) {
		int testNo = question.getTestNo();
		// Question 수정
		int modifyQuestion = teacherService.modifyQuestion(question);
		if(modifyQuestion == 1) {
			log.debug("\u001B[31m"+"qustionModify 성공");
		}
		// Example 수정
		Example[] example = new Example[4];
		log.debug("\u001B[31m"+"exampleLength : "+example.length);
		for(int i=0; i<example.length; i++) {
			example[i] = new Example();
			example[i].setExampleNo(exampleNo[i]);
			// log.debug("\u001B[31m"+"test : "+i);
			log.debug("\u001B[31m"+"exampleNo : "+example[i].getExampleNo());
			example[i].setExampleTitle(exampleTitle[i]);
			log.debug("\u001B[31m"+"exampleTitle : "+example[i].getExampleTitle());
			example[i].setExampleOx("오답");
			if(exampleOx == (i+1)) {
				log.debug("\u001B[31m"+i+"번 선택지가 정답으로 수정되었습니다.");
				example[i].setExampleOx("정답");
			} 
			int modifyExample = teacherService.modifyExample(example[i]);
			if(modifyExample == 1) {
				log.debug("\u001B[31m"+"test : "+example[i]);
				log.debug("\u001B[31m"+i+"번 선택지 수정 성공");
			}
		}		
		return "redirect:/teacher/testOne?testNo="+testNo;
	}
	
	// Test 상세보기(문제 삭제)
	@GetMapping("/teacher/removeQuestion")
	public String removeQuestionExample(@RequestParam(value="questionNo") int questionNo
											,@RequestParam(value="testNo") int testNo){
		teacherService.deleteQuestionExample(questionNo);
		return "redirect:/teacher/testOne?testNo="+testNo;
	}

	// Test 상세보기(문제 등록)
	@PostMapping("/teacher/addQuestionExample")
	public String addQuestionExample(Question question
										, @RequestParam(value="exampleTitle") String[] exampleTitle
										, @RequestParam(value="exampleIdx") int[] exampleIdx
										, @RequestParam(value="exampleOx") int exampleOx) {
		log.debug("\u001B[31m"+"questionTitle : "+question.getQuestionTitle());
		log.debug("\u001B[31m"+"questionIdx : "+question.getQuestionIdx());
		log.debug("\u001B[31m"+"testNo : "+question.getTestNo());
		
		int addQuestion = teacherService.addQuestion(question);
		if(addQuestion == 1) {
			log.debug("\u001B[31m"+"qustionAdd 성공");
		}
		int lastQuestionNo = teacherService.getLastQuestionNo();
		int testNo = question.getTestNo();

		Example[] example = new Example[4];
		log.debug("\u001B[31m"+"exampleLength : "+example.length);
		for(int i=0; i<example.length; i++) {
			example[i] = new Example();
			example[i].setQuestionNo(lastQuestionNo);
			// log.debug("\u001B[31m"+"test : "+i);
			log.debug("\u001B[31m"+"questionNo : "+example[i].getQuestionNo());
			example[i].setExampleTitle(exampleTitle[i]);
			log.debug("\u001B[31m"+"exampleTitle : "+example[i].getExampleTitle());
			example[i].setExampleIdx(exampleIdx[i]);
			example[i].setExampleOx("오답");
			if(exampleOx == (i+1)) {
				example[i].setExampleOx("정답");
			} 
			int addExample = teacherService.addExample(example[i]);
			if(addExample == 1) {
				log.debug("\u001B[31m"+"test : "+example[i]);
				log.debug("\u001B[31m"+exampleIdx[i]+"번 선택지 등록 성공");
			}
		}		
		return "redirect:/teacher/testOne?testNo="+testNo;
	}
	
	
	// Test 상세보기 (문제 목록)
	@GetMapping("/teacher/testOne")
	public String testOne(Model model
								, @RequestParam(value="testNo") int testNo) {
		List<Map<String, Object>> list = teacherService.getExampleList(testNo);
		List<Map<String, Object>> answerList = teacherService.getAnswerList(testNo);
		Test thisTest = teacherService.thisTest(testNo);
		int titleQstcnt = list.size() / 4;
		int lastQeustionNo = teacherService.getLastQuestionNo();
		
		// 문제갯수
		log.debug("\u001B[31m"+"총 문제 수 : "+titleQstcnt);
		model.addAttribute("list", list);
		model.addAttribute("answerList", answerList);
		model.addAttribute("titleQstcnt", titleQstcnt);
		model.addAttribute("thisTest", thisTest);
		model.addAttribute("lastQeustionNo", lastQeustionNo);
		return "teacher/testOne";
		
	}
	
	
	
	// ==================== 시험(Test)====================
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
