package com.bit2016.mysite.action.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.dao.GuestBookDao;
import com.bit2016.mysite.vo.GuestBookVo;
import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

public class ListAction implements Action {

	@Override
	public void execute(
			HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		
		GuestBookDao dao = new GuestBookDao();
		List<GuestBookVo> list = dao.getList();
		
		request.setAttribute("list", list);
		
		WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
		//포워딩 먼저해서 밑의 내용 실행 무시되므로 포워딩은 제일 마지막에
	}

}
