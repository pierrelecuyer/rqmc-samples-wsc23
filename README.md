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
  
The selected functions $f$ were
* SumUeU:
  $f(\mathbf{u}) = -d + \sum_{j=1}^d u_j\exp(u_j)$;
* MC2:
  $f(\mathbf{u}) = -1 + (d-1/2)^{{-d}} \prod_{j=1}^d({d-x_j})$;
* PieceLinGauss:
  $f(\mathbf{u}) = \max\left( d^{-1/2}\sum_{j=1}^d\Phi^{-1}(u_j)-\tau,0\right) - \varphi(\tau)+\tau\Phi(-\tau)$;
* IndSumNormal:
  $f(\mathbf{u}) = - \Phi(-\tau) + \mathbb{I}\[d^{-1/2}\sum_{j=1}^d\Phi^{-1}(u_j)\ge\tau\]$
  where $\mathbb{I}$ is the indicator function;
* SmoothGauss:
  $f(\mathbf{u}) = -\Phi( 1/\sqrt{2}) + {d^{-1/2} \sum_{j=1}^d \Phi(1 + \Phi^{-1}(u_j))}$;
* RidgeJohnsonSU:
  $f(\mathbf{u}) = -\eta+F^{-1}(\Phi(d^{-1/2}\sum_{j=1}^d \Phi^{-1}(u_j)))$
  where $F$ is the CDF of the Johnson's SU distribution with parameters
  $\gamma=\delta=\lambda=1$, $\xi=0$, and $\eta$ is the mean of that distribution.

  
