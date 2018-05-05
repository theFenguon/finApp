package finModels;
import Utility.*;
import environment.Environment;
import finCalcToolbox.FinCalcs;

public class FinModel {

	// Discounted Cash Flow Model ---------------------------------------------
	// INPUTS
	// 			initPV 	= initial principal (present value)
	// 			rate 	= growth rate
	// 			period 	= number of periods
	// OUTPUTS
	//			none
	// TODO: expand method to accept an array of growth rates and pass in environment and company object
	public static void DCF(double initPV, double growthRate, double period) {
		
		double valFV 	= 0;
		double valPV 	= 0;
		double PV_sum 	= 0;
		double PV 		= initPV;

		//TODO: expand DCF method to accept object containing these variables
		double k 		= 0.0614; 						// [%] Discount Rate (WACC) (from Company object)
		double g_t 		= Environment.ten_yr_t_note; 	// [%] Terminal Growth Rate (from environment object [usually 10-yr T-Note yield])
		double debt 	= 2200; 						// [$MM] Outstanding Debt (from Company object)
		double shares	= 38.13;						// [MM] Shares Outstanding (from Company object)
		
		if (debug.FinModel_DCF) { util.print("Starting Value: " + initPV + "\n");}
		
		for (int i = 1; i <= period; i++) {
			
			// Calculate Future Value
			valFV = FinCalcs.FV_Compound(PV, growthRate, 1);
			PV = valFV;

			
			// Calculate Present Value of calculated Future Value
			valPV = FinCalcs.PV_Discounted(PV, k, i);
			PV_sum = PV_sum + valPV;
			
			
			// Debug console print out
			if (debug.FinModel_DCF) {
				util.print("Period: " + i);
				util.print("FV: " + valFV);
				util.print("PV: " + valPV + "\n");				
			} // if

		} // for

		// Cumulative Present Value
		if(debug.FinModel_DCF) {util.print("Cumulative PV: " + PV_sum + "\n");}
		
		// Calculate terminal value		
		double valTerm = FinCalcs.GGM(valFV, g_t, k);
		if(debug.FinModel_DCF) {util.print("Terminal Value: " + valTerm + "\n");}
		
		double termPV = FinCalcs.PV_Discounted((valFV + valTerm), k, period);
		if(debug.FinModel_DCF) {util.print(period + " year Present Value: " + termPV);}
		
		double DCF_value = PV_sum + termPV - debt - valPV;
		if(debug.FinModel_DCF) {util.print(period + " year DCF Intrinsic Value: " + DCF_value);}
		
		double DCF_price = DCF_value/shares;
		if(debug.FinModel_DCF) {util.print(period + " year DCF Share Price: " + DCF_price);}
		
	} // method DCF
	
	
	
	
	
	// Dividend Discount Model ------------------------------------------------
	// INPUTS
	// 			initPV 	= initial principal (present value)
	// 			rate 	= growth rate
	// 			period 	= number of periods
	// OUTPUTS
	//			none
	// TODO: expand model to accept an array of growth rates
	public static void DDM(double initDiv, double growthRate, double period) {
		
		double valFV 	= 0;
		double valPV 	= 0;
		double PV_sum 	= 0;
		double PV 		= initDiv;

		//TODO: expand DCF method to accept object containing these variables
		double k 		= 7.000/100;					// [%] Discount Rate (from Company object)
		double g_t 		= Environment.ten_yr_t_note; 	// [%] Terminal Growth Rate (from environment object [usually 10-yr T-Note yield])
		
		if (debug.FinModel_DDM) { util.print("Starting Value: " + initDiv + "\n");}
		
		for (int i = 1; i <= period; i++) {
			// Calculate Future Value
			valFV = FinCalcs.FV_Compound(PV, growthRate, 1);			
			//valFV = FinCalcs.GGM(PV, growthRate, k);
			PV = valFV;

			
			// Calculate Present Value of calculated Future Value
			valPV = FinCalcs.PV_Discounted(PV, k, i);
			PV_sum = PV_sum + valPV;
			
			
			// Debug console print out
			if (debug.FinModel_DDM) {
				util.print("Period: " + i);
				util.print("FV: " + valFV);
				util.print("PV: " + valPV + "\n");				
			} // if

		} // for

		// Cumulative Present Value
		if(debug.FinModel_DDM) {util.print("Cumulative PV: " + PV_sum + "\n");}
		
		// Calculate terminal value		
		double valTerm = FinCalcs.GGM(valFV, g_t, k);
		if(debug.FinModel_DDM) {util.print("Terminal Value: " + valTerm + "\n");}
		
		double termPV = FinCalcs.PV_Discounted((valFV + valTerm), k, period);
		if(debug.FinModel_DDM) {util.print(period + " year Present Value: " + termPV);}
		
		double DDM_value = PV_sum + termPV - valPV;
		if(debug.FinModel_DDM) {util.print(period + " year DDM Intrinsic Value: " + DDM_value);}
				
	} // method DDM
}
