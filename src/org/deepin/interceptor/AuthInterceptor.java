/*package org.deepin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


*//**
 * 拦截一系列请求。
 *
 *//*
public class AuthInterceptor implements HandlerInterceptor{
	*//**
	 * 处理完成3
	 *//*
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse res, Object obj, Exception e)
			throws Exception {
	}
	*//**
	 * 处理完成后
	 *//*
	public void postHandle(HttpServletRequest arg0, HttpServletResponse res,
			Object obj, ModelAndView model) throws Exception {
	}
	*//**
	 * 请求拦截处理
	 *//*
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object obj) throws Exception {
		//设置编码格式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		 //判断方法，是否执行阻止访问
		////////////////////////////1.  获取请求的方式///////////////////////////////////
		if(!request.getMethod().equals("GET")){
			//得到输出对象
			PrintWriter out= response.getWriter();
			out.print(JSONObject.fromObject(new ResultCode(00001,"请求方式只能为POST")));
			out.flush();
			out.close();
			return false;
		}
		return true;
	}
}
*/