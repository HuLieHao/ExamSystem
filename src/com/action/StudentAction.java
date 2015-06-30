package com.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.model.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.StudentService;

/**
 * 处理学生信息的action
 * @author Administrator
 *
 */
public class StudentAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5022003025699435484L;

	private StudentService studentService;
	
	private Student st;
	
	//学生学号
	private String name;
		
	//学生密码
	private String password;
	
	//设值注入需要的setter方法
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public void setSt(Student st) {
		this.st = st;
	}
	
	public Student getSt() {
		return st;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//学生登录的action方法
	public String userLogin() throws Exception {
		
		if (this.studentService.checkLogin(name, password))
		{
			return SUCCESS;
		}
		
		return ERROR;
	}
	
	//查询全部的学生信息
	public String allStu() throws Exception {
		
		ActionContext context = ActionContext.getContext();
		
		List<Student> list = this.studentService.allStuInfo();
		if (list != null)
		{
			context.put("StuInfoList", list);
		}else {
			context.put("ErrorInfo", "没有查找到学生信息！");
		}
		
		return SUCCESS;
	}
	
	//添加学生信息
	public String addStu() throws Exception {
		
		ActionContext context = ActionContext.getContext();
		if (this.studentService.addStu(st))
		{
			context.put("addInfo", "添加学生成功！");
			return allStu();
		}else {
			context.put("addInfo", "添加学生失败！");
			return allStu();
		}
		
		
	}
	
	//修改学生信息
	public String updateInfo() throws Exception {
		ActionContext context = ActionContext.getContext();
		
		if (this.studentService.updateStu(st))
		{
			context.put("updateInfo", "修改学生信息成功！");
			return allStu();
		}else {
			context.put("updateInfo", "修改学生信息失败！");
			return allStu();
		}
	}
	
	//删除学生信息
	public String deleInfo() throws Exception {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		Object id = request.getParameter("id");
		
		if (this.studentService.deleStu(Integer.parseInt(id.toString())))
		{
			context.put("deleInfo", "删除学生信息成功！");
			return allStu();
		}else {
			context.put("deleInfo", "删除学生信息失败！");
			return allStu();
		}
	}
	
	//按学号或班级查询学生信息
	public String searchInfo() throws Exception {
		
		return SUCCESS;
	}
	
	//修改学生密码
	public String updatePass() throws Exception {
		
		ActionContext context = ActionContext.getContext();
		Student stu = (Student) context.getSession().get("user");
		
		if (this.studentService.updatePass(stu.getStudent_ID(), this.password))
		{
			context.put("updatePassInfo", "密码修改成功！");
			return SUCCESS;
		}else {
			context.put("updatePassInfo", "密码修改失败！");
			return ERROR;
		}
		
	}
	
}
