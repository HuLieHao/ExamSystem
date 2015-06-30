package com.action;

import com.opensymphony.xwork2.ActionSupport;
import com.service.TakeExamService;

/**
 * 处理学生考试的action
 * @author 胡烈豪
 *
 */
public class TakeExamAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4725666258787021701L;
	
	private TakeExamService takeExamService;
	
	//学生提交答案的内容
	private String editorValue;

	
	//设值注入需要的setter方法
	public void setTakeExamService(TakeExamService takeExamService) {
		this.takeExamService = takeExamService;

	}
	

	public void setEditorValue(String editorValue) {
		this.editorValue = editorValue;
	}
	
	public String getEditorValue() {
		return editorValue;
	}

	
	//要参加考试的考题信息
	public String examInfo() throws Exception
	{
		takeExamService.allExamInfo();
		return SUCCESS;
	}
	
	//进入考试页面的判断
	public String takeTest() throws Exception
	{
		//判断考试时间是否已到、在本场考试中是否允许参加考试
		if (takeExamService.judgeTime() && takeExamService.isAllowTakeExam())
		{
			//分配考试试题
			takeExamService.judgeQuestion();
			return SUCCESS;
		}

		return ERROR;
	}
	
	//学生提交答案的action
	public String uploadAnswer() throws Exception
	{
		
		if (takeExamService.uploadAnswer(this.getEditorValue()))
		{
			return SUCCESS;
		}
		return ERROR;
	}
}
