package com.banguanshui.springmvc.controller;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banguanshui.springmvc.model.Blog;
import com.banguanshui.springmvc.service.BlogService;

import com.banguanshui.springmvc.common.Helper;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private Helper helper;
	
	@RequestMapping("/list")
    public String list(Model model)
    {
		List lists = this.blogService.getAllBlog();
		for(Object o : lists){
			Map b = (Map)o;
			b.put("create_time", this.helper.timestampToDate((Integer)b.get("create_time")));
		}
		model.addAttribute("lists",lists);
		return "blog/list";
    }
	
	@RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
	public String read(@PathVariable("id") int id, Model model) {
		Map data = this.blogService.getBlogById(id);
		data.put("create_time", this.helper.timestampToDate((Integer)data.get("create_time")));
		model.addAttribute("data",data);
		return "blog/read";
	}
	

	@RequestMapping("/create")
	public String create() {
		return "blog/create";
	}
	
	@RequestMapping(value="/doCreate")
	public String doCreate(@ModelAttribute("blog") Blog blog,
			@RequestParam("imageTmp") MultipartFile imageTmp,
			HttpServletRequest request,
			HttpServletResponse response,
			ServletRequest req,
			ServletResponse res,
			Model model) throws IOException,ServletException {
		if(blog.getTitle().trim().equals("")) {
			//request.setAttribute("jumpUrl", "/blog/create");
	        //request.setAttribute("jumpMsg", "标题不能为空");
	        model.addAttribute("jumpUrl", "/blog/create");
	        model.addAttribute("jumpMsg", "标题不能为空");
			return "forward:/common/error";
		}
		if(blog.getBody().trim().equals("")) {
	        model.addAttribute("jumpUrl", "/blog/create");
	        model.addAttribute("jumpMsg", "内容不能为空");
			return "forward:/common/error";
		}
		if(imageTmp.isEmpty()) {
	        model.addAttribute("jumpUrl", "/blog/create");
	        model.addAttribute("jumpMsg", "图片不能为空");
			return "forward:/common/error";
		}
		
		
		
		blog.setCreate_time((int) (new Date().getTime()/1000));
		String fileUri = this.helper.handleUploadFile(imageTmp, request.getSession().getServletContext().getRealPath("uploads"));
		blog.setImage(fileUri);

		this.blogService.createBlog(blog);

        model.addAttribute("jumpUrl", "/blog/list");
        model.addAttribute("jumpMsg", "博客发布成功");
		return "forward:/common/success";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable("id") int id, Model model) {
		Map data = this.blogService.getBlogById(id);
		data.put("create_time", this.helper.timestampToDate((Integer)data.get("create_time")));
		model.addAttribute("data",data);
		return "blog/update";
	}

	@RequestMapping(value="/doUpdate",method=RequestMethod.POST)
	public String doUpdate(@ModelAttribute("blog") Blog blog,
			Model model) {

		if(blog.getTitle().trim().equals("")) {
			//request.setAttribute("jumpUrl", "/blog/create");
	        //request.setAttribute("jumpMsg", "标题不能为空");
	        model.addAttribute("jumpUrl", "/blog/update/" + blog.getId());
	        model.addAttribute("jumpMsg", "标题不能为空");
			return "forward:/common/error";
		}
		if(blog.getBody().trim().equals("")) {
	        model.addAttribute("jumpUrl", "/blog/update/" + blog.getId());
	        model.addAttribute("jumpMsg", "内容不能为空");
			return "forward:/common/error";
		}
		String fileUri = this.helper.handleUploadFile(imageTmp, request.getSession().getServletContext().getRealPath("uploads"));
		if(!fileUri.equals("")) {
			blog.setImage(fileUri);
		}
		this.blogService.updateBlogById(blog,blog.getId());
		return "redirect:/blog/list";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") int id) {
		this.blogService.deleteBlogById(id);
		return "redirect:/blog/list";
	}
	
	
	
}
