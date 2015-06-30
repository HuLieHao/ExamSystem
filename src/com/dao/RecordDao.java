package com.dao;

import com.model.Record;

/**
 * 学生考试记录信息接口
 * @author 胡烈豪
 *
 */
public interface RecordDao {
	
	/**
	 * 判断是否已经登录过
	 * @param id
	 * @return 如果已经在其它机器上登录过，则返回false，如果还未登录过则返回true;
	 */
	public Boolean isExists(String id);
	
	/**
	 * 当登录时，添加登录的信息
	 * @param info
	 * @return 添加成功返回true,否则返回false
	 */
	public Boolean setInfo(Record info);
	
	/**
	 * 当用户登录时，记录此电脑的IP，防止在其它电脑登录
	 * @param id 学生id
	 * @param ip 电脑IP
	 * @return 设置成功返回true,否则返回false
	 */
	public Boolean updateIP(String id, String ip);
	
	/**
	 * 查询IP信息
	 * @param id
	 * @return 返回IP
	 */
	public String getIP(String id);
	
	/**
	 * 判断是否已经参加过考试
	 * @param id
	 * @return 已经参加过则返回true,否则返回false;
	 */
	public Boolean getFlag(String id);
	
	/**
	 * 当第一次点击参加考试时，更改标志信息，防止多次点击参加考试
	 * @param id
	 * @return 修改成功返回true,否则返回false;
	 */
	public Boolean uploateFlag(String id);
}
