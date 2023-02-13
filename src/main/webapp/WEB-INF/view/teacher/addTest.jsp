<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시험 등록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
</head>
<body>
	<h1>시험 등록</h1>
	<div>${errorMsg}</div>	
	<form method="post" action="${pageContext.request.contextPath}/teacher/addTest">
		<table border="1">
			<tr>
				<td>Test Title</td>
				<td>
					<input type="text" id="testTitle" name="testTitle">
				</td>
			</tr>
		</table>
		<button type="submit" id="addBtn">시험 추가</button>
	</form>
</body>
<script>
	$('#addBtn').click(function() {
		// 폼 유효성 검사
		// 폼 액션 전송
	});
	
</script>
</html>
