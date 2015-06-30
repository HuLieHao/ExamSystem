package com.dao;


import java.util.List;

import com.model.Exam;
import com.page.Pager;

/**
 * 创建接口ExamDAO，对试题进行  增、删、改、查  的方法
 * @author 胡烈豪
 *
 */
public interface ExamDao {
	
	/**
	 * 添加试题方法
	 * @param exam
	 * @return 返回true表示添加试题成功，否则添加失败
	 */
	public Boolean addExam(Exam exam);
	
	/**
	 * 修改试题方法
	 * @param exam
	 * @return 返回true表示修改成功，否则修改失败
	 */
	public Boolean updateExam(Exam exam);
	
	/**
	 * 删除试题方法
	 * @param exam
	 * @return 返回true，表示删除成功，否则删除失败
	 */
	public Boolean deleteExam(Exam exam);
	
	/**
	 * 查询试题方法
	 * @param title
	 * @return 返回List<Exam>集合
	 */
	public List<Exam> searchExam(String title, Pager pager);
	
	/**
	 * 返回所有的试题方法
	 * @return 返回List<Exam>集合
	 */
	public List<Exam> AllExam(Pager pager);
	
	/**
	 * 返回试题集的个数
	 * @return 试题集的总数
	 */
	public Integer getTotalRows();
	
	/**
	 * 根据ID值，查询指定的试题
	 * @param id
	 * @return Exam
	 */
	public Exam findExamID(Integer id);
	
	
}
