package experiments;

//import umontreal.ssj.mcqmctools.MonteCarloModelDouble;
import umontreal.ssj.rng.RandomStream;
import umontreal.ssj.probdist.NormalDist;

// Experiments for WSC 2023 paper.

public class IndSumNormal implements MonteCarloModelDoubleTag {

	int s;
	double invsqrts, a, sum;

	// Constructor.
	public IndSumNormal(int s) {
		this.s = s;
		invsqrts = 1.0 / Math.sqrt(s);
		a = -NormalDist.cdf01 (-1.0);
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
		if (sum * invsqrts < 1) return a;
		else return (a + 1.0);
	}

	// Descriptor of this model.
	@Override
	public String toString () {
		return "IndSumNormal: Indicator of a sum of normals larger than a constant";
	}

	// Short descriptor (tag) for this model.
	public String getTag () {
		return "IndSumNormal";
	}
}
