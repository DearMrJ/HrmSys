package org.jkl.hrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**   
 * 动态页面请求控制器
 * @author Alan   
 */
@Controller
public class FormController{
	/**？？？？？？？？级联目录不可用？？？？？？？**/
	@RequestMapping(value="/{formName}")
	 public String loginForm(@PathVariable String formName){
		// 动态跳转页面
		System.out.println("跳转到目标："+formName+".jsp");
		return formName;
	}
	
	@RequestMapping(value="/user/{formName}")
	 public String loginForm1(@PathVariable String formName){
		// 动态跳转页面
		System.out.println("跳转到目标："+"user/"+formName+".jsp");
		return "user/"+formName;
	}
	@RequestMapping(value="/dept/{formName}")
	public String loginForm2(@PathVariable String formName){
		// 动态跳转页面
		System.out.println("跳转到目标："+"dept/"+formName+".jsp");
		return "dept/"+formName;
	}
	@RequestMapping(value="/job/{formName}")
	public String loginForm3(@PathVariable String formName){
		// 动态跳转页面
		System.out.println("跳转到目标："+"job/"+formName+".jsp");
		return "job/"+formName;
	}
	@RequestMapping(value="/employee/{formName}")
	public String loginForm4(@PathVariable String formName){
		// 动态跳转页面
		System.out.println("跳转到目标："+"employee/"+formName+".jsp");
		return "employee/"+formName;
	}
	@RequestMapping(value="/salary/{formName}")
	public String loginForm5(@PathVariable String formName){
		// 动态跳转页面
		System.out.println("跳转到目标："+"salary/"+formName+".jsp");
		return "salary/"+formName;
	}

}

