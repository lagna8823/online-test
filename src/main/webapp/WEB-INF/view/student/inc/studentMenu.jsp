<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	<a href="${pageContext.request.contextPath}/employee/empList">사원관리</a>
	<!-- 등록시 ID체크(employee + teacher + student) -->
	
	<a href="${pageContext.request.contextPath}/employee/teacher/teacherList">강사관리</a>
	<!-- 강사목록, 강사삭제 -->
	
	<a href="${pageContext.request.contextPath}/employee/student/studentList">학생관리</a>
	<!-- 학생목록, 학생삭제 -->
	
	<a href="${pageContext.request.contextPath}/loginEmp">로그인</a>
	
	<a href="${pageContext.request.contextPath}/student/modifyStudentPw">학생 비밀번호 수정</a>
	
	<!--
		지난 시험(table : test) 리스트+점수 - 점수확인버턴 - 제출답안지확인(table : paper)
		오늘날짜 시험 리스트 응시버턴 - 시험지 출력(table : question X(조인) example) - 답안지제출(table : paper)
	 -->	
	<a href="${pageContext.request.contextPath}/student/studentTestList">학생 비밀번호 수정</a>
	
	<a href="${pageContext.request.contextPath}/student/logout">로그아웃</a>
	
</div>

