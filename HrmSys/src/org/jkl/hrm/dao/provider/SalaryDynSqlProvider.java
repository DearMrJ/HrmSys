package org.jkl.hrm.dao.provider;


import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.jkl.hrm.entity.Salary;
import org.jkl.hrm.util.common.HrmConstants;

/**
 *
 *@author Alan
 */
public class SalaryDynSqlProvider {
	/**
	 * 动态查询
	 * @param params
	 * @return
	 */
	public String selectWithParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("(SELECT *,ROW_NUMBER() OVER(ORDER BY ID) AS RowId FROM "+HrmConstants.SALARYTABLE+")AS T");
				if(params.get("salary")!=null) {
					Salary salary = (Salary) params.get("salary");
					if (salary.getEmployee()!=null) {
						if(salary.getEmployee().getId()!=null && !salary.getEmployee().getId().equals("")&&salary.getEmployee().getId()!=0) {
							WHERE("employee_id LIKE #{salary.employee.id}");
						}
					}
					if (salary.getJob()!=null) {
						if(salary.getJob().getId()!=null && !salary.getJob().getId().equals("") && salary.getJob().getId()!=0) {
							WHERE("job_id = #{salary.job.id}");
						}
					}
					if (salary.getDept()!=null) {
						if(salary.getDept().getId()!=null && !salary.getDept().getId().equals("") && salary.getDept().getId()!=0) {
							WHERE("dept_id = #{salary.dept.id}");
						}
					}
					if (salary.getEmployeeName()!=null && !salary.getEmployeeName().equals("")) {
						WHERE("employee_name LIKE '%'+#{salary.employeeName}+'%'");
					}
					/*********************可删减**********************/
					if(salary.getBasic_salary()!=0) {
						WHERE("basic_salary LIKE #{salary.basic_salary}");
					}
					if(salary.getBonus()!=0) {
						WHERE("bonus LIKE #{salary.bonus}");
					}
					if(salary.getFine()!=0) {
						WHERE("fine LIKE #{salary.fine}");
					}
					if(salary.getTax()!=0) {
						WHERE("tax LIKE #{salary.tax}");
					}
					/**************************************************/

					if(salary.getReal_wage()!=0) {
						WHERE("real_wage LIKE #{salary.real_wage}");
					}
					if(salary.getYear()!=null && !salary.getYear().equals("")) {
						WHERE("year = #{salary.year} ");
					}
					if(salary.getMonth()!=null && !salary.getMonth().equals("")) {
						WHERE("month = #{salary.month}");
					}
					//分页
					if(params.get("pageModel")!=null) {
						WHERE(" RowId between #{pageModel.firstLimitParam} and #{pageModel.firstLimitParam}+#{pageModel.pageSize}-1");
					}
				}
			}
		}.toString();
		return sql;
	}
	
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public String count(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("count(*)");
				FROM(HrmConstants.SALARYTABLE);
				if (params.get("salary")!=null) {
					Salary salary = (Salary) params.get("salary");
					if (salary.getEmployee()!=null) {
						WHERE("employee_id = #{salary.employee.id}");
					}
					if(salary.getJob()!=null ) {
						WHERE("job_id = #{salary.job.id}");
					}
					if (salary.getYear()!=null && !salary.getYear().equals("")) {
						WHERE("year = #{salary.year}");
					}
					if (salary.getMonth()!=null && !salary.getMonth().equals("")) {
						WHERE("month = #{salary.month}");
					}
				}
			}
		}.toString();
		return sql;
	}
	
	
	
	/**
	 * 
	 * @param salary
	 * @return
	 */
	public String insertSalary(Salary salary) {
		String sql = new SQL() {
			{
				INSERT_INTO(HrmConstants.SALARYTABLE);
				if(salary.getEmployee()!=null && !salary.getEmployee().equals("")) {
					VALUES("employee_id","#{employee.id}");
				}
				if(salary.getJob()!=null && !salary.getJob().equals("")) {
					VALUES("job_id","#{job.id}");
				}
				if(salary.getDept()!=null && !salary.getDept().equals("")) {
					VALUES("dept_id","#{dept.id}");
				}
				if(salary.getEmployeeName()!=null && !salary.getEmployeeName().equals("")) {
					VALUES("employee_name","#{employeeName}");
				}
				if(salary.getBasic_salary()!=0) {
					VALUES("basic_salary","#{basic_salary}");
				}
				if(salary.getBonus()!=0) {
					VALUES("bonus","#{bonus}");
				}
				if(salary.getFine()!=0) {
					VALUES("fine","#{fine}");
				}
				if(salary.getTax()!=0) {
					VALUES("tax","#{tax}");
				}
				if(salary.getReal_wage()!=0) {
					VALUES("real_wage","#{real_wage}");
				}
				if(salary.getYear()!=null && salary.getYear()!="" ) {
					VALUES("year","#{year}");
				}
				if(salary.getMonth()!=null && salary.getMonth()!="" ) {
					VALUES("month","#{month}");
				}
			}
		}.toString();
		return sql;
	}
	
	/**
	 * 更新
	 * @param salary
	 * @return
	 */
	public String updateSalary(Salary salary) {
		String sql = new SQL() {
			{
				UPDATE(HrmConstants.SALARYTABLE);
				if(salary.getBasic_salary()!=0) {
					SET(" basic_salary = #{basic_salary} ");
				}
				if(salary.getBonus()!=0) {
					SET(" bonus = #{bonus} ");
				}
				if(salary.getFine()!=0) {
					SET(" fine = #{fine} ");
				}
				if(salary.getReal_wage()!=0) {
					SET(" real_wage = #{real_wage} ");
				}
				if(salary.getTax()!=0) {
					SET(" tax = #{tax} ");
				}
				if(salary.getMonth()!=null && !salary.getMonth().equals("")) {
					SET(" month = #{month} ");
				}
				if(salary.getYear()!=null && !salary.getYear().equals("")) {
					SET(" year = #{year} ");
				}
				
				WHERE(" id = #{id}");
			}
		}.toString();
		return sql;
	}
	
	
}
