package com.service;

public interface TakeExamService {

	/**
	 * 如果有当前有考试试题，则返回考试试题信息（时间，考题）。
	 * @return 如果当前有考试试题则返回true，否则返回false
	 */
	public Boolean allExamInfo();
	
	/**
	 * 判断考试时间是否已经到
	 * @return 考试时间判断
	 */
	public Boolean judgeTime();
	
	/**
	 * 如果考试时间已到，则按规则分配考题
	 * @return 分配考试试题
	 */
	public Boolean judgeQuestion();
	
	/**
	 * 根据学号和学生限制，判断是否允许参加考试
	 * @return true、false
	 */
	public Boolean isAllowTakeExam();
	/**
	 * 提交学生的答案
	 * @param content 提交的内容
	 * @return 提交成功返回true，否则false
	 */
	public Boolean uploadAnswer(String content);
}
