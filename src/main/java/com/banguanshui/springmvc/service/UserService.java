package com.banguanshui.springmvc.service;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Map getUserByName(String name) {
		String sql = "select * from user where name=?";
		Map data = this.jdbcTemplate.queryForMap(sql, name);
		return data;
	}
}
