package cn.ekgc.dkscm.service;

import cn.ekgc.dkscm.pojo.entity.User;

/**
 * <b>用户模块业务层接口</b>
 * @author Arthur
 * @version 2.0.0 2019-11-19
 * @since 2019-11-19
 */
public interface UserService {
	/**
	 * <b>通过使用手机号码查找用户信息</b>
	 * @param cellphone
	 * @return
	 * @throws Exception
	 */
	User getUserByCellphone(String cellphone) throws Exception;
}
