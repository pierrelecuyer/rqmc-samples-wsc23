package experiments;

import umontreal.ssj.mcqmctools.MonteCarloModelDouble;

public interface MonteCarloModelDoubleTag extends MonteCarloModelDouble {

   /**
    * Returns a very short description (tag).
    */
   public String getTag();

}
