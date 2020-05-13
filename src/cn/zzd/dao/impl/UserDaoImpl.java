package cn.zzd.dao.impl;

import cn.zzd.dao.UserDao;
import cn.zzd.domain.User;
import cn.zzd.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author 张振东
 * @version V1.0
 * @Title:
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date：
 */
public class UserDaoImpl implements UserDao {
	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDs());

	@Override
	public User finByUid(String uid) {
		User user = null;
		try {
			String sql = "select * from t_user where uid=?";
			user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), uid);
		} catch (Exception e){
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void save(User user) {
		String sql = "insert into t_user(uid,uname,upassword,usex,uemail,uregdate)values(?,?,?,?,?,?)";
		template.update(sql, user.getUid(), user.getUname(), user.getUpassword(), user.getUsex(), user.getUemail(), user.getUregdate());
	}

	@Override
	public User findUserByUsernameAndPassword(String uid, String upassword) {
		User user=null;
		String sql ="select *from t_user where uid= ? and upassword=?";
		user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),uid,upassword);
		return user;
	}
}
