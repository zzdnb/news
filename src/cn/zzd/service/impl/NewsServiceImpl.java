package cn.zzd.service.impl;

import cn.zzd.dao.NewsDao;
import cn.zzd.dao.impl.NewsDaoImpl;
import cn.zzd.domain.News;
import cn.zzd.domain.PageBean;
import cn.zzd.service.NewsService;

import java.util.List;

/**
 * @author 张振东
 * @version V1.0
 * @Title:
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date：
 */
public class NewsServiceImpl implements NewsService {
	private  NewsDao dao = new NewsDaoImpl();

	@Override
	public PageBean<News> findNewsPage(String _currentPage, String _rows) {
		int currentPage = Integer.parseInt(_currentPage);
		int rows = Integer.parseInt(_rows);
		if (currentPage <= 0) {
			currentPage = 1;
		}
		PageBean<News> pb = new PageBean<>();

		pb.setRows(rows);
		int totalCount = dao.findTotalCount();
		pb.setTotalCount(totalCount);

		int totalPage = totalCount % rows == 0 ? totalCount / rows : totalCount / rows + 1;
		pb.setTotalPage(totalPage);

		if (currentPage >= totalPage) {
			currentPage = totalPage;
		}
		pb.setCurrentPage(currentPage);
		int start = (currentPage - 1) * rows;
		List<News> list = dao.findByPage(start, rows);
		pb.setList(list);
		//System.out.println(totalPage+".."+currentPage);
		return pb;
	}

	@Override
	public void deleteNews(String id) {
		dao.delNews(id);
	}

	@Override
	public void addNews(News news) {
		dao.addNews(news);
	}

	@Override
	public News findNews(String id) {
		return dao.findNews(id);
	}

	@Override
	public void updateNews(News news) {
		dao.updateNews(news);
	}
}
