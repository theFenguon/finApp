package finToolbox;

import enums.Market_Cap;

public class Categories {
	public static Market_Cap setMarketCapCategory(double market_cap) {
		
		Market_Cap market_cap_category;
				
		if (market_cap < 50000000) {
			market_cap_category = Market_Cap.NANO_CAP;
		}
		else 
		if (market_cap > 50000000 && market_cap < 300000000) {
			
		}
		else
		if (market_cap > 300000000 && market_cap < 20000000000) {
			
		}
		else
		if (market_cap > 20000000000 && market_cap < 50000000) {
		
		}
		else
		if (market_cap > 50000000 && market_cap < 50000000) {
		
		}
		else
		if (market_cap > 50000000 && market_cap < 50000000) {
			
		}
	
		
		return market_cap_category;
	}
}
