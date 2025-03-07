package experiments;

//import umontreal.ssj.mcqmctools.MonteCarloModelDouble;
import umontreal.ssj.probdist.NormalDist;
import umontreal.ssj.rng.RandomStream;

// Experiments for WSC 2023 paper.

public class PieceLinGauss implements MonteCarloModelDoubleTag {

	int s;
	double invsqrts, a, sum;

	// Constructor.
	public PieceLinGauss(int s) {
		this.s = s;
		invsqrts = 1.0 / Math.sqrt(s);
		a = NormalDist.cdf01 (-1.0) - NormalDist.density01 (1.0);
	}

	// Generates and returns X, without IS.
	public void simulate (RandomStream stream) {
		sum = 0.0;
		for (int j = 0; j < s; j++) {
			sum += NormalDist.inverseF01 (stream.nextDouble());
		}
	}

	// Generates and returns X, without IS.
	public double getPerformance () {
		double prod = sum * invsqrts -1.0;
		if (prod < 0.0) return a;
		else return (a + prod);
	}

	// Descriptor of this model.
	@Override
	public String toString () {
		return "PieceLinGauss: Piecewise linear function of s Gaussians";
	}

	// Short descriptor (tag) for this model.
	public String getTag () {
		return "PieceLinGauss";
	}
}