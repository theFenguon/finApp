package main;

import testBench.TestBench;
import utility.Util;
import entity.Company;
import interfaces.CSV;

// TODO: Run sensitivity analysis to see how closely the percentages can be estimated given the financials
// e.g. if the financials are rounded to the nearest million, how much does that impact the calculated
// percentages? Since DDM is very sensitive to slight changes in percentage, this will help put error bars
// or confidence intervals on the DDM data (and DCF data).

public class Main {
	public static String test;
	public static void main (String[] args) {		
		
		// Instantiates a test bench to test material out
		//TestBench t = new TestBench();
		//t.runTest();		
		
		//String[][] data = CSV.importCSV2Arr("JNJ");
		//Util.print(data[0][0]);
		
		Company c = new Company("JNJ");
		//Util.print(c.years[1]);
		
		
		// TODO: cycle through companies and get their valuation vs. market price
		// TODO: track industries and sectors in a market-wide calculations
		
		
	} // method main
} // class Main