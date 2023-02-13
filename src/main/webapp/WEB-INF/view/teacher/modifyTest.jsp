<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>시험 수정</title>
	</head>
	<body>
		<h1>시험 수정</h1>
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
			<button type="submit" id="modifyBtn">시험 수정</button>
		</form>
	</body>
</html>