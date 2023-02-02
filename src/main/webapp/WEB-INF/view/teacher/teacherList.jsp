<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Teacher List</title>
	</head>
	<body>
		<!-- empMenu include -->
		<div>
			<c:import url="/WEB-INF/view/employee/inc/empMenu.jsp"></c:import>
		</div>
		
		<h1>Teacher List</h1>
		<a href="${pageContext.request.contextPath}/employee/teacher/addTeacher">강사등록</a>
		<table border="1">
			<tr>
				<th>teacherId</th>
				<th>teacherName</th>
				<th>삭제</th>
			</tr>
			<c:forEach var="e" items="${list}">
				<tr>
					<td>${e.teacherId}</td>
					<td>${e.teacherName}</td>
					<td>
						<a href="${pageContext.request.contextPath}/employee/teacher/removeTeacher?teacherNo=${e.teacherNo}">
							삭제
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<!-- 검색 -->
		<form method="get" action="${pageContext.request.contextPath}/employee/teacher/teacherList">
			<input type="text" name="searchWord" value="${searchWord}">
			<button type="submit">이름검색</button>
		</form>
		
		
		<!-- 페이징 -->
		<div>
		
			<!-- 페이지 1 처음으로 -->
			<a href="${pageContext.request.contextPath }/employee/teacher/teacherList?currentPage=1&searchWord=${searchWord}&rowPerPage=${rowPerPage}">Start</a>
			
			
			<!-- 페이지 -10 이전 -->
			<c:if test="${currentPage > 1 }"> 
				<a href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=${currentPage-10}&searchWord=${searchWord}&rowPerPage=${rowPerPage}">-10</a>
			</c:if>
				
		
		
			<!-- 페이지 1 ~ 10 -->
			<c:forEach var="i" begin="${beginPage}" end="${endPage}" step="1">
					<a href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=${i}&searchWord=${searchWord}">${i}</a>
			</c:forEach>	
			
			
			<!-- 페이지 +10 다음 -->
			<c:if test="${currentPage < endPage }"> 
				<a href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=${currentPage+10}&searchWord=${searchWord}&rowPerPage=${rowPerPage}">+10</a>
			</c:if>
			
			
			<!-- 페이지 마지막 -->
			<a href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=${lastPage}&searchWord=${searchWord}&rowPerPage=${rowPerPage}">end</a>
		</div>
	</body>
</html>
