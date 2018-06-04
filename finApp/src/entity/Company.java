package entity;

import java.util.Arrays;

import enums.Industry;
import enums.Sector;
import finToolbox.FinCalcs;
import finToolbox.FinModel;
import interfaces.CSV;
import utility.Util;

// Placeholder for Company Class
public class Company {
	
	// TODO: Constructor should take in a ticker symbol and pull all of these (and more) values from a database
	
	Sector s 	= Sector.CONSUMER_DISCRETIONARY;
	Industry i 	= Industry.AUTO_COMPONENTS;
	public static boolean paysDividends 		= true;
	
	public double terminalGrowthRate		= 2.000/100;		// [%]
	//public static double WACC 					= FinCalcs.WACC();	// [%]
	
	// Indices
	public int sector_ind;
	public int industry_ind;
	public int price_ind;
	public int market_cap_ind;
	public int beta_ind;
	public int shares_out_ind;
	public int debt_out_ind;
	public int bond_yield_ind;
	public int EPS_TTM_ind;
	public int BV_TTM_ind;
	public int CR_MRQ_ind;
	public int DE_MRQ_ind;
	public int years_ind;
	public int net_income_ind;
	public int EPS_ind;
	public int dividends_ind;
	public int shares_ind;
	public int BV_ind;
	public int FCF_ind;
	public int DCF_rate_ind;
	public int DDM_rate_ind;
	
	// Values
	public String sector;
	public String industry;
	public double currentMarketPrice;
	public double marketCapitalization;
	public double beta;
	public double sharesOutstanding;
	public double debtOutstanding;
	public double longTermBondYield;
	public double EPS_TTM;
	public double BV_TTM;
	public double CR_MRQ;
	public double DE_MRQ;
	
	// Arrays
	public double[] years;
	public double[] netIncome;
	public double[] EPS;
	public double[] dividends;
	public double[] shares;
	public double[] bookValue;
	public double[] freeCashFlow;
	public double[] DCF_rate;
	public double[] DDM_rate;
	
	// Arrays
	public String[][] data;
		
	public Company (String ticker){
	   
		data = CSV.importCSV2Arr(ticker);
		
		//Index data array and store local variables
		storeData(data);
		
		//Util.print(data[FCF_ind].length);
		//Util.print(data[FCF_ind][1]);
		
		//double valuation = valueCompany(data);
	}
	
	private double valueCompany(String[][] data) {
		double val;
		val = 5;
		return val;
	}
	
	private void storeData (String[][] data) {
		
		for(int i = 0; i < data.length; i++) {
			
			// Assign index values to variables for later use
			String val = data[i][0];
			switch (val){
			
				// Sector
				case "Sector":
					sector = data[i][1];
				break;
				
				// Industry
				case "Industry":
					industry = data[i][1];
				break;
				
				// Current Market Price
				case "Current_Market_Price":
					// Store Current Market Price
					currentMarketPrice = Double.parseDouble(data[i][1]);
				break;
				
				// Market Capitalization
				case "Market_Cap":
					// Store Market Cap
					marketCapitalization = Double.parseDouble(data[i][1]);
				break;
				
				// Beta
				case "Beta":
					// Store Beta
					beta = Double.parseDouble(data[i][1]);
				break;
				
				// Shares Outstanding
				case "Shares_Outstanding":
					// Store Shares Outstanding
					sharesOutstanding = Double.parseDouble(data[i][1]);
				break;
				
				// Outstanding Debt
				case "Outstanding_Debt":
					// Store Debt Outstanding
					debtOutstanding = Double.parseDouble(data[i][1]);
				break;
				
				// Long Term Bond Yield
				case "Long_Term_Bond_Yields":
					// Store Long Term Bond Yield
					longTermBondYield = Double.parseDouble(data[i][1]);
				break;
				
				// Trailing Twelve Months Earnings per Share
				case "EPS_TTM":
					// Store Long Term Bond Yield
					EPS_TTM = Double.parseDouble(data[i][1]);
				break;
				
				// Trailing Twelve Months Book Value
				case "BV_TTM":
					// Store Long Term Bond Yield
					BV_TTM = Double.parseDouble(data[i][1]);
				break;
				
				// Most Recent Quarter Current Value
				case "Current_Ratio_MRQ":
					// Store Long Term Bond Yield
					CR_MRQ = Double.parseDouble(data[i][1]);
				break;
				
				// Most Recent Quarter Debt to Equity Ratio
				case "Debt_to_Equity_MRQ":
					// Store Long Term Bond Yield
					DE_MRQ = Double.parseDouble(data[i][1]);
				break;
				
				// Years
				case "Years":
					
					// Generate array with double values. Convert strings to doubles
					years = new double[data[i].length];
					for (int j = 1; j < data[i].length; j++) {
						//Util.print(data[i][j]);
						years[j] = Double.parseDouble(data[i][j]);
					}
					
				break;
				
				// Net Income
				case "Net_Income":
					
					// Generate array with double values. Convert strings to doubles
					netIncome = new double[data[i].length];
					for (int j = 1; j < data[i].length; j++) {
						//Util.print(data[i][j]);
						netIncome[j] = Double.parseDouble(data[i][j]);
					}
					
				break;
				
				// Earnings Per Share
				case "EPS":
					
					// Generate array with double values. Convert strings to doubles
					EPS = new double[data[i].length];
					for (int j = 1; j < data[i].length; j++) {
						//Util.print(data[i][j]);
						EPS[j] = Double.parseDouble(data[i][j]);
					}
					
				break;
				
				// Dividends
				case "Dividends":
					
					// Generate array with double values. Convert strings to doubles
					dividends = new double[data[i].length];
					for (int j = 1; j < data[i].length; j++) {
						//Util.print(data[i][j]);
						dividends[j] = Double.parseDouble(data[i][j]);
					}
					
				break;
				
				// Shares
				case "Shares":
					
					// Generate array with double values. Convert strings to doubles
					shares = new double[data[i].length];
					for (int j = 1; j < data[i].length; j++) {
						//Util.print(data[i][j]);
						shares[j] = Double.parseDouble(data[i][j]);
					}
					
				break;
				
				// Book Value
				case "Book_Value":

					// Generate array with double values. Convert strings to doubles
					bookValue = new double[data[i].length];
					for (int j = 1; j < data[i].length; j++) {
						//Util.print(data[i][j]);
						bookValue[j] = Double.parseDouble(data[i][j]);
					}
					
				break;

				// Free Cash Flow
				case "Free_Cash_Flow":
					
					// Generate array with double values. Convert strings to doubles
					freeCashFlow = new double[data[i].length];
					for (int j = 1; j < data[i].length; j++) {
						//Util.print(data[i][j]);
						freeCashFlow[j] = Double.parseDouble(data[i][j]);
					}
					
				break;
				
				// Discounted Cash Flow Growth Rates
				case "DCF_Growth_Rates":
				
					// Generate array with double values. Convert strings to doubles
					if(data[i].length > 2) {
						DCF_rate = new double[data[i].length];
						for (int j = 1; j < data[i].length; j++) {
							//Util.print(data[i][j]);
							DCF_rate[j] = Double.parseDouble(data[i][j]);
						}
					} //if
					else {
						double DCF_rate = Double.parseDouble(data[i][1]);
					} //else
						

					
				break;
				
				// Discounted Dividend Model Growth Rates
				case "DDM_Growth_Rates":
					
					// Generate array with double values. Convert strings to doubles
					if(data[i].length > 2) {
						DDM_rate = new double[data[i].length];
						for (int j = 1; j < data[i].length; j++) {
							//Util.print(data[i][j]);
							DDM_rate[j] = Double.parseDouble(data[i][j]);
						}
					} //if
					else {
						double DDM_rate = Double.parseDouble(data[i][1]);
					} //else
					
				break;
		
			} // switch
		} // for
	} // parseData
	
} // class Company