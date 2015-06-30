package com.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dao.ExamDao;
import com.model.Exam;
import com.page.Pager;
import com.page.PagerHelper;
import com.service.ExamService;
import com.util.Cache;

public class ExamServiceImpl implements ExamService {
	
	private ExamDao examDao;
	
	private Cache cache;
	
	public void setExamDao(ExamDao examDao) {
		this.examDao = examDao;
	}
	//设值注入需要的setter方法
	public void setCache(Cache cache) {
		this.cache = cache;
	}

	@Override
	public List<Exam> allExam() {
		
		//设置分页的一些信息
		int totalRows = 0;
		Pager pager = null;
		int pageSize = 10;
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		totalRows = this.examDao.getTotalRows(); //获得数据的总个数
		
		pager = PagerHelper.getPager(request, totalRows, pageSize);
		pager.setLinkUrl("allExam.action");
		request.setAttribute("pb",pager);
		List<Exam> examList = this.examDao.AllExam(pager);
		
		return examList;
	}

	@Override
	public Boolean addOrUpdateExam(String id, Exam exam) {
		
		Exam ex = this.examDao.findExamID(Integer.parseInt(id));
		
		if (ex == null)
		{
			return this.examDao.addExam(exam);
		}else {
			
			ex.setContent(exam.getContent());
			ex.setDescription(exam.getDescription());
			ex.setTitle(exam.getTitle());
			ex.setFlag(exam.getFlag());
			return this.examDao.updateExam(ex);
		}
			
	}

	@Override
	public Boolean delExam(String id) {
		
		Exam exam = this.examDao.findExamID(Integer.parseInt(id));
		return this.examDao.deleteExam(exam);
	}

	@Override
	public Boolean addExam(String id) {
		
		Exam exam = this.examDao.findExamID(Integer.parseInt(id));
		exam.setFlag(1);
		if (this.cache.setTestID(id) && this.examDao.updateExam(exam))
		{
			return true;
		}
		
		return false;
	}

	@Override
	public Boolean cancelExam(String id) {

		Exam exam = this.examDao.findExamID(Integer.parseInt(id));
		exam.setFlag(0);
		if (this.cache.cancleTestID(id) && this.examDao.updateExam(exam))
		{
			return true;
		}
		return false;
	}
	@Override
	public Exam findExamID(Integer id) {
		
		return this.examDao.findExamID(id);
	}
	
	

}
