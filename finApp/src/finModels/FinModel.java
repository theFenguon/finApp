package finModels;
import Utility.util;
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
		
		util.print("Starting Value: " + initPV + "\n");
		
		for (int i = 1; i <= period; i++) {
			
			// Calculate Future Value
			valFV = FinCalcs.FV_Compound(PV, growthRate, 1);
			PV = valFV;

			
			// Calculate Present Value of calculated Future Value
			valPV = FinCalcs.PV_Discounted(PV, k, i);
			PV_sum = PV_sum + valPV;
			
			
			// Debug console print out
			util.print("Period: " + i);
			util.print("FV: " + valFV);
			util.print("PV: " + valPV + "\n");

		} //for

		// Cumulative Present Value
		util.print("Cumulative PV: " + PV_sum + "\n");
		
		// Calculate terminal value		
		double valTerm = FinCalcs.GGM(valFV, g_t, k);
		util.print("Terminal Value: " + valTerm + "\n");
		
		double termPV = FinCalcs.PV_Discounted((valFV + valTerm), k, period);
		util.print(period + " year Present Value: " + termPV);
		
		double DCF_value = PV_sum + termPV - debt - valPV;
		util.print(period + " year DCF Intrinsic Value: " + DCF_value);
		
		double DCF_price = DCF_value/shares;
		util.print(period + " year DCF Share Price: " + DCF_price);
		
	} // method DCF
	
	
	
	
	
	// Dividend Discount Model ------------------------------------------------
	// INPUTS
	// 			initPV 	= initial principal (present value)
	// 			rate 	= growth rate
	// 			period 	= number of periods
	// OUTPUTS
	//			none
	// TODO: expand model to accept an array of growth rates
	public static void DDM() {
		
	} // method DDM
}
