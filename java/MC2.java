package experiments;

import umontreal.ssj.rng.RandomStream;

// Experiments for WSC 2023 paper

public class MC2 implements MonteCarloModelDoubleTag {

   int s;
   double ds, ds2, prod;

   // Constructor.
   public MC2(int s) {
      this.s = s;
      ds = (double) s;
      ds2 = 1.0 / (ds - 0.5);
   }

   // Generates and returns X, without IS.
   public void simulate(RandomStream stream) {
      prod = 1.0;
      for (int j = 0; j < s; j++) {
         prod *= (ds - stream.nextDouble()) * ds2;
         // prod *= (s-stream.nextDouble()) / (s - 0.5);
      }
   }

   // Generates and returns X, without IS.
   public double getPerformance() {
      return prod - 1.0;
   }

   // Descriptor of this model.
   @Override
   public String toString() {
      return "Morokoff and Caflisch function";
   }

   // Short descriptor (tag) for this model.
   public String getTag() {
      return "MC2";
   }
}
