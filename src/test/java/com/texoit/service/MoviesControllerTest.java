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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.texoit.controller.MoviesController;
import com.texoit.response.MovieWinnerResponse;
import com.texoit.response.MoviesResponse;

@ExtendWith(MockitoExtension.class)
class MoviesControllerTest {

	MoviesController controller;

	@Mock
	MoviesService service;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		controller = new MoviesController();
	}

	//TODO: por algum motivo o jUnit está falhando por não conseguir iniciar o mock do service
	//sempre lança a exceção: java.lang.NullPointerException: Cannot invoke "com.texoit.service.MoviesService.listWinners()" because "this.service" is null
	//mas, a lógica do teste está feita, mesmo a lógica do controller ser bem simples, apenas retornando o resultado da classe Service

    @Test
    public void test_listWinners() {
        when(service.listWinners()).thenReturn(listMovies());

        ResponseEntity<MoviesResponse> result = controller.list();

    	assertThat(result).isNotNull();
    	assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    	assertThat(result.getBody().getMin().size()).isEqualTo(2);
    	assertThat(result.getBody().getMax().size()).isEqualTo(1);
    }

    private MoviesResponse listMovies() {
		MoviesResponse response = new MoviesResponse();
		List<MovieWinnerResponse> min = new ArrayList<>();
		List<MovieWinnerResponse> max = new ArrayList<>();

		MovieWinnerResponse winner1 = new MovieWinnerResponse();
		winner1.setProducer("Producer A");
		winner1.setPreviousWin(1990);
		winner1.setFollowingWin(1991);
		winner1.setInterval(1);
		min.add(winner1);

		MovieWinnerResponse winner2 = new MovieWinnerResponse();
		winner2.setProducer("Producer B");
		winner2.setPreviousWin(2000);
		winner2.setFollowingWin(2001);
		winner2.setInterval(1);
		min.add(winner2);

		MovieWinnerResponse winner3 = new MovieWinnerResponse();
		winner3.setProducer("Producer C");
		winner3.setPreviousWin(2000);
		winner3.setFollowingWin(2022);
		winner3.setInterval(1);
		max.add(winner3);

		response.setMin(min);
		response.setMax(max);

		return response;
    }
}