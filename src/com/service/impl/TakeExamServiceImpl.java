package com.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dao.ExamDao;
import com.dao.RecordDao;
import com.model.Exam;
import com.model.Student;
import com.opensymphony.xwork2.ActionContext;
import com.service.TakeExamService;
import com.util.Cache;
import com.util.ConfigUploadFile;
import com.util.FilterIP;

public class TakeExamServiceImpl implements TakeExamService{
	
	private Cache cache;
	private RecordDao record;
	private FilterIP filterIP;
	private ExamDao examDao;
	private ConfigUploadFile uploadFile;
	
	//设值注入需要的setter方法
	public void setCache(Cache cache) {
		this.cache = cache;
	}
	
	//设值注入需要的setter方法
	public void setRecord(RecordDao record) {
		this.record = record;
	}
	
	//设值注入需要的setter方法
	public void setFilterIP(FilterIP filterIP) {
		this.filterIP = filterIP;
	}
	//设值注入需要的setter方法
	public void setExamDao(ExamDao examDao) {
		this.examDao = examDao;
	}
	
	//设值注入需要的setter方法
	public void setUploadFile(ConfigUploadFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	@Override
	public Boolean allExamInfo()
	{
		
		ActionContext context = ActionContext.getContext();
		
		Map timeMap = getNowTestTime();
		
		//如果考试时间不为空
		if (timeMap != null)
		{
			String startTime = (String)timeMap.get("startTime");
			if (!startTime.equals(""))
			{
				context.put("startTime", startTime);
			}else {
				context.put("startTime", "错误！");
			}
			
			String endTime = (String)timeMap.get("endTime");
			if (!endTime.equals(""))
			{
				context.put("endTime", endTime);
			}else {
				context.put("endTime", "错误！");
			}
			
			String lessTime = (String)timeMap.get("lessTime");
			if (!lessTime.equals(""))
			{
				context.put("lessTime", lessTime);
			}else {
				context.put("lessTime", "错误！");
			}

		}else {
			context.put("testInfoError", "当前没有试题试题!");
		}
		
		
		List<Map<String, String>> testlist = (List<Map<String, String>>)cache.get("testID");
		if (testlist.size() > 0)
		{
			context.put("testIDlist", testlist);
		}else {
			context.put("testInfoError", "当前没有试题试题!");
			return false;
		}
		
		return true;
	}
	
	public Map getNowTestTime()
	{
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			Date nowDate = format.parse(format.format(new Date()));
		
			//获取所有的考试时间
			List<Map<String, String>> timelist = (List<Map<String, String>>)this.cache.get("testTime");
			
			for (int i = 0; i < timelist.size(); i++)
			{
				Map<String, String> timeMap = timelist.get(i);
				
				Long endTime = format.parse((String)timeMap.get("endTime")).getTime() / 1000;
				
				//获取的每个考试时间段的结束时间与当前时间判断，如果大于当前时间，则把这场考试时间设为有效时间。
				if (endTime > (nowDate.getTime() / 1000))
				{
					return timeMap;
				}	
			}
			
		} catch (ParseException e) {
					
			e.printStackTrace();
		} 
		
		return null;
				
	}
	
	@Override
	public Boolean judgeTime() {
		
		Map timeMap = getNowTestTime();
		
		if (timeMap != null)
		{
			//处理格式化时间时的异常
			try {
				//格式化时间
				DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						
				String startTime = (String)timeMap.get("startTime"); //考试开始时间
				String endTime = (String)timeMap.get("endTime");	//考试结束时间
				String lessTime = (String)timeMap.get("lessTime");	//考试限制时间
						
				Date time1 = format.parse(startTime);
				Date time2 = format.parse(endTime);
				
				//获取系统时间
				Date now = new Date();
				Date time4 = format.parse(format.format(now));
				
				
				long n1 = time1.getTime() / 1000; //考试开始时间
				long n2 = time2.getTime() / 1000; //考试结束时间
				long n4 = time4.getTime() / 1000; //当前系统时间
				
				String[] arr = lessTime.split(":");
				long total = Integer.parseInt(arr[0]) * 60 * 60 + Integer.parseInt(arr[1]) * 60 + Integer.parseInt(arr[2]);
				
				ActionContext context = ActionContext.getContext();
				Map map = context.getSession();
				Student stu = (Student) map.get("user");
				//查询是否已经参加过考试
				Boolean flag = this.record.getFlag(stu.getStudent_ID());
				
				if (n4 <= n1)
				{
					context.put("testInfoError", "考试时间未到！");
					return false;
				}
				else if (n4 >= n2)
				{
					context.put("testInfoError", "考试时间已过！");
					return false;
				}
				else if (total <= (n4 - n1))
				{
					context.put("testInfoError", "未在规定的考试时间内参加考试！！");
					return false;
				}else if (flag)
				{
					context.put("testInfoError", "你已经参加过考试！！");
					return false;
				}
				else {
					//考试时间倒计时
					context.put("timeStamp",n2 + "");
					return true;
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return false;
	}

	@Override
	public Boolean judgeQuestion() {
		
		ActionContext context = ActionContext.getContext();
		Map map = context.getSession();
		Student info = (Student)map.get("user");
			
		String ipStr = this.filterIP.getUserIP();
		char ipChar = ipStr.charAt(ipStr.length()-1);
		//IP地址最后一位
		int ipEnd = Integer.parseInt(ipChar + "");
		
		//返回考试试题的集合 
		List<Map<String, String>> testList = (List<Map<String, String>>) this.cache.get("testID");
		//得到参加考试试题的题数
		int testNum = testList.size();
		
		
		//得到当前的考试时间
		Map nowMap = getNowTestTime();
		//获得当前考试的场次
		int testID = Integer.parseInt((String)nowMap.get("id"));
		
		System.out.println("IP地址最后一位：" + ipEnd);
		System.out.println("考试试题的题数：" + testNum);
		System.out.println("当前的考试场次：" + testID);
		
		int quesID = (ipEnd + testID) % testNum;
		
		System.out.println("考生得到的试题ID" + quesID);
		
		//当学生提交答案时，可以知道学生考的题号
		map.put("quesID", quesID+1);
		Map<String, String> questinoMap = (Map<String, String>)testList.get(quesID);
		Exam exam = this.examDao.findExamID(Integer.parseInt((String)questinoMap.get("id")));
		context.put("id", exam.getId());   //往考试页面传递考试id
		context.put("info", exam);		
		
//		int idEnd = Integer.parseInt(info.getStudent_ID().charAt(10) + "");
//		int m = 0;
//		
//		String type[] = ((String) this.cache.get("testRules")).split(" ");
//		int operNum = Integer.parseInt(type[2]);
//		//返回考试试题的集合
//		List questionID = (List) this.cache.get("testID");
//		//分别选择不同的运算符时，对应不同的算法
//		if (type[0].equals("+"))
//		{
//			switch (type[1]) {
//			case "+":
//				m = idEnd + ipEnd + questionID.size() + operNum;
//				break;
//			case "-":
//				m = idEnd + ipEnd - questionID.size() + operNum;
//				break;
//			case "*":
//				m = idEnd + ipEnd * questionID.size() + operNum;
//				break;
//			default:
//				m = idEnd + ipEnd / questionID.size() + operNum;
//				break;
//			}
//			
//		}else if (type[0].equals("-"))
//		{
//			switch (type[1]) {
//			case "+":
//				m = idEnd - ipEnd + questionID.size() + operNum;
//				break;
//			case "-":
//				m = idEnd - ipEnd - questionID.size() + operNum;
//				break;
//			case "*":
//				m = idEnd - ipEnd * questionID.size() + operNum;
//				break;
//			default:
//				m = idEnd - ipEnd / questionID.size() + operNum;
//				break;
//			}
//		}else if (type[0].equals("*"))
//		{
//			switch (type[1]) {
//			case "+":
//				m = idEnd * ipEnd + questionID.size() + operNum;
//				break;
//			case "-":
//				m = idEnd * ipEnd - questionID.size() + operNum;
//				break;
//			case "*":
//				m = idEnd * ipEnd * questionID.size() + operNum;
//				break;
//			default:
//				m = idEnd * ipEnd / questionID.size() + operNum;
//				break;
//			}
//		}else {
//			switch (type[1]) {
//			case "+":
//				m = idEnd / ipEnd + questionID.size() + operNum;
//				break;
//			case "-":
//				m = idEnd / ipEnd - questionID.size() + operNum;
//				break;
//			case "*":
//				m = idEnd / ipEnd * questionID.size() + operNum;
//				break;
//			default:
//				m = idEnd / ipEnd / questionID.size() + operNum;
//				break;
//			}
//		}
//		
//		//取余得到选择的考题
//		if (m < 0){
//			m = -m;
//		}
//		m = m % questionID.size();
		
		
		return true;
	}

	@Override
	public Boolean isAllowTakeExam()
	{
		ActionContext context = ActionContext.getContext();
		Map map = context.getSession();
		
		Student info = (Student)map.get("user");
		//获取学生限制列表
		List<Map<String, String>> allowList = (List<Map<String, String>>)this.cache.get("allowID");
		//得到当前的考试时间
		Map nowMap = getNowTestTime();
		
		int n = Integer.parseInt((String)nowMap.get("id")) - 1;
		System.out.println("allowList" + allowList.size() + " " + n);
		if (allowList.size() > n)
		{
			
			//根据当前考试时间的ID来获取学生限制表中的信息
			Map mapAllow = allowList.get(n);
			
			//如果学生限制考试列表中的信息与当前学生的信息一致则是允许参加考试
			if (((String)mapAllow.get("professional")).equals(info.getProfessional()) && ((String)mapAllow.get("classes")).equals(info.getClasses()))
			{
				return true;
			}
		}
		context.put("testInfoError", "在本场考试中你没有资格参加此次考试！");
		return false;
	}
	
	@Override
	public Boolean uploadAnswer(String content) {
		
		ActionContext context = ActionContext.getContext();
		Map map = context.getSession();
		
		Student info = (Student)map.get("user"); //获取学生的实体bean
		Integer id = (Integer)map.get("quesID");  //获取学生考试试题的题号
		
		//设置ConfigUploadFile的参数
		this.uploadFile.content = content;
		this.uploadFile.title = info.getStudent_ID() +  info.getName() + id + ".java";
		this.uploadFile.directory = (String)this.cache.get("upload");
		//启动线程
		this.uploadFile.run();
		
		//把学生已经参加考试的flag记录到数据库中 flag = 1;
		this.record.uploateFlag(info.getStudent_ID());
		
		return true;
	}

}
