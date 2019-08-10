package org.jkl.hrm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jkl.hrm.dao.DeptDao;
import org.jkl.hrm.dao.EmployeeDao;
import org.jkl.hrm.dao.JobDao;
import org.jkl.hrm.dao.SalaryDao;
import org.jkl.hrm.dao.UserDao;
import org.jkl.hrm.entity.Dept;
import org.jkl.hrm.entity.Employee;
import org.jkl.hrm.entity.Job;
import org.jkl.hrm.entity.Salary;
import org.jkl.hrm.entity.User;
import org.jkl.hrm.service.HrmService;
import org.jkl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//@Transactional只有readonly="false",才可以增删改，如下
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("hrmService")
public class HrmServiceImpl implements HrmService{
	/***
	 *自动注入持久层Dao对象
	 *
	 **/
	@Autowired
	private UserDao userDao;
	@Autowired
	private DeptDao deptDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private JobDao jobDao;
	@Autowired
	private SalaryDao salaryDao;
	
	/************************************用户服务接口实现*************************************/
	/***
	 * HrmServiceImpl 接口login方法实现
	 * @see { HrmService }
	 **/
	@Transactional(readOnly=true)
	@Override
	public User login(String loginname, String password) {
		System.out.println("HrmServiceImpl login -- >>");
		return userDao.selectByLoginnameAndPassword(loginname, password);
	}
	/***
	 * HrmServiceImpl 接口findUserById方法实现
	 * @see { HrmService }
	 **/
	@Transactional(readOnly=true)
	@Override
	public User findUserById(Integer id) {
		return userDao.selectById(id);
	}
	/***
	 * HrmServiceImpl 接口findUser方法实现
	 * @see { HrmService }
	 **/
	@Transactional(readOnly=true)
	@Override
	public List<User> findUser(User user, PageModel pageModel) {
		/***当前需要分页的  总数据条数**/
		Map<String, Object> params = new HashMap<>();
		params.put("user", user);
		int recordCount = userDao.count(params);
		System.out.println("recordCount -->> "+recordCount);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0) {
			/***开始分页查询数据， 查询第几页的数据**/
			params.put("pageModel", pageModel);
		}
		List<User> users = userDao.selectByPage(params);
		return users;
	}
	/***
	 * HrmServiceImpl 接口removeUserById方法实现
	 * @see { HrmService }
	 **/
	@Override
	public void removeUserById(Integer id) {
		 userDao.deleteById(id);
	}
	
	/***
	 * HrmServiceImpl 接口modifyUser方法实现
	 * @see { HrmService }
	 **/
	@Override
	public void modifyUser(User user) {
		 userDao.update(user);
	}
	/***
	 * HrmServiceImpl 接口addUser方法实现
	 * @see { HrmService }
	 **/
	@Override
	public void addUser(User user) {
		userDao.save(user);
	}
	
	
	/********************** **********员工服务接口实现********** *****************************/
	/**
	 * (non-Javadoc)
	 * @see org.jkl.hrm.service.HrmService#findEmployee(org.jkl.hrm.domain.Employee, org.jkl.hrm.util.tag.PagerModel)
	 */
	@Transactional(readOnly=true)
	@Override
	public List<Employee> findEmployee(Employee employee, PageModel pageModel) {
		/***当前需要分页的 总数据条数**/
		Map<String, Object> params =new HashMap<>();
		params.put("employee", employee);
		int recordCount = employeeDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0) {
			/***开始分页查询数据：查询第几页的数据**/
			params.put("pageModel", pageModel);
		}
		List<Employee> employees = employeeDao.selectByPage(params);
		//
		for(Employee employee2 : employees) {
			System.out.println(employee2.getName());
		}
		return employees;
	}
	/**
	 * (non-Javadoc)
	 * @see org.jkl.hrm.service.HrmService#removeEmployeeById(java.lang.Integer)
	 */
	@Override
	public void removeEmployeeById(Integer id) {
		employeeDao.deleteById(id);
	}
	/**
	 * (non-Javadoc)
	 * @see org.jkl.hrm.service.HrmService#findEmployeeById(java.lang.Integer)
	 */
	@Transactional(readOnly=true)
	@Override
	public Employee findEmployeeById(Integer id) {
		return employeeDao.selectById(id);
	}
	/**
	 * (non-Javadoc)
	 * @see org.jkl.hrm.service.HrmService#addEmployee(org.jkl.hrm.domain.Employee)
	 */
	@Override
	public void addEmployee(Employee employee) {
		employeeDao.save(employee);
	}
	/**
	 * (non-Javadoc)
	 * @see org.jkl.hrm.service.HrmService#modifyEmployee(org.jkl.hrm.domain.Employee)
	 */
	@Override
	public void modifyEmployee(Employee employee) {
		 employeeDao.update(employee);
	}
	@Override
	public int countEmployee(Employee employee) {
		Map<String, Object> params = new HashMap<>();
		params.put("employee", employee);
		return employeeDao.count(params);
	}
	
	
	/********************** **********部门服务接口实现********** *****************************/
	/**
	 * (non-Javadoc)
	 * @see org.jkl.hrm.service.HrmService#findDept(org.jkl.hrm.domain.Dept, org.jkl.hrm.util.tag.PagerModel)
	 */
	@Transactional(readOnly=true)
	@Override
	public List<Dept> findDept(Dept dept, PageModel pageModel) {
		/***当前需要 分页的总数据 条数**/
		Map<String, Object> params = new HashMap<>();
		params.put("dept", dept);
		int recordCount = deptDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0) {
			/***开始分页查询数据：查询第几页的数据**/
			params.put("pageModel", pageModel);
		}
		List<Dept> depts = deptDao.selectByPage(params);
		return depts;
	}
	/**
	 * (non-Javadoc)
	 * @see org.jkl.hrm.service.HrmService#findAllDept()
	 */
	@Transactional(readOnly=true)
	@Override
	public List<Dept> findAllDept() {
		return deptDao.selectAllDept();
	}
	/**
	 * (non-Javadoc)
	 * @see org.jkl.hrm.service.HrmService#removeDeptById(java.lang.Integer)
	 */
	@Override
	public void removeDeptById(Integer id) {
		deptDao.deleteById(id);
	}
	/**
	 * (non-Javadoc)
	 * @see org.jkl.hrm.service.HrmService#addDept(org.jkl.hrm.domain.Dept)
	 */
	@Override
	public void addDept(Dept dept) {
		 deptDao.save(dept);
	}
	/**
	 * (non-Javadoc)
	 * @see org.jkl.hrm.service.HrmService#findDeptById(java.lang.Integer)
	 */
	@Transactional(readOnly=true)
	@Override
	public Dept findDeptById(Integer id) {
		return deptDao.selectById(id);
	}
	/**
	 * (non-Javadoc)
	 * @see org.jkl.hrm.service.HrmService#modifyDept(org.jkl.hrm.domain.Dept)
	 */
	@Override
	public void modifyDept(Dept dept) {
		 deptDao.update(dept);
	}
	/********************** **********职位服务接口实现********** *****************************/
	/**
	 * (non-Javadoc)
	 * @see org.jkl.hrm.service.HrmService#findAllJob()
	 */
	@Transactional(readOnly=true)
	@Override
	public List<Job> findAllJob() {
		return jobDao.selectAllJob();
	}
	/**
	 * (non-Javadoc)
	 * @see org.jkl.hrm.service.HrmService#findJob(org.jkl.hrm.domain.Job, org.jkl.hrm.util.tag.PagerModel)
	 */
	@Transactional(readOnly=true)
	@Override
	public List<Job> findJob(Job job, PageModel pageModel) {
		/***当前需要分页的 总数据条数**/
		Map<String ,Object> params = new HashMap<>();
		params.put("job", job);
		int recordCount = jobDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0) {
			/***开始分页查询数据：查询第几页的数据**/
			params.put("pageModel", pageModel);
		}
		List<Job> jobs = jobDao.selectByPage(params);
		return jobs;
	}
	/**
	 * (non-Javadoc)
	 * @see org.jkl.hrm.service.HrmService#removeJobById(java.lang.Integer)
	 */
	@Override
	public void removeJobById(Integer id) {
		 jobDao.deleteById(id);
	}
	/**
	 * (non-Javadoc)
	 * @see org.jkl.hrm.service.HrmService#addJob(org.jkl.hrm.domain.Job)
	 */
	@Override
	public void addJob(Job job) {
		 jobDao.save(job);
	}
	/**
	 * (non-Javadoc)
	 * @see org.jkl.hrm.service.HrmService#findJobById(java.lang.Integer)
	 */
	@Transactional(readOnly=true)
	@Override
	public Job findJobById(Integer id) {
		return jobDao.selectById(id);
	}
	/**
	 * (non-Javadoc)
	 * @see org.jkl.hrm.service.HrmService#modifyJob(org.jkl.hrm.domain.Dept)
	 */
	@Override
	public void modifyJob(Job job) {
		 jobDao.update(job);
	}
	
	/********************** **********薪资服务接口实现********** *****************************/
	/**
	 * (non-Javadoc)
	 * @see org.jkl.hrm.service.HrmService#findSalary(org.jkl.hrm.entity.Salary, org.jkl.hrm.util.tag.PageModel)
	 */
	@Transactional(readOnly=true)
	@Override
	public List<Salary> findSalary(Salary salary,PageModel pageModel) {
		/***当前需要分页的 总数据条数**/
		Map<String ,Object> params = new HashMap<>();
		params.put("salary", salary);
		int recordCount = salaryDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0) {
			/***开始分页查询数据：查询第几页的数据**/
			params.put("pageModel", pageModel);
		}
		/****结果返回***/
		List<Salary> salaries = salaryDao.selectByPage(params);
		
		return salaries;
	}
	/**
	 * (non-Javadoc)
	 * @see org.jkl.hrm.service.HrmService#findAllSalary()
	 */
	@Transactional(readOnly=true)
	@Override
	public List<Salary> findAllSalary() {
		return salaryDao.selectAllSalary();
	}
	/**
	 * (non-Javadoc)
	 * @see org.jkl.hrm.service.HrmService#findSalaryById(java.lang.Integer)
	 */
	@Override
	public Salary findSalaryById(Integer id) {
		return salaryDao.selectById(id);
	}
	/**
	 * (non-Javadoc)
	 * @see org.jkl.hrm.service.HrmService#insertSalary(org.jkl.hrm.entity.Salary)
	 */
	@Override
	public void addSalary(Salary salary) {
		salaryDao.save(salary);
	}
	/**
	 * (non-Javadoc)
	 * @see org.jkl.hrm.service.HrmService#deleteSalary(java.lang.Integer)
	 */
	@Override
	public void deleteSalary(Integer id) {
		salaryDao.delete(id);
	}
	/**
	 * (non-Javadoc)
	 * @see org.jkl.hrm.service.HrmService#updateSalary(org.jkl.hrm.entity.Salary)
	 */
	@Override
	public void modifySalary(Salary salary) {
		salaryDao.update(salary);
	}
	
	
	
	
	
}
