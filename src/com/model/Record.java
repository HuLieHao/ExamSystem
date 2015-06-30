package com.model;

/**
 * 考试记录信息表
 * @author 胡烈豪
 *
 */
public class Record {
	
	//id字段
	private Integer id;
	
	//学号字段
	private String student_ID;
	
	//ip记录
	private String ip;
	
	//登录时间
	private String loginTime;
	
	//退出时间
	private String exitTime;
	
	//是否参加考试标志：0 未参加考试 	1已参加考试
	private String flag;
	
	
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getExitTime() {
		return exitTime;
	}

	public void setExitTime(String exitTime) {
		this.exitTime = exitTime;
	}
	
	

}
