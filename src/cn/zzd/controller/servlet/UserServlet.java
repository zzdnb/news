package cn.zzd.controller.servlet;

import cn.zzd.domain.User;
import cn.zzd.service.UserService;
import cn.zzd.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

/**
 * @author 张振东
 * @version V1.0
 * @Title:
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date：
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
	private UserService userService = new UserServiceImpl();
	/**
	 * 注册
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Map<String, String[]> parameterMap = request.getParameterMap();
		User user = new User();
		//封装对象
		try {
			BeanUtils.populate(user, parameterMap);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		user.setUregdate(new Date());
		System.out.println(user);
		boolean flag = userService.regist(user);
		if(flag){
			request.getSession().setAttribute("successMsg","注册成功");
			response.sendRedirect(request.getContextPath()+"/regist.jsp");
		}else {
			request.getSession().setAttribute("errorMsg","注册失败");
			response.sendRedirect(request.getContextPath()+"/regist.jsp");
		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//1.设置编码
        request.setCharacterEncoding("utf-8");

	//2.获取数据
	//2.1获取用户填写验证码
	String verifycode = request.getParameter("verifycode");

	//3.验证码校验
	HttpSession session = request.getSession();
	String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//确保验证码一次性
        if(!checkcode_server.equalsIgnoreCase(verifycode)){
		//验证码不正确
		//提示信息
		session.setAttribute("login_msg","验证码错误！");
		//跳转登录页面
		response.sendRedirect("/login.jsp");

		return;
	}

	Map<String, String[]> map = request.getParameterMap();
	//4.封装User对象
	User user = new User();
        try {
		BeanUtils.populate(user,map);
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		e.printStackTrace();
	}
System.out.println(user);

	//5.调用Service查询
	UserService service = new UserServiceImpl();
	User loginUser = service.login(user);
	//6.判断是否登录成功
        if(loginUser != null){
		//登录成功
		//将用户存入session
		session.setAttribute("user",loginUser);
		//跳转页面
		response.sendRedirect("/news/findNewsByPage");
	}else {
			//登录失败
			//提示信息
			session.setAttribute("login_msg", "用户名或密码错误！");
			//跳转登录页面
			response.sendRedirect("/login.jsp");

		}
}}
