package cn.ekgc.dkscm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.ekgc.dkscm.controller.base.BaseController;

/**
 * <b>用户模块控制器</b>
 * @author Arthur
 * @version 2.0.0 2019-11-19
 * @since 2019-11-19
 */
@Controller("userController")
@RequestMapping("/user")
public class UserController extends BaseController {
	
	/**
	 * <b>转发加载用户登录界面</b>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String forwardUserLoginForm() throws Exception {
		return "user/user_login";
	}
	
	/**
	 * <b>登录错误，系统重定向到退出</b>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginError() throws Exception {
		// 登录错误，系统重定向
		return "redirect:logout";
	}
}
