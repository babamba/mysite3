package com.bit2016.mysite.action.board;

import com.bit2016.web.Action;
import com.bit2016.web.ActionFactory;

public class BoardActionFactory extends ActionFactory {
	
	@Override
	public Action getAction(String actionName) {
		Action action = null;

		if("write".equals(actionName)){
			action = new WriteAction();
		}else if("writeform".equals(actionName)){
			action = new WriteFormAction();
		}else if("modify".equals(actionName)){
			action = new ModifyAction();
		}else if("delete".equals(actionName)){
			action = new DeleteAction();
		}else if("view".equals(actionName)){
			action = new ViewAction();
		}else if("update".equals(actionName)){
			action = new UpdateAction();
		}else if("reply".equals(actionName)){
			action = new ReplyAction();
		}else if("modifyformaction".equals(actionName)){
			action = new ModifyFormAction();
		}else{
			action = new ListAction();
		}
		
		
		return action;
	}
	
}
