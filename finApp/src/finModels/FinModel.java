package finModels;
import java.util.Arrays;
import java.util.stream.*;
import Utility.*;
import environment.Environment;
import finCalcToolbox.FinCalcs;
import user.User;

// TODO: Add flag that checks if discount rate < growth rate. This breaks GGM.

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
		double k 		= 0.1055; 						// [%] Discount Rate (WACC) (from Company object)
		double g_t 		= Environment.ten_yr_t_note; 	// [%] Terminal Growth Rate (from environment object [usually 10-yr T-Note yield])
		double debt 	= 30500; 						// [$MM] Outstanding Debt (from Company object)
		double shares	= 5000.05;						// [MM] Shares Outstanding (from Company object)
		
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
	
	
	
	
	
	// Discounted Cash Flow Model (Overloaded) --------------------------------
		// INPUTS
		// 			initPV 	= initial principal (present value)
		// 			rate 	= growth rate
		// 			period 	= number of periods
		// OUTPUTS
		//			none
		// TODO: expand method to accept an array of growth rates and pass in environment and company object
		public static void DCF(double initPV, double[] growthRate, double period) {
			
			//TODO: add try/catch: period >= length of growthRate array. If not, throw exception and exit method
			if (period <= growthRate.length) {boolean err = true;}
			
			
			double valFV 	= 0;
			double valPV 	= 0;
			double PV_sum 	= 0;
			double PV 		= initPV;

			//TODO: expand DCF method to accept object containing these variables
			double k 		= 0.1055; 						// [%] Discount Rate (WACC) (from Company object)
			double g_t 		= Environment.ten_yr_t_note; 	// [%] Terminal Growth Rate (from environment object [usually 10-yr T-Note yield])
			double debt 	= 30500; 						// [$MM] Outstanding Debt (from Company object)
			double shares	= 5000.05;						// [MM] Shares Outstanding (from Company object)
			
			if (debug.FinModel_DCF) { util.print("Starting Value: " + initPV + "\n");}
			
			for (int i = 1; i <= period; i++) {
				
				// Calculate Future Value
				valFV = FinCalcs.FV_Compound(PV, growthRate[i-1], 1);
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
	//
	public static void DDM_GGM(double initDiv, double growthRate, double period) {
		
		double valFV 	= 0;
		double PV 		= initDiv;

		//TODO: expand DCF method to accept object containing these variables
		double k 		= User.discount_rate;	// [%] Discount Rate (from User object)
		
		if (debug.FinModel_DDM) { util.print("DDM_GGM~ Starting Value: " + initDiv + "\n");}
		
		for (int i = 1; i <= period; i++) {
			// Calculate Future Value
			valFV = FinCalcs.GGM(PV, growthRate, k);
						
			// Debug console print out
			if (debug.FinModel_DDM) {
				util.print("DDM_GGM~ Period: " + i);
				util.print("DDM_GGM~ FV: " + valFV);
			} // if
			
			PV = PV*(1+growthRate);

		} // for
						
	} // method DDM
	
	
	
	
	
		// Earnings Per Share Stable Growth  ----------------------------------
		// INPUTS
		// 			EPS 		= earning per share
		// 			growthRate 	= growth rate
		//			PE			= price to earnings ratio
		// 			nPeriods 	= number of periods
		// OUTPUTS
		//			none
		//
		// REFERENCES
		//			https://www.youtube.com/watch?v=HgxGcWsWKI4
		//
		public static void EPS_PE_Stable(double EPS, double growthRate, double PE, double nPeriods) {
			
			// Using constant EPS and PE over nPeriods, calculate stock price at nPeriods
			double val = EPS*PE*Math.pow((1+growthRate),nPeriods);
			
			// Discount value back to current intrinsic value using average stock market growth rate
			val = val/Math.pow((1+Environment.avg_mkt_growth), nPeriods);
			
			if (debug.FinModel_EPS_PE_Stable) {util.print("EPS Stable~ Intrinsic Value: " + val);}
		} // EPS_Stable
		
		
		
		
		// Graham Model -------------------------------------------------------
		// INPUTS
		// 			EPS =  Earnings per Share (typically Trailing Twelve Months)
		// 			BV 	=  Book Value (typically Trailing Twelve Months)
		//
		// OUTPUTS
		//			none
		//
		// REFERENCES
		//			none
		public static void Graham(double EPS, double BV) {
			
			// Graham number should be below 22.5
			double val = Math.sqrt(22.5*EPS*BV);
			
			if (debug.FinModel_Graham) {util.print("Graham Model~ Intrinsic Value: " + val);}
		} // Graham Number
		
		
		
		public static void DDM(double initDiv, double growthRate[], int nPeriods) {
					
			double PV_sum 	= 0;
			double k 		= User.discount_rate;	// [%] Discount Rate (from User object)
			
			// Instantiate Future and Present Value Arrays
			double[] FV 	= new double[nPeriods];
			double[] PV 	= new double[nPeriods];
			
			// Extract terminal growth rate from passed growth array
			int end = (int) nPeriods-1;
			double g_t 		= growthRate[end]; 	// [%] Terminal Growth Rate
			
			
			if (debug.FinModel_DDM) { util.print("Starting Value: " + initDiv + "\n");}
			
			// Seed arrays with initial present value at year 0
			FV[0] = initDiv;
			PV[0] = 0;
			
			
			for (int i = 1; i <= end; i++) {
				
				// Calculate Future Value
				FV[i] = FinCalcs.FV_Compound(FV[i-1], growthRate[i-1], 1);
				//PV = valFV[i];

				
				// Calculate Present Value of Future Value
				PV[i] = FinCalcs.PV_Discounted(FV[i], k, i);				
				
				// Debug console print out
				if (debug.FinModel_DDM) {
					util.print("Period: " + i);
					util.print("FV: " + FV[i]);
					util.print("PV: " + PV[i] + "\n");
				} // if

			} // for

			// Cumulative Present Value
			
			PV_sum = Arrays.stream(PV).sum();
			if(debug.FinModel_DDM) {util.print("Cumulative PV: " + PV_sum);}
			
			// Calculate terminal value
			double valTerm = FinCalcs.GGM(FV[end], g_t, k);
			if(debug.FinModel_DDM) {util.print("Terminal Value: " + valTerm + "\n");}
			
			//double termPV = FinCalcs.PV_Discounted((FV[end] + valTerm), k, nPeriods);
			double termPV = FinCalcs.PV_Discounted(valTerm, k, nPeriods);
			
			double DDM_value = PV_sum + termPV;
			if(debug.FinModel_DDM) {util.print(nPeriods + " year DDM Intrinsic Value Share Price: " + DDM_value);}
							
		} // method DDM
		
				
}
