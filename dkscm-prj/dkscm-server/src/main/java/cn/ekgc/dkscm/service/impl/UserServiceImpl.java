package cn.ekgc.dkscm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ekgc.dkscm.dao.UserDao;
import cn.ekgc.dkscm.pojo.entity.User;
import cn.ekgc.dkscm.service.UserService;

/**
 * <b>用户模块业务层接口实现类</b>
 * @author Arthur
 * @version 2.0.0 2019-11-19
 * @since 2019-11-19
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	/**
	 * <b>通过使用手机号码查找用户信息</b>
	 * @param cellphone
	 * @return
	 * @throws Exception
	 */
	public User getUserByCellphone(String cellphone) throws Exception {
		// 通过将所有的查询参数都封装到Map集合中，这样可以统一在Dao中的查询方法
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// 设定需要查询的条件
		paramMap.put("cellphone", cellphone);
		// 使用Dao层进行查询，得到用户集合
		List<User> userList = userDao.findUserListByPageAndQuery(paramMap);
		// 由于业务设定，cellphone在数据库属于唯一的，因此如果该列表有数据，也只能有一个
		if (userList != null && userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}
}
