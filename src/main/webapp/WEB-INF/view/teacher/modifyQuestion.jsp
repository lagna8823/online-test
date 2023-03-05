<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>문제 수정</h1>
	<form action="${pageContext.request.contextPath}/teacher/modifyQuestion" method="post">
		<c:forEach var="q" items="${list}">
			<c:if test="${q.exampleIdx == 1}">
				<div>
					${q.questionIdx}.
				 	<input type="text" name="questionTitle" value="${q.questionTitle}">
				 	<input type="hidden" name="questionNo" value="${q.questionNo}">
				 	<input type="hidden" name="testNo" value="${q.testNo}">
				 </div>
			</c:if>
			<br>
			<!-- 선택지 -->
			<c:if test="${q.exampleIdx == 1}">
				<div>
					&nbsp;① 
					<input type="text" name="exampleTitle" value="${q.exampleTitle}">
					<input type="hidden" name="exampleNo" value="${q.exampleNo}">
					<c:if test="${q.exampleOx == '정답'}">
						<input type="radio" name="exampleOx" value="1" checked>
					</c:if>
					<c:if test="${q.exampleOx == '오답'}">
						<input type="radio" name="exampleOx" value="1">
					</c:if>
				</div>
			</c:if>
			<c:if test="${q.exampleIdx == 2}">
				<div>
					&nbsp;②
					<input type="text" name="exampleTitle" value="${q.exampleTitle}">
					<input type="hidden" name="exampleNo" value="${q.exampleNo}">
					<c:if test="${q.exampleOx == '정답'}">
						<input type="radio" name="exampleOx" value="2" checked>
					</c:if>
					<c:if test="${q.exampleOx == '오답'}">
						<input type="radio" name="exampleOx" value="2">
					</c:if>
				</div>
			</c:if>
			<c:if test="${q.exampleIdx == 3}">
				<div>
					&nbsp;③ 
					<input type="text" name="exampleTitle" value="${q.exampleTitle}">
					<input type="hidden" name="exampleNo" value="${q.exampleNo}">
					<c:if test="${q.exampleOx == '정답'}">
						<input type="radio" name="exampleOx" value="3" checked>
					</c:if>
					<c:if test="${q.exampleOx == '오답'}">
						<input type="radio" name="exampleOx" value="3">
					</c:if>
				</div>
			</c:if>
			<c:if test="${q.exampleIdx == 4}">
				<div>
					&nbsp;④ 
					<input type="text" name="exampleTitle" value="${q.exampleTitle}">
					<input type="hidden" name="exampleNo" value="${q.exampleNo}">
					<c:if test="${q.exampleOx == '정답'}">
						<input type="radio" name="exampleOx" value="4" checked>
					</c:if>
					<c:if test="${q.exampleOx == '오답'}">
						<input type="radio" name="exampleOx" value="4">
					</c:if>
				</div>
				<br>
			</c:if>
		</c:forEach>
		<button type="submit">수정</button>
	</form>
</body>
</html>