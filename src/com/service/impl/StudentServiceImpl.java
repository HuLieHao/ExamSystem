package com.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dao.RecordDao;
import com.dao.StudentDao;
import com.model.Record;
import com.model.Student;
import com.opensymphony.xwork2.ActionContext;
import com.page.Pager;
import com.page.PagerHelper;
import com.service.StudentService;
import com.util.FilterIP;

public class StudentServiceImpl implements StudentService {
	
	private StudentDao studentDao;
	
	private RecordDao record;
	
	private FilterIP filterIP;
	
	////设值注入需要的setter方法
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	//设值注入需要的setter方法
	public void setRecord(RecordDao record) {
		this.record = record;
	}
	//设值注入需要的setter方法
	public void setFilterIP(FilterIP filterIP) {
		this.filterIP = filterIP;
	}
	
	@Override
	public List<Student> allStuInfo() {
		
		/**
		 * 分页的一些信息
		 */
		int totalRows = 0;
		Pager pager = null; 
		int pageSize = 10;
		HttpServletRequest request = ServletActionContext.getRequest();
		
		totalRows = studentDao.getTotalRows();
		pager = PagerHelper.getPager(request, totalRows, pageSize); 
		pager.setLinkUrl("AllStuInfo.do?"); 
		request.setAttribute("pb", pager);
		List<Student> userlist = studentDao.allStuinfo(pager);
		ActionContext ctx = ActionContext.getContext();
		ctx.getSession().put("url","AllStuInfo.do?");
		
		return userlist;
	}

	@Override
	public Boolean addStu(Student student) {
		
		return this.studentDao.addStu(student);
	}

	@Override
	public Boolean updateStu(Student student) {
		
		Student stu = this.studentDao.findUserById(student.getId());
		stu.setName(student.getName());
		stu.setClasses(student.getClasses());
		stu.setProfessional(student.getProfessional());
		stu.setSex(student.getSex());
		stu.setStudent_ID(student.getStudent_ID());
		return this.studentDao.updateStu(stu);
	}
	
	@Override
	public Boolean deleStu(Integer id) {
		
		Student stu = this.studentDao.findUserById(id);
		return this.studentDao.DeleStu(stu);
	}



	@Override
	public Boolean checkLogin(String name, String password) {
		
		ActionContext context = ActionContext.getContext();
		Map map = context.getSession();
		
		String recordIP = this.record.getIP(name);  //从记录表中取出用户的IP，如果没有则为null;
		String userIP = this.filterIP.getUserIP();  //获取用户电脑的IP

		//验证是否已经在其它电脑上登录过
		if ( recordIP != null && !userIP.equals(recordIP))
		{
			context.put("loginInfo", "你已经在其它电脑上登录过，不能再次登录！");
			return false;
		}
		
		Student student = this.studentDao.checkLogin(name, password);
		
		//如果验证通过
		if (student != null)
		{
			System.out.println("===== " + student.getStudent_ID() + student.getName() + " 已登录！=====");
			map.put("user", student);
		}else {
			context.put("loginInfo", "学号或密码不正确！");
			return false;
		}
		
		//如果recordIP等于null,则插入这个数据
		if (recordIP == null)
		{
			//如果recordIP为NULL，则把IP记录到数据库中，防止在其它电脑上登录。
			Record recordInfo = new Record();
			recordInfo.setStudent_ID(student.getStudent_ID());
			recordInfo.setIp(userIP);
			recordInfo.setLoginTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			recordInfo.setExitTime("");
			//刚插入一条记录时，flag为0，还未参加考试
			recordInfo.setFlag("0");
			
			//插入数据
			this.record.setInfo(recordInfo);
		}
		
		
		return true;
	}



	@Override
	public Boolean updatePass(String id, String newPass) {
		
		return this.studentDao.updateUserPass(id, newPass);
	}

}
