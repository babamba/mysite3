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

public class AjaxDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String no = request.getParameter( "no" );
		String password = request.getParameter( "password" );
		
		GuestBookVo vo = new GuestBookVo();
		vo.setNo(Long.parseLong( no ));
		vo.setPassword(password);
		GuestBookDao dao = new GuestBookDao();
		
		boolean result = dao.delete(vo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(result){
			map.put("result", "success");
			map.put("data", no);
		}
		
		map.put("result", "fail");
		map.put("message", "삭제안돼");
		
		response.setContentType("application/json; charset=utf-8");
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().println(jsonObject.toString());

	}

}
