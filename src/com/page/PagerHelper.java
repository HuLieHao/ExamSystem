package com.page;

import javax.servlet.http.HttpServletRequest;

public class PagerHelper {
	
	 public static Pager getPager(HttpServletRequest httpServletRequest,
	      int totalRows,int pageSize) {
		 
	  // 定义pager对象，用于传到页面
	  Pager pager = new Pager(totalRows,pageSize);
	  
	  // 从Request对象中获取当前页号
	  String currentPage = httpServletRequest.getParameter("cpage");
	  // 如果当前页号为空，表示为首次查询该页
	  // 如果不为空，则刷新pager对象，输入当前页号等信息
	  if (currentPage != null) {
	   pager.setStart(Integer.parseInt(currentPage));
	  } else {
	   pager.setStart(1);
	  }
	  return pager;
	 }
	}

