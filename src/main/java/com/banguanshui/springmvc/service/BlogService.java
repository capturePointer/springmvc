package com.banguanshui.springmvc.service;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.banguanshui.springmvc.model.Blog;


@Service
public class BlogService {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List getAllBlog() {
		List blogList = this.jdbcTemplate
				.queryForList("select * from blog order by id desc");
		return blogList;
	}
	
	public Map getBlogById(int id) {
		Map blog = this.jdbcTemplate
				.queryForMap("select * from blog where id = ?",new Object[] {id});
		return blog;
	}

	public void createBlog(Blog blog) {
		this.jdbcTemplate.update(
	        "insert into blog (title, body, create_time, image) values (?, ?, ?, ?)",
	        blog.getTitle(), blog.getBody(), blog.getCreate_time(),blog.getImage());
	}
	
	public void updateBlogById(Blog blog, int id) {
		this.jdbcTemplate.update(
				"update blog set title=?,image=?,body=? where id=?",
				blog.getTitle(),blog.getImage(),blog.getBody(),id
				);
	}
	
	public void deleteBlogById(int id) {
		this.jdbcTemplate.update(
				"delete from blog where id=?",
				id
				);
	}
}
