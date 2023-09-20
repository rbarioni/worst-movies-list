package com.texoit.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texoit.model.Movie;
import com.texoit.repository.MovieRepository;
import com.texoit.response.MovieWinnerResponse;
import com.texoit.response.MoviesResponse;

@Service
public class MoviesService {

	@Autowired
	private MovieRepository repository;

	public MoviesResponse listWinners() {
		//recupera apenas os filmes premiados
		List<Movie> movies = repository.findAllByWinner(true);

		//lista com apenas os produtorers dos filmes
		List<String> producers = movies.stream().map(Movie::getProducers).collect(Collectors.toList());

		//conjunto com todos os nomes de produtores premiados, sem repetições
		Set<String> producerNames = new HashSet<>();
		String[] names;
		for (String producer : producers) {
			names = producer.split(",| and ");
			for (int i = 0; i < names.length; i++) {
				//para evitar algum espaço em branco que exista no arquivo
				if(!names[i].trim().equalsIgnoreCase("")) {
					producerNames.add(names[i].trim());
				}
			}
		}

		//separa os filmes premiados por produtor
		Map<String, List<Movie>> moviesProducer = new HashMap<>();
		List<Movie> moviesByProducer;
		for (String name : producerNames) {
			moviesByProducer = movies.stream().filter(m -> m.getProducers().contains(name)).sorted(Comparator.comparing(Movie::getYear)).collect(Collectors.toList());

			//interessa apenas produtores que ganharam o prêmio ao menos 2 vezes, já ordenado pelo ano
			if(moviesByProducer.size() > 1) {
				moviesProducer.put(name, moviesByProducer);
			}
		}

		for (String key : moviesProducer.keySet()) {
			System.out.println("*****");
			System.out.println("key:"+key);
			System.out.println("size:"+moviesProducer.get(key).size());

			for (Movie movie : moviesProducer.get(key)) {
				System.out.println(movie.getTitle());
				System.out.println(movie.getYear());
			}
		}

		//lógica para calcular os intervalos minimos e maximos entre premiações
		Map<String, List<Movie>> minInterval = new HashMap<>();
		Map<String, List<Movie>> maxInterval = new HashMap<>();
		int minDiff = Integer.MAX_VALUE;
		int maxDiff = 0;
		List<Movie> pMovies;
		Movie m1;
		Movie m2;
		int result;
		for (String key : moviesProducer.keySet()) {
			pMovies = moviesProducer.get(key);
			for (int i = 0; i < pMovies.size() - 1; i++) {
				m1 = pMovies.get(i);
				m2 = pMovies.get(i+1);
				result = m2.getYear() - m1.getYear();
				//caso diferença seja igual a mínima, apenas adiciona
				if(result == minDiff) {
					minInterval.put(key, Arrays.asList(m1, m2));
				}
				//caso diferença seja menor que a mínima, limpa o map e adiciona
				if(result < minDiff) {
					minInterval.clear();
					minInterval.put(key, Arrays.asList(m1, m2));

					minDiff = result;
				}

				//caso diferença seja igual a máxima, apenas adiciona
				if(result == maxDiff) {
					maxInterval.put(key, Arrays.asList(m1, m2));
				}
				//caso diferença seja maior que a máxima, limpa o map e adiciona
				if(result > maxDiff) {
					maxInterval.clear();
					maxInterval.put(key, Arrays.asList(m1, m2));

					maxDiff = result;
				}
			}
		}

		//monta o objeeto de retorno
		MoviesResponse response = new MoviesResponse();
		List<MovieWinnerResponse> min = new ArrayList<>();
		List<MovieWinnerResponse> max = new ArrayList<>();
		MovieWinnerResponse winner;
		for (Map.Entry<String, List<Movie>> entry : minInterval.entrySet()) {
			String producer = entry.getKey();
			List<Movie> producersMovies = entry.getValue();

			winner = new MovieWinnerResponse();
			winner.setProducer(producer);
			winner.setPreviousWin(producersMovies.get(0).getYear());
			winner.setFollowingWin(producersMovies.get(1).getYear());
			winner.setInterval(producersMovies.get(1).getYear() - producersMovies.get(0).getYear());

			min.add(winner);
		}
		for (Map.Entry<String, List<Movie>> entry : maxInterval.entrySet()) {
			String producer = entry.getKey();
			List<Movie> producersMovies = entry.getValue();

			winner = new MovieWinnerResponse();
			winner.setProducer(producer);
			winner.setPreviousWin(producersMovies.get(0).getYear());
			winner.setFollowingWin(producersMovies.get(1).getYear());
			winner.setInterval(producersMovies.get(1).getYear() - producersMovies.get(0).getYear());

			max.add(winner);
		}

		response.setMin(min);
		response.setMax(max);
		return response;
	}
}