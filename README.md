# RQMC-samples-wsc23
Randomized quasi-Monte Carlo data for small function examples

## What this repository is about
In the paper 

* P. L'Ecuyer, M. Nakayama, A. B. Owen, and B. Tuffin, ``Confidence Intervals for Randomized Quasi-Monte Carlo Estimators,'' Proceedings of the 2023 Winter Simulation Conference, IEEE Press, 2023, 445-456. 
  See [wsc23boot.pdf](https://www-labs.iro.umontreal.ca/~lecuyer/myftp/papers/wsc23boot-cor2024.pdf)

we report an extensive experiment that compares different methods to compute a confidence interval for a quantity that is estimated by the average of a small number $R$ of independent replicates of an RQMC estimator. These methods include Student and bootstrap confidence intervals. We selected various test functions $f$ defined so that their integral over the unit hypercube $[0,1)^d$ is zero, in $d = 4, 8, 16, 32$ dimensions. For each, we tried different RQMC methods with $2^k$ points for $k = 6, 8, 10, 12, 14$. The RQMC methods and the choice of parameters for the point sets are described in the paper. They are: 
* a lattice rule with a random shift (Lat-RS);
* a lattice rule with a random shift followed by a tent (or baker's) transform (Lat-RSB);
* a Sobol net with a random digital shift (Sob-DS);
* a Sobol net with a linear matrix scramble followed by random digital shift (Sob-LMS);
* a Sobol net with a nested uniform scramble (Sob-NUS).
  
The functions $f$ considered in that paper were
