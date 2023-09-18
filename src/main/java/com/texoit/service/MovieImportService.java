package com.texoit.service;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.texoit.model.CSVMovie;

import jakarta.annotation.PostConstruct;

@Service
public class MovieImportService {

	@PostConstruct
	public void init() {
		System.out.println("teste");
		
		try {

			// Hashmap to map CSV data to
	        // Bean attributes.
	        Map<String, String> mapping = new
	                      HashMap<String, String>();
	        mapping.put("year", "year");
	        mapping.put("title", "title");
	        mapping.put("studios", "studios");
	        mapping.put("producers", "producers");
	        mapping.put("winner", "winner");
	 
	        // HeaderColumnNameTranslateMappingStrategy
	        // for Student class
	        HeaderColumnNameTranslateMappingStrategy<CSVMovie> strategy =
	             new HeaderColumnNameTranslateMappingStrategy<CSVMovie>();
	        strategy.setType(CSVMovie.class);
	        strategy.setColumnMapping(mapping);

			FileReader reader = new FileReader("movielist.csv");
			CSVReader csv = new CSVReaderBuilder(reader).withSkipLines(1).build();
			List<String[]> allData = csv.readAll();
			for (String[] row : allData) {
                for (String cell : row) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }

			CsvToBean builder = new CsvToBeanBuilder(reader).withMappingStrategy(strategy).withSkipLines(1).build();

	        // call the parse method of CsvToBean
	        // pass strategy, csvReader to parse method
	        List<CSVMovie> list = builder.parse();
	        
	        for (CSVMovie e : list) {
	            System.out.println(e);
	        }
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}