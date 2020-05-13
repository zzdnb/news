package cn.zzd.service.impl;

import cn.zzd.dao.UserDao;
import cn.zzd.dao.impl.UserDaoImpl;
import cn.zzd.domain.User;
import cn.zzd.service.UserService;

/**
 * @author 张振东
 * @version V1.0
 * @Title:
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date：
 */
public class UserServiceImpl implements UserService {
	UserDao userDao = new UserDaoImpl();

	@Override
	public boolean regist(User user) {
		User u = userDao.finByUid(user.getUid());
		if (u != null) {
			return false;
		}
		userDao.save(user);
		return true;
	}

	@Override
	public User login(User user) {
		return userDao.findUserByUsernameAndPassword(user.getUid(), user.getUpassword());
	}
}

