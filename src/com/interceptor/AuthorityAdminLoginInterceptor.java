package com.interceptor;

import java.util.Map;

import com.model.Administrator;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 拦截器
 * @author Administrator
 *
 */
public class AuthorityAdminLoginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		//取得请求相关的ActionContext实例
		ActionContext ctx = invocation.getInvocationContext();
		Map session = ctx.getSession();
		//取出名为user的Session属性
		Administrator admin = (Administrator)session.get("adminInfo");
		if (admin != null)
		{
			return invocation.invoke();
		}
				
		return Action.INPUT;
	}

}
