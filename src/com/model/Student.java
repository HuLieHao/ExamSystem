package com.model;


/**
 * 学生信息对应的bean类
 * 
 * @author 胡烈豪
 *
 */
public class Student {
	
	//自动增长的主键
	private Integer id;
	
	//对应表中字段student_ID属性
	private String student_ID;
	
	//对应表中字段name属性
	private String name;			

	//对应表中字段sex属性
	private String sex;		
	
	//对应表中字段classes属性
	private String classes;	
	
	//对应表中字段professional属性
	private String professional;				

	//对应表中字段password属性,默认密码为："888888";
	private String password;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStudent_ID() {
		return student_ID;
	}

	public void setStudent_ID(String student_ID) {
		this.student_ID = student_ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
