package cn.zzd.controller.servlet;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 张振东
 * @version V1.0
 * @Title:
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date：
 */
@WebServlet("/baseServlet")
public class BaseServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//完成方法请求
		String uri = req.getRequestURI();
		// /trvael/user/add
		System.out.println("uri="+uri);

		//获取方法名称
		String methodName = uri.substring(uri.lastIndexOf("/")+1);
		System.out.println(methodName);
		//获取方法对象
        //谁调用我我代表谁
		//System.out.println(this);
		try {
			Method method=this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);//执行方法
			method.invoke(this,req,resp);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}


}
