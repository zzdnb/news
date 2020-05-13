package cn.zzd.service;

import cn.zzd.domain.News;
import cn.zzd.domain.PageBean;

/**
 * @author 张振东
 * @version V1.0
 * @Title:
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date：
 */
public interface NewsService {
	PageBean<News> findNewsPage(String currentPage, String rows);

	void deleteNews(String id);

	void addNews(News news);

	News findNews(String id);

	void updateNews(News news);
}
