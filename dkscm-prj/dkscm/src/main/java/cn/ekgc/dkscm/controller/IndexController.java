package cn.ekgc.dkscm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.ekgc.dkscm.controller.base.BaseController;
import cn.ekgc.dkscm.pojo.entity.Menu;
import cn.ekgc.dkscm.pojo.entity.Role;
import cn.ekgc.dkscm.pojo.entity.User;
import cn.ekgc.dkscm.service.MenuService;

/**
 * <b>首页面模块控制器</b>
 * @author Arthur
 * @version 2.0.0 2019-11-20
 * @since 2019-11-20
 */
@Controller("indexController")
public class IndexController extends BaseController {
	@Resource(name = "menuService")
	private MenuService menuService;
	
	/**
	 * <b>使用当前登录用户查找该用户所对应的首页面菜单列表</b>
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap modelMap) throws Exception {
		// 获得当前登录用户信息
		User user = (User) session.getAttribute("user");
		// 通过当前登录用户信息得到其角色信息
		Role role = user.getRole();
		// 通过角色信息查询该角色所对应的的首页面菜单列表
		List<Menu> menuList = menuService.getMenuListForIndex(role);
		// 将该菜单列表放入ModelMap中，转发到页面
		modelMap.put("menuList", menuList);
		
		return "index";
	}
}
