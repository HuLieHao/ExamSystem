package com.service;

import java.util.List;

import com.model.Student;

public interface StudentService {
	
	/**
	 * 验证学生登录是否正确
	 * @param name 学号
	 * @param password	密码
	 * @return 成功返回true，反之返回false;
	 */
	public Boolean checkLogin(String name, String password);
	
	/**
	 * 返回全部的学生信息
	 * @return 成功返回全部的学生，反之返回null;
	 */
	public List<Student> allStuInfo();
	
	/**
	 * 添加学生信息
	 * @param student
	 * @return 成功返回true,否则返回false;
	 */
	public Boolean addStu(Student student);
	
	/**
	 * 修改学生信息
	 * @param student
	 * @return 成功返回true,否则返回false;
	 */
	public Boolean updateStu(Student student);
	
	/**
	 * 删除学生信息
	 * @param id
	 * @return 成功返回true,否则返回false;
	 */
	public Boolean deleStu(Integer id);
	
	/**
	 * 修改学生的密码
	 * @param id	学生学号
	 * @param newPass 新密码
	 * @return 修改成功返回true, 否则返回false;
	 */
	public Boolean updatePass(String id, String newPass);

}
