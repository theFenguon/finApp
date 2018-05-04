package finCalcToolbox;

public class FinCalcs {
	
	public static double FV_Simple(double PV, double r, double t) {
		double FV = PV*(1+r*t);
		return FV;
	}
	
	public static double FV_Compound(double PV, double i, double t) {		
		double FV = PV*Math.pow((1+i),t);
		return FV;
	}
}
