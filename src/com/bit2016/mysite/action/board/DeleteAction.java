package com.bit2016.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit2016.mysite.dao.BoardDao;
import com.bit2016.mysite.vo.BoardVo;
import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null) {
			WebUtil.redirect(request, response, "/main");
			return;
		}

		session.getAttribute("authUser");
		
		
		BoardVo vo = new BoardVo();
		BoardDao dao = new BoardDao();

		String no = request.getParameter("no");
		Long pno = Long.parseLong(no);
		dao.delete(pno);

		WebUtil.redirect(request, response, "/mysite3/board");

	}

}
