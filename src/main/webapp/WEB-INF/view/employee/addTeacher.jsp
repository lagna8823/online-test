<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강사 등록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
</head>
<body>
	<h1>강사 등록</h1>
	<div>${errorMsg}</div>	
	<form method="post" action="${pageContext.request.contextPath}/employee/teacher/addTeacher">
		<table border="1">
			<tr>
				<td>teacherId</td>
				<td>
					<input type="text" id="teacherId" name="teacherId">
					<button type="button" id="ckBtn">중복검사</button>
				</td>
			</tr>
			<tr>
				<td>teacherPw</td>
				<td><input type="password" id="teacherPw" name="teacherPw"></td>
			</tr>
			<tr>
				<td>teacherName</td>
				<td><input type="text" id="teacherName" name="teacherName"></td>
			</tr>	
		</table>
		<button type="submit" id="addBtn">강사 추가</button>
	</form>
</body>
<script>
	$('#ckBtn').click(function(){
		$.ajax({
			url:'${pageContext.request.contextPath}/employee/idCheck'
			, type:'get'
			, data : {id:$('#teacherId').val()}
			, success:function(model){ // model : 'YES' / 'NO'
				if(model=='YES') {
					// 사용가능한 아이디
					$('#teacherId').val($('#teacherId').val());
					alert($('#teacherId').val()+'는 사용가능한 아이디입니다');
				} else {
					// 사용중인 아이디
					alert($('#teacherId').val()+'는 사용중인 아이디입니다');
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
