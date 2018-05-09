package testBench;

import finToolbox.FinCalcs;
import finToolbox.FinModel;
import finToolbox.M;
import utility.Util;

// TODO: Run sensitivity analysis to see how closely the percentages can be estimated given the financials
// e.g. if the financials are rounded to the nearest million, how much does that impact the calculated
// percentages? Since DDM is very sensitive to slight changes in percentage, this will help put error bars
// or confidence intervals on the DDM data (and DCF data).

public class Main {
	public static void main (String[] args) {		

		// DCF ----------------------------------------------------------------
		// Discounted Cash Flow with Stable Growth
		double initPV 			= 15541;		// [$MM] Initial Present Value (from Financials)
		double FCFGrowthRate 	= 2.150/100;	// [%] Assumed constant growth rate (from Financials)
		int nPeriods 			= 10;			// Number of periods to evaluate (user defined)	
		FinModel.DCF(initPV, FCFGrowthRate, nPeriods);
		
		// Discounted Cash Flow with Variable Growth
		double[] FCFGrowthRateArr = {
				2.150/100, 
				2.150/100, 
				2.150/100, 
				2.150/100, 
				2.150/100, 
				2.150/100, 
				2.150/100, 
				2.150/100, 
				2.150/100, 
				2.150/100};
		FinModel.DCF(initPV, FCFGrowthRateArr, nPeriods);
		
		
		// DDM ----------------------------------------------------------------
		// Dividend Discount Model with Stable Growth
		double initDiv			= 3.15;			// [$] Initial dividend
		double DivGrowthRate	= 4.000/100;	// [%] Assumed constant growth rate (from Financials)
		FinModel.DDM_Stable(initDiv, DivGrowthRate);
		
		// Dividend Discount Model with Variable Growth
		nPeriods 				= 10;			// Number of periods to evaluate (user defined)			
		double[] DivGrowthRateArr = {
				4.000/100, 
				4.000/100, 
				4.000/100, 
				4.000/100, 
				4.000/100, 
				4.000/100, 
				4.000/100, 
				4.000/100, 
				4.000/100, 
				4.000/100};
		FinModel.DDM_Multi(initDiv, DivGrowthRateArr, nPeriods);
		
		// EPS_PE Stable ------------------------------------------------------
		double EPS 				= 5.75;
		double EPSGrowthRate 	= 2.600/100;
		double PE				= 22.18;
		FinModel.EPS_PE_Stable(EPS, EPSGrowthRate, PE, nPeriods);
		
		// Graham Guidance ----------------------------------------------------
		// Graham Guidance for stock price
		double BV 	= 27.54;
		FinModel.Graham(EPS, BV);
		
		double[] arr = {1, 2, 3, 4, 5};
		double q = M.moving_average(arr,3);
		Util.print("Moving Average: " + q);
		
		double[] arr2 = {1, 2, 3, 4};
		double foo = FinCalcs.GeometricMean(arr2);
				
	} // method main
} // class Main
