package com.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;




import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;



import com.model.Exam;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ExamService;

/**
 * 处理考试试题的actioin
 * @author 胡烈豪
 *
 */
public class ExamAction extends ActionSupport {

	/**
	 * 
	 */

	private ExamService examService;
	
	private Exam exam;
	
	private JSONObject object;
	
	//要修改的试题ID
	private String id;
	
	
	//设值注入需要的setter方法
	public void setExamService(ExamService examService) {
		this.examService = examService;
	}
	
	
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	
	public Exam getExam() {
		return exam;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setObject(JSONObject object) {
		this.object = object;
	}
	public JSONObject getObject() {
		return object;
	}
	//所有的试题信息
	public String allExam() throws Exception {
		
		ActionContext context = ActionContext.getContext();
		List<Exam> list = this.examService.allExam();
		
		if (list != null)
		{
			context.put("examInfoList", list);
		}else {
			context.put("ErrorInfo", "没有查找到试题信息！");
		}
		
		return SUCCESS;
		
	}
	
	//增加或修改试题信息
	public String addOrUpdateExam() throws Exception {
		
		ActionContext context = ActionContext.getContext();
		if (this.examService.addOrUpdateExam(id,exam))
		{
			context.put("examInfo", "修改试题信息成功！");
			return allExam();
		}else {
			context.put("examInfo", "修改试题信息失败！");
			return allExam();
		}
	}
	
	//删除试题信息
	public String delExam() throws Exception {
		
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		String examid = request.getParameter("id");
		if (this.examService.delExam(examid))
		{
			context.put("delexamInfo", "修改试题信息成功！");
			return allExam();
		}else {
			context.put("delexamInfo", "修改试题信息失败！");
			return allExam();
		}
		
	}
	
	//将试题添加到本次考试中
	public String addExam() throws Exception {
		
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		String examid = request.getParameter("id");
		
		if (this.examService.addExam(examid))
		{
			context.put("addexamInfo", "添加考试试题成功！");
			return allExam();
		}else {
			context.put("addexamInfo", "添加考试试题失败！");
			return allExam();
		}
	}
	
	//在本次考试中取消这个试题
	public String cancelExam() throws Exception {
		
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		String examid = request.getParameter("id");
		
		if (this.examService.cancelExam(examid))
		{
			context.put("addexamInfo", "取消考试试题成功！");
			return allExam();
		}else {
			context.put("addexamInfo", "取消考试试题失败！");
			return allExam();
		}
	}
	
	//查看试题
	public String seeExam() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String questionID = request.getParameter("questionID");
		
		int id = Integer.parseInt(questionID);
		Exam examInfo = this.examService.findExamID(id);

		Map<String, String> map = new HashMap<String, String>();
		map.put("content", examInfo.getContent());
		map.put("title", examInfo.getTitle());
		map.put("desc", examInfo.getDescription());
		map.put("flag", examInfo.getFlag() + "");
		map.put("id", examInfo.getId() + "");
		this.object = JSONObject.fromObject(map);
		
		return SUCCESS;
	}
}
