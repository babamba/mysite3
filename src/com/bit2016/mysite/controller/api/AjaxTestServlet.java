package com.bit2016.mysite.controller.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bit2016.mysite.vo.UserVo;

@WebServlet("/ajax")
public class AjaxTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		
		List<UserVo> list = new ArrayList<UserVo>();
		
		
		UserVo vo1 = new UserVo();
		vo1.setNo(10L);
		vo1.setName("바밤바");
		vo1.setEmail("babamba@gmail.com");
		vo1.setGender("male");
		list.add(vo1);
		
		UserVo vo2 = new UserVo();
		vo2.setNo(20L);
		vo2.setName("둘리");
		vo2.setEmail("dully@gmail.com");
		vo2.setGender("female");
		list.add(vo2);
		
		PrintWriter out = response.getWriter();
		
//		JSONObject jsonObject = JSONObject.fromObject(list); //객체하나면 JSONObject
		JSONArray jsonArray = JSONArray.fromObject(list);	 //list면  JSONArray
		out.println(jsonArray.toString());
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
