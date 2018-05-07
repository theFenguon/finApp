package entity;

import finToolbox.FinCalcs;

// Placeholder for Company Class
public class C {
	
	// TODO: Constructor should take in a ticker symbol and pull all of these (and more) values from a database
	
	public static boolean paysDividends 		= true;
	
	public static double debtOutstanding 		= 26675;			// [$MM]
	public static double sharesOutstanding 		= 2690;				// [shares]
	public static double longTermBondYield  	= 2.980/100;		// [%]
	public static double marketCapitalization 	= 345083;			// [$MM]
	public static double beta					= 0.530;			// [unitless]
	public static double terminalGrowthRate		= 2.000/100;		// [%]
	
	public static double WACC 					= FinCalcs.WACC();	// [%]
	
}