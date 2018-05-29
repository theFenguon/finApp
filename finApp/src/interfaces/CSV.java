package interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import config.Config;

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
	public static String[][] importCSV2Arr (String ticker){
		
		List<List<String>> data = new ArrayList<>();
		
		// Import CSV data as a nested list (list of lists)
		data = CSV.CSVParser(ticker);
				
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
	public static List<List<String>> CSVParser (String ticker) {
		String fileName = Config.CSVpath + ticker + "_Financials.csv";
	    File file= new File(fileName);
	
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
	        e.printStackTrace();
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
