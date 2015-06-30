package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.dao.ExamDao;
import com.model.Exam;


/**
 * 通过缓存来存取XML配置文件数据
 * @author 胡烈豪
 *
 */
public class Cache {
	
	private InputStream xml = null;
	private SAXReader reader = null;
	private Document document = null;
	private Element root =  null;
	
	private ExamDao examDao;
	//设值注入需要的setter方法
	public void setExamDao(ExamDao examDao) {
		this.examDao = examDao;
	}
	
	//缓存的数据
	private HashMap<String, Object> mapValues = new HashMap<String, Object>();
	//读取配置文件数据时，配置文件修改的时间
	private static long lastModified = 0;
	
	//配置文件路径
	private String path = "";
	
	private static Cache cache = new Cache();
	
	public static Cache getInstance()
	{
		return cache;
	}
	
	private Cache()
	{
		//this.xml = this.getClass().getResourceAsStream("sconfig.xml");
		try {
			this.xml = new FileInputStream("c:\\config.xml");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		this.reader = new SAXReader();
		try {
			this.document = this.reader.read(this.xml);
			this.xml.close();
			this.root = this.document.getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String getPath() {
		//return path = this.getClass().getResource("c:\\config.xml").getFile();
		return path = "c:\\config.xml";
	}
	
	//返回最后一次修改的时间
	private synchronized long getModified()
	{
		File file = new File(this.getPath());
		return file.lastModified();
	}
	
	//把XML中的值读取到map中
	private synchronized void readXML()
	{
		//new Cache();
		//清除原有的值
		mapValues.clear();
				
		//作业上传目录
		String upload = this.root.element("Upload").getText();
		mapValues.put("upload", upload);
		
		//考题选择规则
		String testRules = this.root.element("TestRules").getText();
		mapValues.put("testRules",testRules);
		
		//考试时间
		List testTimes = this.root.element("TestTime").elements();
		List<Map<String, String>> testTime = new ArrayList<Map<String, String>>();
		for (Iterator i = testTimes.iterator(); i.hasNext(); )
		{
			//构建一个Map对象，存放考试时间信息
			Map<String, String> map = new HashMap<String, String>();
			Element time = (Element)i.next();
			map.put("id", time.attributeValue("id"));
			map.put("startTime", time.element("StartTime").getText());
			map.put("endTime", time.element("EndTime").getText());
			map.put("lessTime", time.element("LessTime").getText());
			testTime.add(map);
		}
		mapValues.put("testTime", testTime);
		
		//考试学生限制
		List allowIDs = this.root.element("AllowID").elements();
		List<Map<String, String>> allowID = new ArrayList<Map<String, String>>();
		for (Iterator i = allowIDs.iterator(); i.hasNext(); )
		{
			//构建一个Map对象，存放考试时间信息
			Map<String, String> map = new HashMap<String, String>();
			Element time = (Element)i.next();
			map.put("id", time.attributeValue("id"));
			map.put("classes", time.element("Classes").getText());
			map.put("professional", time.element("Professional").getText());
			allowID.add(map);
		}
		mapValues.put("allowID", allowID);
		
		//考试试题ID
		List testIDs = this.root.element("TestID").elements();
		List<Map<String, String>> testIDList = new ArrayList<Map<String, String>>();
		for (Iterator i = testIDs.iterator(); i.hasNext(); )
		{
			//构建一个Map对象，存放考试ID的相关信息
			Map<String, String> map = new HashMap<String, String>();
			Element testID = (Element)i.next();
			map.put("id", testID.attributeValue("id"));
			map.put("title", testID.element("title").getText());
			map.put("desc", testID.element("desc").getText());
			testIDList.add(map);
		}
		mapValues.put("testID",testIDList);
		
		//允许考试的单个IP
		List singip = this.root.element("SinglIP").elements("IP");
		List<String> singiplist = new ArrayList<String>();
		for (Iterator i = singip.iterator(); i.hasNext(); ) {
			Element ip = (Element)i.next();
			singiplist.add(ip.getText());
		}
		mapValues.put("singlip", singiplist);
		
		//允许考试的IP段
		List segmengip = this.root.element("SegmentIP").elements("IP");
		List<String> segmentiplist = new ArrayList<String>();
		for (Iterator i = segmengip.iterator(); i.hasNext(); ) {
			Element ip = (Element)i.next();
			String startip = ip.element("StartIP").getText();
			String endip = ip.element("EndIP").getText();
			segmentiplist.add(startip);
			segmentiplist.add(endip);
			
		}
		mapValues.put("segmentip", segmentiplist);
		
		//获取最后的修改时间
		lastModified = this.getModified();
	}
	
	//从XML或缓存中获取值
	public synchronized Object get (String key)
	{
		Long time = getModified();
		//判断XML最后一次修改的时间是否等于读取值时的时间
		if (time == lastModified)
		{
			System.out.println("从缓存中获取配置文件数据");
			return this.mapValues.get(key);
			
		}else {
			this.readXML();
			System.out.println("从XML文件中读取配置文件数据");
			return this.mapValues.get(key);
		}
	}
	
	//设置考试开始时间、结束时间、限制时间、上传目录、考题选择规则、要考试的考题ID号。
	public Boolean setObject (String target, String value)
	{
		
		if (this.writeObject(target, value))
		{
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * 
	 * @param times 第几场考试
	 * @param startTime 考试开始时间
	 * @param endTime 考试结束时间
	 * @param lessTime 考试时间限制
	 * @return 第几场考试
	 */
	public Integer setTestTime(Integer times, String startTime,  String endTime, String lessTime)
	{
		Element parent = this.root.element("TestTime");
		
		//创建子节点：Time id：第几场考试
		Element child = DocumentHelper.createElement("Time");
		child.addAttribute("id", times + "");
		
		//创建子节点：StartTime 考试开始时间 
		Element st = DocumentHelper.createElement("StartTime");
		st.setText(startTime);
		
		//创建子节点：EndTime 考试结束时间
		Element et = DocumentHelper.createElement("EndTime");
		et.setText(endTime);
		
		//创建子节点：LessTime 考试时间限制
		Element lt = DocumentHelper.createElement("LessTime");
		lt.setText(lessTime);
		
		//添加子节点
		child.add(st);
		child.add(et);
		child.add(lt);
		
		parent.add(child);
		
		OutputFormat format = OutputFormat.createPrettyPrint(); 
		format.setEncoding("UTF-8");
		try {
			XMLWriter writer = new XMLWriter(new FileOutputStream(new File(this.getPath())),format);
			writer.write(this.document);
			writer.flush();
			writer.close();
			
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return times;
	}
	
	/**
	 * 取消所有考试时间
	 * @return 成功返回true, 失败返回false
	 */
	public Boolean clearTestTime()
	{
		Element parent = this.root.element("TestTime");
		@SuppressWarnings("unchecked")
		List<Element> childs = this.root.element("TestTime").elements("Time");
		
		for (int i = 0; i < childs.size(); i++)
		{
			parent.remove(childs.get(i));
		}
		
		//当取消考试时间时，那么考试学生限制也应该取消
		clearAllowID();
		
		OutputFormat format = OutputFormat.createPrettyPrint(); 
		format.setEncoding("UTF-8");
		try {
			XMLWriter writer = new XMLWriter(new FileOutputStream(new File(this.getPath())),format);
			writer.write(this.document);
			writer.flush();
			writer.close();
			
			return true;
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 设置允许考试的学生
	 * @param times 第几场考试
	 * @param classes 班级
	 * @param professional 专业
	 * @return
	 */
	public Boolean setAllowID(Integer times, String classes, String professional)
	{
		Element parent = this.root.element("AllowID");
		
		Element child = DocumentHelper.createElement("Stu");
		child.addAttribute("id", times + "");
		
		Element cl = DocumentHelper.createElement("Classes");
		cl.setText(classes);
		Element pr = DocumentHelper.createElement("Professional");
		pr.setText(professional);
		
		child.add(cl);
		child.add(pr);
		
		parent.add(child);
		
		OutputFormat format = OutputFormat.createPrettyPrint(); 
		format.setEncoding("UTF-8");
		try {
			XMLWriter writer = new XMLWriter(new FileOutputStream(new File(this.getPath())),format);
			writer.write(this.document);
			writer.flush();
			writer.close();
			
			return true;
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean clearAllowID()
	{
		Element parent = this.root.element("AllowID");
		@SuppressWarnings("unchecked")
		List<Element> childs = this.root.element("AllowID").elements("Stu");
		
		for (int i = 0; i < childs.size(); i++)
		{
			parent.remove(childs.get(i));
		}
		
		OutputFormat format = OutputFormat.createPrettyPrint(); 
		format.setEncoding("UTF-8");
		try {
			XMLWriter writer = new XMLWriter(new FileOutputStream(new File(this.getPath())),format);
			writer.write(this.document);
			writer.flush();
			writer.close();
			
			return true;
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	//设置考试考题ID
	public Boolean setTestID (String id)
	{
		//通过id值查找实体bean
		Exam exam = this.examDao.findExamID(Integer.parseInt(id));
		//得到父节点
		Element parent = this.root.element("TestID");
		//创建子节点：考试试题ID
		Element child = DocumentHelper.createElement("ID");
		child.addAttribute("id", id);
		//创建id的子节点：考试试题标题
		Element title = DocumentHelper.createElement("title");
		title.setText(exam.getTitle());
		//创建id的子节点：考试试题描述
		Element desc = DocumentHelper.createElement("desc");
		desc.setText(exam.getDescription());
		
		child.add(title);
		child.add(desc);
		parent.add(child);
		
		OutputFormat format = OutputFormat.createPrettyPrint(); 
		format.setEncoding("UTF-8");
		try {
			XMLWriter writer = new XMLWriter(new FileOutputStream(new File(this.getPath())),format);
			writer.write(this.document);
			writer.flush();
			writer.close();
			
			return true;
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//取消考试考题ID
	public Boolean cancleTestID (String id)
	{
		
		//删除原来已经保存的结点。
		Element parent = this.root.element("TestID");
		@SuppressWarnings("unchecked")
		List<Element> childs = this.root.element("TestID").elements("ID");
		//查到到要取消考试试题的ID
		for (int i = 0; i < childs.size(); i++)
		{
			//如果是的话，就删除子节点
			if (childs.get(i).attributeValue("id").equals(id))
			{
				parent.remove(childs.get(i));
			}
		}
		
		OutputFormat format = OutputFormat.createPrettyPrint(); 
		format.setEncoding("UTF-8");
		try {
			XMLWriter writer = new XMLWriter(new FileOutputStream(new File(this.getPath())),format);
			writer.write(this.document);
			writer.flush();
			writer.close();
			
			return true;
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//设置单个IP
	public Boolean setSinglIP (List<String> ips)
	{
		//删除原来已经保存的结点。
		Element parent = this.root.element("SinglIP");
		@SuppressWarnings("unchecked")
		List<Element> childs = this.root.element("SinglIP").elements("IP");
		for (int i = 0; i < childs.size(); i++)
		{
			parent.remove(childs.get(i));
		}
		
		//添加节点
		for (int i = 0; i < ips.size(); i++)
		{
			Element child = DocumentHelper.createElement("IP");
			child.addAttribute("id", i + "");
			child.setText(ips.get(i));
			parent.add(child);
		}
		
		OutputFormat format = OutputFormat.createPrettyPrint(); 
		format.setEncoding("UTF-8");
		try {
			XMLWriter writer = new XMLWriter(new FileOutputStream(new File(this.getPath())),format);
			writer.write(this.document);
			writer.flush();
			writer.close();
			
			return true;
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	//设置段IP
	public Boolean setSegmentIP (List<String> startip, List<String> endip)
	{
		//删除原来已经保存的结点。
		Element parent = this.root.element("SegmentIP");
		@SuppressWarnings("unchecked")
		List<Element> childs = this.root.element("SegmentIP").elements("IP");
		for (int i = 0; i < childs.size(); i++)
		{
			parent.remove(childs.get(i));
		}
		//添加节点
		for (int i = 0; i < startip.size(); i++)
		{
			//创建节点
			Element child = DocumentHelper.createElement("IP");
			child.addAttribute("id", i + "");
			
			Element grandchild_1 = DocumentHelper.createElement("StartIP");
			grandchild_1.setText(startip.get(i));
			
			Element grandchild_2 = DocumentHelper.createElement("EndIP");
			grandchild_2.setText(endip.get(i));
			
			child.add(grandchild_1);
			child.add(grandchild_2);
			parent.add(child);
		}
		
		OutputFormat format = OutputFormat.createPrettyPrint(); 
		format.setEncoding("UTF-8");
		try {
			XMLWriter writer = new XMLWriter(new FileOutputStream(new File(this.getPath())),format);
			writer.write(this.document);
			writer.flush();
			writer.close();
			
			return true;
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	//写入到XML中
	private Boolean writeObject(String target, String object)
	{
		this.root.element(target).setText(object);
		OutputFormat format = OutputFormat.createPrettyPrint(); 
		format.setEncoding("UTF-8");
		try {
			XMLWriter writer = new XMLWriter(new FileOutputStream(new File(this.getPath())),format);
			writer.write(this.document);
			writer.flush();
			writer.close();
			
			return true;
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		Cache cache = Cache.getInstance();
		
		System.out.println("考试开始时间：" + cache.get("startTime"));
		System.out.println("考试结束时间" + cache.get("endTime").equals(""));
		System.out.println("考试时间限制" + cache.get("lessTime"));
		System.out.println("作业上传目录" + cache.get("upload"));
		System.out.println("考试试题选择规则：" + cache.get("testRules"));
		List<Map<String, String>> list = (List<Map<String, String>>)cache.get("testID");
		
		System.out.println("考试试题信息");
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = list.get(i);
			System.out.println(map.get("id"));
			System.out.println(map.get("title"));
			System.out.println(map.get("desc"));
		}
		
		System.out.println("考试单个IP限制");
		List listip = (List)cache.get("singlip");
		for (int i = 0; i < listip.size(); i++) {
			System.out.println(listip.get(i));
		}
		
		System.out.println("考试IP段限制");
		List listips = (List)cache.get("segmentip");
		for (int i = 0; i < listips.size(); i++) {
			System.out.println(listips.get(i));
		}
	}
		

}
