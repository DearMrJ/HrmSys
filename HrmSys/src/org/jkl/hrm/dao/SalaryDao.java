package org.jkl.hrm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.jkl.hrm.dao.provider.SalaryDynSqlProvider;
import org.jkl.hrm.entity.Salary;
import org.jkl.hrm.util.common.HrmConstants;

public interface SalaryDao {
	/**
	 * 动态查询薪资
	 * @param params
	 * @return
	 */
	@SelectProvider(type=SalaryDynSqlProvider.class,method="selectWithParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="employee_id",property="employee",
			one=@One(select="org.jkl.hrm.dao.EmployeeDao.selectById")),
		@Result(column="job_id",property="job",
			one=@One(select="org.jkl.hrm.dao.JobDao.selectById")),
		@Result(column="dept_id",property="dept",
			one=@One(select="org.jkl.hrm.dao.DeptDao.selectById")),
		@Result(column="employee_name",property="employeeName"),
		@Result(column="basic_salary",property="basic_salary"),
		@Result(column="bonus",property="bonus"),
		@Result(column="tax",property="tax"),
		@Result(column="fine",property="fine"),
		@Result(column="real_wage",property="real_wage"),
		@Result(column="create_date",property="#{createDate}")
	})
	List<Salary> selectByPage(Map<String, Object> params);
	
	/**
	 * 所有记录
	 * @return
	 */
	@Select("select * from "+HrmConstants.SALARYTABLE+"")
	List<Salary> selectAllSalary();
	/**
	 * 根据id查询记录
	 * @param id
	 * @return
	 */
	@Select("select * from "+HrmConstants.SALARYTABLE+" where id = #{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="employee_id",property="employee",
			one=@One(select="org.jkl.hrm.dao.EmployeeDao.selectById")),
		@Result(column="job_id",property="job",
			one=@One(select="org.jkl.hrm.dao.JobDao.selectById")),
		@Result(column="dept_id",property="dept",
			one=@One(select="org.jkl.hrm.dao.DeptDao.selectById")),
		@Result(column="employee_name",property="employeeName"),
		@Result(column="basic_salary",property="basic_salary"),
		@Result(column="bonus",property="bonus"),
		@Result(column="tax",property="tax"),
		@Result(column="fine",property="fine"),
		@Result(column="real_wage",property="real_wage"),
		@Result(column="create_date",property="#{createDate}")
	})
	Salary selectById(Integer id);
	
	/**
	 * 插入薪资信息
	 * @param salary
	 */
	@SelectProvider(type=SalaryDynSqlProvider.class,method="insertSalary")
	void save(Salary salary) ;
	
	/**
	 * 移除薪资记录
	 * @param id
	 */
	@Delete("delete from "+HrmConstants.SALARYTABLE+" where id = #{id}")
	void delete(Integer id);
	
	
	/**
	 * 更新薪资信息(罚款、奖金、税收等)
	 * @param salary
	 */
	@SelectProvider(type=SalaryDynSqlProvider.class,method="updateSalary")
	void update(Salary salary);
	
	/**
	 * 动态查询符合条件人数
	 * @param params
	 * @return
	 */
	@SelectProvider(type=SalaryDynSqlProvider.class,method="count")
	int count(Map<String, Object> params);
	
	
	
	
	
	
	
	
}
