package com.texoit.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.texoit.model.CSVMovie;

import jakarta.annotation.PostConstruct;

@Service
public class MovieImportService {

	@PostConstruct
	public void init() {
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

			//FileReader reader = new FileReader("movielist.csv");
			InputStream is = getClass().getClassLoader().getResourceAsStream("movielist.csv");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
//			CSVReader csv = new CSVReaderBuilder(br).withSkipLines(1).build();
//			List<String[]> allData = csv.readAll();
//			System.out.println("****START");
//			for (String[] row : allData) {
//                for (String cell : row) {
//                    System.out.print(cell + "\t");
//                }
//                System.out.println();
//            }
//			System.out.println("****END");

			CsvToBean builder = new CsvToBeanBuilder(br).withMappingStrategy(strategy).withSkipLines(1).withSeparator(';').build();

	        // call the parse method of CsvToBean
	        // pass strategy, csvReader to parse method
	        List<CSVMovie> list = builder.parse();
	        System.out.println("****START");
	        for (CSVMovie e : list) {
	            System.out.println(e);
	        }
	        System.out.println("****END");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}