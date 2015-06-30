package com.dao;

import java.util.List;

import com.model.Student;
import com.page.Pager;

/**
 *  定义一个接口，实现对学生信息的各种操作
 * @author 胡烈豪
 *
 */
public interface StudentDao {
	
	/**
	 * 验证学生登录是否正确
	 * @param name
	 * @param password
	 * @return	成功返回Student对象，反之返回null;
	 */
	public Student checkLogin(String name, String password);
	
	/**
	 * 返回所有学生的信息
	 * @param pager 分页
	 * @return 成功返回全部的学生，反之返回null
	 */
	public List<Student> allStuinfo(Pager pager);
	
	/**
	 * 下载学生的信息
	 * @return List<StuInfo>
	 */
	public List<Student> downloadStuInfo();
	
	/**
	 * 返回全部学生信息的行数
	 * @return 成功返回行数,反之返回0
	 */
	public int getTotalRows();
	
	/**
	 * 返回根据指定内容查到的学生信息的行数
	 * @param searchType
	 * @param searchValue
	 * @return int
	 */
	public int getCountBySearch(Integer searchType, String searchValue);
	
	/**
	 * 返回根据指定内容查到的学生的信息
	 * @param searchType
	 * @param searchValue
	 * @param pager
	 * @return List<StuInfo>
	 */
	public List<Student> queryBySearch(Integer searchType, String searchValue, Pager pager);
	
	/**
	 * 根据专业和班级查看学生bean的信息行数
	 * @param choiceValue_1
	 * @param choiceValue_2
	 * @return int
	 */
	public int getCountByChoice(String choiceValue_1, String choiceValue_2);
	
	/**
	 * 根据专业和班级查看学生的信息
	 * @param choiceValue_1
	 * @param choiceValue_2
	 * @param pager
	 * @return List<StuInfo>
	 */
	public List<Student> queryByChoice(String choiceValue_1, String choiceValue_2, Pager pager);
	
	/**
	 * 根据id删除学生的信息
	 * @param id
	 * @return true:删除成功，false:删除失败
	 */
	public Boolean DeleStu(Student id);
	
	/**
	 * 根据id查询学生的信息
	 * @param id
	 * @return StuInfo
	 */
	public Student findUserById(Integer id);
	
	/**
	 * 添加学生的信息
	 * @param s 学生对象
	 * @return true:添加成功，false:添加失败
	 */
	public Boolean addStu(Student student);
	
	/**
	 * 修改学生的信息
	 * @param s 学生对象
	 * @return true:修改成功，false:修改失败
	 */
	public Boolean updateStu(Student student);
	
	/**
	 * 根据学生的学号，返回学生的实体bean对象
	 * @param studentID
	 * @return StuInfo
	 */
	public Student finUserByStuID(String studentID);
	
	/**
	 * 修改学生的密码
	 * @param studentID
	 * @param newPass
	 * @return boolean
	 */
	public Boolean updateUserPass(String studentID, String newPass);
	
	/**
	 * 统计总共有多少个班级
	 * @return
	 */
	public List getCountByClasses();
	
	/**
	 * 统计总共有多少个专业
	 * @return
	 */
	public List getCountByProfessional();
}
