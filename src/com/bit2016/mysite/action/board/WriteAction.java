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
import com.sun.javafx.geom.transform.GeneralTransform3D;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("write");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String groupNo = request.getParameter("groupNo");
		String depth = request.getParameter("depth");
		String userNo = request.getParameter("userNo");
		
		//세션
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		UserVo uservo = new UserDao().get(authUser.getNo());
		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo();
		
		vo.setTitle(title);
		vo.setContent(content);
		vo.setGroupNo(01);
		vo.setDepth(01);
		vo.setUserNo(uservo.getNo());
		
		dao.write(vo);
		
		WebUtil.redirect(request, response, "/mysite3/board");
	}

}
