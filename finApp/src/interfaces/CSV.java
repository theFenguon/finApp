package interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import config.Config;
import utility.Util;

public class CSV {
		
	// importCSV2Arr ----------------------------------------------------------
	// Calls (2) methods: 
	// (1) Imports CSV to nested list
	// (2) Converts nested list to multidimensional array
	//
	// INPUTS
	// 			ticker = ticker symbol
	// OUTPUTS
	//			arr = multidimensional output array
	public static String[][] importCSV2Arr (String category, String ticker){
		
		List<List<String>> data = new ArrayList<>();
		
		String filePath = null;
		
			// Construct filepath
			switch(category) {
			
				// Company Financials
				case "financials":
					filePath = Config.CSVpath + ticker + "_Financials.csv";
				break;
				
				// Ticker Symbols and CIK
				case "TickerCIK":
					filePath = Config.CSVpath + "TickerCIK.csv";
				break;
				
			} //switch
			
		// Import CSV data as a nested list (list of lists)
		try {
			data = CSV.CSVParser(filePath);
		} catch (Exception e) {
			System.out.println(e.getClass());
		}
				
		// Transfer nested list to array
		String[][] arr = CSV.list2Arr(data);
						
		return arr;
		
	}
	
	// importCSV2Arr ----------------------------------------------------------
	// Calls (2) methods: 
	// (1) Imports CSV to nested list
	// (2) Converts nested list to multidimensional array
	//
	// INPUTS
	// 			ticker = ticker symbol
	// OUTPUTS
	//			arr = multidimensional output array
	public static String[][] importCSV2Arr (String category){
		
		List<List<String>> data = new ArrayList<>();
		
		String filePath = null;
		
		
		// Construct filepath
		switch(category) {
			
			// Ticker Symbols and CIK
			case "TickerCIK":
				filePath = Config.CSVpath + "TickerCIK.csv";
			break;
			
		} //switch
		
		
		// Import CSV data as a nested list (list of lists)
		data = CSV.CSVParser(filePath);
				
		// Transfer nested list to array
		String[][] arr = CSV.list2Arr(data);
						
		return arr;
		
	}
	
	// CSV Parser -------------------------------------------------------------
	// Imports data from .csv file and stores as a nested list
	// INPUTS
	// 			String ticker = ticker symbol used to generate file name
	// OUTPUTS
	//			data = nested list of data
	public static List<List<String>> CSVParser (String filePath) {
		File file = null;
		
		try {
	    file= new File(filePath);
		} catch (Exception e) {
			System.out.println(e.getClass());
		}
	
	    // this gives you a 2-dimensional array of strings
	    List<List<String>> data = new ArrayList<>();
	    Scanner inputStream;
	
	    try{
	        inputStream = new Scanner(file);
	
	        while(inputStream.hasNext()){
	            String line= inputStream.next();
	            String[] values = line.split(",");
	            // this adds the currently parsed line to the 2-dimensional string array
	            data.add(Arrays.asList(values));
	        }
	
	        inputStream.close();
	        
	    }catch (FileNotFoundException e) {
	        //e.printStackTrace();
	    	//System.out.println(e.getClass());
	    	Util.print("Not Found");
	    }
	       
	    return data;
	
	} // CSVParser
	
	
	
	
	
	// list2Arr ---------------------------------------------------------------
	// Converts nested list into a multidimensional array
	// INPUTS
	// 			list = nested list
	// OUTPUTS
	//			arr = multidimensional output array
	public static String[][] list2Arr (List<List<String>> list) {
		
		String[][] arr = new String[list.size()][];
		String[] blankArray = new String[0];
		
		for(int i = 0; i < list.size(); i++) {
			arr[i] = list.get(i).toArray(blankArray);
		}
		
		return arr;
	} // list2Arr
	
	
	
	
} //CSV Interface
