package finCalcToolbox;

import Utility.debug;
import Utility.util;

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
		if (debug.FinCalcs_FV_Simple) {util.print("FV: " + FV);}
		
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
		if (debug.FinCalcs_FV_Compound) {util.print("FV: " + FV);}
		
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
		if (debug.FinCalcs_PV_Discounted) {util.print("PV: " + PV);}
		
		return PV;
	} // method PV_Discounted
	
	
	
	
	
	// Gordon Growth Model ----- ----------------------------------------------
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
		if (debug.FinCalcs_GGM) {util.print("T0: " + T0);}

		return T0;
	}

	
	
	
	
} // class FinCalcs
