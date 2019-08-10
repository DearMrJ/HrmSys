package org.jkl.hrm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jkl.hrm.entity.User;
import org.jkl.hrm.util.common.HrmConstants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 判断用户权限的springmvc 的拦截器
 */
public class MyInterceptor implements HandlerInterceptor{
	/**定义不需要拦截的请求**/
	private static final String[] IGNORE_URI = {"/loginForm","/login","404","/register"};
	
	/**
	 * preHandle 方法是进行处理器拦截用的，该方法将在controller 处理之前进行调用，
	 * 当preHandle的返回值为false的时候整个请求就结束了。
	 * 如果preHandle的返回值为true ，则会继续执行postHandle 和afterCompletion.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/** 默认用户没有登录 **/
		boolean flag = false;
		/**获得请求的ServletPath**/
		String servletPath = request.getServletPath();
		/**判断请求是否需要拦截**/
		for(String s:IGNORE_URI) {
			if(servletPath.contains(s)) {
				flag = true;
				break;
			}
		}
		
		/** 拦截请求 */
		if(!flag) {
			/**	1.获取session中的用户 */
			User user = (User) request.getSession().getAttribute(HrmConstants.CURRENT_USER);
			/** 2.判断用户是否已经登录 */
			if(user == null) {
				/** 如果用户没有登录，跳转到登录页面 */
				request.setAttribute("message", "请先登录再进行访问！");
				/**没有登录则跳转至loginForm**/
				request.getRequestDispatcher(HrmConstants.LOGIN).forward(request, response);
				return flag;
			}else {
				System.out.println(user.getLoginname());
				flag=true;
			}
		}
		return flag;
	}
	
	/**
	 * 该方法需要preHandle 方法的返回值 为true时才会执行。
	 * 执行时间是处理器处理之后，也就是controller的方法调用之后执行
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView mv) throws Exception {
	}
	
	/**
	 * 该方法需要preHandle 方法的返回值 为true时才会执行。
	 * 该方法将会在整个请求完成之后执行，主要作用是用于清理资源。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	

}
