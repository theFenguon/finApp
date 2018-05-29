package finToolbox;

import entity.Company;
import environment.Environment;
import user.User;
import utility.Debug;
import utility.Util;

public class FinCalcs {
	
	// Simple Interest Future Value -------------------------------------------
	// Calculates simple interest future value of a Present Value principal 
	// INPUTS
	// 			PV = present value of cash in dollars
	// 			rate = discount rate in percent
	// 			period = number of periods
	// OUTPUTS
	//			FV = future value of cash in dollars
	public static double FV_Simple(double PV, double rate, double period) {
		double FV = PV*(1+rate*period);
		
		// Debug console output
		if (Debug.FinCalcs_FV_Simple) {Util.print("FV_Simple~ FV: " + FV);}
		
		return FV;
	} // method FV_Simple
	
	
	
	
	
	// Compound Interest Future Value -----------------------------------------
	// Calculates compound interest future value of a Present Value principal 
	// INPUTS
	// 			PV = present value of cash in dollars
	// 			rate = discount rate in percent
	// 			period = number of periods
	// OUTPUTS
	//			FV = future value of cash in dollars
	public static double FV_Compound(double PV, double rate, double period) {		
		double FV = PV*Math.pow((1+rate),period);
		
		// Debug console output
		if (Debug.FinCalcs_FV_Compound) {Util.print("FV_Compound~ FV: " + FV);}
		
		return FV;
	} // method FV_Compound
	
	
	
	
	
	// Discounted Present Value of Cash ---------------------------------------
	// Calculates the present value of cash from a future value
	// INPUTS
	// 			FV = future value of cash in dollars
	// 			rate = discount rate in percent
	// 			period = number of periods
	// OUTPUTS
	//			PV = present value of cash in dollars
	public static double PV_Discounted(double FV, double rate, double period) {
		double PV = FV/Math.pow((1+rate), period);
		
		// Debug console output
		if (Debug.FinCalcs_PV_Discounted) {Util.print("PV_Discounted~ PV: " + PV);}
		
		return PV;
	} // method PV_Discounted
	
	
	
	
	
	// Gordon Growth Model ----------------------------------------------------
 	// Calculates the present value of cash from a future value
	// INPUTS
	// 			D0 = future value of cash in dollars
	// 			g = growth rate
	// 			k = discount rate
	// OUTPUTS
	//			T0 = terminal value
	public static double GGM(double D0, double g, double k) {
		double T0 = D0*(1+g)/(k-g);
		
		// Debug console output
		if (Debug.FinCalcs_GGM) {Util.print("GGM~ T0: " + T0);}

		return T0;
	}

	
	
	

	// Graham Number ------------------------------------------------------
	// INPUTS
	// 			PE	=  Price to Earnings Ratio (typically Trailing Twelve Months)
	// 			PBV =  Price to Book Value (typically Trailing Twelve Months)
	//
	// OUTPUTS
	//			none
	//
	// REFERENCES
	//			none
	public static double Graham_Number(double PE, double PBV) {
		
		// Graham number should be below 22.5
		// P/E (TTM) * P/BV (TTM) <= 22.5
		// P/E < 15
		// P/BV < 1.5
		
		double val = PE*PBV;
					
		if (Debug.FinCalcs_Graham_Number) {Util.print("Graham Number~ Graham Number: " + val);}
		
		return val;
		
	} // Graham Number
	
	
	
	
	
	// Weighted Average Cost of Capital (WACC) --------------------------------
	// Calculates Weighted Average Cost of Capital (WACC) 
	// INPUTS
	// 			none
	//
	// OUTPUTS
	//			WACC = Weighted Average Cost of Capital
	public static double WACC(Company c) {		
		// Market Assumptions -------------------------------------------------
			// Risk-Free Rate
			double riskFreeRate 		= Environment.ten_yr_t_note;
			
			// Required Market Rate
			double reqMarketRate 		= User.required_market_rate;
			
			// Market Risk Premium
			double riskPremium			= reqMarketRate - riskFreeRate;
				
		// Equity Assumptions -------------------------------------------------
			// Beta
			double beta 				= c.beta;
			
			// Cost of Equity
			double costOfEquity 		= riskFreeRate + riskPremium*beta;
			
			// Market Value of Equity
			double marketCap			= c.marketCapitalization;
			
		// Debt Assumptions ---------------------------------------------------
			// Pre-Tax Cost of Debt
			double preTaxCostOfDebt 	= c.longTermBondYield;
			
			// Marginal Tax Rate
			double taxRate				= Environment.marginal_tax_rate;
			
			// After Tax Cost of Debt
			double afterTaxCostOfDebt 	= (1-taxRate)*preTaxCostOfDebt;
			
			// Estimated Market Value of Debt
			double debtMV 				= c.debtOutstanding;
		
		// Firm Assumptions ---------------------------------------------------
			// Firm Value
			double firmValue 			= marketCap + debtMV;
			
			// Debt to Firm Value
			double debtToFirmValue 		= debtMV/firmValue;
		
		// Calculate WACC -----------------------------------------------------
			double WACC = (1-debtToFirmValue)*costOfEquity + afterTaxCostOfDebt*debtToFirmValue;
					
		return WACC;
	} // method WACC
	
	
	// Weighted Average Cost of Capital (WACC) --------------------------------
	// Calculates Weighted Average Cost of Capital (WACC) using CAPM 
	// (Capital Asset Pricing Model), which uses uses a company's beta (a
	// measure of volatility in the market) as a proxy for risk
	//
	// INPUTS
	// 			none
	//
	// USES
	//			CAPM()
	//
	// OUTPUTS
	//			WACC = Weighted Average Cost of Capital
	public static double WACC_CAPM(Company c) {		
		// Market Assumptions -------------------------------------------------
			// Risk-Free Rate
			double riskFreeRate 		= Environment.ten_yr_t_note;
			
			// Required Market Rate
			double reqMarketRate 		= User.required_market_rate;
			
			// Market Risk Premium
			double riskPremium			= reqMarketRate - riskFreeRate;
				
		// Equity Assumptions -------------------------------------------------
			// Beta
			double beta 				= c.beta;
			
			// Cost of Equity
			double costOfEquity 		= riskFreeRate + riskPremium*beta;
			
			// Market Value of Equity
			double marketCap			= c.marketCapitalization;
			
		// Debt Assumptions ---------------------------------------------------
			// Pre-Tax Cost of Debt
			double preTaxCostOfDebt 	= c.longTermBondYield;
			
			// Marginal Tax Rate
			double taxRate				= Environment.marginal_tax_rate;
			
			// After Tax Cost of Debt
			double afterTaxCostOfDebt 	= (1-taxRate)*preTaxCostOfDebt;
			
			// Estimated Market Value of Debt
			double debtMV 				= c.debtOutstanding;
		
		// Firm Assumptions ---------------------------------------------------
			// Firm Value
			double firmValue 			= marketCap + debtMV;
			
			// Debt to Firm Value
			double debtToFirmValue 		= debtMV/firmValue;
		
		// Calculate WACC -----------------------------------------------------
			double capm = CAPM(riskFreeRate,reqMarketRate,beta);
			
			double WACC = (1-debtToFirmValue)*costOfEquity + afterTaxCostOfDebt*debtToFirmValue;
					
		return WACC;
	} // method WACC
	
	
	
	
	
	// Compound Average Growth Rate (CAGR) ------------------------------------
	// 
	// INPUTS
	// 			BV = Beginning Value
	//			EV = Ending Value
	//			nPeriods = Number of Periods
	//
	// OUTPUTS
	//			CAGR = Compound Average Growth Rate
	//
	// REFERENCES
	//			https://www.investopedia.com/terms/c/cagr.asp
	//
	public static double CAGR(double BV, double EV, int nPeriods) {	
			
		double exp = (double) 1/nPeriods;
		double CAGR = Math.pow((EV/BV),exp)-1;
		
		if (Debug.FinCalcs_CAGR) {Util.print("CAGR~ Compound Average Growth Rate: " + CAGR);}
		
		return CAGR;
	}
	
	
	
	
	
	// Capital Asset Pricing Model (CAPM) -------------------------------------
	// 
	// INPUTS
	// 			r_f = Risk-free Rate
	//			r_m = Expected Market Return
	//			beta = Beta of the Security
	//
	// OUTPUTS
	//			CAPM = Capital Asset Pricing Model
	//
	// REFERENCES
	//			https://www.investopedia.com/terms/c/capm.asp
	//
	public static double CAPM(double r_f, double r_m, double beta) {	
			
		double CAPM = r_f + beta*(r_m - r_f);
		
		if (Debug.FinCalcs_CAPM) {Util.print("CAPM~ Capital Asset Pricing Model: " + CAPM);}
		
		return CAPM;
	}
	
	
	
	
	// Geometric Mean ---------------------------------------------------------
	// 
	// INPUTS
	// 			arr = Input array
	//
	// OUTPUTS
	//			GeoMean = Geometric Mean
	//
	// REFERENCES
	//			https://en.wikipedia.org/wiki/Geometric_mean
	//
	public static double GeometricMean(double[] arr) {	
		
		int n = arr.length;
		double product = 1;
		
		for (int i = 0; i <= n-1; i++) {
			product = product*arr[i];
		}
		
		double GeoMean = Math.pow(product, Math.pow(n, -1));
		
		if (Debug.FinCalcs_GeoMean) {Util.print("Geometric Mean~ Geometric Mean: " + GeoMean);}
		
		return GeoMean;
	}
	
	
	
	
	// Unlevered Beta ---------------------------------------------------------
	// Calculates Unlevered Beta
	// INPUTS
	// 			arr = Input array
	//
	// OUTPUTS
	//			unleveredBeta = Unlevered Beta
	//
	// REFERENCES
	//			https://www.investopedia.com/terms/u/unleveredbeta.asp
	//
	public static double UnleveredBeta(double beta, double tax_rate, double debt, double equity) {	
		
		double unleveredBeta = beta/(1+(1-tax_rate)*(debt/equity));
		
		if (Debug.FinCalcs_UnleveredBeta) {Util.print("Unlevered Beta~ Unlevered Beta: " + unleveredBeta);}
		
		return unleveredBeta;
	}
	
	
	
	
	// Unlevered Beta (Overloaded) --------------------------------------------
	// Calculates Unlevered Beta
	// INPUTS
	// 			arr = Input array
	//
	// OUTPUTS
	//			unleveredBeta = Unlevered Beta
	//
	// REFERENCES
	//			https://www.investopedia.com/terms/u/unleveredbeta.asp
	//
	public static double UnleveredBeta(double beta, double tax_rate, double debtToEquity) {	
		
		double unleveredBeta = beta/(1+(1-tax_rate)*(debtToEquity));
		
		if (Debug.FinCalcs_UnleveredBeta) {Util.print("Unlevered Beta~ Unlevered Beta: " + unleveredBeta);}
		
		return unleveredBeta;
	}
	
	
	//TODO: Cost of Equity https://www.investopedia.com/terms/c/costofequity.asp
	//TODO: Cost of Debt https://www.investopedia.com/terms/c/costofdebt.asp
	
	
	
	
} // class FinCalcs
