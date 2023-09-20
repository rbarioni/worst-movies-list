package com.texoit.response;

import java.util.List;

public class MoviesResponse {

	private List<MovieWinnerResponse> min;
	private List<MovieWinnerResponse> max;

	public List<MovieWinnerResponse> getMin() {
		return min;
	}
	public void setMin(List<MovieWinnerResponse> min) {
		this.min = min;
	}
	public List<MovieWinnerResponse> getMax() {
		return max;
	}
	public void setMax(List<MovieWinnerResponse> max) {
		this.max = max;
	}
}