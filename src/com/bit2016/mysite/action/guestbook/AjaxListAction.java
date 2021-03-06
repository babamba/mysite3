package com.bit2016.mysite.action.guestbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.bit2016.mysite.dao.GuestBookDao;
import com.bit2016.mysite.vo.GuestBookVo;
import com.bit2016.web.Action;
import com.bit2016.web.util.WebUtil;

public class AjaxListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String sPage = request.getParameter("p");
		
		if("".equals(sPage)){
			sPage="1";
		}
		int page = Integer.parseInt(sPage);
		
		GuestBookDao dao = new GuestBookDao();
		List<GuestBookVo> list = dao.getList(page);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", list);
		
		response.setContentType("application/json; charset=utf-8");
		JSONObject jsonobject = JSONObject.fromObject(map);
		
		response.getWriter().println(jsonobject.toString());

	}

}
