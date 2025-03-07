package experiments;

//import umontreal.ssj.mcqmctools.MonteCarloModelDouble;
import umontreal.ssj.rng.RandomStream;

// Experiments for WSC 2023 paper.

public class Exponential implements MonteCarloModelDoubleTag {

   int s;
   double sum;
   double a = 2.0 / 3.0;

   // Constructor.
   public Exponential(int s) {
      this.s = s;
   }

   // Generates and returns X, without IS.
   public void simulate(RandomStream stream) {
      sum = 0.0;
      for (int j = 0; j < s; j++) {
         sum += stream.nextDouble();
      }
   }

   // Generates and returns X, without IS.
   public double getPerformance() {
      return Math.exp(a * sum);
   }

   // Descriptor of this model.
   @Override
   public String toString() {
      return "Exponential: exponential of a sum of uniforms over [0,a]";
   }

   // Short descriptor (tag) for this model.
   public String getTag() {
      return "Exponential";
   }
}