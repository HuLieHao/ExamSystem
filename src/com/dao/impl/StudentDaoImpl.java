package com.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.StudentDao;
import com.model.Administrator;
import com.model.Student;
import com.page.Pager;

public class StudentDaoImpl extends HibernateDaoSupport implements StudentDao {
	
	private Session session = null;

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> allStuinfo(Pager pager) {
		
		List<Student> list = null;
		
		try{
			session = this.getSession();
			String sql = "FROM Student order by student_ID";
			
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
		}finally {
			this.releaseSession(session);
		}
		
		return list;
	}

	@Override
	public List<Student> downloadStuInfo() {
		
		return null;
	}

	@Override
	public int getTotalRows() {
		
		int rows = 0;
		
		try{
			this.session = this.getSession();
			
			String sql = "FROM Student";
			
			rows = session.createQuery(sql).list().size();
				
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.releaseSession(session);
		}
		return rows;
	}

	@Override
	public int getCountBySearch(Integer searchType, String searchValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Student> queryBySearch(Integer searchType, String searchValue,
			Pager pager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCountByChoice(String choiceValue_1, String choiceValue_2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Student> queryByChoice(String choiceValue_1,
			String choiceValue_2, Pager pager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean DeleStu(Student id) {
		
		try{
			this.getHibernateTemplate().delete(id);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Student findUserById(Integer id) {
		
	try{
			
			return this.getHibernateTemplate().get(Student.class, id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public Boolean addStu(Student student) {
		
	try{
			this.getHibernateTemplate().save(student);
			return true;
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean updateStu(Student student) {
		
	try{
			
			this.getHibernateTemplate().update(student);
			return true;
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Student finUserByStuID(String studentID) {
		return null;
	}

	@Override
	public Boolean updateUserPass(String studentID, String newPass) {
		
		session = this.getSession();
		String sql = "UPDATE Student SET password=:Pass WHERE student_ID=:stuid";
		if (session.createQuery(sql).setParameter("Pass", newPass).setParameter("stuid", studentID).executeUpdate() > 0)
		{
			this.releaseSession(session);
			return true;
		}
		
		this.releaseSession(session);
		return false;
	}

	@Override
	public Student checkLogin(String name, String password) {
		try {
			session = this.getSession();
			String sql = "FROM Student WHERE student_ID=:Pname and password=:Ppassword";
			
			@SuppressWarnings("unchecked")
			List<Student> list = session.createQuery(sql)
										.setParameter("Pname", name)
										.setParameter("Ppassword", password)
										.list();
			this.releaseSession(session);
			if (list.size() > 0)
			{
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

	@Override
	public List getCountByClasses() {
		try {
			session = this.getSession();
			String sql = "select classes from Student group by classes";
			
			@SuppressWarnings("unchecked")
			List<Student> list = session.createQuery(sql)
										.list();
			this.releaseSession(session);
			if (list.size() > 0)
			{
				return list;
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

	@Override
	public List getCountByProfessional() {
		try {
			session = this.getSession();
			String sql = "select professional from Student group by professional";
			
			@SuppressWarnings("unchecked")
			List<Student> list = session.createQuery(sql)
										.list();
			this.releaseSession(session);
			if (list.size() > 0)
			{
				return list;
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
