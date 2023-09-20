package com.texoit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.texoit.response.MoviesResponse;
import com.texoit.service.MoviesService;

@RestController
@RequestMapping("/movies")
public class MoviesController {

	@Autowired
	private MoviesService service;

	@GetMapping
	public ResponseEntity<MoviesResponse> list() {
		return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(service.listWinners());
	}
}