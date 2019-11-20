package cn.ekgc.dkscm.dao;

import java.util.List;
import java.util.Map;

import cn.ekgc.dkscm.pojo.entity.User;

/**
 * <b>用户模块数据持久层接口</b>
 * @author Arthur
 * @version 2.0.0 2019-11-19
 * @since 2019-11-19
 */
public interface UserDao {
	/**
	 * <b>根据查询条件，进行分页查询用户信息列表</b>
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List<User> findUserListByPageAndQuery(Map<String, Object> paramMap) throws Exception;
}
