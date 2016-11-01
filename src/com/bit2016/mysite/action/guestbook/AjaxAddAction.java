package com.bit2016.mysite.action.guestbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.bit2016.mysite.dao.GuestBookDao;
import com.bit2016.mysite.vo.GuestBookVo;
import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

public class AjaxAddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter( "name" );
		String password = request.getParameter( "password" );
		String content = request.getParameter( "content" );
		
		GuestBookVo vo = new GuestBookVo();
		vo.setName(name);
		vo.setContent(content);
		vo.setPassword(password);
		
		GuestBookDao dao = new GuestBookDao();
		
		Long no = dao.insert(vo);
		
		GuestBookVo guestBookVo = dao.get(no);
		System.out.println(guestBookVo.toString());
		
		
		response.setContentType("application/json; charset=utf-8");

		Map<String, Object> map = new HashMap<String, Object>();
		
		if(no == null){
			map.put("result", "fail");
			map.put("message", "방명록을 등록하지 못했습니다.");	
		}else{
			
			if(guestBookVo == null){
				map.put("result", "fail");
				map.put("message", "글을 불러오지 못했습니다.");	
			}
			map.put("result", "success");
			map.put("data", guestBookVo);	
		}
		
		JSONObject jsonobject = JSONObject.fromObject(map);
		
		response.getWriter().println(jsonobject.toString());


	}

}
