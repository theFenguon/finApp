package user;

import environment.Environment;

public class User {
	public static double required_market_rate 	= 9.000/100; 					// Used for calculating market risk premium
	public static double risk_free_rate			= Environment.ten_yr_t_note;	// Used for calculating market risk premium
	public static double market_risk_premium 	= required_market_rate - risk_free_rate;
	
	public static double discount_rate			= 10.000/100; 					// Market Risk Aversion (used for DDM)
}
