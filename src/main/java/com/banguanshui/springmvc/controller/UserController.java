package com.banguanshui.springmvc.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.banguanshui.springmvc.common.Helper;
import com.banguanshui.springmvc.service.BlogService;
import com.banguanshui.springmvc.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * User
 *
 */
@Controller
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private Helper helper;
	
	@RequestMapping("/login")
    public String login()
    {
		return "user/login";
    }

	@RequestMapping(value="/doLogin",method=RequestMethod.POST)
	public String doLogin(@RequestParam("name") String name,
			@RequestParam("pass") String pass,
			HttpServletRequest request,
			Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		if(name.trim().equals("")) {
	        model.addAttribute("jumpUrl", "/user/login");
	        model.addAttribute("jumpMsg", "用户名不能为空");
			return "forward:/common/error";
		}

		if(pass.trim().equals("")) {
	        model.addAttribute("jumpUrl", "/user/login");
	        model.addAttribute("jumpMsg", "密码不能为空");
			return "forward:/common/error";
		}
		
		Map data = this.userService.getUserByName(name);
		if(data != null) {
			if(this.helper.encoderByMd5(pass).equals(data.get("pass"))) {
				request.getSession().setAttribute("user", data);
		        model.addAttribute("jumpUrl", "/blog/list");
		        model.addAttribute("jumpMsg", "登陆成功，正跳转到博客列表");
				return "forward:/common/success";
			}
			else {
		        model.addAttribute("jumpUrl", "/user/login");
		        model.addAttribute("jumpMsg", "密码错误");
			}
		}
		else {
	        model.addAttribute("jumpUrl", "/user/login");
	        model.addAttribute("jumpMsg", "用户名不存在");
		}
		request.getSession().setAttribute("user", null);
		return "forward:/common/error";
	}
	
	@RequestMapping(value="/loginOut",method=RequestMethod.GET)
	public String loginOut(HttpServletRequest request) {
		request.getSession().setAttribute("user", null);
		return "redirect:/user/login";
	}
}
