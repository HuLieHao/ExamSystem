package com.service.impl;

import java.util.List;

import com.dao.RulesDao;
import com.dao.StudentDao;
import com.service.RulesService;

public class RulesServiceImpl implements RulesService {
	
	private RulesDao rulesDao;
	private StudentDao studentDao;
	
	//设值注入需要的setter方法
	public void setRulesDao(RulesDao rulesDao) {
		this.rulesDao = rulesDao;
	}
	
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	@Override
	public Integer getTimesID() {
		
		return this.rulesDao.getTimesID();
	}
	
	@Override
	public Boolean clearTime() {
		
		return this.rulesDao.clearTime();
	}

	@Override
	public Boolean setTimes(Integer timeID, String starttime, String endTime, String lessTime) {
		
		return this.rulesDao.setTimes(timeID, starttime, endTime, lessTime);
		
	}
	
	@Override
	public Integer getAllowID() {
		
		return this.rulesDao.getAllowID();
	}
	
	@Override
	public Boolean clearAllowID() {
		
		return this.rulesDao.clearAllowID();
	}
	
	@Override
	public Boolean setAllowID(Integer times, String classes, String professional) {
		
		return this.rulesDao.setAllowID(times, classes, professional);
	}

	@Override
	public Boolean setChoiceQuestions(String rules) {
		
		return this.rulesDao.setChoiceQuestions(rules);
	}

	@Override
	public Boolean setExamDirectory(String dir) {
		
		return this.rulesDao.setExamDirectory(dir);
	}

	@Override
	public Boolean setIP(List<String> startIP, List<String> endIP,
			List<String> oneIP) {
		
		return this.rulesDao.setIP(startIP, endIP, oneIP);
	}

	@Override
	public List getCountByClasses() {
		
		return this.studentDao.getCountByClasses();
	}

	@Override
	public List getCountByProfessional() {
		
		return this.studentDao.getCountByProfessional();
	}

}
