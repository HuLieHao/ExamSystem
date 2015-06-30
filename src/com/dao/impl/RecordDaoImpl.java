package com.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.RecordDao;
import com.model.Record;

public class RecordDaoImpl extends HibernateDaoSupport implements RecordDao {
	
	private Session session = null;
	
	@Override
	public Boolean isExists(String id) {
			
			this.session = this.getSession();
			
			String sql = "from Record where student_ID=:Pid";
			@SuppressWarnings("unchecked")
			List<Record>  list = this.session.createQuery(sql).setParameter("Pid", id).list();
			this.releaseSession(session);		
			if (list.size() > 0)
			{
				
				return true;
			}else {
				return false;
			}
	}

	@Override
	public Boolean setInfo(Record info) {
		
		this.getHibernateTemplate().save(info);
		return true;
	}

	@Override
	public Boolean updateIP(String id, String ip) {
		
		this.session = this.getSession();
		
		String sql = "UPDATE Record SET ip=:Pip WHERE student_ID=:Pid";
		
		int n = this.session.createQuery(sql).setParameter("Pip", ip).setParameter("Pid", id).executeUpdate();
		this.releaseSession(session);
		
		if (n > 0)
		{
			return true;
		}else {
			return false;
		}
	}

	@Override
	public String getIP(String id) {
		
		this.session = this.getSession();
		
		String sql = "from Record where student_ID=:Pid";
		
		@SuppressWarnings("unchecked")
		List<Record>  list = this.session.createQuery(sql).setParameter("Pid", id).list();
		
		String ip = null;
		if (list.size() > 0)
		{
			ip = list.get(0).getIp();
		}
		this.releaseSession(session);
		return ip;
	}

	@Override
	public Boolean getFlag(String id) {

		this.session = this.getSession();
		String sql = "from Record where student_ID=:Pid";
		@SuppressWarnings("unchecked")
		List<Record>  list = this.session.createQuery(sql).setParameter("Pid", id).list();
		this.releaseSession(session);
		//当列表不为空，且flag标志为0时返回true，代表未参加过考试
		if (list.size() > 0 && list.get(0).getFlag().equals("0"))
		{
			return false;
		}else {
			return true;
		}
	}

	@Override
	public Boolean uploateFlag(String id) {
		
		this.session = this.getSession();
		String sql = "UPDATE Record SET flag=1 WHERE student_ID=:Pid";
		
		int n = this.session.createQuery(sql).setParameter("Pid", id).executeUpdate();
		
		this.releaseSession(session);
		if (n > 0)
		{
			return true;
		}else {
			return false;
		}
	}

}
