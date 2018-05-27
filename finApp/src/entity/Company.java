package entity;

import enums.Industry;
import enums.Sector;
import finToolbox.FinCalcs;
import interfaces.CSV;

// Placeholder for Company Class
public class Company {
	
	// TODO: Constructor should take in a ticker symbol and pull all of these (and more) values from a database
	
	Sector s 	= Sector.CONSUMER_DISCRETIONARY;
	Industry i 	= Industry.AUTO_COMPONENTS;
	public static boolean paysDividends 		= true;
	
	public static double debtOutstanding 		= 26675;			// [$MM]
	public static double sharesOutstanding 		= 2690;				// [shares]
	public static double longTermBondYield  	= 2.980/100;		// [%]
	public static double marketCapitalization 	= 345083;			// [$MM]
	public static double beta					= 0.530;			// [unitless]
	public static double terminalGrowthRate		= 2.000/100;		// [%]
	public static double WACC 					= FinCalcs.WACC();	// [%]
	
	// Indices
	public static int sector_ind;
	public static int industry_ind;
	public static int price_ind;
	public static int market_cap_ind;
	public static int shares_out_ind;
	public static int debt_out_ind;
	public static int bond_yield_ind;
	public static int EPS_TTM_ind;
	public static int BV_TTM_ind;
	public static int CR_MRQ_ind;
	public static int DE_MRQ_ind;
	public static int years_ind;
	public static int net_income_ind;
	public static int EPS_ind;
	public static int dividends_ind;
	public static int shares_ind;
	public static int BV_ind;
	public static int FCF_ind;
		
	public Company (String ticker){
	   
		String[][] data = CSV.importCSV2Arr(ticker);
		
		//Parse data array and store local variables
		parseData(data);
		
	}
	
	public void parseData (String[][] data) {
		int len = data.length;
		
		for(int i = 0; i < data.length; i++) {
			
			// Assign index values to variables for later use
			String val = data[i][0];
			switch (val){
			
				// Sector
				case "Sector":
					sector_ind = i;
				break;
				
				// Industry
				case "Industry":
					industry_ind = i;
				break;
				
				// Current Market Price
				case "Current_Market_Price":
					price_ind = i;
				break;
				
				// Market Capitalization
				case "Market_Cap":
					market_cap_ind = i;
				break;
				
				// Shares Outstanding
				case "Shares_Outstanding":
					shares_out_ind = i;
				break;
				
				// Outstanding Debt
				case "Outstanding_Debt":
					debt_out_ind = i;
				break;
				
				// Long Term Bond Yield
				case "Long_Term_Bond_Yields":
					bond_yield_ind = i;
				break;
				
				// Trailing Twelve Months Earnings per Share
				case "EPS_TTM":
					EPS_TTM_ind = i;
				break;
				
				// Trailing Twelve Months Book Value
				case "BV_TTM":
					BV_TTM_ind = i;
				break;
				
				// Most Recent Quarter Current Value
				case "Current_Ratio_MRQ":
					CR_MRQ_ind = i;
				break;
				
				// Most Recent Quarter Debt to Equity Ratio
				case "Debt_to_Equity_MRQ":
					DE_MRQ_ind = i;
				break;
			
			} // switch
		} // for

		
		
	} // parseData

	
	
}