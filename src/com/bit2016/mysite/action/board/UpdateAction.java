package com.bit2016.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bit2016.mysite.dao.BoardDao;
import com.bit2016.mysite.dao.UserDao;
import com.bit2016.mysite.vo.BoardVo;
import com.bit2016.mysite.vo.UserVo;
import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String title = request.getParameter("title");
//		String content = request.getParameter("content");

		//세션
		HttpSession session = request.getSession();
		if (session == null) {
			WebUtil.redirect(request, response, "/main");
			return;
		}
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		UserVo uservo = new UserDao().get(authUser.getNo());
		
		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo();
		
//		vo.setTitle(title);
//		vo.setContent(content);

		vo.setUserNo(uservo.getNo());
		
		dao.write(vo);

	}

}
