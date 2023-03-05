<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>


	<!-- teacherMenu include -->
	<c:if test="${loginTeacher != null && loginStudent == null}">
	<div>
		<c:import url="/WEB-INF/view/teacher/inc/teacherMenu.jsp"></c:import>
	</div>
	</c:if>
	
	<!-- studentMenu include -->
	<c:if test="${loginTeacher == null && loginStudent != null}">
	
	<div>
		<c:import url="/WEB-INF/view/student/inc/studentMenu.jsp"></c:import>
	</div>
	</c:if>
	
	<div>
		
		<a href="${pageContext.request.contextPath}/loginEmp">직원 로그인</a>
		<a href="${pageContext.request.contextPath}/loginTeacher">강사 로그인</a>
		<a href="${pageContext.request.contextPath}/loginStudent">학생 로그인</a>
		<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
		
	</div>

</body>
</html>