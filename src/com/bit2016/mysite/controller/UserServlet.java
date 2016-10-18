package com.bit2016.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2016.mysite.action.main.MainActionFactory;
import com.bit2016.mysite.action.user.UserActionFactory;
import com.bit2016.web.Action;
import com.bit2016.web.ActionFactory;


@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("UserBookServlet");
		
		String actionName = request.getParameter("a");
		System.out.println("액션네임에 쿼리스트링 파라미터 받아오기"+actionName);
		
		ActionFactory af = new UserActionFactory();
		System.out.println("유저액션팩토리 객체생성 "+af);
		
		Action action = af.getAction(actionName);
		System.out.println("유저액션팩토리 객체에 있는 겟액션을 통해 쿼리스트링에 맞는 액션 담기"+action);
		
		System.out.println("담아온 액션 실행");
		action.execute(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
