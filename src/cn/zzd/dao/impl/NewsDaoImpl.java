package cn.zzd.dao.impl;

import cn.zzd.dao.NewsDao;
import cn.zzd.domain.News;
import cn.zzd.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.List;

/**
 * @author 张振东
 * @version V1.0
 * @Title:
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date：
 */
public class NewsDaoImpl implements NewsDao {
	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDs());
	@Override
	public int findTotalCount() {
		String sql = "select count(*)from t_news ";
		int count = template.queryForObject(sql,Integer.class);
		return count;

	}

	@Override
	public List<News> findByPage(int start, int rows) {
		String sql = "select * from t_news limit ?,?";
		List<News> news;
		news=template.query(sql,new BeanPropertyRowMapper<News>(News.class),start,rows);
		return news;
	}

	@Override
	public void delNews(String id) {
		String sql = "delete from t_news where id=?";
		template.update(sql,Integer.parseInt(id));
	}

	@Override
	public void addNews(News news) {
		String sql ="insert into t_news(id,title,author,content,enterdate,hot)values(?,?,?,?,?,?)";
		template.update(sql,news.getId(),news.getTitle(),news.getAuthor(),news.getContent(),news.getEnterdate(),news.getHot());

	}

	@Override
	public News findNews(String id) {
		String sql ="select * from t_news where id=?";
		News news = template.queryForObject(sql,new BeanPropertyRowMapper<News>(News.class),Integer.parseInt(id));
		return news;
	}

	@Override
	public void updateNews(News news) {
		String sql =" update t_news set title=?,author=?,content=?,enterdate=?,hot=? where id=?";
		template.update(sql,news.getTitle(),news.getAuthor(),news.getContent(),news.getEnterdate(),news.getHot(),news.getId());
	}
}
