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

@Slf4j // static Log log = new Log();
@WebFilter("/employee/*")
public class EmpLoginFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		log.debug("\u001B[31m"+"EmpLoginFilter(직원 로그인 확인 중)");

		if(request instanceof HttpServletRequest) { // 웹브라우저일경우 형변환 (ServletRequest의 자식타입)
			HttpServletRequest req = (HttpServletRequest)request;
			HttpSession session = req.getSession();
			// HttpServletRequest req = (HttpServletRequest)request;
    		// HttpSession session = req.getSession();
			
			// 직원 로그인 후 사용
			if(session.getAttribute("loginEmp") == null) {
				String returnMsg = "restricted access"; // return 문구 
				((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()
						+ "/loginStudent?returnMsg="+returnMsg);
				return;
			}
		} else {
			log.debug("웹브라우저 요청만 허용합니다");
			return; 
		}
			
			
		// controller 전
		chain.doFilter(request, response);
		// controller 후
	}
}
