package com.dao.impl;

import java.util.List;
import java.util.Map;

import com.dao.RulesDao;
import com.util.Cache;

public class RulesDaoImpl implements RulesDao {
	
	private Cache cache;
	
	public void setCache(Cache cache) {
		this.cache = cache;
	}
	
	@Override
	public Integer getTimesID() {
		
		return ((List<Map<String, String>>)this.cache.get("testTime")).size();

	}
	
	@Override
	public Boolean clearTime() {
		return this.cache.clearTestTime();
	}
	
	@Override
	public Boolean setTimes(Integer timeID, String starttime, String endTime, String lessTime) {
		
//		this.cache.setObject("StartTime", starttime);
//		this.cache.setObject("EndTime", endTime);
//		this.cache.setObject("LessTime", lessTime);
		
		this.cache.setTestTime(timeID, starttime, endTime, lessTime);
		
		return true;
	}
	
	@Override
	public Integer getAllowID() {
		
		return ((List<Map<String, String>>)this.cache.get("allowID")).size();
	}
	
	@Override
	public Boolean clearAllowID() {
		
		return this.cache.clearAllowID();
	}
	
	@Override
	public Boolean setAllowID(Integer times, String classes, String professional) {
		
		return this.cache.setAllowID(times, classes, professional);
	}

	@Override
	public Boolean setChoiceQuestions(String rules) {
		
		return this.cache.setObject("TestRules", rules);
	}

	@Override
	public Boolean setExamDirectory(String dir) {
		
		return this.cache.setObject("Upload", dir);
	}

	@Override
	public Boolean setIP(List<String> startIP, List<String> endIP,
			List<String> oneIP) {
		
		if (this.cache.setSinglIP(oneIP) && this.cache.setSegmentIP(startIP, endIP))
		{
			return true;
		}
		return false;
	}
	

}
