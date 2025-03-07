# RQMC-samples-wsc23
Randomized quasi-Monte Carlo (RQMC) data for small function examples

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

Our experiment had two stages. In the first stage, for each of the $6\times 5\times 5\times 4 = 600$ combination of ($f$, method, $k$, $d$), 
we generated a pool of $N = 10,000$ independent realizations $y_1, \ldots, y_N$ of the RQMC estimator and stored the sorted values in files.
We did this in Java by using the packages `hups` and `mcqmctools` from the 
[SSJ library](https://github.com/umontreal-simul/ssj). We think that each of these large samples is highly representative of the true 
distribution of the corresponding RQMC estimator, so we can look at its empirical variance, skewness, kurtosis, and histogram,
to assess the properties of the RQMC estimator.

In the second stage, when computing confidence intervals to assess their qualities, we used the empirical distribution of these $N$ realizations 
in place of the true distribution of the RQMC estimator. That is, for each of the 600 cases, instead of generating new RQMC samples each time
we wanted to compute a confidence interval for a given $R$, we just drew a sample of size $R$ from the pool of size $N$.
This is much faster and almost the same as generating fresh samples. 

The main purpose of this GitHub site is to provide the files that contain the $N$ independent RQMC realizations for each
of the 600 cases examined in the paper.  There are actually more files because we provide results for two more functions that were not in the paper.  
Each file contains $N = 10,000$ real numbers $y_1, \ldots, y_N$, one per line, and nothing else.
The file names have the form `function-d-method-k-N.dat`, where `function` is the function name and `method` is the method name.
For example, the file `PiecewiseLinGaus-8-Sob-LMS-16-10000.dat` contains the $N=10,000$ values for the function PiecewiseLinGaus in $d=8$ dimensions,
for Sobol points with LMS, with $2^{16}$ points.  All these files are in directory `RepsRQMC`.
Alternatively, these files are also available in Dropbox at 
[this link](https://www.dropbox.com/scl/fo/cs43u4eq01or9lhu3qm1r/AIbwuW8xQG94LqsW0vvBNPs?rlkey=2lm9pm3i1r51gntlcs0qdr946&st=4hegr4yj&dl=0).

The Java code used to produce all these results is in the `java` directory. To run it or add other cases, one must first 
install SSJ by following the instructions given in the README of the [SSJ library](https://github.com/umontreal-simul/ssj)
and run the Java code by using SSJ.  The easiest way is to create a Maven project as described there.


  
