package finCalcToolbox;

public class Ratios {
	public static double PE(double price, double earnings) {
		double PE = price/earnings;
		return PE;
	}
	
	public static double EPS(double earnings, double shares) {
		double EPS = earnings/shares;
		return EPS;
	}
	
	public static double PBV(double price, double bookValue) {
		double PBV = price/bookValue;
		return PBV;
	}
}
