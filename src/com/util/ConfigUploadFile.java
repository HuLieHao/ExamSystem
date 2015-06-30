package com.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ConfigUploadFile extends Thread {
	
	//学生提交答案的内容
	public String content;
	
	//学生提交答案的名字（学号+学号+题号）
	public String title;
	
	//学生提交答案的路径
	public String directory;
	
	/**
	 * 上传学生提交的答案
	 * @param content 学生提交答案的内容
	 * @param title 学生提交答案的名字
	 */
	private void uploadAnswer()
	{
		File dir = new File(directory);
		
		//如果路径是否存在，如果不存在则创建一个
		if (!dir.exists())
		{
			dir.mkdirs();
			System.out.println("===== 创建上传作业文件夹： " + this.directory + " =====");
		}
		
		File file = new File(this.directory + "/" + this.title);
		
		try {
			file.createNewFile();
			
			FileOutputStream outputStream = new FileOutputStream(file);
			//过滤学生的答案
			this.filterAnswer();
			
			outputStream.write(this.content.getBytes());
			outputStream.close();
			
			System.out.println("===== " +  this.title + "文件上传成功！=====");
		} catch (IOException e) {
			
			System.out.println("===== " +  this.title + "文件创建失败！=====");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 过滤学生提交的答案，过滤一些特殊符号，如：&amp;(&) &gt;(>) &lt;(<)
	 */
	public void filterAnswer()
	{
		this.content = this.content.replaceAll("&gt;", ">").replaceAll("&lt;", "<").replaceAll("&amp;", "&");
	}

	@Override
	public void run() {
		
		this.uploadAnswer();
	}
}
