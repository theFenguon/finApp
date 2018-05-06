package testBench;

import finToolbox.FinModel;

public class Main {
	public static void main (String[] args) {		

		// DCF ----------------------------------------------------------------
		// Discounted Cash Flow with Stable Growth
		double initPV 			= 12424;		// [$MM] Initial Present Value (from Financials)
		double FCFGrowthRate 	= 2.300/100;	// [%] Assumed constant growth rate (from Financials)
		int nPeriods 			= 10;			// Number of periods to evaluate (user defined)	
		FinModel.DCF(initPV, FCFGrowthRate, nPeriods);
		
		// Discounted Cash Flow with Variable Growth
		double[] FCFGrowthRateArr = {
				2.300/100, 
				2.300/100, 
				2.300/100, 
				2.300/100, 
				2.300/100, 
				5.000/100, 
				5.000/100, 
				5.000/100, 
				5.000/100, 
				5.000/100};
		FinModel.DCF(initPV, FCFGrowthRateArr, nPeriods);
		
		
		// DDM ----------------------------------------------------------------
		// Dividend Discount Model with Stable Growth
		double initDiv			= 2.08;			// [$] Initial dividend
		double DivGrowthRate	= 2.000/100;	// [%] Assumed constant growth rate (from Financials)
		FinModel.DDM_Stable(initDiv, DivGrowthRate);
		
		// Dividend Discount Model with Variable Growth
		nPeriods 				= 1;			// Number of periods to evaluate (user defined)			
		double[] DivGrowthRateArr = {
				5.000/100, 
				5.000/100, 
				5.000/100, 
				5.000/100, 
				5.000/100, 
				5.000/100, 
				5.000/100, 
				5.000/100, 
				5.000/100, 
				5.000/100};
		FinModel.DDM_Multi(initDiv, DivGrowthRateArr, nPeriods);
		
		// EPS_PE Stable ------------------------------------------------------
		FinModel.EPS_PE_Stable(1.5, (10.000/100), 12, 5);
		
		// Graham Guidance ----------------------------------------------------
		// Graham Guidance for stock price
		double EPS 	= 10.35;
		double BV 	= 85.73;
		FinModel.Graham(EPS, BV);
		
	} // method main
} // class Main
