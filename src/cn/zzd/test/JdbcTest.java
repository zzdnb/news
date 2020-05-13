package cn.zzd.test;

import cn.zzd.dao.UserDao;
import cn.zzd.dao.impl.NewsDaoImpl;
import cn.zzd.dao.impl.UserDaoImpl;
import cn.zzd.domain.User;
import cn.zzd.service.impl.NewsServiceImpl;
import org.junit.Test;

/**
 * @author 张振东
 * @version V1.0
 * @Title:
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date：
 */
public class JdbcTest {
	@Test
	public void test01(){
		User user =new User();
		user.setUid("ss");
	new UserDaoImpl().save(user);
	}
	@Test
	public void test02(){
		User user =new User();

		System.out.println(new NewsServiceImpl().findNewsPage("1","5"));
	}
}
