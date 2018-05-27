package main;

import testBench.TestBench;
import utility.Util;

import java.util.ArrayList;
import java.util.List;

import finToolbox.Categories;
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
		
		
		String[][] data = CSV.importCSV2Arr("JNJ");
		Util.print(data[0][0]);

		
	} // method main
} // class Main
