package testBench;

import finModels.FinModel;

public class Main {
	public static void main (String[] args) {		
		
		double initPV 			= 618;		// [$MM] Initial Present Value (from Financials)
		double FCFGrowthRate 	= 0.08;		// [%] Assumed constant growth rate (from Financials)
		double nPeriods 		= 10;		// Number of periods to evaluate (user defined)
				
		FinModel.DCF(initPV, FCFGrowthRate, nPeriods);
	}
}
