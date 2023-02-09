<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 등록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
</head>
<body>
	<h1>사원추가</h1>
	<div>${errorMsg}</div>
	<form method="post" action="${pageContext.request.contextPath}/employee/addEmp">
		<table border="1">
			<tr>
				<td>empId</td>
				<td><input type="text" id="empId" name="empId"><button type="button" id="ckBtn">중복검사</button></td>
				
			</tr>
			<tr>
				<td>empPw</td>
				<td><input type="password" id="empPw" name="empPw"></td>
			</tr>
			<tr>
				<td>empName</td>
				<td><input type="text" name="empName"></td>
			</tr>	
		</table>
		<button type="submit" id="addBtn">사원추가</button>
	</form>
</body>
<script>
	$('#ckBtn').click(function(){
		$.ajax({
			url:'idCheck'
			, type:'get'
			, data : {id:$('#empId').val()}
			, success:function(model){ // model : 'YES' / 'NO'
				if(model=='YES') {
					// 사용가능한 아이디
					$('#empId').val($('#empId').val());
					alert($('#empId').val()+'는 사용가능한 아이디입니다');
				} else {
					// 사용중인 아이디
					alert($('#empId').val()+'는 사용중인 아이디입니다');
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
