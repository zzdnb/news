package cn.zzd.controller.servlet;

import cn.zzd.domain.News;
import cn.zzd.domain.PageBean;
import cn.zzd.service.NewsService;

import cn.zzd.service.impl.NewsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author 张振东
 * @version V1.0
 * @Title:
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date：
 */
@WebServlet("/news/*")
public class NewsServlet extends BaseServlet {
	private NewsService service = new NewsServiceImpl();

	public void addNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.设置编码
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String content = request.getParameter("content");
		String date_str = request.getParameter("enterdate");
		String hot_str = request.getParameter("hot");
		// String-->Util.Date
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		int hot;
		News news = null;
		try {
			date = df.parse(date_str);
			hot = Integer.parseInt(hot_str);
			news = new News(0, title, author, content, date, hot);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		//4.调用Service保存
		service.addNews(news);

		response.sendRedirect("/news/findNewsByPage");
	}


	public void delNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取id
		String id = request.getParameter("id");
		//2.调用service删除
		service.deleteNews(id);

		//3.跳转到查询所有Servlet
		response.sendRedirect("/news/findNewsByPage");
	}

	public void findNewsByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentPage = request.getParameter("currentPage");
		String rows = request.getParameter("rows");
		if (currentPage == null || "".equals(currentPage)) {
			currentPage = "1";
		}
		if (rows == null || "".equals(rows)) {
			rows = "5";
		}
		PageBean<News> pb = service.findNewsPage(currentPage, rows);
		System.out.println(pb);
		request.getSession().setAttribute("pb", pb);
		response.sendRedirect("/list.jsp");
	}

	public void updateNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.设置编码

		request.setCharacterEncoding("utf-8");
		String id_str=request.getParameter("id");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String content = request.getParameter("content");
		String date_str = request.getParameter("enterdate");
		String hot_str = request.getParameter("hot");
		// String-->Util.Date
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		int hot;
		int id;
		News news = null;
		try {
			date = df.parse(date_str);
			hot = Integer.parseInt(hot_str);
			id=Integer.parseInt(id_str);
			news = new News(id, title, author, content, date, hot);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//4.调用Service保存
		service.updateNews(news);

		response.sendRedirect("/news/findNewsByPage");
	}

	public void findNews1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取id
		String id = request.getParameter("id");
		//2.调用Service查询
		News news = service.findNews(id);

		//3.将user存入request
		request.getSession().setAttribute("news", news);
		response.sendRedirect("/display.jsp");
	}
	public void findNews2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取id
		String id = request.getParameter("id");
		//2.调用Service查询
		News news = service.findNews(id);

		//3.将user存入request
		request.getSession().setAttribute("news", news);
		response.sendRedirect("/update.jsp");
	}
}

