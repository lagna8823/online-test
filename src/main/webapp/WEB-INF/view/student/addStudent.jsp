<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 등록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
</head>
<body>
	<h1>학생 등록</h1>
	<div>${errorMsg}</div>	
	<form method="post" action="${pageContext.request.contextPath}/employee/student/addStudent">
		<table border="1">
			<tr>
				<td>studentId</td>
				<td>
					<input type="text" id="studentId" name="studentId">
					<button type="button" id="ckBtn">중복검사</button>
				</td>
			</tr>
			<tr>
				<td>studentPw</td>
				<td><input type="password" id="studentPw" name="studentPw"></td>
			</tr>
			<tr>
				<td>studentName</td>
				<td><input type="text" id="studentName" name="studentName"></td>
			</tr>	
		</table>
		<button type="submit" id="addBtn">학생 추가</button>
	</form>
</body>
<script>
	$('#ckBtn').click(function(){
		$.ajax({
			url:'${pageContext.request.contextPath}/employee/idCheck'
			, type:'get'
			, data : {id:$('#studentId').val()}
			, success:function(model){ // model : 'YES' / 'NO'
				if(model=='YES') {
					// 사용가능한 아이디
					$('#studentId').val($('#studentId').val());
					alert($('#studentId').val()+'는 사용가능한 아이디입니다');
				} else {
					// 사용중인 아이디
					alert($('#studentId').val()+'는 사용중인 아이디입니다');
				}
			}
		});
	});
	
	$('#addBtn').click(function() {
		// 폼 유효성 검사
		// 폼 액션 전송
	});
	
</script>
</html>