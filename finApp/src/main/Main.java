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
		
		String[] roster = new String[2];
		roster[0] = "JNJ";
		roster[1] = "NKE";
		
		Company c;
		TestBench t = new TestBench();
		
		for (int i = 0; i<roster.length; i++) {
			Util.print(roster[i]);
			c = new Company(roster[i]);
			t.testCompany(c);
			Util.print("\n");
		}
		
		
		/*
		String[][] tickerList = CSV.importCSV2Arr("TickerCIK");
		
		for (int i = 0; i<tickerList.length; i++) {
			Util.print(tickerList[i][0]);
			c = new Company(tickerList[i][0]);
			t.testCompany(c);
			Util.print("\n");
		}
		*/
		
		// TODO: cycle through companies and get their valuation vs. market price
		// TODO: track industries and sectors in a market-wide calculations
		
		
	} // method main
} // class Main