package com.banguanshui.springmvc.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * Home
 *
 */
@Controller
public class HomeController 
{
	@RequestMapping("/")
    public String index()
    {
		return "home/index";
    }
}
