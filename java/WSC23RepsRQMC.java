package experiments;

import java.io.*;
// import umontreal.ssj.mcqmctools.MonteCarloModelDouble;
import umontreal.ssj.mcqmctools.RQMCExperiment;
import umontreal.ssj.util.Chrono;


// Generate and store RQMC replicates for various models, for WSC 2023 paper

public class WSC23RepsRQMC extends RQMCExperiment {
	
	public static void main(String[] args) throws IOException {

		int m = 10000;                // Number of RQMC randomizations.
		MonteCarloModelDoubleTag model;
		Chrono timerTotal = new Chrono();

		for (int s = 4; s <= 32; s *= 2) {
			for (int k = 6; k <= 14; k = k+2) {
				// Uncomment the model you want below.  ***
				//model = new SumUeU(s); 
				//model = new IndBox(s);
				//model = new PieceLinGauss(s);
				model = new IndSumNormal(s);
				//model = new SmoothGauss(s);
				//model = new SumUniforms(s);
		   	    RQMCSamples.simulRepsAllTypes (model, s, k, m);
			}
		}
	    System.out.println ("Total time for everything: " 
		        + timerTotal.format() + "\n====================== \n");
	}
}
