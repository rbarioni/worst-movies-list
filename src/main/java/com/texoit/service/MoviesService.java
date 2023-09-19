package com.texoit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texoit.model.Movie;
import com.texoit.repository.MovieRepository;

@Service
public class MoviesService {

	@Autowired
	private MovieRepository repository;

	public List<Movie> findAll() {
		List<Movie> movies = repository.findAllByWinner(true);
		
		for (Movie movie : movies) {
			System.out.println(movie);
		}
		return movies;
	}
}