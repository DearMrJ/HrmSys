package org.jkl.hrm.entity;

import java.io.Serializable;

public class Job implements Serializable{
	private Integer id;		//id
	private String name;	//部门名称
	private String remark;	//详细描述
	//无参构造器
	public Job() {
		super();
	}
	
	public Job(Integer id, String name, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.remark = remark;
	}
	
	//setter和getter方法
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
