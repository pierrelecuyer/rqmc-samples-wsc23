package experiments;

//import umontreal.ssj.mcqmctools.MonteCarloModelDouble;
import umontreal.ssj.probdist.LognormalDist;
import umontreal.ssj.rng.RandomStream;

// Experiments for WSC 2023 paper

public class SumLogNorm implements MonteCarloModelDoubleTag {

	int s;
	double sig, a, sum;

	// Constructor.
	public SumLogNorm(int s) {
		this.s = s;
		sig = 1;
		a = Math.exp(sig*sig/2);
	}

	// Generates and returns X, without IS.
	public void simulate (RandomStream stream) {
		sum = 0.0;
		for (int j = 0; j < s; j++) {
			sum += LognormalDist.inverseF (0,sig,stream.nextDouble());
		}
	}

	// Generates and returns X, without IS.
	public double getPerformance () {
		return sum - a*s;
	}

	// Descriptor of this model.
	@Override
	public String toString () {
		return "SumLogNorms: sum of s lognorms";
	}

	// Short descriptor (tag) for this model.
	public String getTag () {
		return "SumLogNorms";
	}
}
