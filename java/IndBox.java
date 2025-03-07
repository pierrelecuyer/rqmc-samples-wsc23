package experiments;

//import umontreal.ssj.mcqmctools.MonteCarloModelDouble;
import umontreal.ssj.rng.RandomStream;

// Experiments for WSC 2023 paper.

public class IndBox implements MonteCarloModelDoubleTag {

   int s;
   double a, b, sum;

   // Constructor.
   public IndBox(int s) {
      this.s = s;
      b = 1.0 / Math.PI;
      a = s * b;
   }

   // Generates and returns X, without IS.
   public void simulate(RandomStream stream) {
      sum = 0.0;
      for (int j = 0; j < s; j++) {
         if (stream.nextDouble() < b)
            sum += 1.0;
      }
   }

   // Generates and returns X, without IS.
   public double getPerformance() {
      return sum - a;
   }

   // Descriptor of this model.
   @Override
   public String toString() {
      return "IndBox: Sum of indicators of (U_j < 1/pi)";
   }

   // Short descriptor (tag) for this model.
   public String getTag() {
      return "IndBox";
   }
}
