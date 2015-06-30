package com.service;

import com.model.Administrator;

/**
 * AdminService接口
 * @author 胡烈豪
 *
 */
public interface AdminService {
	
	/**
	 * 管理员的登录检查
	 * @param admin
	 * @return 如果登录成功返回Administrator对象，反之返回NULL
	 */
	public Administrator adminLogin(Administrator admin);
	
	/**
	 * 管理员密码修改
	 * @param admin
	 * @return 如果修改成功返回true,反之返回false;
	 */
	public Boolean updatePass(String password);

}
