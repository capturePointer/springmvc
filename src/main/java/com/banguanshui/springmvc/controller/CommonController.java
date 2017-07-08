package com.banguanshui.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/common")
public class CommonController {

	@RequestMapping(value="/error")
	public String error(Model model,
			HttpServletRequest request,
			HttpServletResponse response) {
	//	model.addAttribute("jumpUrl", request.getAttribute("jumpUrl"));
	//	model.addAttribute("jumpMsg", request.getAttribute("jumpMsg"));
		return "common/error";
	}

	@RequestMapping(value="/success")
	public String success() {
		return "common/success";
	}
}
