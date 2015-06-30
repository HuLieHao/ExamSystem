package com.model;



/**
 *  管理员表对应的Bean类，
 *  
 * @author 胡烈豪
 *
 */

public class Administrator{
	
	//自动增长的主键
	private Integer id;
	
	//对应表中字段的name属性
	private String name;
	
	//对应表中字段的password属性
	private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
}
