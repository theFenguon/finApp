package finToolbox;

public class M {
	
	// Average
	public static double average(double[] in) {	
		double sum = 0;
		
		for (int i = 0; i < in.length; i++) {
			sum = sum + in[i];
		}
		
		double average = sum/in.length;
		return average;
	}
	
	
	// N-Point Moving Average
	// TODO: Review this method
	public static double moving_average(double[] in, int nPoint) {
		
		int start = in.length - nPoint;
		double sum = 0;
		
		for (int i = start; i < in.length; i++) {
			sum = sum + in[i];
		}
		
		double average = sum/nPoint;
		return average;
	}
	
	
	
}
