package com.texoit.model;

import com.opencsv.bean.CsvBindByName;

public class CSVMovie {

	@CsvBindByName
	private Integer year;
	
	@CsvBindByName
	private String title;
	
	@CsvBindByName
	private String studios;
	
	@CsvBindByName
	private String producers;
	
	@CsvBindByName
	private boolean winner;

	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStudios() {
		return studios;
	}
	public void setStudios(String studios) {
		this.studios = studios;
	}
	public String getProducers() {
		return producers;
	}
	public void setProducers(String producers) {
		this.producers = producers;
	}
	public boolean getWinner() {
		return winner;
	}
	public void setWinner(boolean winner) {
		this.winner = winner;
	}
	@Override
	public String toString() {
		return "CSVMovie [year=" + year + ", title=" + title + ", studios=" + studios + ", producers=" + producers
				+ ", winner=" + winner + "]";
	}
	
}