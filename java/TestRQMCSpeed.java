package experiments;

import umontreal.ssj.hups.CachedPointSet;
import umontreal.ssj.hups.DigitalNetBase2;
import umontreal.ssj.hups.LMScrambleShift;
import umontreal.ssj.hups.NestedUniformScrambling;
import umontreal.ssj.hups.PointSet;
import umontreal.ssj.hups.PointSetIterator;
import umontreal.ssj.hups.PointSetRandomization;
import umontreal.ssj.hups.RQMCPointSet;
import umontreal.ssj.hups.RandomShift;
import umontreal.ssj.hups.SobolSequence;
import umontreal.ssj.rng.LFSR113;
import umontreal.ssj.rng.RandomStream;
import umontreal.ssj.util.Chrono;

	public class TestRQMCSpeed {

		RandomStream noise = new LFSR113();
		Chrono timer = new Chrono();

		// Here we only randomize the points, but do not generate them.
	    public void testSpeedRandomize (int numReps, PointSet p, PointSetRandomization rand) {
		   int n = p.getNumPoints();
		   double sum = 0.0;
		   RQMCPointSet ps = new RQMCPointSet (p, rand);
	  	   System.out.println("Time to randomize the points for n = " + n + ",  m = " + numReps + " times ");
	       timer.init();
	       for (int rep=0; rep<numReps; rep++) {
	 		  ps.randomize();
		  	  sum += ps.getNumPoints();
	       }
	       System.out.println("Total CPU time:      " + timer.getSeconds() + " seconds");
		   System.out.println("Number of points (test) = " +  sum / numReps + "\n");
	    }

        // Here we randomize the points, generate them explicitly, and compute the average of all the coordinates.
	    public void testSpeedGenerate (int numReps, PointSet p, PointSetRandomization rand) {
		   int n = p.getNumPoints();
		   int dim = p.getDimension();
		   RQMCPointSet ps = new RQMCPointSet (p, rand);
	       PointSetIterator stream = ps.iterator ();
		   double[] point = new double[dim];
		   double sum = 0.0;
	  	   System.out.println("Time to randomize, generate with nextPoint(), and add the points coordinates ");
	  	   System.out.println("  for n = " + n + ", and m = " + numReps + " times ");
	       timer.init();
	       for (int rep=0; rep<numReps; rep++) {
	 		  ps.randomize();
	 		  stream.resetStartStream();
              for (int i=0; i<n; i++) {
                  stream.nextPoint (point, dim);
            	  for (int j=0; j<dim; j++)
            		  sum += point[j];
              }
	       }
	       System.out.println("Total CPU time:      " + timer.getSeconds() + " seconds");
		   System.out.println("Average = " +  sum / (n * dim * numReps) + "\n");
	    }

        // Same as previous method, except that here we generate only one coordinate at a time,
	    // with one function call per coordinate.
	    public void testSpeedGenerateSep (int numReps, PointSet p, PointSetRandomization rand) {
		   int n = p.getNumPoints();
		   int dim = p.getDimension();
		   RQMCPointSet ps = new RQMCPointSet (p, rand);
	       PointSetIterator stream = ps.iterator ();
		   // double[] point = new double[dim];
		   double sum = 0.0;
	  	   System.out.println("Time to randomize, generate with nextDouble(), and add the points coordinates ");
	  	   System.out.println("  for n = " + n + ", and m = " + numReps + " times ");
	       timer.init();
	       for (int rep=0; rep<numReps; rep++) {
	 		  ps.randomize();
	 		  stream.resetStartStream();
              for (int i=0; i<n; i++) {
            	  for (int j=0; j<dim; j++)
            		  sum += stream.nextDouble();
    	 		  stream.resetNextSubstream();
              }
	       }
	       System.out.println("Total CPU time:      " + timer.getSeconds() + " seconds");
		   System.out.println("Average = " +  sum / (n * dim * numReps) + "\n");
	    }


		public static void main(String[] args) {
			int dim = 10;
		    int m = 10;                     // Number of QMC randomizations.

		    TestRQMCSpeed ts = new TestRQMCSpeed();
			RandomStream noise = new LFSR113();

		    DigitalNetBase2 p15 = new SobolSequence (15, 31, dim); // 2^{15} points.
		    // DigitalNetBase2 p18 = new SobolSequence (18, 31, dim); // 2^{18} points.
		    DigitalNetBase2 p20 = new SobolSequence (20, 31, dim); // 2^{20} points.
     	    CachedPointSet cp15 = new CachedPointSet(p15);
     	    // CachedPointSet cp18 = new CachedPointSet(p18);
     	    CachedPointSet cp20 = new CachedPointSet(p20);

		    PointSetRandomization randNUS = new NestedUniformScrambling (noise, 31); // NUS
		    PointSetRandomization randLMS = new LMScrambleShift(noise);  // LMS
		    PointSetRandomization randshift = new RandomShift(noise);  // Random digital shift

		  	System.out.println("Warmup runs \n");
		    ts.testSpeedRandomize (m, cp15, randNUS);
		    ts.testSpeedRandomize (m, p15, randLMS);
		  	System.out.println("----------------------------------- \n");

		    System.out.println("Sobol points with NUS \n");
		    ts.testSpeedRandomize (m, cp15, randNUS);
		    ts.testSpeedGenerate (m, cp15, randNUS);
		    ts.testSpeedGenerateSep (m, cp15, randNUS);
		    ts.testSpeedRandomize (m, cp20, randNUS);
		    ts.testSpeedGenerate (m, cp20, randNUS);
		    ts.testSpeedGenerateSep (m, cp20, randNUS);
		  	System.out.println("----------------------------------- \n");

		  	System.out.println("Sobol points with LMS \n");
		    ts.testSpeedRandomize (m, p15, randLMS);
		    ts.testSpeedGenerate (m, p15, randLMS);
		    ts.testSpeedGenerateSep (m, p15, randLMS);
		    ts.testSpeedRandomize (m, p20, randLMS);
		    ts.testSpeedGenerate (m, p20, randLMS);
		    ts.testSpeedGenerateSep (m, p20, randLMS);
		  	System.out.println("----------------------------------- \n");

		  	System.out.println("Sobol points with random digital shift only \n");
		    ts.testSpeedRandomize (m, p15, randshift);
		    ts.testSpeedGenerate (m, p15, randshift);
		    ts.testSpeedGenerateSep (m, p15, randshift);
		    ts.testSpeedRandomize (m, p20, randshift);
		    ts.testSpeedGenerate (m, p20, randshift);
		    ts.testSpeedGenerateSep (m, p20, randshift);
		    }
    }
