<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<!-- empMenu include -->
		<div>
			<c:import url="/WEB-INF/view/employee/inc/empMenu.jsp"></c:import>
		</div>
		
		<h1>Employee List</h1>
		<a href="${pageContext.request.contextPath}/employee/addEmp">사원등록</a>
		<table border="1">
			<tr>
				<th>empId</th>
				<th>empName</th>
				<th>삭제</th>
			</tr>
			<c:forEach var="e" items="${list}">
				<tr>
					<td>${e.empId}</td>
					<td>${e.empName}</td>
					<td>
						<a href="${pageContext.request.contextPath}/employee/removeEmp?empNo=${e.empNo}">
							삭제
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<!--  검색 -->
		<form method="get" action="${pageContext.request.contextPath}/employee/empList">
			<input type="text" name="searchWord" value="${searchWord}">
			<button type="submit">이름검색</button>
		</form>
		
					
		<!-- 페이징 -->
		<div>
		
			<!-- 페이지 1 처음으로 -->
			<a href="${pageContext.request.contextPath }/employee/empList?currentPage=1&searchWord=${searchWord}&rowPerPage=${rowPerPage}">Start</a>
			
			
			<!-- 페이지 -10 이전 -->
			<c:if test="${currentPage > 1 }"> 
				<a href="${pageContext.request.contextPath}/employee/empList?currentPage=${currentPage-10}&searchWord=${searchWord}&rowPerPage=${rowPerPage}">-10</a>
			</c:if>
				
		
		
			<!-- 페이지 1 ~ 10 -->
			<c:forEach var="i" begin="${beginPage}" end="${endPage}" step="1">
					<a href="${pageContext.request.contextPath}/employee/empList?currentPage=${i}&searchWord=${searchWord}">${i}</a>
			</c:forEach>	
			
			
			<!-- 페이지 +10 다음 -->
			<c:if test="${currentPage < endPage }"> 
				<a href="${pageContext.request.contextPath}/employee/empList?currentPage=${currentPage+10}&searchWord=${searchWord}&rowPerPage=${rowPerPage}">+10</a>
			</c:if>
			
			
			<!-- 페이지 마지막 -->
			<a href="${pageContext.request.contextPath}/employee/empList?currentPage=${lastPage}&searchWord=${searchWord}&rowPerPage=${rowPerPage}">end</a>
		</div>
	</body>
</html>
