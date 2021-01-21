<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/guestbook/insert" method="post">
		<table border="1" width="500">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value=""></td>
				<td>비밀번호</td>
				<td><input type="password" name="password" value=""></td>
			</tr>
			<tr>
				<td colspan="4">
					<textarea cols=60 rows=5 name="content"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<button type="submit">확인</button>
				</td>
			</tr>
		</table>
	</form>
	
	<br><br>

	<c:forEach items="${requestScope.gList}" var="guestVo">
		<table border="1" width="500">
			<tr>
				<td>${guestVo.no}</td>
				<td>${guestVo.name}</td>
				<td>${guestVo.regDate}</td>
				<td><a href="${pageContext.request.contextPath}/guestbook/guestDelete?no=${guestVo.no}">삭제</a></td>
				<!-- <td><a href="/guestbook3/guestbook/guestDelete?no=${guestVo.no}">삭제</a></td>  -->
			</tr>
			<tr>
				<td colspan="4">
					${guestVo.content}
				</td>
			</tr>
		</table>
		<br>
	</c:forEach>
</body>
</html>