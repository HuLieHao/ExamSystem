package com.model;

/**
 * 	配置信息的Bean类
 * 
 * 	"配置"表的字段：
 * 				1:考试开始时间
 * 				2:考试结束时间
 * 				3:时间限制
 * 				4:作业上传目录
 * 				5:考题选择规则
 * 				6:要考试的考题ID号
 * 				7:允许考试的单个IP
 * 				8:允许考试的IP段
 * @author 胡烈豪
 *
 */

public class Config {
	
	private Integer id;  		//id号 唯一标识某一列
	
	private String startTime;	//考试开始时间
	
	private String endTime;		//考试结束时间
	
	private String lessTime;	//时间限制
	
	private String upload;		//上传目录
	
	private String testRules;		//考题选择规则
	
	private String testID;		//要考试的考题ID号
	
	private String singlIP;		//允许考试的单个IP
	
	private String segmentIP;	//允许考试的IP段
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getLessTime() {
		return lessTime;
	}

	public void setLessTime(String lessTime) {
		this.lessTime = lessTime;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public String getTestRules() {
		return testRules;
	}

	public void setTestRules(String testRules) {
		this.testRules = testRules;
	}

	public String getTestID() {
		return testID;
	}

	public void setTestID(String testID) {
		this.testID = testID;
	}

	public String getSinglIP() {
		return singlIP;
	}

	public void setSinglIP(String singlIP) {
		this.singlIP = singlIP;
	}

	public String getSegmentIP() {
		return segmentIP;
	}

	public void setSegmentIP(String segmentIP) {
		this.segmentIP = segmentIP;
	}
	
}
