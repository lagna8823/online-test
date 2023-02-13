package goodee.gdj58.online.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.TeacherService;
import goodee.gdj58.online.vo.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	@Autowired TeacherService teacherService;
	
	// 홈화면
	@GetMapping("/Home")
	public String home(Model model
						, @RequestParam(value="restrictedMsg", defaultValue="") String restrictedMsg) {
		log.debug("\u001B[31m"+"restrictedMsg : "+restrictedMsg);
		if(!restrictedMsg.equals("")) { // 필터에서 걸러져 returnMsg를 받은 경우
			model.addAttribute("restrictedMsg", restrictedMsg);
		}
		//List<Test> list = teacherService.getLatestTestList();
		//model.addAttribute("list",list);
		return "/home";
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/Home";
	}
}
