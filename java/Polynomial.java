package experiments;

//import umontreal.ssj.mcqmctools.MonteCarloModelDouble;
import umontreal.ssj.rng.RandomStream;

// Experiments for WSC 2024 paper.

public class Polynomial implements MonteCarloModelDoubleTag {

   int s;
   double prod;
   double[] a;

   // Constructor.
   public Polynomial(int s) {
      this.s = s;
      a = new double[s];
      for (int j = 0; j < s; j++)
         a[j] = (double) j / (double) s;
   }

   // Generates and returns X, without IS.
   public void simulate(RandomStream stream) {
      prod = 1.0;
      for (int j = 0; j < s; j++) {
         prod *= 1.0 + a[j] * (stream.nextDouble() - 0.5);
      }
   }

   // Generates and returns X, without IS.
   public double getPerformance() {
      return prod;
   }

   // Descriptor of this model.
   @Override
   public String toString() {
      return "Polynomial test function from Genz";
   }

   // Short descriptor (tag) for this model.
   public String getTag() {
      return "Polynomial";
   }
}