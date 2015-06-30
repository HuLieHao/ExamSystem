package com.dao;

import com.model.Administrator;

/**
 * 管理员登录接口
 * @author 胡烈豪
 *
 */
public interface AdminDao{


	/**
	 * 验证用户名是否存在
	 * @param info
	 * @return boolean
	 */
	public boolean checkName(String  name);
	
	/**
	 * 验证密码是否正确
	 * @param info
	 * @return boolean
	 */
	public boolean checkPassword(String password);
	
	
	/**
	 * 根据管理员姓名，查找实体bean类
	 * @param name
	 * @return
	 */
	public Administrator findAdminByName(String name);
	
	/**
	 * 修改管理员的基本信息
	 */
	public boolean updateAdminInfo(Administrator info);
	
	/**
	 * 管理员的登录检查
	 * @param admin
	 * @return 如果登录成功返回Administrator对象，反之返回NULL
	 */
	public Administrator adminLogin(Administrator admin);
}
