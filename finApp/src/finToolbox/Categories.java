package finToolbox;

public class Categories {
	public static String setMarketCapCategory(double market_cap) {
		
		String market_cap_category;
				
		if (market_cap < 50) {
			market_cap_category = "NANO_CAP";
		}
		else if (market_cap > 50 && market_cap < 300) {
			market_cap_category = "MICRO_CAP";
		}
		else if (market_cap >= 300 && market_cap < 2000) {
			market_cap_category = "SMALL_CAP";
		}
		else if (market_cap > 2000 && market_cap <= 10000) {
			market_cap_category = "MID_CAP";
		}
		else if (market_cap > 10000 && market_cap < 200000) {
			market_cap_category = "LARGE_CAP";
		}
		else {
			market_cap_category = "MEGA_CAP";
		}
		
		return market_cap_category;
	}
}