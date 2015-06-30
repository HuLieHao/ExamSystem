package com.interceptor;

import java.util.Map;

import com.model.Student;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityUserLoginInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5202086873101589087L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		//取得请求相关的ActionContext实例
		ActionContext ctx = invocation.getInvocationContext();
		Map session = ctx.getSession();
		//取出名为user的Session属性
		Student user = (Student)session.get("user");
		
		if (user != null)
		{
			return invocation.invoke();
		}
		
		return Action.INPUT;
	}

}
