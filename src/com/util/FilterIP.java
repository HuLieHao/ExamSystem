package com.util;

import java.net.InetAddress;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 拦截登录用户IP
 * @author 烈豪
 *
 */
public class FilterIP extends ActionSupport {

	
	private Cache cache;
	
	public void setCache(Cache cache) {
		this.cache = cache;
	}
	
	/**
	 * 一个IP，是一个32位无符号的二进制数。故用long的低32表示无符号32位二进制数。
	 * @param ip
	 * @return long
	 */
	public long getIP(InetAddress ip)
	{
		byte[] b = ip.getAddress();
		
		 long l = b[0] << 24L & 0xff000000L | b[1] << 16L & 0xff0000L | b[2] << 8L & 0xff00 | b[3] << 0L & 0xff;
		 return l;
	}
	
	/**
	 * 获得登录用户的IP
	 * @return ip
	 */
	public String getUserIP()
	{
		/*
		 * 获得系统的IP地址
		 * 	String s = ServletActionContext.getRequest().getRemoteHost();
		 *  System.out.println("IP:" +s);
		 */
		
		ActionContext ctx = ActionContext.getContext();
		
		HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
		
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    }
	
		return ip;
	}

	/**
	 * 过滤用户的IP，只有合法的IP才能登录
	 * @return 
	 * @throws Exception
	 */
	public String filterIP() throws Exception {
		
		@SuppressWarnings("unchecked")
		List<String> IPS = (List<String>) this.cache.get("segmentip");
		@SuppressWarnings("unchecked")
		List<String> IP = (List<String>) this.cache.get("singlip");
		long l = this.getIP(InetAddress.getByName(this.getUserIP())); //登录用户的IP
		System.out.println("IP限制" + this.getUserIP());
		System.out.println("IP限制" + InetAddress.getByName(this.getUserIP()));
		//判断用户的IP是否在设置的段IP里
		for (int i = 0; i < IPS.size(); i+=2)
		{
			long s = this.getIP(InetAddress.getByName(IPS.get(i)));
			long e = this.getIP(InetAddress.getByName(IPS.get(i + 1)));
			
			//如果在设置的IP段里，则返回SUCCESS;
			if (l >= s && l <= e)
			{
				return SUCCESS;
			}
		}
		//判断用户的IP是否在单独设置的IP里
		for (int i = 0; i < IP.size(); i++)
		{
			long s = this.getIP(InetAddress.getByName(IP.get(i)));
			
			//如果在设置的单个IP里，则返回SUCCESS;
			if (l == s)
			{
				return SUCCESS;
			}
		}
		return ERROR;
		//return SUCCESS;//测试方便
	}
}
