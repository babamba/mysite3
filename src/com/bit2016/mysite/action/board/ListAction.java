package com.bit2016.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.dao.BoardDao;
import com.bit2016.mysite.vo.BoardVo;
import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

public class ListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		List<BoardVo> list = new BoardDao().getList(20);
		new BoardDao();
		
		
		request.setAttribute("list", list);
		System.out.println("listAction");
		
		WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp" );
	}

}
