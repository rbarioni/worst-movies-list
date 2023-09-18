package com.texoit.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import jakarta.annotation.PostConstruct;

@Configuration
public class Database {

	@Autowired
	private JdbcTemplate template;
	
	@PostConstruct
	private void init() {
		String sqls[] = {
				"DROP TABLE movies IF EXISTS",
				"CREATE TABLE movies(id serial, movie_year integer, movie_title varchar(255), movie_studios varchar(255), movie_producers varchar(255), winner boolean, PRIMARY KEY (id))"
		};

		Arrays.asList(sqls).forEach(sql -> {
			template.execute(sql);
		});
	}
}
