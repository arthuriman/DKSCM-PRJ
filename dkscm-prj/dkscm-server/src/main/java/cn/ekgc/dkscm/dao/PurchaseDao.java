package cn.ekgc.dkscm.dao;

import java.util.List;
import java.util.Map;

import cn.ekgc.dkscm.pojo.entity.Purchase;

/**
 * <b>菜单模块数据持久层接口</b>
 * @author Arthur
 * @version 2.0.0 2019-11-19
 * @since 2019-11-19
 */
public interface PurchaseDao {
	/**
	 * <b>根据查询条件获得采购列表</b>
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List<Purchase> findPurchaseListByQuery(Map<String, Object> paramMap) throws Exception;

	/**
	 * <b>保存申请信息</b>
	 * @param purchase
	 * @throws Exception
	 */
	void savePurchase(Purchase purchase) throws Exception;

	/**
	 * <b>修改采购信息</b>
	 * @param purchase
	 * @throws Exception
	 */
	void updatePurchase(Purchase purchase) throws Exception;
}
