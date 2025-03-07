package experiments;

import umontreal.ssj.mcqmctools.MonteCarloModelDouble;
import umontreal.ssj.rng.RandomStream;

// Experiments for WSC 2023 paper

public class SumUniforms implements MonteCarloModelDoubleTag {

	int s;
	double sum;

	// Constructor.
	public SumUniforms(int s) {
		this.s = s;
	}

	// Generates and returns X, without IS.
	public void simulate (RandomStream stream) {
		sum = 0.0;
		for (int j = 0; j < s; j++) {
			sum += stream.nextDouble();
		}
	}

	// Generates and returns X, without IS.
	public double getPerformance () {
		return 2.0 * sum / s - 1.0;
	}

	// Descriptor of this model.
	@Override
	public String toString () {
		return "SumUniforms: Twice the average of s U(0,1)";
	}

	// Short descriptor (tag) for this model.
	public String getTag () {
		return "SumUniforms";
	}
}
