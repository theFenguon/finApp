package testBench;
import Utility.util;
import finCalcToolbox.*;

public class Main {
	public static void main (String[] args) {	
		double foo = FinCalcs.FV_Simple(100, 0.1, 1);
		util.print(foo);
	}
}
