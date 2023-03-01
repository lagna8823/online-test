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
		<div>
			<c:import url="/WEB-INF/view/teacher/inc/teacherMenu.jsp"></c:import>
		</div>
		<div>
			<span>${thisTest.testDate}</span>
			<form action="${pageContext.request.contextPath}/teacher/modifyTest" method="post">
				<p>
					<span>제목</span>
					<input type="hidden" name="testNo" value="${thisTest.testNo}">
					<input type="text" name="testTitle" value="${thisTest.testTitle}">
				</p>
				<button type="submit">수정</button>
			</form>
		</div>
					
        <!-- 문제추가하기 -->
		<h5>문제 추가</h5>
		<div>
			<form action="${pageContext.request.contextPath}/teacher/addQuestionExample" method="post">
				<table class="table table-hover">
					<!-- 문제 -->
					<tr>
						<th style="width:100px;">문제번호</th>
						<td><input style="width:70px;" type="number" name="questionIdx"></td>
					</tr>
					<tr>
						<td colspan="2">
							<textarea rows="5" cols="80" name="questionTitle" placeholder="문제를 입력하세요"></textarea>
						</td>
					</tr>
				</table>
				
				<!-- 선택지 항목 -->
				<table class="table table-hover">
					<tr>
						<th style="width:35px;">①</th>
						<td>
							<input type="text" class="exampleTitle" name="exampleTitle" style="width:350px" placeholder="1번 선택지 답을 입력하세요">
							<input type="hidden" class="exampleIdx" name="exampleIdx" value="1">
							<input type="radio" class="exampleOx" name="exampleOx" value="1"> 
						</td>
					</tr>
					<tr>
						<th>②</th>
						<td>
							<input type="text" class="exampleTitle" name="exampleTitle" style="width:350px" placeholder="2번 선택지 답을 입력하세요">
							<input type="hidden" class="exampleIdx" name="exampleIdx" value="2">
							<input type="radio" class="exampleOx" name="exampleOx" value="2">
						</td>
					</tr>
					<tr>
						<th>③</th>
						<td>
							<input type="text" class="exampleTitle" name="exampleTitle" style="width:350px" placeholder="3번 선택지 답을 입력하세요">
							<input type="hidden" class="exampleIdx" name="exampleIdx" value="3">
							<input type="radio" class="exampleOx" name="exampleOx" value="3">
						</td>
					</tr>
					<tr>
						<th>④</th>
						<td>
							<input type="text" class="exampleTitle" name="exampleTitle" style="width:350px" placeholder="4번 선택지 답을 입력하세요">
							<input type="hidden" class="exampleIdx" name="exampleIdx" value="4">
							<input type="radio" class="exampleOx" name="exampleOx" value="4">
						</td>
					</tr>
				</table>
				<input type="hidden" name="testNo" value="${thisTest.testNo}">
				<div class="text-center">
					<button type="submit">등록</button>
				</div>
			</form>
		</div>
		
		<!-- 문제목록 출력 -->
		<h5>문제 목록</h5>
		<div>
			<c:forEach var="q" items="${list}">
				<div>
					<!-- 문제 -->
					<c:if test="${q.exampleIdx == 1}">
						<div style="font-weight:bold;">
							${q.questionIdx}. ${q.questionTitle}
						</div>
						<div>
							<a href="${pageContext.request.contextPath}/teacher/modifyQuestion?questionNo=${q.questionNo}&testNo=${thisTest.testNo}">수정</a>
							<a href="${pageContext.request.contextPath}/teacher/removeQuestion?questionNo=${q.questionNo}&testNo=${thisTest.testNo}">삭제</a>
						</div>
					</c:if>
					<br>
					<!-- 선택지 -->
					<c:if test="${q.exampleIdx == 1}">
						<div>
							&nbsp;① ${q.exampleTitle}
						</div>
					</c:if>
					<c:if test="${q.exampleIdx == 2}">
						<div>
							&nbsp;② ${q.exampleTitle}
						</div>
					</c:if>
					<c:if test="${q.exampleIdx == 3}">
						<div>
							&nbsp;③ ${q.exampleTitle}
						</div>
					</c:if>
					<c:if test="${q.exampleIdx == 4}">
						<div>
							&nbsp;④ ${q.exampleTitle}
						</div>
						<br>
					</c:if>
				</div>
			</c:forEach>
			
			<h5>=========답지===========</h5>
			<c:forEach var="a" items="${answerList}">
				<span>${a.questionIdx}) </span>
				<span style="font-weight:bold; color:red;">${a.exampleIdx}</span>
			</c:forEach>
		</div>
	</body>
</html>