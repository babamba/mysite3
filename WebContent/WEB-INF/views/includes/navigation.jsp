<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


		<div id="navigation" style ="position:relative">
			<ul style = "position:absolute; left:'50px'" >
			<c:choose>
			
				<c:when test = "${param.menu == 'main' }">
				<li class = "selected"><a href="/mysite3/main">바밤바</a></li>
				<li><a href="${pageContext.request.contextPath}/guestbook">방명록</a></li>
				<li><a href="${pageContext.request.contextPath}/api/guestbook?a=ajax">방명록(ajax)</a></li>
				<li><a href="${pageContext.request.contextPath}/board">게시판</a></li>
				</c:when>
				
				<c:when test = "${param.menu == 'guestbook' }">
				<li><a href="${pageContext.request.contextPath}/main">바밤바</a></li>
				<li class = "selected"><a href="${pageContext.request.contextPath}/guestbook">방명록</a></li>
				<li><a href="${pageContext.request.contextPath}/api/guestbook?a=ajax"">방명록(ajax)</a></li>
				<li><a href="${pageContext.request.contextPath}/board">게시판</a></li>
				</c:when>
				
				<c:when test = "${param.menu == 'guestbook-ajax' }">
				<li><a href="${pageContext.request.contextPath}/main">바밤바</a></li>
				<li><a href="${pageContext.request.contextPath}/guestbook">방명록</a></li>
				<li class = "selected"><a href="${pageContext.request.contextPath}/api/guestbook?a=ajax">방명록(ajax)</a></li>
				<li><a href="${pageContext.request.contextPath}/board">게시판</a></li>
				</c:when>
				
				<c:when test = "${param.menu == 'board' }">
				<li><a href="${pageContext.request.contextPath}/main">바밤바</a></li>
				<li><a href="${pageContext.request.contextPath}/guestbook">방명록</a></li>
				<li><a href="${pageContext.request.contextPath}/api/guestbook?a=ajax">방명록(ajax)</a></li>
				<li class = "selected"><a href="${pageContext.request.contextPath}/board">게시판</a></li>
				</c:when>
				
				<c:otherwise>
				<li><a href="/mysite3/main">바밤바</a></li>
				<li><a href="/mysite3/guestbook">방명록</a></li>
				<li><a href="/mysite3/board">게시판</a></li>
				</c:otherwise>
				
			</c:choose>
				
			</ul>
		</div>