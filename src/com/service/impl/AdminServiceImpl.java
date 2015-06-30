package com.service.impl;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.dao.AdminDao;
import com.model.Administrator;
import com.service.AdminService;

public class AdminServiceImpl implements AdminService {
	
	private AdminDao adminDao;
	
	//设值注入需要的setter方法
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public Administrator adminLogin(Administrator admin) {
		
		return adminDao.adminLogin(admin);
	}

	@Override
	public Boolean updatePass(String password) {
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		Administrator admin = (Administrator) session.getAttribute("adminInfo");
		
		admin.setPassword(password);
		
		return this.adminDao.updateAdminInfo(admin);
	}

}
