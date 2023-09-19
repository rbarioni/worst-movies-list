package com.texoit.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.texoit.model.CSVMovie;
import com.texoit.model.Movie;
import com.texoit.repository.MovieRepository;

import jakarta.annotation.PostConstruct;

@Service
public class MovieImportService {

	@Autowired
	private MovieRepository repository;

	@PostConstruct
	public void init() {
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream("movielist.csv");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			CsvToBean<CSVMovie> builder = new CsvToBeanBuilder<CSVMovie>(br).withType(CSVMovie.class).withSeparator(';').build();
	        List<CSVMovie> list = builder.parse();

	        Movie movie;
	        for (CSVMovie m : list) {
	            movie = new Movie();
	            movie.setProducers(m.getProducers());
	            movie.setStudios(m.getStudios());
	            movie.setTitle(m.getTitle());
	            movie.setWinner(m.getWinner());
	            movie.setYear(m.getYear());

	            repository.save(movie);
	        }
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}