package finToolbox;

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
	
} // class FinCalcs
