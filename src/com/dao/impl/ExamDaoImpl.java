package com.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.ExamDao;
import com.model.Exam;
import com.page.Pager;

public class ExamDaoImpl extends HibernateDaoSupport implements ExamDao {
	
	private Session session = null;

	@Override
	public Boolean addExam(Exam exam) {
		try {
			this.getHibernateTemplate().save(exam);
			return true;
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public Boolean updateExam(Exam exam) {
		try {
			this.getHibernateTemplate().update(exam);;
			return true;
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean deleteExam(Exam exam) {
		try {
			this.getHibernateTemplate().delete(exam);
			return true;
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Exam> searchExam(String title, Pager pager) {
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exam> AllExam(Pager pager) {
		
		List<Exam> list = null;
		try{
			session = this.getSession();
			String sql = "FROM Exam";
			
			list = session.createQuery(sql)
									.setFirstResult(pager.getStartRow())
									.setMaxResults(pager.getPageSize())
									.list();
			
			//当没有数据时
			if (list.size() <= 0)
				return null;
			
			//当最后一页的数据不足页面大小时 添加Null
			while (list.size() < pager.getPageSize())
			{
				list.add(null);
			}
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}finally{
			this.releaseSession(session);
		}
		return list;
	}

	@Override
	public Integer getTotalRows() {
		
		int rows = 0;
		
		try{
			this.session = this.getSession();
			
			String sql = "FROM Exam";
			
			rows = session.createQuery(sql).list().size();
				
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.releaseSession(session);
		}
		return rows;
	}

	@Override
	public Exam findExamID(Integer id) {
		
		try{
			return this.getHibernateTemplate().get(Exam.class, id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
