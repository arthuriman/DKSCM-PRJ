package cn.ekgc.dkscm.service;

import cn.ekgc.dkscm.pojo.entity.Purchase;
import cn.ekgc.dkscm.pojo.entity.User;
import cn.ekgc.dkscm.pojo.vo.Page;

/**
 * <b>采购模块业务层接口</b>
 * @author Arthur
 * @version 2.0.0 2019-11-19
 * @since 2019-11-19
 */
public interface PurchaseService {
	/**
	 * <b>根据采购状态分页查询采购分页对象</b>
	 * @param page
	 * @param statusCode
	 * @return
	 * @throws Exception
	 */
	Page<Purchase> getPurchasePageByStatusCode(Page<Purchase> page, String statusCode) throws Exception;

	/**
	 * <b>保存采购信息</b>
	 * @param purchase
	 * @param applicant
	 * @return
	 * @throws Exception
	 */
	boolean savePurchase(Purchase purchase, User applicant) throws Exception;

	/**
	 * <b>修改采购信息</b>
	 * @param purchase
	 * @return
	 * @throws Exception
	 */
	boolean updatePurchase(Purchase purchase) throws Exception;
}
