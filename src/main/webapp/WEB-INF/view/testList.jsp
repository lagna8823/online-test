<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Test List</title>
	</head>
	<body>
		<!-- teacherMenu include -->
		<div>
			<c:import url="/WEB-INF/view/teacher/inc/teacherMenu.jsp"></c:import>
		</div>
		
		<h1>Test List</h1>
		<c:if test="${loginTeacher != null && loginStudent == null}">
			<a href="${pageContext.request.contextPath}/teacher/addTest">시험등록</a>
		</c:if>
		<table border="1">
			<thead>
				<tr>
					<th>testNo</th>
					<th>testTitle</th>
					<th>testDate</th>
					<c:if test="${loginStudent != null && loginTeacher == null}">
						<th>응시하기</th>
					</c:if>
					<c:if test="${loginTeacher != null && loginStudent == null}">
						<th>삭제</th>
					</c:if>
				</tr>
			</thead>
			
			<tbody>
			<!-- 시험목록 출력 -->
			<c:forEach var="t" items="${list}">
				<tr>
					<td>${t.testNo}</td>
				<c:choose>
					<c:when test="${loginTeacher != null}">
						<td>
							<a href="${pageContext.request.contextPath}/teacher/testOne?testNo=${t.testNo}">${t.testTitle}</a>
						</td>
					</c:when>
					<c:otherwise>
						<td>
							${t.testTitle}
						</td>
					</c:otherwise>
				</c:choose>
					<td>${t.testDate}</td>
					<c:if test="${loginTeacher == null && loginStudent != null}">
						<td>
							<a href="${pageContext.request.contextPath}/student/examTest?testNo=${t.testNo}">문제풀기</a>
						</td>	
					</c:if>
					<c:if test="${loginTeacher != null && loginStudent == null}">
					<td>
						<a href="${pageContext.request.contextPath}/teacher/removeTest?testNo=${t.testNo}">
							삭제
						</a>
					</td>
					</c:if>
				</tr>
			</c:forEach>
			
		</table>
		
		<!-- 검색 -->
		<form method="get" action="${pageContext.request.contextPath}/teacher/testList">
			<input type="text" name="searchWord" value="${searchWord}">
			<button type="submit">제목검색</button>
		</form>
		
		
		<!-- 페이징 -->
		<div>
		
			<!-- 페이지 1 처음으로 -->
			<a href="${pageContext.request.contextPath }/teacher/testList?currentPage=1&searchWord=${searchWord}&rowPerPage=${rowPerPage}">Start</a>
			
			
			<!-- 페이지 -10 이전 -->
			<c:if test="${currentPage > 1 }"> 
				<a href="${pageContext.request.contextPath}/teacher/testList?currentPage=${currentPage-10}&searchWord=${searchWord}&rowPerPage=${rowPerPage}">-10</a>
			</c:if>
				
		
		
			<!-- 페이지 1 ~ 10 -->
			<c:forEach var="i" begin="${beginPage}" end="${endPage}" step="1">
					<a href="${pageContext.request.contextPath}/teacher/testList?currentPage=${i}&searchWord=${searchWord}">${i}</a>
			</c:forEach>	
			
			
			<!-- 페이지 +10 다음 -->
			<c:if test="${currentPage < endPage }"> 
				<a href="${pageContext.request.contextPath}/teacher/testList?currentPage=${currentPage+10}&searchWord=${searchWord}&rowPerPage=${rowPerPage}">+10</a>
			</c:if>
			
			
			<!-- 페이지 마지막 -->
			<a href="${pageContext.request.contextPath}/teacher/testList?currentPage=${lastPage}&searchWord=${searchWord}&rowPerPage=${rowPerPage}">end</a>
		</div>
	</body>
</html>
