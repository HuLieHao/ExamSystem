package com.action;

import java.util.Map;

import com.model.Administrator;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.AdminService;

/**
 * 处理管理员信息的action
 * @author 胡烈豪
 *
 */
public class AdminAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6915054637565821046L;

	private AdminService adminService;
	
	private Administrator admin;
	
	//设值注入需要的setter方法
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}
	
	public Administrator getAdmin() {
		return admin;
	}
	
	//管理员登录的方法
	public String adminLogin() throws Exception {
		
		ActionContext context = ActionContext.getContext();
		Map session = context.getSession();
		
		//验证用户名的有效性,如果登录成功返回Administrator，反之返回NULL;
		if (admin.getName() != null && admin.getPassword() != null)
		{
			Administrator isAdmin = this.adminService.adminLogin(admin);
			
			if (isAdmin == null)
			{
				context.put("AdminLoginInfo", "管理员用户名或密码错误！");
				return ERROR;
			}
			session.put("adminInfo", isAdmin);
			return SUCCESS;
		}
		session.put("AdminLoginInfo", "用户名或密码为能为空");
		return ERROR;
	}
	
	//修改管理员密码的方法 
	public String updatePass() throws Exception {
		
		ActionContext context = ActionContext.getContext();
		
		if (this.adminService.updatePass(admin.getPassword()))
		{
			context.put("updateInfo", "密码修改成功！");
			return SUCCESS;
		}else {
			context.put("updateInfo", "密码修改失败！");
			return ERROR;
		}
		
	}
	
}
