package cn.ekgc.dkscm.dao;

import java.util.List;
import java.util.Map;

import cn.ekgc.dkscm.pojo.entity.Menu;

/**
 * <b>菜单模块数据持久层接口</b>
 * @author Arthur
 * @version 2.0.0 2019-11-19
 * @since 2019-11-19
 */
public interface MenuDao {
	/**
	 * <b>根据查询条件查询菜单信息列表</b>
	 * @param params
	 * @return
	 * @throws Exception
	 */
	List<Menu> findListByQuery(Map<String, Object> params) throws Exception;
}
