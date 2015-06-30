package com.dao;

import java.util.List;

public interface RulesDao {
	
	/**
	 * 获取总共有几场考试
	 * @return 次数
	 */
	public Integer getTimesID();
	
	/**
	 * 清除考试时间
	 * @return true,false
	 */
	public Boolean clearTime();
	
	/**
	 * 设置考试时间
	 * @param starttime 开始时间
	 * @param endTime 结束时间
	 * @param lessTime 限制时间
	 * @return 设置成功返回true,反之返回false
	 */
	public Boolean setTimes(Integer timeID, String starttime, String endTime, String lessTime);
	
	/**
	 * 获得总共设置了几场考试学生限制
	 * @return 次数
	 */
	public Integer getAllowID();
	
	/**
	 * 清除考试学生限制
	 * @return true,false
	 */
	public Boolean clearAllowID();
	
	/**
	 * 设置考试学生限制
	 * @param times 次数
	 * @param classes 班级
	 * @param professional 专业
	 * @return 设置成功返回true,反之返回false
	 */
	public Boolean setAllowID(Integer times, String classes, String professional);
	
	/**
	 * 设置考题分配规则
	 * @param rules  分配规则
	 * @return 设置成功返回true，反之返回false
	 */
	public Boolean setChoiceQuestions(String rules);
	
	/**
	 * 设置作业上传目录
	 * @param dir 目录
	 * @return 设置成功返回true，反之返回false;
	 */
	public Boolean setExamDirectory(String dir);
	
	/**
	 * 设置允许考试的IP段和单个IP
	 * @param startIP	开始IP段
	 * @param endIP		结束IP段
	 * @param oneIP		单个IP
	 * @return 设置成功返回true,反之返回false;
	 */
	public Boolean setIP(List<String> startIP, List<String> endIP, List<String> oneIP);


	
}
