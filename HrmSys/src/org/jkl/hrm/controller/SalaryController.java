package org.jkl.hrm.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static org.jkl.hrm.util.tax.countTax.*;
import org.jkl.hrm.entity.Dept;
import org.jkl.hrm.entity.Employee;
import org.jkl.hrm.entity.Job;
import org.jkl.hrm.entity.Salary;
import org.jkl.hrm.service.HrmService;
import org.jkl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 薪资请求控制器
 *@author Alan
 */
@Controller
public class SalaryController {
	
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	/**
	 * 分页动态查询薪资记录
	 * @param pageIndex
	 * @param salary
	 * @param mv
	 * @return
	 */
	@RequestMapping("/salary/selectSalary") //dao层注意判断对象空否
	public String selectSalary(Integer pageIndex,Integer dept_id ,
			Integer employee_id,Integer job_id,String employee_name ,
			@ModelAttribute Salary salary,Model model) {
		/******分页实现*******/
		System.out.println("selectSalary -->>");
		System.out.println("pageIndex = " + pageIndex);
		System.out.println("salary = " + salary);
		PageModel pageModel = new PageModel();
		System.out.println("getPageIndex = " + pageModel.getPageIndex());
		System.out.println("getPageSize = " + pageModel.getPageSize());
		System.out.println("getRecordCount = " + pageModel.getRecordCount());
		
		if (pageIndex!=null) {
			pageModel.setPageIndex(pageIndex);
		}
		//用于下拉菜单
		List<Job> jobs = hrmService.findAllJob();
		List<Dept> depts = hrmService.findAllDept();
		//默认分页显示所有记录
		/*******************/
		this.genericAssociation(job_id, dept_id, employee_id, employee_name, salary);
		List<Salary> salaries = hrmService.findSalary(salary, pageModel);
		// 设置Model数据
		model.addAttribute("salaries", salaries);
		model.addAttribute("jobs", jobs);
		model.addAttribute("depts", depts);
		model.addAttribute("pageModel", pageModel);
		return "salary/salary";
	}
	
	/**
	 * 新增薪资记录
	 * @param flag
	 * @param job_id
	 * @param employee_id
	 * @param salary (需要)
	 * @param mv
	 * @return
	 */
	@RequestMapping("salary/addSalary")
	public ModelAndView addSalary(String flag,Integer job_id,Integer employee_id,String employee_name,
			Integer dept_id ,@ModelAttribute Salary salary,ModelAndView mv) {
		
		if(flag.equals("1")) {
			// 查询职位信息
			List<Job> jobs = hrmService.findAllJob();
			List<Dept> depts = hrmService.findAllDept();
			// 设置Model数据
			mv.addObject("jobs", jobs);
			mv.addObject("depts", depts);
			// 返回添加员工页面
			mv.setViewName("salary/showAddSalary");
		}else {
			this.genericAssociation(job_id, dept_id,employee_id,employee_name, salary);
			DateFormat df = new SimpleDateFormat("yyyy-MM");
			String date = df.format(new Date());
			String[] dateArray = date.split("-");
			salary.setYear(dateArray[0]);
			salary.setMonth(dateArray[1]);
			double tax = count(salary.getBasic_salary(), salary.getBonus(), salary.getFine());
			salary.setTax(tax);
			hrmService.addSalary(salary);
			mv.setViewName("redirect:/salary/selectSalary");
		}
		return mv;
	}
	
	
	/**
	 * 奖罚、更新薪资信息
	 * @param job_id
	 * @param employee_id
	 * @param salary
	 * @return
	 */
	@RequestMapping("/salary/updateSalary")
	public ModelAndView updateSalary(String flag,String employee_name ,Integer dept_id ,
			Integer job_id,Integer employee_id,
			@ModelAttribute Salary salary,ModelAndView mv) {
		if(flag.equals("1")) {
			Salary sly = hrmService.findSalaryById(salary.getId());
			mv.addObject("job", sly.getJob());		//前端接收用于下拉菜单
			mv.addObject("employee", sly.getEmployee());
			mv.addObject("salary", sly);
			System.out.println(sly.getId());
			mv.setViewName("salary/showUpdateSalary");
		}else {
			
			this.genericAssociation(job_id, dept_id, employee_id, employee_name, salary);
			hrmService.modifySalary(salary);
			//返回并显示所有
			mv.setViewName("redirect:/salary/selectSalary");
		}
		return mv;
	}
	
	/**
	 * 删除薪资记录
	 * @param ids
	 * @return
	 */
	@RequestMapping("/salary/deleteSalary")
	public ModelAndView deleteSalary(String ids,ModelAndView mv) {
		String[] idArray = ids.split(",");
		for(String id : idArray) {
			/**此处实现单个/多个记录 同时删除**/
			hrmService.deleteSalary(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/salary/selectSalary");
		return mv;//直接return String会被视图解析器拦截而不能进入控制器
	}
	
	/**
	 * 动态计算 平均工资
	 * @param mv
	 * @param salary
	 * @return
	 */
	@RequestMapping("salary/countAvg")
	public ModelAndView countAvg(String flag ,String employee_name,String sex ,Integer job_id,
			Integer employee_id,Integer dept_id,ModelAndView mv,@ModelAttribute Salary salary) {
		if (flag.equals("1")) {
			List<Job> jobs = hrmService.findAllJob();
			List<Dept> depts = hrmService.findAllDept();
			mv.addObject("jobs", jobs);
			mv.addObject("depts", depts);
			System.out.println("111111");
			for(Job job : jobs) {
				System.out.println(job.getName());
			}
			mv.setViewName("salary/showCountAvg");
		}else {
			this.genericAssociation(job_id, dept_id,employee_id,employee_name, salary);
			PageModel pageModel = new PageModel();
			if(salary.getMonth().length()==1) {
				salary.setMonth("0"+salary.getMonth());
			}
			List<Salary> salaries = hrmService.findSalary(salary, pageModel);
			double totalCount = 0;
			/**for-each 实现计算平均工资*/
			for(Salary sly :salaries) {
				totalCount+=sly.getReal_wage();
			}
			totalCount/=salaries.size();
			mv.addObject("totalCount", totalCount);
			List<Job> jobs = hrmService.findAllJob();
			List<Dept> depts = hrmService.findAllDept();
			mv.addObject("jobs", jobs);
			mv.addObject("depts", depts);
			System.out.println("2222222222");
			System.out.println(totalCount);
			mv.setViewName("salary/showCountAvg");
		}
		
		return mv;
	}
	
	
	
	/**
	 * 自动关联
	 * @param dept_id
	 * @param employee_id
	 * @param salary
	 */
	private void genericAssociation(Integer job_id,Integer dept_id ,Integer employee_id,String employee_name , Salary salary) {
		if(job_id != null && !job_id.equals("")) {
			Job job = new Job();
			job.setId(job_id);
			salary.setJob(job);
		}
		if (dept_id!=null && !dept_id.equals("")) {
			Dept dept = new Dept();
			dept.setId(dept_id);
			salary.setDept(dept);
		}
		if (employee_id !=null && !employee_id.equals("")) {
			Employee employee = new Employee();
			employee.setId(employee_id);
			salary.setEmployee(employee);
		}
		if (employee_name!=null && !employee_name.equals("")) {
			salary.setEmployeeName(employee_name);
		}
		
		
	}
	
	
	
	
}
