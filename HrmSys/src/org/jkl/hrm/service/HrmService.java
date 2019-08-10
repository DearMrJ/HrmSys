package org.jkl.hrm.service;

import java.util.List;

import org.jkl.hrm.entity.Dept;
import org.jkl.hrm.entity.Employee;
import org.jkl.hrm.entity.Job;
import org.jkl.hrm.entity.Salary;
import org.jkl.hrm.entity.User;
import org.jkl.hrm.util.tag.PageModel;

public interface HrmService {
	/**
	 * 用户登录
	 * @param	loginname
	 * @param	password
	 * @return 	User对象
	 */
	User login(String loginname,String password);
	/**
	 * 根据id查询用户
	 * @param	id
	 * @return 	User对象
	 */
	User findUserById(Integer id);
	/**
	 * 获得所有用户
	 * @return User对象的List集合 
	 */
	List<User> findUser(User user,PageModel pageModel);
	/**
	 * 根据id删除用户
	 * @param	User对象
	 */
	void removeUserById(Integer id);
	/**
	 * 修改用户
	 * @param	User对象
	 */
	void modifyUser(User user);
	/**
	 * 添加用户
	 * @param 	User 对象
	 */
	void addUser(User user);
	/**
	 * 获取所有员工
	 * @param	employee查询条件
	 * @param 	pageModel分页对象
	 * @param	Dept 对象的List集合
	 */
	List<Employee> findEmployee(Employee employee,PageModel pageModel);
	/**
	 * 根据id删除员工
	 * @param	id
	 */
	void removeEmployeeById(Integer id);
	/**
	 * 根据id查询员工
	 * @param	id
	 * @return	employee对象
	 */
	Employee findEmployeeById(Integer id);
	/**
	 * 添加员工
	 * @param	employee对象
	 */
	void addEmployee(Employee employee);
	/**
	 * 修改员工
	 * @param	employee对象
	 */
	void modifyEmployee(Employee employee);
	/**
	 * 根据条件查询员工总数
	 * @param employee
	 * @return
	 */
	int countEmployee(Employee	employee);
	/**
	 * 获取所有部门，分页查询
	 * @return	Dept对象的List集合
	 */
	List<Dept> findDept(Dept dept, PageModel pageModel );
	/**
	 * 获取所有部门
	 * @return 	Dept对象的List集合
	 */
	List<Dept> findAllDept();
	/**
	 * 根据id删除部门
	 * @param 	id
	 */
	void removeDeptById(Integer id );
	/**
	 * 添加部门
	 * @param	dept对象
	 */
	void addDept(Dept dept);
	/**
	 * 根据id查询部门
	 * @param  	id
	 * @return 	dept对象
	 */
	Dept findDeptById(Integer id );
	/**
	 * 修改部门
	 * @param	dept对象
	 */
	void modifyDept(Dept dept);
	/**
	 * 获取所有职位
	 * @return	Job对象的List集合
	 */
	List<Job> findAllJob();
	/**
	 * 获取所有职位，分页查询
	 * @return	Job对象的list集合
	 */
	List<Job> findJob(Job job,PageModel pageModel);
	/**
	 * 根据id删除职位
	 * @param	id
	 */
	void removeJobById(Integer id);
	/**
	 * 添加职位
	 * @param	Job对象
	 */
	void addJob(Job job);
	/**
	 * 根据id查询职位
	 * @param 	id
	 */
	Job findJobById(Integer id);
	/**
	 * 修改职位
	 * @param	dept对象
	 */
	void modifyJob(Job job);
	
	
	
	/**
	 * 动态查询薪资记录
	 * @param params
	 * @return
	 */
	List<Salary> findSalary(Salary salary,PageModel pageModel);
	/**
	 * 所有记录
	 * @return
	 */
	List<Salary> findAllSalary();
	/**
	 * 根据id查询薪资记录
	 * @param id
	 * @return
	 */
	Salary findSalaryById(Integer id);
	/**
	 * 插入薪资记录
	 * @param salary
	 */
	void addSalary(Salary salary);
	/**
	 * 根据id删除薪资记录
	 * @param id
	 */
	void deleteSalary(Integer id);
	/**
	 * 修改薪资记录
	 * @param salary
	 */
	void modifySalary(Salary salary);
	
}
