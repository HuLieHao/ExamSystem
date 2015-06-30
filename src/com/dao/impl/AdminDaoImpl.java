package com.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.AdminDao;
import com.model.Administrator;

/**
 * 实现管理员接口
 * @author 胡烈豪
 *
 */
public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao {
	
	private Session session = null;

	
	/**
	 * 验证用户名是否存在
	 * @param info
	 * @return boolean
	 */
	public boolean checkName(String name) {
		
		
		try{
			session = this.getSession();
			
			String sql = "FROM Administrator WHERE name=:Pname";
			
			List list = session.createQuery(sql).setParameter("Pname", name).list();
			
			if (list.size() > 0)
			{
				this.releaseSession(session);
				return true;
			}else {
				return false;
			}
			
		}catch (Exception e)
		{
			e.printStackTrace();
			
		}finally {
			
			this.releaseSession(session);
		}
		return false;
	}
	
	/**
	 * 验证密码是否正确
	 * @param info
	 * @return boolean
	 */
	public boolean checkPassword(String password) {
		
		try{
			session = this.getSession();
			
			String sql = "FROM Administrator WHERE password=:Ppassword";
			
			List list = session.createQuery(sql).setParameter("Ppassword", password).list();
			
			if (list.size() > 0)
			{
				this.releaseSession(session);
				return true;
			}else {
				return false;
			}
			
		}catch (Exception e)
		{
			e.printStackTrace();
		
		}finally {
			
			this.releaseSession(session);
		}
		return false;
	}

	/**
	 * 通过管理员姓名，查找该管理员的基本信息bean
	 */
	public Administrator findAdminByName(String name) {
		
		try{
			
			session = this.getSession();
			
			String sql = "FROM Administrator WHERE name=:Pname";
			
			List<Administrator> list = session.createQuery(sql).setParameter("Pname", name).list();
		
			if (list.size() > 0)
			{
				this.releaseSession(session);
				return list.get(0);
			}else {
				return null;
			}
			
		}catch (Exception e)
		{
			e.printStackTrace();
			
		}finally {
			this.releaseSession(session);
		}
		return null;
		
	}

	/**
	 * 通过管理员id，修改管理员的密码
	 */
	public boolean updateAdminInfo(Administrator info) {
		
		try{
			
			this.getHibernateTemplate().update(info);
			return true;
			
		}catch(Exception e) {
			
			e.printStackTrace();	
			return false;
		}
		
	}

	@Override
	public Administrator adminLogin(Administrator admin) {
		
		try {
			session = this.getSession();
			String sql = "FROM Administrator WHERE name=:Pname and password=:Ppassword";
			
			@SuppressWarnings("unchecked")
			List<Administrator> list = session.createQuery(sql)
										.setParameter("Pname", admin.getName())
										.setParameter("Ppassword", admin.getPassword())
										.list();
			
			if (list.size() > 0)
			{
				this.releaseSession(session);
				return list.get(0);
			}else {
				return null;
			}
			
		} catch(Exception e)
		{
			e.printStackTrace();
		} finally{
			
			this.releaseSession(session);
		}
		return null;
	}

}
