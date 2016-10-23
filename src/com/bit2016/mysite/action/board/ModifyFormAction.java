package com.bit2016.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.dao.BoardDao;
import com.bit2016.mysite.vo.BoardVo;
import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long no = Long.parseLong(request.getParameter("no"));
		BoardDao dao = new BoardDao();
		BoardVo vo = dao.view(no);

		request.setAttribute("list", vo);
		
		
		WebUtil.forward(request, response, "/WEB-INF/views/board");
		System.out.println("modifyformaction");
		
		

	}

}
