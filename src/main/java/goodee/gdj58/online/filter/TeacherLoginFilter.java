package goodee.gdj58.online.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@WebFilter("/teacher/*")
public class TeacherLoginFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		log.debug("\u001B[33m"+"TeacherLoginFilter(강사 로그인 확인 중)");
		
		if(request instanceof HttpServletRequest) {
			HttpSession session = ((HttpServletRequest)request).getSession();
			// HttpServletRequest req = (HttpServletRequest)request;
    		// HttpSession session = req.getSession();
			
			// 강사 로그인 후 사용
			if(session.getAttribute("loginTeacher") == null) {
				String returnMsg = "restricted access"; // return 문구 
				((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()
						+ "/loginStudent?returnMsg="+returnMsg);
				return;
			}
		} else {
			log.debug("\u001B[31m"+"웹브라우저 요청만 허용합니다");
			return;
		}
		
		// controller 전
		chain.doFilter(request, response);
		// controller 후
	}
}
