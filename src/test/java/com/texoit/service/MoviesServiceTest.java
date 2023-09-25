package com.texoit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.texoit.model.Movie;
import com.texoit.repository.MovieRepository;
import com.texoit.response.MoviesResponse;

@ExtendWith(MockitoExtension.class)
class MoviesServiceTest {

	MoviesService service;

	@Mock
	MovieRepository repository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		service = new MoviesService();
	}

	//TODO: por algum motivo o jUnit está falhando por não conseguir iniciar o mock do repository
	//sempre lança a exceção: java.lang.NullPointerException: Cannot invoke "com.texoit.repository.MovieRepository.findAllByWinner(boolean)" because "this.repository" is null
	//mas, a lógica do teste está feita, com o mock do método do repository e o resultado esperado após executar a classe Service

    @Test
    public void test_listWinners() {
        when(repository.findAllByWinner(true)).thenReturn(getMovies());

        MoviesResponse result = service.listWinners();

    	assertThat(result).isNotNull();
    	assertThat(result.getMin().size()).isEqualTo(2);
    	assertThat(result.getMax().size()).isEqualTo(1);
    }

    private List<Movie> getMovies() {
    	List<Movie> movies = new ArrayList<>();
    	Movie m1 = new Movie();
    	m1.setTitle("Movie 1");
    	m1.setYear(1990);
    	m1.setWinner(true);
    	m1.setStudios("Studio 1");
    	m1.setProducers("Producer A");
    	movies.add(m1);

    	Movie m2 = new Movie();
    	m2.setTitle("Movie 2");
    	m2.setYear(2000);
    	m2.setWinner(true);
    	m2.setStudios("Studio 2");
    	m2.setProducers("Producer A");
    	movies.add(m2);

    	Movie m3 = new Movie();
    	m3.setTitle("Movie 3");
    	m3.setYear(2010);
    	m3.setWinner(true);
    	m3.setStudios("Studio 3");
    	m3.setProducers("Producer B");
    	movies.add(m3);

    	Movie m4 = new Movie();
    	m4.setTitle("Movie 4");
    	m4.setYear(2011);
    	m4.setWinner(true);
    	m4.setStudios("Studio 4");
    	m4.setProducers("Producer B");
    	movies.add(m4);

    	Movie m5 = new Movie();
    	m5.setTitle("Movie 5");
    	m5.setYear(2015);
    	m5.setWinner(true);
    	m5.setStudios("Studio 5");
    	m5.setProducers("Producer C");
    	movies.add(m5);

    	Movie m6 = new Movie();
    	m6.setTitle("Movie 6");
    	m6.setYear(2016);
    	m6.setWinner(true);
    	m6.setStudios("Studio 6");
    	m6.setProducers("Producer C");
    	movies.add(m6);

    	return movies;
    }
}