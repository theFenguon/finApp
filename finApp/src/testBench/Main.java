package testBench;

import Utility.util;
import finCalcToolbox.FinCalcs;
import finModels.FinModel;

public class Main {
	public static void main (String[] args) {		

		// DCF ----------------------------------------------------------------
		// Discounted Cash Flow with Variable Growth
//		double initPV 			= 12424;		// [$MM] Initial Present Value (from Financials)
//		double FCFGrowthRate 	= 2.300/100;	// [%] Assumed constant growth rate (from Financials)
//		double nPeriods 		= 10;			// Number of periods to evaluate (user defined)	
//		FinModel.DCF(initPV, FCFGrowthRate, nPeriods);
//		
//		// Generate growth rate array for variable FCF growth
//		double[] FCFGrowthRateArr = {2.300/100, 2.300/100, 2.300/100, 2.300/100, 2.300/100, 5.000/100, 5.000/100, 5.000/100, 5.000/100, 5.000/100};
//		FinModel.DCF(initPV, FCFGrowthRateArr, nPeriods);
		
		
		// DDM ----------------------------------------------------------------
		// Dividend Discount Model with Variable Growth
		double initDiv			= 0.93;			// [$] Initial dividend
		double DivGrowthRate	= 9.930/100;	// [%] Assumed constant growth rate (from Financials)
		int nPeriods 		= 5;			// Number of periods to evaluate (user defined)	
		//FinModel.DDM(initDiv, DivGrowthRate, nPeriods);
		
		double[] DivGrowthRateArr = {7.000/100, 7.000/100, 10.000/100, 12.000/100, 5.000/100, 
				5.000/100, 5.000/100, 5.000/100, 5.000/100, 5.000/100};
		FinModel.DDM(initDiv, DivGrowthRateArr, nPeriods);
		//FinModel.DDM_TEST(initDiv, DivGrowthRate, nPeriods);
		//FinModel.DDM_GGM(initDiv, DivGrowthRate, nPeriods);
		
		// GGM ----------------------------------------------------------------
		// Single-year Dividend Discount Model with Stable Growth
		//FinCalcs.GGM(2.19, DivGrowthRate, 12.000/100);
		
		// EPS_PE Stable ------------------------------------------------------
		//FinModel.EPS_PE_Stable(1.5, (10.000/100), 12, 5);
		
		// Graham Guidance ----------------------------------------------------
		// Graham Guidance for stock price
//		double EPS 	= 10.35;
//		double BV 	= 85.73;
//		FinModel.Graham(EPS, BV);
		
	}
}
