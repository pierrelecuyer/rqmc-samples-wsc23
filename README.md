# RQMC-samples-wsc23
Randomized quasi-Monte Carlo data for small function examples

## What this repository is about
In the paper 

* P. L'Ecuyer, M. Nakayama, A. B. Owen, and B. Tuffin, ``Confidence Intervals for Randomized Quasi-Monte Carlo Estimators,'' Proceedings of the 2023 Winter Simulation Conference, IEEE Press, 2023, 445-456. 
  See [wsc23boot.pdf](https://www-labs.iro.umontreal.ca/~lecuyer/myftp/papers/wsc23boot-cor2024.pdf)

we report an extensive experiment that compares different methods to compute a confidence interval for a quantity that is estimated by the average of a small number $R$ of independent replicates of an RQMC estimator. These methods include Student and bootstrap confidence intervals. We selected various test functions $f$ defined so that their integral over the unit hypercube $[0,1)^d$ is zero, in various dimensions $d$. 
