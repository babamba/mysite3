<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>

<script>
	var isEnd = false;
	var page = 0;
	var render = function(vo, where) {
		var htmls = "<li><table width=510 border=1>"
				+ "<tr><td><span>"
				+ vo.name
				+ "&nbsp</span>&nbsp&nbsp"
				+ vo.no
				+ "&nbsp&nbsp"
				+ vo.date
				+ "&nbsp</td>"
				+ "<tr><td colspan=4>"
				+ vo.content
				+ "</td></tr>"
				+ "<tr><td align=right><a class=\"delete\" href=\"/mysite3/guestbook?a=deleteform&no=136\">삭제</a>"
				+ "</td></tr></table> <br></li>";
		//js template libarary -> ejs

		$("#list-guestbook").append(htmls);
	}
	var fetchList = function() {
		if (isEnd == true) {
			return;
		}
		$
				.ajax({
					url : "${pageContext.request.contextPath}/api/guestbook?a=ajax-list&p="
							+ ++page,
					type : "get",
					dataType : "json",
					//data:"a=ajax=add&name=asdfasdf&password=asdfasdf&content=asdfasdfa"  //포스트방식으로
					
					success : function(response) {
						if (response.result != "success") {
							console.error(response.message);
							isEnd = true;
							return;
						}

						//렌더링
						$(response.data).each(function(index, vo) {
							render(vo);
						});

						if (response.data.length < 5) {
							isEnd = true;
							$("#btn-fetch").prop("disabled", true);
						}
					},
					error : function(jqXHR, status, e) {
						console.error(status + ":" + e);
					}
				});
	}

	$(function() {
		$("add-form").submit(function(event){
			event.preventDefault();
			
			//ajax insert
		});
		
		
		$(window).scroll(function(){
			var $window = $(this);
			var scrollTop = $window.scrollTop();
			var windowHeight = $window.height();
			var documentHeight = $(document).height();
			
			// 스크롤 바가 바닥까지 왔을 때(맨바닥 보다 20셀 정도 덜 왔을 때 )
			if(scrollTop + windowHeight + 10 > documentHeight){
				//console.log("call fetchList");
				fetchList();
			}
			
			//console.log(scrollTop + ":" + windowHeight + ":" + documentHeight);
		});
		
		$("#btn-fetch").click(function() {
			fetchList();
		});

		// 1번째 리스트 가져오기
		// fetchList();

	});
	
	$(function(){
		var $window = $(window);
		var top = $(window).scrollTop();
		 var speed          = 1000;     // 따라다닐 속도 : "slow", "normal", or "fast" or numeric(단위:msec)
		    var easing         = 'linear'; // 따라다니는 방법 기본 두가지 linear, swing
		    var $layer         = $('#stv_list'); // 레이어 셀렉팅
		    var layerTopOffset = 0;   // 레이어 높이 상한선, 단위:px
		    $layer.css('position', 'absolute');
		    /*사용자 설정 값 끝*/
		 
		    // 스크롤 바를 내린 상태에서 리프레시 했을 경우를 위해
		    if (top > 0 )
		        $win.scrollTop(layerTopOffset+top);
		    else
		        $win.scrollTop(0);
		 
		    //스크롤이벤트가 발생하면
		    $(window).scroll(function(){
		        yPosition = $win.scrollTop() - 123;
		        if (yPosition < 0)
		        {
		            yPosition = 0;
		        }
		        $layer.animate({"top":yPosition }, {duration:speed, easing:easing, queue:false});
		
	});
});
	
</script>
</head>
<body>


	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div class="float_sidebar" id="content">
			<div id="guestbook">
				<tr>
					<h1>방명록</h1>
				</tr>
				<form id="add-form" action="/mysite3/add" method="post">
					<input type="hidden" name="a" value="add">
					<table>
						<tr>
							<td><input type="text" name="name" placeholder="이름"></td>
						</tr>
						<tr>
							<td><input type="password" name="pass" placeholder="비밀번호"></td>
						</tr>
						<tr>
							<td><textarea name="content" id="content"
									placeholder="글을 작성해 주세요"></textarea></td>
						</tr>
						<tr class="submit">
							<td><input type="submit" name="submit" VALUE="submit"></td>
						</tr>
					</table>
				</form>
				<ul id="list-guestbook">

				</ul>
				<button style="margin-top: 10px" id="btn-fetch">가져오기</button>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax" />
		</c:import>

		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>