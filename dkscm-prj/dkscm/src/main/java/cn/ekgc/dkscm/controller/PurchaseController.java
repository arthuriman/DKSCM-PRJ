package cn.ekgc.dkscm.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ekgc.dkscm.controller.base.BaseController;
import cn.ekgc.dkscm.pojo.entity.Purchase;
import cn.ekgc.dkscm.pojo.entity.Status;
import cn.ekgc.dkscm.pojo.entity.User;
import cn.ekgc.dkscm.pojo.vo.Page;
import cn.ekgc.dkscm.service.PurchaseService;
import cn.ekgc.dkscm.util.ConstantUtil;

/**
 * <b>采购模块控制器</b>
 * @author Arthur
 * @version 2.0.0 2019-11-20
 * @since 2019-11-20
 */
@Controller("purchaseController")
@RequestMapping("/purchase")
public class PurchaseController extends BaseController {
	@Resource(name = "purchaseService")
	private PurchaseService purchaseService;
	
	/**
	 * <b>根据采购状态转发到相应的页面</b>
	 * @param statusCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{statusCode}/index", method = RequestMethod.GET)
	public String forwardIndexByStatusCode(@PathVariable("statusCode")String statusCode) 
			throws Exception {
		// 因为使用了异步请求，因此对于该处理，仅仅是转发到列表页面
		if (ConstantUtil.STATUS_APPLY.equals(statusCode)) {
			// 已申请，未审批
			return "purchase/purchase_apply_index";
		} else if (ConstantUtil.STATUS_REVIEW.equals(statusCode)) {
			// 已审批，未采购
			return "purchase/purchase_review_index";
		} else if (ConstantUtil.STATUS_PURCHASE.equals(statusCode)) {
			// 已采购，未审批入库
			return "purchase/purchase_purchase_index";
		} else if (ConstantUtil.STATUS_STORE_REVIEW.equals(statusCode)) {
			// 已审批入库，未入库
			return "purchase/purchase_store_review_index";
		} else if (ConstantUtil.STATUS_STORE.equals(statusCode)) {
			// 已入库，未领取
			return "purchase/purchase_store_index";
		} else if (ConstantUtil.STATUS_RECEIVE.equals(statusCode)) {
			// 已领取
			return "purchase/purchase_receive_index";
		} else if (ConstantUtil.STATUS_REVIEW_BACK.equals(statusCode)) {
			// 申请驳回
			return "purchase/purchase_review_back_index";
		} else if (ConstantUtil.STATUS_STORE_BACK.equals(statusCode)) {
			// 入库申请驳回
			return "purchase/purchase_store_back_index";
		} else if (ConstantUtil.STATUS_APPLY_BACK.equals(statusCode)) {
			// 申请撤回
			return "purchase/purchase_store_back_index";
		}
		return "";
	}
	
	/**
	 * <b>根据采购状态查询相应的分页列表</b>
	 * @param pageNum
	 * @param pageSize
	 * @param draw
	 * @param statusCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{statusCode}/list", method = RequestMethod.POST)
	@ResponseBody
	public Page<Purchase> getPurchaseListByStatusCodeForPage(Integer pageNum, Integer pageSize, 
			Integer draw, @PathVariable("statusCode")String statusCode) throws Exception {
		// 使用分页信息封装分页对象
		Page<Purchase> page = new Page<Purchase>(pageNum, pageSize, draw);
		// 根据分页对象和采购状态查询对应的数据信息
		page = purchaseService.getPurchasePageByStatusCode(page, statusCode);
		
		return page;
	}
	
	/**
	 * <b>转发物资采购申请页面</b>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/apply", method = RequestMethod.GET)
	public String forwardPurchaseApplyPage() throws Exception {
		// 因为在加载申请页面的时候，是不需要相应的数据，直接转发即可
		return "purchase/purchase_apply";
	}
	
	/**
	 * <b>提出采购申请</b>
	 * @param purchase
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	@ResponseBody
	public boolean savePurchaseApply(Purchase purchase) throws Exception {
		// 获得当前登录用户，作为申请人信息
		User user = (User) session.getAttribute("user");
		// 对于校验来说，前端校验有可能失效，因此无论有无前端校验，都必须有后端校验
		// 校验通过后，使用业务层进行保存
		return purchaseService.savePurchase(purchase, user);
	}
	
	/**
	 * <b>加载审批界面</b>
	 * @param purchaseId
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/review/{purchaseId}", method = RequestMethod.GET)
	public String forwardReviewPage(@PathVariable("purchaseId")Long purchaseId, ModelMap map) 
			throws Exception {
		// 在打开审批页面的时候，同时也需要传递此时采购的id
		map.put("purchaseId", purchaseId);
		return "purchase/purchase_review";
	}
	
	/**
	 * <b>进行申请审批</b>
	 * @param purchaseId
	 * @param reviewRemark
	 * @param statusCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/review/{purchaseId}", method = RequestMethod.POST)
	@ResponseBody
	public boolean reviewPurchase(@PathVariable("purchaseId")Long purchaseId, String reviewRemark, String statusCode) 
			throws Exception {
		// 通过状态编码封装状态对象
		Status status = new Status();
		status.setStatusCode(statusCode);
		// 创建Purchase对象
		Purchase purchase = new Purchase();
		purchase.setPurchaseId(purchaseId);
		purchase.setReviewRemark(reviewRemark);
		purchase.setStatus(status);
		// 获得当前登录用户
		User reviewer = (User) session.getAttribute("user");
		purchase.setReviewer(reviewer);
		// 审批时间
		purchase.setReviewTime(new Date());
		
		return purchaseService.updatePurchase(purchase);
	}
	
	/**
	 * <b>加载购买页面</b>
	 * @param purchaseId
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/buy/{purchaseId}", method = RequestMethod.GET)
	public String forwardPurchasePage(@PathVariable("purchaseId")Long purchaseId, ModelMap map) 
			throws Exception {
		map.put("purchaseId", purchaseId);
		return "purchase/purchase_buy";
	}
	
	/**
	 * <b>购买物资</b>
	 * @param purchase
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	@ResponseBody
	public boolean buyPurchase(Purchase purchase) throws Exception {
		// 获得当前登录用户
		User purchaser = (User) session.getAttribute("user");
		purchase.setPurchaser(purchaser);
		purchase.setPurchaseTime(new Date());
		return purchaseService.updatePurchase(purchase);
	}
}
