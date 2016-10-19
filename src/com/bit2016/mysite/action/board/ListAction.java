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
		System.out.println("listAction");
		
		BoardDao dao = new BoardDao();
		List<BoardVo> list = new BoardDao().getList(10);
		
		request.setAttribute("list", list);
		
		WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp" );
	}

}
