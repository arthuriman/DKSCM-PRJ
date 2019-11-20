package cn.ekgc.dkscm.service;

import java.util.List;

import cn.ekgc.dkscm.pojo.entity.Menu;
import cn.ekgc.dkscm.pojo.entity.Role;

/**
 * <b>菜单模块业务层接口</b>
 * @author Arthur
 * @version 2.0.0 2019-11-19
 * @since 2019-11-19
 */
public interface MenuService {
	/**
	 * <b>根据角色信息查找其所对应的的首页面菜单列表</b>
	 * @param role
	 * @return
	 * @throws Exception
	 */
	List<Menu> getMenuListForIndex(Role role) throws Exception;
}
