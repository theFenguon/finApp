package testBench;
import Utility.util;
import environment.Env;
import finCalcToolbox.*;
import finModels.FinModel;

public class Main {
	public static void main (String[] args) {	
		double foo = FinCalcs.FV_Simple(100, 0.1, 1);
		util.print(foo);
		
		double bar = FinCalcs.FV_Compound(1000, 0.01, 2);
		util.print(bar);
		
		//five_yr_DCF = FinModel.DCF(n, r);Env.term_growth_rate
	}
}
