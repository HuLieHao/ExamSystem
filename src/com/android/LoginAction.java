package com.android;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.model.Administrator;
import com.opensymphony.xwork2.ActionSupport;
import com.service.AdminService;
import com.service.RulesService;

/**
 * android服务端登录
 * @author 胡烈豪
 *
 */
public class LoginAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{

	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private String name;
	private String password;
	
	private String startTime;
	private String endTime;
	
	private String dir;
	
	private String startIP;
	private String endIP;
	
	private AdminService adminService;
	private RulesService rulesService;
	//设值注入需要的setter方法
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	public void setRulesService(RulesService rulesService) {
		this.rulesService = rulesService;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	
	public String getStartIP() {
		return startIP;
	}
	public void setStartIP(String startIP) {
		this.startIP = startIP;
	}
	public String getEndIP() {
		return endIP;
	}
	public void setEndIP(String endIP) {
		this.endIP = endIP;
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		
		this.response = arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		
		this.request = arg0;
	}
	
	
	public String adminlogin() throws Exception
	{
		this.response.setContentType("text/html;charset=utf-8");
		this.response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		
		Administrator admin = new Administrator();
		
		admin.setName(this.name);
		admin.setPassword(this.password);
		Administrator isadmin = this.adminService.adminLogin(admin);
		if (isadmin == null)
		{
			json.put("loginInfo", "0");
		}else {
			System.out.println(this.name + "用户   通过android客户端登录！");
			json.put("loginInfo", "1");
		}
		
		this.response.getWriter().write(json.toString());
		
		return SUCCESS;
	}
	
	public String setTimes() throws Exception {
		
		this.response.setContentType("text/html;charset=utf-8");
		this.response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		
		this.rulesService.clearTime();
		if (this.rulesService.setTimes(1, this.startTime, this.endTime, "00:30:00"))
		{
			json.put("timeInfo", "1");
		}else {
			json.put("timeInfo", "0");
		}
		this.response.getWriter().write(json.toString());
		return SUCCESS;
	}
	
	public String setDir() throws Exception {
		
		this.response.setContentType("text/html;charset=utf-8");
		this.response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		
		if (this.rulesService.setExamDirectory(dir))
		{
			json.put("dirInfo", "1");
		}else {
			json.put("dirInfo", "0");
		}
		this.response.getWriter().write(json.toString());
		return SUCCESS;
	}
	
	public String setIPS() throws Exception {
		
		this.response.setContentType("text/html;charset=utf-8");
		this.response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		
		List<String> startlist = new ArrayList<String>();
		startlist.add(startIP);
		List<String> endlist = new ArrayList<String>();
		endlist.add(endIP);
		List<String> iplist = new ArrayList<String>();
		
		if (this.rulesService.setIP(startlist, endlist, iplist))
		{
			json.put("IPInfo", "1");
		}else {
			json.put("IPInfo", "0");
		}
		this.response.getWriter().write(json.toString());
		return SUCCESS;
	}

}
