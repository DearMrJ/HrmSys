package org.jkl.hrm.dao.provider;

import static org.jkl.hrm.util.common.HrmConstants.EMPLOYEETABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.jkl.hrm.entity.Employee;


public class EmployeeDynaSqlProvider {
	//分页动态查询
	public String selectWithParam(Map<String , Object> params) {
		String sql =new SQL() {
			{
				SELECT ("*");
				FROM("(SELECT *,ROW_NUMBER() OVER(ORDER BY ID) AS RowId FROM "+EMPLOYEETABLE+"");
				if(params.get("employee")!=null) {
					Employee employee = (Employee) params.get("employee");
					if(employee.getDept()!=null && employee.getDept().getId()!=null && employee.getDept().getId() !=0) {
						WHERE(" DEPT_ID = #{employee.dept.id}");
					}
					if(employee.getJob()!=null && employee.getJob().getId()!=null && employee.getJob().getId() !=0) {
						WHERE(" JOB_ID = #{employee.job.id}");
					}
					if(employee.getName()!=null && !employee.getName().equals("")) {
						WHERE(" NAME LIKE '%'+#{employee.name}+'%'");
					}
					if(employee.getPhone()!=null && !employee.getPhone().equals("")) {
						WHERE(" PHONE LIKE '%'+#{employee.phone}+'%'");
					}
					if(employee.getCardId()!=null && !employee.getCardId().equals("")) {
						WHERE(" CARD_ID LIKE '%'+#{employee.cardId}+'%'");
					}
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					if(employee.getSex()!=null && employee.getSex().equals("")) {
						WHERE(" SEX = #{employee.sex}");
					}
				}
			}
		}.toString();
		String sql1 ="";
		if(params.get("pageModel")!=null) { //新new 一个SQL(),里面若是没有DML()
			sql1 ="WHERE  RowId between #{pageModel.firstLimitParam} and #{pageModel.firstLimitParam}+#{pageModel.pageSize}-1";
		}
		sql=sql+")AS T "+sql1;
		System.out.println(sql);
		return sql;
		
		/*
		String sql =new SQL() {
			{
				SELECT ("*");
				FROM("(SELECT *,ROW_NUMBER() OVER(ORDER BY ID) AS RowId FROM "+EMPLOYEETABLE+")AS T");
				if(params.get("employee")!=null) {
					Employee employee = (Employee) params.get("employee");
					if(employee.getDept()!=null && employee.getDept().getId()!=null && employee.getDept().getId() !=0) {
						WHERE(" DEPT_ID = #{employee.dept.id}");
					}
					if(employee.getJob()!=null && employee.getJob().getId()!=null && employee.getJob().getId() !=0) {
						WHERE(" JOB_ID = #{employee.job.id}");
					}
					if(employee.getName()!=null && !employee.getName().equals("")) {
						WHERE(" NAME LIKE '%'+#{employee.name}+'%'");
					}
					if(employee.getPhone()!=null && !employee.getPhone().equals("")) {
						WHERE(" PHONE LIKE '%'+#{employee.phone}+'%'");
					}
					if(employee.getCardId()!=null && !employee.getCardId().equals("")) {
						WHERE(" CARD_ID LIKE '%'+#{employee.cardId}+'%'");
					}
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					if(employee.getSex()!=null && employee.getSex().equals("")) {
						WHERE(" SEX = #{employee.sex}");
					}
					
					//1.WHERE(") )AS T)"); 不可行
					//2.WHERE("1 = 1)AS T)"); 不可行 ,自动补全成"WHERE(1 = 1)AS T) and RowId..."
					//3.将pageModel独立一个SQL判断，不可行。SQL对象内没有DML语句(CRUD)，返回空
	
					//分页
					if(params.get("pageModel")!=null) {
						WHERE(" RowId between #{pageModel.firstLimitParam} and #{pageModel.firstLimitParam}+#{pageModel.pageSize}-1");
					}
				}
			}
		}.toString();
		System.out.println(sql);
		return sql;*/
	}
	
	//动态查询总数量
	public String count(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(EMPLOYEETABLE);
				if(params.get("employee")!=null) {
					Employee employee = (Employee) params.get("employee");
					if(employee.getDept()!=null && employee.getDept().getId()!=null && employee.getDept().getId()!=0) {
						WHERE(" DEPT_ID = #{employee.dept.id}");
					}
					if(employee.getJob()!=null && employee.getJob().getId()!=null && employee.getJob().getId()!=0) {
						WHERE(" JOB_ID = #{employee.job.id} ");
					}
					if(employee.getName()!=null && employee.getName().equals("")) {
						WHERE(" NAME LIKE '%'+#{employee.name}+'%'");
					}
					if(employee.getPhone()!=null && employee.getPhone().equals("")) {
						WHERE(" PHONE LIKE '%'+#{employee.phone}+'%'");
					}
					if(employee.getCardId()!=null && employee.getCardId().equals("")) {
						WHERE(" CARD_ID LIKE '%'+#{employee.cardId}+'%'");
					}
				}
			}
		}.toString();
	}
	
	//动态插入insert
	public String insertEmployee(Employee employee) {
		return new SQL() {
			{
				INSERT_INTO(EMPLOYEETABLE);
				if(employee.getName()!=null) {
					VALUES("name", "#{name}");
				}
				if(employee.getCardId()!=null) {
					VALUES("CARD_ID", "#{cardId}");
				}
				if(employee.getAddress()!=null) {
					VALUES("ADDRESS", "#{address}");
				}
				if(employee.getPostCode()!=null) {
					VALUES("POST_CODE", "#{postCode}");
				}
				if(employee.getTel()!=null) {
					VALUES("TEL", "#{tel}");
				}
				if(employee.getPhone()!=null) {
					VALUES("PHONE", "#{phone}");
				}
				if(employee.getQqNum()!=null) {
					VALUES("QQ_NUM", "#{qqNum}");
				}
				if(employee.getEmail()!=null) {
					VALUES("EMAIL", "#{email}");
				}
				if(employee.getSex()!=null) {
					VALUES("SEX", "#{sex}");
				}
				if(employee.getParty()!=null) {
					VALUES("PARTY", "#{party}");
				}
				if(employee.getBirthday()!=null) {
					VALUES("BIRTHDAY", "#{birthday}");
				}
				if(employee.getRace()!=null) {
					VALUES("RACE", "#{race}");
				}
				if(employee.getEducation()!=null) {
					VALUES("EDUCATION", "#{education}");
				}
				if(employee.getSpeciality()!=null) {
					VALUES("SPECIALITY", "#{speciality}");
				}
				if(employee.getHobby()!=null) {
					VALUES("HOBBY", "#{hobby}");
				}
				if(employee.getRemark()!=null) {
					VALUES("REMARK", "#{remark}");
				}
				if(employee.getCreateDate()!=null) {
					VALUES("CREATEDATE", "#{createDate}");
				}
				if(employee.getDept()!=null) {
					VALUES("DEPT_ID", "#{dept.id}");
				}
				if(employee.getJob()!=null) {
					VALUES("JOB_ID", "#{job.id}");
				}
			}
		}.toString();
	}
	//动态更新update
	public String updateEmployee(Employee employee) {
		return new SQL() {
			{
				UPDATE(EMPLOYEETABLE);
				if(employee.getName()!=null) {
					SET("name = #{name}");
				}
				if(employee.getCardId()!=null) {
					SET("CARD_ID = #{cardId}");
				}
				if(employee.getAddress()!=null) {
					SET("ADDRESS = #{address}");
				}
				if(employee.getPostCode()!=null) {
					SET("POST_CODE = #{postCode}");
				}
				if(employee.getTel()!=null) {
					SET("TEL = #{tel}");
				}
				if(employee.getPhone()!=null) {
					SET("PHONE = #{phone}");
				}
				if(employee.getQqNum()!=null) {
					SET("QQ_NUM = #{qqNum}");
				}
				if(employee.getEmail()!=null) {
					SET("EMAIL = #{email}");
				}
				if(employee.getSex()!=null) {
					SET("SEX = #{sex}");
				}
				if(employee.getParty()!=null) {
					SET("PARTY = #{party}");
				}
				if(employee.getBirthday()!=null) {
					SET("BIRTHDAY = #{birthday}");
				}
				if(employee.getRace()!=null) {
					SET("RACE = #{race}");
				}
				if(employee.getEducation()!=null) {
					SET("EDUCATION = #{education}");
				}
				if(employee.getSpeciality()!=null) {
					SET("SPECIALITY = #{speciality}");
				}
				if(employee.getHobby()!=null) {
					SET("HOBBY = #{hobby}");
				}
				if(employee.getRemark()!=null) {
					SET("REMARK = #{remark}");
				}
				if(employee.getCreateDate()!=null) {
					SET("CREATEDATE = #{createDate}");
				}
				if(employee.getDept()!=null) {
					SET("DEPT_ID = #{dept.id}");
				}
				if(employee.getJob()!=null) {
					SET("JOB_ID = #{job.id}");
				}
				WHERE("ID = #{id}");
			}
		}.toString();
	}
	
}
