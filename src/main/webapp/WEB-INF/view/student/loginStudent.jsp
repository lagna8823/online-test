<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>LOG IN</title>
		<meta charset="utf-8">
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
		    $(document).ready(function() {
				// 유효성검사
				$('#loginBtn').click(function(){
					// 아이디 유효성 체크
					if($('#id').val() == '') {
						$('#idMsg').text('아이디를 입력해주세요');
						$('#id').focus();
						return;
					} else {
						$('#idMsg').text('');
					}
					// 패스워드 유효성 체크
					if($('#pw').val() == '') {
						$('#pwMsg').text('패스워드를 입력해주세요');
						$('#pw').focus();
						return;
					} else {
						$('pwMsg').text('');
					}
					
					$('#loginForm').submit();
				});
			});
	    </script>
	</head>
	
	<body>
		<div>	
			<form id="loginForm" action="${pageContext.request.contextPath}/loginStudent" method="post">
			        <!-- 아이디 -->				        
			        <div>
			        	<span>student Id</span>
			            <input id="id" type="text" name="studentId">
			            <small id="idMsg"></small>
			        </div>
			        <!-- 패스워드 -->
			        <div>
			        	<span>studentPw</span>
			            <input id="pw" type="password" name="studentPw">
			            <small id="pwMsg"></small>
			        </div>
			        <!-- 로그인 실패시 문구출력 -->
			        <c:if test="${failLoginStu == 'failLoginStu'}">
			        		<small>존재하지않는 아이디이거나 패스워드가 일치하지 않습니다.</small>
			        </c:if>
		            <div>
		                <button id="loginBtn" type="button">LOG IN</button>
		            </div>
	          </form>
		</div>
	</body>
</html>