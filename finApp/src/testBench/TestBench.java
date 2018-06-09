package testBench;

import entity.Company;
import finToolbox.FinCalcs;
import finToolbox.FinModel;
import finToolbox.M;
import utility.Util;

public class TestBench {
	
	public void testCompany(Company c) {
		
		// DCF
		FinModel.DCF(c.freeCashFlow[c.freeCashFlow.length-1], c.DCF_rate, c, 5);
		FinModel.DCF(c.freeCashFlow[c.freeCashFlow.length-1], c.DCF_rate, c, 10);
		
		// DDM
		FinModel.DDM_Multi(c.dividends[c.dividends.length-1], c.DDM_rate, 5);
		FinModel.DDM_Multi(c.dividends[c.dividends.length-1], c.DDM_rate, 10);
		
		// Graham Guidance
		FinModel.Graham(c.EPS_TTM, c.BV_TTM);
	}
	
	public void runTest(Company c) {
	// DCF ----------------------------------------------------------------
		// Discounted Cash Flow with Stable Growth
		double initPV 			= 15541;		// [$MM] Initial Present Value (from Financials)
		double FCFGrowthRate 	= 2.150/100;	// [%] Assumed constant growth rate (from Financials)
		int nPeriods 			= 10;			// Number of periods to evaluate (user defined)	
		FinModel.DCF(initPV, FCFGrowthRate, c, nPeriods);
		
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
		FinModel.DCF(initPV, FCFGrowthRateArr, c, nPeriods);
				
		// DDM ----------------------------------------------------------------
		// Dividend Discount Model with Stable Growth
		double initDiv			= 3.15;			// [$] Initial dividend
		double DivGrowthRate	= .000/100;	// [%] Assumed constant growth rate (from Financials)
		FinModel.DDM_Stable(initDiv, DivGrowthRate);
		
		// Dividend Discount Model with Variable Growth
		nPeriods 				= 10;			// Number of periods to evaluate (user defined)			
		double[] DivGrowthRateArr = {
				3.000/100, 
				3.000/100, 
				3.000/100, 
				3.000/100, 
				3.000/100, 
				3.000/100, 
				3.000/100, 
				3.000/100, 
				3.000/100, 
				3.000/100};
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
			
	}
}
