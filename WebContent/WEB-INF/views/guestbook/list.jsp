<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<%
	pageContext.setAttribute("newLine", "\n");
%>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	

	<div id="container">
		<c:import url = "/WEB-INF/views/includes/header.jsp"/>
	<div id="content">
		<div id="guestbook">
			<h1>방명록</h1>
				<form action="/mysite3/guestbook" method="post">
					<input type="hidden" name="a" value="add">
					<table>
						<tr>
							<td><input type="text" name="name" placeholder="이름"></td>
						</tr>
						<tr>
							<td><input type="password" name="pass" placeholder="비밀번호"></td>
							</tr>				
						<tr>
							<td colspan=4><textarea name="content" id="content"></textarea></td>
						</tr>
						<tr class="submit">
						<td><input type="submit" name="submit" VALUE="submit"></td>
						</tr>
					</table>
				</form>
				<ul>
						<c:set var="count" value="${fn:length(list)}"/>
						<c:forEach items="${list }" var="vo" varStatus="status">
					<li>	
						<table width=510 border=1>
							<tr>
								<td><span>${vo.name }&nbsp</span>&nbsp&nbsp${count - status.index}번째 글&nbsp&nbsp ${vo.date}&nbsp</td>
							<tr>
							<td colspan=4>${fn:replace(vo.content, newLine, "<br>")}</td>
							</tr>
							<tr>
								<td><a href = "/mysite3/guestbook?a=deleteform&no=${vo.no}">삭제</a>
							</td>
							</tr>
						</table>
						<br>
					</li>
						</c:forEach>
				</ul>
			</div>
		</div>
			<c:import url = "/WEB-INF/views/includes/navigation.jsp">
			<c:param name = "menu" value = "guestbook"/>
			</c:import>
			
			<c:import url = "/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>