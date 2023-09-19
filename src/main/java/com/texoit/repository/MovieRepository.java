package com.texoit.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.texoit.model.Movie;

@Repository
public interface MovieRepository extends CrudRepository<Movie, String>{

	@Override
    List<Movie> findAll();

    List<Movie> findAllByWinner(boolean winner);
}