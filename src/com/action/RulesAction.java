package com.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.RulesService;

/**
 * 处理考试规则的action
 * @author 胡烈豪
 *
 */
public class RulesAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2643492838172949596L;

	private RulesService rulesService;
	
	//第几场考试时间
	private Integer timeId;
	
	// 开始考试时间
	private String startTime;
	
	// 结束考试时间
	private String endTime;
		
	//考试时间限制
	private String lessTime;
		
	//允许考试IP段的开始
	private List<String> startIPS;
		
	//允许考试IP段的结束
	private List<String> endIPS;
		
	//允许考试的单个IP
	private List<String> oneIP;
		
	//设置考试答案提交的目录
	private String directory;
		
	//考题选择学号与ip的运算符
	private String OperID;
		
	//考题选择ip与题数的运算符
	private String OperIP;
		
	private String OperNum;	
	
	//第几场考试学生限制
	private Integer allowID;
	
	//考试学生限制的专业
	private String professional;
	
	//考试学生限制的班级
	private String classes;
	
	//设值注入需要的setter方法
	public void setRulesService(RulesService rulesService) {
		this.rulesService = rulesService;
	}
	
	public Integer getTimeId() {
		return timeId;
	}
	
	public void setTimeId(Integer timeId) {
		this.timeId = timeId;
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


	public List<String> getStartIPS() {
		return startIPS;
	}


	public void setStartIPS(List<String> startIPS) {
		this.startIPS = startIPS;
	}


	public List<String> getEndIPS() {
		return endIPS;
	}


	public void setEndIPS(List<String> endIPS) {
		this.endIPS = endIPS;
	}


	public List<String> getOneIP() {
		return oneIP;
	}


	public void setOneIP(List<String> oneIP) {
		this.oneIP = oneIP;
	}


	public String getDirectory() {
		return directory;
	}


	public void setDirectory(String directory) {
		this.directory = directory;
	}


	public String getOperID() {
		return OperID;
	}


	public void setOperID(String operID) {
		OperID = operID;
	}


	public String getOperIP() {
		return OperIP;
	}


	public void setOperIP(String operIP) {
		OperIP = operIP;
	}


	public String getOperNum() {
		return OperNum;
	}


	public void setOperNum(String operNum) {
		OperNum = operNum;
	}
	
	public void setClasses(String classes) {
		this.classes = classes;
	}
	
	public String getClasses() {
		return classes;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}
	
	public String getProfessional() {
		return professional;
	}
	
	public Integer getAllowID() {
		return allowID;
	}

	public void setAllowID(Integer allowID) {
		this.allowID = allowID;
	}

	//进入考试规则页面的一些操作
	public String preRuels() throws Exception
	{
		ActionContext context = ActionContext.getContext();
		
		/*
		 *进入考试规则页面前的考试，总共有几场考试 
		 */
		
		int testID = this.rulesService.getTimesID();
		int allows = this.rulesService.getAllowID();
		//如果没有设置过考试时间，则从1开始
		if (testID == 0)
		{
			context.put("testID", 1);
		}else {
		
			context.put("testID", testID + 1);
			
			//如果没有设置过考试学生限制，则从1开始
			if (allows == 0)
			{
				context.put("allowID", 1);
			}else if (allows + 1 <= testID) {
				context.put("allowID", allows + 1);
			} else {
				context.put("allowID", "-1");
			}

		}
		
				
		List classesList = this.rulesService.getCountByClasses();
		List professionalList = this.rulesService.getCountByProfessional();
		
		if (classesList != null && professionalList != null)
		{
			context.put("classesList", classesList);
			context.put("professionalList", professionalList);
		}
		
		return SUCCESS;
	}
	
	//设置考试时间
	public String setTimes() throws Exception
	{
		ActionContext context = ActionContext.getContext();
		if (this.rulesService.setTimes(timeId, startTime, endTime, lessTime))
		{
			context.put("timeInfo", "考试时间设置成功！");
			return SUCCESS;
		}
		context.put("timeInfo", "考试时间设置出错！");
		return SUCCESS;
	}
	
	//清除考试时间
	public String clearTime() throws Exception
	{
		ActionContext context = ActionContext.getContext();
		if (this.rulesService.clearTime() == true)
		{
			context.put("clearTime", "考试时间已重置！");
		}else {
			context.put("clearTime", "清除考试时间失败！");
		}
		return SUCCESS;
	}
	
	public String setAllowID() throws Exception
	{
		ActionContext context = ActionContext.getContext();
		if (this.rulesService.setAllowID(allowID, classes, professional))
		{
			context.put("allowIDInfo", "学生限制考试设置成功！");
		}else {
			context.put("allowIDInfo", "学生限制考试设置失败！");
		}
		return SUCCESS;
	}
	
	public String clearAllowID() throws Exception 
	{
		ActionContext context = ActionContext.getContext();
		if (this.rulesService.clearAllowID() == true)
		{
			context.put("clearAllowIDInfo", "考试时间已重置！");
		}else {
			context.put("clearAllowIDInfo", "清除考试时间失败！");
		}
		return SUCCESS;
	}
	
	//设置考题选择规则
	public String setChoiceQuestions() throws Exception
	{
		ActionContext context = ActionContext.getContext();
		if (this.rulesService.setChoiceQuestions(this.OperID + " " + this.OperIP + " " + this.OperNum))
		{
			context.put("rulesInfo", "考试时间设置成功！");
			return SUCCESS;
		}
		context.put("rulesInfo", "考试时间设置出错！");
		return SUCCESS;
		
	}
	
	//设置允许考试的IP
	public String setIP() throws Exception
	{
		ActionContext context = ActionContext.getContext();
		if (this.rulesService.setIP(this.startIPS, this.endIPS, this.oneIP))
		{
			context.put("ipInfo", "考试时间设置成功！");
			return SUCCESS;
		}
		context.put("ipInfo", "考试时间设置出错！");
		return SUCCESS;
	}
	
	//设置作业上传目录
	public String setExamDirectory() throws Exception
	{
		
		ActionContext context = ActionContext.getContext();
		if (this.rulesService.setExamDirectory(this.getDirectory()))
		{
			context.put("dirInfo", "作业上传目录设置成功！");
			return SUCCESS;
		}
		context.put("dirInfo", "作业上传目录设置出错！");
		return SUCCESS;
	}
}
