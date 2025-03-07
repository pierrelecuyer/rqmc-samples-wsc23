package experiments;

//import umontreal.ssj.mcqmctools.MonteCarloModelDouble;
import umontreal.ssj.rng.RandomStream;

// Experiments for WSC 2023 paper.

public class CornerPeak implements MonteCarloModelDoubleTag {

   int s;
   double sum;
   double[] a;

   // Constructor.
   public CornerPeak(int s) {
      this.s = s;
      a = new double[s];
      for (int j = 0; j < s; j++)
         a[j] = (double) (j + 1) / (double) s;
   }

   // Generates and returns X, without IS.
   public void simulate(RandomStream stream) {
      sum = 0.0;
      for (int j = 0; j < s; j++) {
         sum += a[j] * stream.nextDouble();
      }
   }

   // Generates and returns X, without IS.
   public double getPerformance() {
      return Math.pow((1.0 + sum), -(s + 1));
   }

   // Descriptor of this model.
   @Override
   public String toString() {
      return "Corner Peak function from Genz";
   }

   // Short descriptor (tag) for this model.
   public String getTag() {
      return "CornerPeak";
   }
}