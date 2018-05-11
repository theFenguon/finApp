package finToolbox;
import java.util.Arrays;

import entity.Company;
import environment.Environment;
import user.User;
import utility.*;

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
		double k 		= Company.WACC;					// [%] Discount Rate (WACC) (from Company object)
		double g_t 		= Company.terminalGrowthRate; 	// [%] Terminal Growth Rate (from Company object [usually 10-yr T-Note yield])
		double debt 	= Company.debtOutstanding; 		// [$MM] Outstanding Debt (from Company object)
		double shares	= Company.sharesOutstanding;		// [MM] Shares Outstanding (from Company object)
		
		if (Debug.FinModel_DCF_Calc) { Util.print("Starting Value: " + initPV + "\n");}
		
		for (int i = 1; i <= period; i++) {
			
			// Calculate Future Value
			valFV = FinCalcs.FV_Compound(PV, growthRate, 1);
			PV = valFV;

			
			// Calculate Present Value of calculated Future Value
			valPV = FinCalcs.PV_Discounted(PV, k, i);
			PV_sum = PV_sum + valPV;
			
			
			// Debug console print out
			if (Debug.FinModel_DCF_Calc) {
				Util.print("Period: " + i);
				Util.print("FV: " + valFV);
				Util.print("PV: " + valPV + "\n");				
			} // if

		} // for

		// Cumulative Present Value
		if(Debug.FinModel_DCF_Calc) {Util.print("Cumulative PV: " + PV_sum + "\n");}
		
		// Calculate terminal value		
		double valTerm = FinCalcs.GGM(valFV, g_t, k);
		if(Debug.FinModel_DCF_Calc) {Util.print("Terminal Value: " + valTerm + "\n");}
		
		double termPV = FinCalcs.PV_Discounted((valFV + valTerm), k, period);
		if(Debug.FinModel_DCF_Calc) {Util.print(period + " year Present Value: " + termPV);}
		
		double DCF_value = PV_sum + termPV - debt - valPV;
		if(Debug.FinModel_DCF_Calc) {Util.print(period + " year DCF Intrinsic Value: " + DCF_value);}
		
		double DCF_price = DCF_value/shares;
		if(Debug.FinModel_DCF_Final) {Util.print("DCF Stable~ " + period + " year DCF Share Price: " + DCF_price);}
		
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
			//if (period <= growthRate.length) {boolean err = true;}
			
			
			double valFV 	= 0;
			double valPV 	= 0;
			double PV_sum 	= 0;
			double PV 		= initPV;

			//TODO: expand DCF method to accept object containing these variables
			double k 		= Company.WACC;					// [%] Discount Rate (WACC) (from Company object)
			double g_t 		= Company.terminalGrowthRate;		// [%] Terminal Growth Rate (from environment object [usually 10-yr T-Note yield])
			double debt 	= Company.debtOutstanding;		// [$MM] Outstanding Debt (from Company object)
			double shares	= Company.sharesOutstanding;		// [MM] Shares Outstanding (from Company object)
			
			if (Debug.FinModel_DCF_Calc) { Util.print("Starting Value: " + initPV + "\n");}
			
			for (int i = 1; i <= period; i++) {
				
				// Calculate Future Value
				valFV = FinCalcs.FV_Compound(PV, growthRate[i-1], 1);
				PV = valFV;

				
				// Calculate Present Value of calculated Future Value
				valPV = FinCalcs.PV_Discounted(PV, k, i);
				PV_sum = PV_sum + valPV;
				
				
				// Debug console print out
				if (Debug.FinModel_DCF_Calc) {
					Util.print("Period: " + i);
					Util.print("FV: " + valFV);
					Util.print("PV: " + valPV + "\n");		
				} // if

			} // for

			// Cumulative Present Value
			if(Debug.FinModel_DCF_Calc) {Util.print("Cumulative PV: " + PV_sum + "\n");}
			
			// Calculate terminal value		
			double valTerm = FinCalcs.GGM(valFV, g_t, k);
			if(Debug.FinModel_DCF_Calc) {Util.print("Terminal Value: " + valTerm + "\n");}
			
			double termPV = FinCalcs.PV_Discounted((valFV + valTerm), k, period);
			if(Debug.FinModel_DCF_Calc) {Util.print(period + " year Present Value: " + termPV);}
			
			double DCF_value = PV_sum + termPV - debt - valPV;
			if(Debug.FinModel_DCF_Calc) {Util.print(period + " year DCF Intrinsic Value: " + DCF_value);}
			
			double DCF_price = DCF_value/shares;
			if(Debug.FinModel_DCF_Final) {Util.print("DCF Multi~ " + period + " year DCF Share Price: " + DCF_price);}
			
		} // method DCF
	
	
	
	// Dividend Discount Model: Stable Growth ---------------------------------
	// INPUTS
	// 			initPV 	= initial principal (present value)
	// 			rate 	= growth rate
	//
	// OUTPUTS
	//			none
	//	
	// RESOURCES
	//			http://www.investinganswers.com/financial-dictionary/income-dividends/gordon-growth-model-5270
	//
	public static void DDM_Stable(double initDiv, double growthRate) {
		
		double valFV 	= 0;
		double PV 		= initDiv;

		// TODO: expand DCF method to accept object containing these variables
		// TODO: Revisit this assumption for the discount value k
		double k 		= User.required_market_rate;	// [%]
		
		if (Debug.FinModel_DDM_Stable_Calc) { Util.print("DDM_Stable~ Starting Value: " + initDiv);}
		
		valFV = FinCalcs.GGM(PV, growthRate, k);
								
		// Debug console print out
		if (Debug.FinModel_DDM_Stable_Final) {Util.print("DDM_Stable~ FV: " + valFV);}


	} // method DDM_Stable
	
	

		
		
		// Discounted Dividend Model: Multistage Model ------------------------
		// INPUTS
		// 			initDiv 	= Initial dividend (current value)
		// 			growthRate 	= Growth rate array
		//			nPeriods 	= Number of periods
		//
		// OUTPUTS
		//			none
		//	
		// RESOURCES
		//			http://www.investinganswers.com/financial-dictionary/income-dividends/gordon-growth-model-5270
		//
		public static void DDM_Multi(double initDiv, double growthRate[], int nPeriods) {
			
			// User-defined discount rate
			// TODO: Revisit this assumption for the discount value k
			double k 		= User.required_market_rate;	// [%]
			
			// Instantiate Future and Present Value Arrays
			double[] FV 	= new double[nPeriods];
			double[] PV 	= new double[nPeriods];
			
			// Extract terminal growth rate from passed growth array
			int end 		= (int) nPeriods-1;
			double g_t 		= growthRate[end]; 	// [%] Terminal Growth Rate
			
			if (Debug.FinModel_DDM_Multi_Calc) { Util.print("DDM_Multi~ Starting Value: " + initDiv + "\n");}
			
			// Seed arrays with initial present value at year 0
			FV[0] = initDiv;
			PV[0] = 0;
			
			
			for (int i = 1; i <= end; i++) {
				
				// Calculate Future Value
				FV[i] = FinCalcs.FV_Compound(FV[i-1], growthRate[i-1], 1);

				
				// Calculate Present Value of Future Value
				PV[i] = FinCalcs.PV_Discounted(FV[i], k, i);				
				
				
				// Debug console print out
				if (Debug.FinModel_DDM_Multi_Calc) {
					Util.print("DDM_Multi~ Period: " + i);
					Util.print("DDM_Multi~ FV: " + FV[i]);
					Util.print("DDM_Multi~ PV: " + PV[i] + "\n");
				} // if

			} // for

			// Calculate Cumulative Present Value
			double PV_sum = Arrays.stream(PV).sum();
			if(Debug.FinModel_DDM_Multi_Calc) {Util.print("DDM_Multi~ Cumulative PV: " + PV_sum);}
			
			// Calculate Terminal Value (Future Value)
			double valTerm = FinCalcs.GGM(FV[end], g_t, k);
			if(Debug.FinModel_DDM_Multi_Calc) {Util.print("DDM_Multi~ Terminal Value: " + valTerm + "\n");}
			
			// Calculate Terminal Value Present Value
			double termPV = FinCalcs.PV_Discounted(valTerm, k, nPeriods);
			
			// Calculate DDM Intrinsic Value share price
			double DDM_value = PV_sum + termPV;
			if(Debug.FinModel_DDM_Multi_Final) {Util.print("DDM_Multi~ " + nPeriods + "-year DDM Intrinsic Value Share Price: " + DDM_value);}
							
		} // method DDM_Multi
		
		
		
		
		
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
			
			if (Debug.FinModel_Graham_Final) {Util.print("Graham Model~ Intrinsic Value Share Price: " + val);}
		} // method Graham
		
		
		
		
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
			
			if (Debug.FinModel_EPS_PE_Stable_Final) {Util.print("EPS Stable~ Intrinsic Value Share Price: " + val);}
		} // method EPS_PE_Stable
				
}
