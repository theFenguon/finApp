package entity;

import enums.Industry;
import enums.Sector;
import finToolbox.FinCalcs;

// Placeholder for Company Class
public class Company {
	
	// TODO: Constructor should take in a ticker symbol and pull all of these (and more) values from a database
	
	Sector s 	= Sector.CONSUMER_DISCRETIONARY;
	Industry i 	= Industry.AUTO_COMPONENTS;
	
	public Company (String name, double cashFlow, int recentQuater){
	   
	}
	public static boolean paysDividends 		= true;
	
	public static double debtOutstanding 		= 26675;			// [$MM]
	public static double sharesOutstanding 		= 2690;				// [shares]
	public static double longTermBondYield  	= 2.980/100;		// [%]
	public static double marketCapitalization 	= 345083;			// [$MM]
	public static double beta					= 0.530;			// [unitless]
	public static double terminalGrowthRate		= 2.000/100;		// [%]
	
	public static double WACC 					= FinCalcs.WACC();	// [%]
	
	
}