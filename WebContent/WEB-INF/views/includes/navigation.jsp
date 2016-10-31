<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>

<script>
$(window).scroll(function( ){  //스크롤이 움직일때마다 이벤트 발생 
    var position = $(window).scrollTop(); // 현재 스크롤바의 위치값을 반환합니다.
    $("#navigation").stop().animate({top:position}, 10); //해당 오브젝트 위치값 재설정
});
</script>

		<div id="navigation" style ="position:relative">
			<ul style = "position:absolute; left:'50px'" >
			<c:choose>
			
				<c:when test = "${param.menu == 'main' }">
				<li class = "selected"><a href="/mysite3/main">바밤바</a></li>
				<li><a href="${pageContext.request.contextPath}/guestbook">방명록</a></li>
				<li><a href="${pageContext.request.contextPath}/guestbook?a=ajax">방명록(ajax)</a></li>
				<li><a href="${pageContext.request.contextPath}/board">게시판</a></li>
				</c:when>
				
				<c:when test = "${param.menu == 'guestbook' }">
				<li><a href="${pageContext.request.contextPath}/main">바밤바</a></li>
				<li class = "selected"><a href="${pageContext.request.contextPath}/guestbook">방명록</a></li>
				<li><a href="${pageContext.request.contextPath}/guestbook?a=ajax">방명록(ajax)</a></li>
				<li><a href="${pageContext.request.contextPath}/board">게시판</a></li>
				</c:when>
				
				<c:when test = "${param.menu == 'guestbook-ajax' }">
				<li><a href="${pageContext.request.contextPath}/main">바밤바</a></li>
				<li><a href="${pageContext.request.contextPath}/guestbook">방명록</a></li>
				<li class = "selected"><a href="${pageContext.request.contextPath}/guestbook?a=ajax">방명록(ajax)</a></li>
				<li><a href="${pageContext.request.contextPath}/board">게시판</a></li>
				</c:when>
				
				<c:when test = "${param.menu == 'board' }">
				<li><a href="${pageContext.request.contextPath}/main">바밤바</a></li>
				<li><a href="${pageContext.request.contextPath}/guestbook">방명록</a></li>
				<li><a href="${pageContext.request.contextPath}/guestbook?a=ajax">방명록(ajax)</a></li>
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