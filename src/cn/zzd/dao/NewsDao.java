package cn.zzd.dao;

import cn.zzd.domain.News;

import java.util.List;

/**
 * @author 张振东
 * @version V1.0
 * @Title:
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date：
 */
public interface NewsDao {
	int findTotalCount();

	List<News> findByPage(int start, int rows);

	void delNews(String id);

	void addNews(News news);

	News findNews(String id);

	void updateNews(News news);
}
