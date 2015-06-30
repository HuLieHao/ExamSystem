package com.service;

import java.util.List;

import com.model.Exam;

public interface ExamService {
	
	/**
	 * 返回全部的试题信息
	 * @return 成功返回全部的试题信息，否则返回null
	 */
	public List<Exam> allExam();
	
	/**
	 * 根据ID的值确实是修改试题还是添加试题
	 * @param exam
	 * @return 修改或添加成功返回true, 反之false;
	 */
	public Boolean addOrUpdateExam(String id, Exam exam);
	
	/**
	 * 根据ID值删除试题
	 * @param id
	 * @return 删除成功返回true,反之false;
	 */
	public Boolean delExam(String id);

	/**
	 * 把考试试题的ID添加到XML中，并在数据库标记为已考
	 * @param id 试题ID
	 * @return 添加成功返回true,反之false;
	 */
	public Boolean addExam(String id);
	
	/**
	 * 把试题从XML中取消掉，并取消掉数据库中的标记
	 * @param id 试题ID
	 * @return 取消成功返回true,反之false
	 */
	public Boolean cancelExam(String id);
	
	/**
	 * 根据ID值，查询指定的试题
	 * @param id
	 * @return Exam
	 */
	public Exam findExamID(Integer id);
}
