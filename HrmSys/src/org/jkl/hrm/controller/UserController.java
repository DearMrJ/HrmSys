package org.jkl.hrm.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jkl.hrm.entity.User;
import org.jkl.hrm.service.HrmService;
import org.jkl.hrm.util.common.HrmConstants;
import org.jkl.hrm.util.encryption.RSAUtils;
import org.jkl.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**   
 * 用户请求控制器
 * @author Alan   
 */
@Controller
public class UserController {
	/**
	 * 自动注入UserService
	 */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	/**
	 * 处理登录请求
	 * @param String loginname 登录名
	 * @param String password  密码
	 * @return ModelAndView 跳转的视图
	 */
	@RequestMapping("/login")//必须接收Form传来的字段，action="login",否则bad request
	public ModelAndView login(@RequestParam("loginname") String loginname, 
			@RequestParam("password") String password,
			HttpSession session,ModelAndView mv) {
		System.out.println("当前sessionid："+session.getId());
		System.out.println("解密前："+password);
		/**********************此处实现前端数据RSA解密**************************/
		try {
			password = RSAUtils.decryptBase64(password);//解密获得密码明文
		} catch (Exception e) {
			System.out.println("解密出错啦！");
		}
		System.out.println("解密后："+password);
		/******************************************************************/
		//调用业务逻辑组件判断用户是否可以登录
		User user = hrmService.login(loginname, password);

		if(user!=null) {
			//将用户保存到HttpSession当中
			session.setAttribute(HrmConstants.CURRENT_USER, user);
			//客户端跳转到main页面
			session.setAttribute("current_username",user.getUsername());
			session.setAttribute("current_state", user.getUserstatus());
			mv.setViewName("redirect:/main");
		}else {
			//设置登录失败提示消息
			mv.addObject("message", "登录名或密码错误！请重新输入..");
			//服务器内部跳转到登录页面
			mv.setViewName("forward:/loginForm");
		}
		return mv;
	}
	
	/**
	 * 处理查询请求
	 * @param pageIndex 请求的是第几页
	 * @param employee 模糊查询参数
	 * @param Model model 
	 */
	@RequestMapping(value="/user/selectUser")
	public String selectUser(Integer pageIndex,@ModelAttribute User user,Model model) {
		System.out.println("user=" + user.getUsername());
		PageModel pageModel = new PageModel();
		if(pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询用户信息 */
		List<User> users = hrmService.findUser(user, pageModel);
		model.addAttribute("users",users);
		model.addAttribute("pageModel",pageModel);
		return "user/user";//通过视图处理器转换成 WEB-INF/jsp/user/user.jsp
	}
	
	/**
	 * 处理删除用户请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 */
	@RequestMapping(value="/user/removeUser")
	public ModelAndView removeUser(String ids,ModelAndView mv) {
		//分解id字符串
		String[] idArray = ids.split(",");
		for(String id :idArray) {
			//根据id删除员工
			hrmService.removeUserById(Integer.parseInt(id));
		}
		//设置客户端客户端跳转到查询请求
		mv.setViewName("redirect:/user/selectUser");
		//返回 ModelAndView
		return mv;
	}
	
	/**
	 * 处理修改用户请求
	 * @param String flag 标记 ，  1表示跳转到修改页面，2表示执行修改操作
	 * @param User user  要修改用户的对象
	 * @param ModelAndView mv
	 */
	@RequestMapping(value="/user/updateUser")
	public ModelAndView updateUser(String flag,@ModelAttribute User user,ModelAndView mv) {
		if(flag.equals("1")) {
			mv.setViewName("redirect:/user/showUpdateUser");
		}else {
			//根据id查询用户
			User target = hrmService.findUserById(user.getId());
			
			//设置Model数据
			mv.addObject("user",target);
			
		}
		return mv;
	}
	
	/**
	 * 处理添加请求
	 * @param String flag 标记， 1 表示跳转到添加页面 ， 2.表示执行添加操作
	 * @param User user 要添加用户的对象
	 * @param ModelAndView mv
	 */
	@RequestMapping(value="/user/addUser")
	public ModelAndView addUser(String flag,@ModelAttribute User user,ModelAndView mv) {
		if(flag.equals("1")){
			//设置跳转到添加页面
			mv.setViewName("user/showAddUser");
		}else {
			//执行添加操作
			hrmService.addUser(user);
			//设置客户端跳转 到查询操作
			mv.setViewName("redirect:/user/selectUser");
		}
		return mv;
	}
	
	
	@RequestMapping("user/regist")
	public ModelAndView regist(String username , String password ,ModelAndView mv) {
		//注册前判断用户是否已经存在
		if(hrmService.login(username, password)==null) {
			/**********************此处实现前端数据RSA解密**************************/
			try {
				password = RSAUtils.decryptBase64(password);//解密获得密码明文
			} catch (Exception e) {
				System.out.println("解密出错啦！");
			}
			System.out.println("解密后："+password);
			/******************************************************************/
			User user = new User(username,username, password);
			hrmService.addUser(user);
			mv.setViewName("redirect:/main");
		}else {
			mv.addObject("message","用户已存在！");
			mv.setViewName("register");
		}
		return mv;
	}
	
	
	/**
	 * 用户登出
	 * @param session
	 * @param mv
	 * @return
	 */
	@RequestMapping("/user/logout")
	public ModelAndView logout(HttpSession session,ModelAndView mv) {
		session.removeAttribute(HrmConstants.CURRENT_USER);
		mv.setViewName("redirect:/loginForm");
		return mv;
	}
	
	/**
	 * 产生公钥public key，前端点击登录button触发此服务，并将数据发送至前端用于加密
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequestMapping("/user/getPublicKey")
	public String getPublicKey(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
		try {
			PrintWriter writer = httpServletResponse.getWriter();//前端getKey.js中Ajax接收
			String publickey = RSAUtils.generateBase64PublicKey();
			writer.write(publickey);
//			System.out.println(publickey);//打印公钥
			return null;
		} catch (Exception e) {
			/**do something**/
			return null;
		}
	}
}
