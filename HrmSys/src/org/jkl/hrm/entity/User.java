package org.jkl.hrm.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	public Integer id;			//id
	private String username;	//用户名
	private String loginname;	//登录名
	private String password;	//密码
	private Integer userstatus;	//用户状态
	private Date createDate;	//建档日期
	
	//无参构造器
	public User() {}
	
	public User(String username, String loginname, String password) {
		this.username = username;
		this.loginname = loginname;
		this.password = password;
	}
	
	//setter和getter方法
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getUserstatus() {
		return userstatus;
	}
	public void setUserstatus(Integer userstatus) {
		this.userstatus = userstatus;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
