package com.model;


/**
 * 	考题的Bean类
 * 
 * @author 胡烈豪
 *
 */
public class Exam {

	//自动增长的主键
	private Integer id;
	
	//试题标题
	private String title;	
	
	//试题描述
	private String description;
	
	//试题内容
	private String content;
	
	//试题状态，0是未参加当前考试，1是参加当前考试
	private Integer flag;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
}
