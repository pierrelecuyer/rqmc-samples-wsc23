package experiments;

import umontreal.ssj.mcqmctools.MonteCarloModelDouble;
import umontreal.ssj.rng.RandomStream;

// Experiments for WSC 2023 paper: sum of U_j \exp(U_j)

public class SumUeU implements MonteCarloModelDoubleTag {

   int s;
   double sum, u;

   // Constructor.
   public SumUeU(int s) {
      this.s = s;
   }

   // Generates and returns X, without IS.
   public void simulate(RandomStream stream) {
      sum = 0.0;
      for (int j = 0; j < s; j++) {
         u = stream.nextDouble();
         sum += u * Math.exp(u);
      }
   }

   // Generates and returns X, without IS.
   public double getPerformance() {
      return sum - s;
   }

   // Descriptor of this model.
   @Override
   public String toString() {
      return "SumUeU: Sum of U_j exp(U_j)";
   }

   // Short descriptor (tag) for this model.
   public String getTag() {
      return "SumUeU";
   }
}
