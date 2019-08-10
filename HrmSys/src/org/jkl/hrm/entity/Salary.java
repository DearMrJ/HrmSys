package org.jkl.hrm.entity;

import java.util.Date;

/**
 *	薪资实体类
 *@author Alan
 */
public class Salary {
	
	private Integer id;
	private Job job;
	private Dept dept;
	private Employee employee;
	private String employeeName;
	private double basic_salary;	//基本工资
	private double tax;				//税收
	private double bonus;			//奖金
	private double fine;			//罚金
	private double real_wage;		//实发工资
	private String year;			//年
	private String month;			//月
	private Date createDate;
	
	
	public Salary() { }//无参构造器
	
	public Salary(Employee employee, double basic_salary, double tax, double bonus, double fine, double real_wage,
			String year, String month) {
		this.employee = employee;
		this.basic_salary = basic_salary;
		this.tax = tax;
		this.bonus = bonus;
		this.fine = fine;
		this.real_wage = real_wage;
		this.year = year;
		this.month = month;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public double getBasic_salary() {
		return basic_salary;
	}
	public void setBasic_salary(double basic_salary) {
		this.basic_salary = basic_salary;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getBonus() {
		return bonus;
	}
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	public double getFine() {
		return fine;
	}
	public void setFine(double fine) {
		this.fine = fine;
	}
	//计算实发工资
	public double getReal_wage() {
		return this.basic_salary+this.bonus-this.fine-this.tax;
	}
	public void setReal_wage(double real_wage) {
		this.real_wage = real_wage;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	
	
	
	
}
