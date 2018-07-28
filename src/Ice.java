///////////////////////////////////////////////////////////////////////////////
//                
// Title:            HW9
// Files:            Ice.java
// Semester:         Fall 2017
//
// Author:           Akshay Mishra, mishra27@wisc.edu
// CS Login:         mishra
// Lecturer's Name:  Jerry
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.*;


/**
 * The Wisconsin State Climatology Office keeps a record on the number of days 
 * Lake Mendota was covered by ice at 
 * http://www.aos.wisc.edu/~sco/lakes/Mendota-ice.html.
 * This class Ice.java tries to predict future behaviors and other tasks 
 * 
 * @author aksha
 *
 */
public class Ice {

	public static void main(String[] args) {

		int flag = Integer.valueOf(args[0]);

		// initializing x and y values from webite
		int[] y = { 118, 151, 121, 96, 110, 117, 132, 104, 125, 118,
				125, 123, 110, 127, 131, 99, 126, 144, 136, 126,
				91, 130, 62, 112, 99, 161, 78, 124, 119, 124, 128, 131,
				113, 88, 75, 111, 97, 112, 101, 101, 91, 110,
				100, 130, 111, 107, 105, 89, 126, 108, 97, 94, 83, 106,
				98, 101, 108, 99, 88, 115, 102, 116, 115, 82,
				110, 81, 96, 125, 104, 105, 124, 103, 106, 96, 107, 98,
				65, 115, 91, 94, 101, 121, 105, 97, 105, 96, 82,
				116, 114, 92, 98, 101, 104, 96, 109, 122, 114, 81, 85, 
				92, 114, 111, 95, 126, 105, 108, 117, 112, 113,
				120, 65, 98, 91, 108, 113, 110, 105, 97, 105, 107, 88,
				115, 123, 118, 99, 93, 96, 54, 111, 85, 107, 89,
				87, 97, 93, 88, 99, 108, 94, 74, 119, 102, 47, 82, 53,
				115, 21, 89, 80, 101, 95, 66, 106, 97, 87, 109,
				57, 87, 117, 91, 62, 65 };

		int[] x = { 1855, 1856, 1857, 1858, 1859, 1860, 1861, 1862,
				1863, 1864, 1865, 1866, 1867, 1868, 1869, 1870,
				1871, 1872, 1873, 1874, 1875, 1876, 1877, 1878, 1879,
				1880, 1881, 1882, 1883, 1884, 1885, 1886, 1887,
				1888, 1889, 1890, 1891, 1892, 1893, 1894, 1895, 1896,
				1897, 1898, 1899, 1900, 1901, 1902, 1903, 1904,
				1905, 1906, 1907, 1908, 1909, 1910, 1911, 1912, 1913,
				1914, 1915, 1916, 1917, 1918, 1919, 1920, 1921,
				1922, 1923, 1924, 1925, 1926, 1927, 1928, 1929, 1930,
				1931, 1932, 1933, 1934, 1935, 1936, 1937, 1938,
				1939, 1940, 1941, 1942, 1943, 1944, 1945, 1946, 1947,
				1948, 1949, 1950, 1951, 1952, 1953, 1954, 1955,
				1956, 1957, 1958, 1959, 1960, 1961, 1962, 1963, 1964,
				1965, 1966, 1967, 1968, 1969, 1970, 1971, 1972,
				1973, 1974, 1975, 1976, 1977, 1978, 1979, 1980, 1981, 
				1982, 1983, 1984, 1985, 1986, 1987, 1988, 1989,
				1990, 1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998, 
				1999, 2000, 2001, 2002, 2003, 2004, 2005, 2006,
				2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 
				2016 };


		// When flag = 100, prints out the data set. One year per line with 
		// the x value first, a space, then the y value
		if (flag == 100) {

			// Iterates through x and y, and print out each elements 
			for (int i = 0; i < y.length; i++)
				System.out.println(x[i] + " " + y[i]);
		}


		// When flag = 200, prints n the number of data points, 
		// the sample mean and standard deviation of y		
		if (flag == 200) {

			// prints n
			System.out.println(y.length);


			// sums the elements of y
			int sum = 0;
			for (int i : y) {
				sum += i;
			}

			// divides by n to find mean
			double mean = sum / (double)y.length;

			// prints mean
			System.out.println(String.format("%.2f", mean));


			double summation = 0;

			// calculate the summation term
			for (int i = 0; i < y.length; i++) {
				double power = (Math.pow((y[i] - mean), 2));
				summation += power;
			}

			// calculate the term inside root
			double innerTerm = summation / (y.length - 1);

			// calculates standard deviation
			double deviation = Math.sqrt(innerTerm);

			// prints standard deviation
			System.out.println(String.format("%.2f", deviation));
		}

		// if flag = 300, performs linear regresion with model
		// f(x) = beta0 + beta1*x
		// and prints corresponding MSE
		if (flag == 300) {

			// Initialize values for beta0 and beta1 from command arg
			double beta0 = Double.valueOf(args[1]);
			double beta1 = Double.valueOf(args[2]);

			// calls method to calculate MSE
			double mse = mseCalculator(y, x, beta0, beta1);

			System.out.println(String.format("%.2f", mse));

		}

		// if flag = 400, performs gradient descent on MSE
		if (flag == 400) {

			double summation0 = 0;
			double summation1 = 0;

			// Initialize values for beta0 and beta1 from command arg
			double beta0 = Double.valueOf(args[1]);
			double beta1 = Double.valueOf(args[2]);

			// calls on method to find partial derivatives
			double mseB0 = partialWithBeta0(y, x, summation0, beta0, beta1);
			double mseB1 = partialWithBeta1(y, x, summation1, beta0, beta1);

			System.out.println(String.format("%.2f", mseB0));
			System.out.println(String.format("%.2f", mseB1));

		}

		// if flag = 500, Gradient descent starts from initial parameter of
		// beta1 = beta0 = 0, and iterates and updates beta value to find
		// MSE in each iteration
		if (flag == 500) {

			// initializing n and # of iterations
			double n = Double.valueOf(args[1]);
			int t = Integer.valueOf(args[2]);

			double beta0 = 0;
			double beta1 = 0;

			// iterates t times
			for ( int i = 1; i <= t; i++) {

				double summation0 = 0;
				double summation1 = 0;

				// calculates partial derivative by calling helper method
				double partial0 = 
						partialWithBeta0(y, x, summation0, beta0, beta1);

				System.out.println(partial0);

				// calculates partial derivative by calling helper method
				double partial1 = 
						partialWithBeta1(y, x, summation1, beta0, beta1);

				System.out.println(partial1);

				// updating beta values
				beta0 = beta0 - n*partial0;
				beta1 = beta1 - n*partial1;

				// calculates MSE by calling helper method
				double mse = mseCalculator(y, x, beta0, beta1);

				//System.out.println(i + " " + String.format("%.2f", beta0)
				//+ " " + String.format("%.2f", beta1) + " " + 
				//String.format("%.2f", mse));
			}
		}

		// if flag = 600, computes the closed-form solution for the parameters
		// directly
		if (flag == 600) {

			// finds mean of y
			int ySum = 0;
			for (int i : y) {
				ySum += i;
			}
			double yMean = ySum / (double)y.length;

			// finds mean for x
			int xSum = 0;
			for (int i : x) {
				xSum += i;
			}
			double xMean = xSum / (double)x.length;


			double summationDenominator = 0;
			double summationNumerator = 0;

			// calculates the numerator part of beta1 
			for (int i = 0; i < y.length; i++) {
				double power = (Math.pow((x[i] - xMean), 2));
				summationDenominator += power;
			}

			// calculates the  denominator part of b1
			for (int i = 0; i < y.length; i++) {
				summationNumerator += (x[i]-xMean)*(y[i] - yMean);		 
			}

			// uses numerator and denominator to find beta1
			double beta1 = summationNumerator/summationDenominator;

			// finds beta0
			double beta0 = yMean - beta1*xMean;

			double mse = mseCalculator(y, x, beta0, beta1);
			System.out.println(String.format("%.2f", beta0)
					+ " " + String.format("%.2f", beta1) + " " + 
					String.format("%.2f", mse));
		}

		// if flag = 700, print a single real number which is the 
		// predicted ice days for that year
		if ( flag == 700) {

			// initializes year from cmd line arg
			int year = Integer.valueOf(args[1]);

			// finds mean of y
			int ySum = 0;
			for (int i : y) {
				ySum += i;
			}
			double yMean = ySum / (double)y.length;

			// finds mean of x
			int xSum = 0;
			for (int i : x) {
				xSum += i;
			}
			double xMean = xSum / (double)x.length;


			double summationDenominator = 0;
			double summationNumerator = 0;

			// calculates the  denominator part of b1
			for (int i = 0; i < y.length; i++) {
				double power = (Math.pow((x[i] - xMean), 2));
				summationDenominator += power;
			}

			// calculates the numerator part of beta1 

			for (int i = 0; i < y.length; i++) {
				summationNumerator += (x[i]-xMean)*(y[i] - yMean);		 
			}

			// uses numerator and denominator to find beta1
			double beta1 = summationNumerator/summationDenominator;

			// finds beta0
			double beta0 = yMean - beta1*xMean;

			// predicts future behavior
			double future = beta0 + beta1*year;

			System.out.println(String.format("%.2f", future));
		}

		// if flag = 800, first normalizes the input x the starts from initial
		// parameter of beta1 = beta0 = 0, and iterates and updates beta value
		// to find MSE in each iteration
		if ( flag == 800) {			

			// initializes n and # of iteration from cmd line arg
			double n = Double.valueOf(args[1]);
			int t = Integer.valueOf(args[2]);

			// finds mean of x
			int xSum = 0;
			for (int i : x) {
				xSum += i;
			}
			double xMean = xSum / (double)x.length;			

			double summation = 0;

			// finds standard deviation of x
			for (int i = 0; i < x.length; i++) {
				double power = (Math.pow((x[i] - xMean), 2));
				summation += power;
			}
			double innerTerm = summation / (x.length - 1);
			double deviation = Math.sqrt(innerTerm);

			double beta0 = 0;
			double beta1 = 0;

			// repeats the same step as in flag 500, but wit normalized x
			for ( int i = 1; i <= t; i++) {

				double summation0 = 0;
				double summation1 = 0;

				// finds partial derivative for beta0 with normalized x
				for (int j = 0; j < y.length; j++) {
					double term0 = beta0 + 
							(beta1*(x[j]-xMean)/deviation) - y[j];

					summation0 += term0;

				}
				double partial0 = 2*summation0 / (y.length);

				// finds partial derivative for beta1 with normalized x
				for (int j = 0; j < y.length; j++) {
					double term1 = (beta0 + (beta1*(x[j]-xMean)/deviation) 
							- y[j])*(x[j]-xMean)/deviation;

					summation1 += term1;
				}
				double partial1 = 2*summation1 / (y.length);

				// updates beta values in each iteration
				beta0 = beta0 - n*partial0;
				beta1 = beta1 - n*partial1;

				double summation2 = 0;

				// finds MSE with normalized x
				for (int j = 0; j < y.length; j++) {
					double power = (Math.pow((beta0 + 
							(beta1*(x[j]-xMean)/deviation) - y[j]), 2));
					summation2 += power;
				}

				double mse = summation2 / (y.length);

				System.out.println(i + " " + String.format("%.2f", beta0)
				+ " " + String.format("%.2f", beta1) + " " + 
				String.format("%.2f", mse));
			}
		}

		// if flag = 900, implement Stochastic Gradient Descent (SGD)
		if (flag == 900) {

			// initializes n and # of iteration from cmd line arg
			double n = Double.valueOf(args[1]);
			int t = Integer.valueOf(args[2]);

			// finds x mean
			int xSum = 0;
			for (int i : x) {
				xSum += i;
			}
			double xMean = xSum / (double)x.length;

			double summation = 0;

			// finds standard deviation of x
			for (int i = 0; i < x.length; i++) {
				double power = (Math.pow((x[i] - xMean), 2));
				summation += power;
			}

			double innerTerm = summation / (x.length - 1);
			double deviation = Math.sqrt(innerTerm);

			double beta0 = 0;
			double beta1 = 0;

			// random number generator
			Random rng = new Random();

			for ( int i = 1; i <= t; i++) {

				// gets new int after every iteration
				int r = rng.nextInt(162);

				// finds partial derivative for beta0 with approximation
				double partial0 =
						2*(beta0 + beta1*((x[r]-xMean)/deviation)-y[r]);

				// finds partial derivative for beta1 with approximation
				double partial1 = 2*(beta0 + beta1*((x[r]-xMean)/deviation)
						-y[r])*((x[r]-xMean)/deviation);


				// updates beta values after each iteration
				beta0 = beta0 - n*partial0;
				beta1 = beta1 - n*partial1;

				double summation2 = 0;

				// finds MSE with normalized x
				for (int j = 0; j < y.length; j++) {
					double power = (Math.pow((beta0 + (beta1*(x[j]-xMean)/deviation) - y[j]), 2));
					summation2 += power;
				}

				double mse = summation2 / (y.length);

				System.out.println(i + " " + String.format("%.2f", beta0)
				+ " " + String.format("%.2f", beta1) + " " + 
				String.format("%.2f", mse));
			}
		}
	}

	/**
	 * Helper method to calculate MSE for corresponding MSE values
	 * 
	 * @param y array representing days
	 * @param x array representing years
	 * @param beta0 value for beta0
	 * @param beta1 value for beta1
	 * @return MSE value for corresponding beta values
	 */
	public static double mseCalculator(int[] y, int[] x, double beta0,
			double beta1) {

		double summation = 0;

		// calculation for MSE
		for (int i = 0; i < y.length; i++) {
			double power = (Math.pow((beta0 + (beta1*x[i]) - y[i]), 2));
			summation += power;
		}

		double mse = summation / (y.length);
		return mse;
	}

	/**
	 * Helper method to find partial derivative with respect to beta1
	 * 
	 * @param y array representing days
	 * @param x array representing years
	 * @param summation1 summation value
	 * @param beta0 value for beta0
	 * @param beta1 value for beta1
	 * @return partial derivative with respect to beta1
	 */
	public static double partialWithBeta1(int[] y, int[] x, 
			double summation1, double beta0, double beta1) {

		// calculation to find Partial derivative	
		for (int i = 0; i < y.length; i++) {
			double term1 = (beta0 + (beta1*x[i]) - y[i])*x[i];

			summation1 += term1;
		}

		double mseB1 = 2*summation1 / (y.length);
		return mseB1;
	}

	/**
	 * Helper method to find partial derivative with respect to beta0
	 * 
	 * @param y array representing days
	 * @param x array representing years
	 * @param summation1 summation value
	 * @param beta0 value for beta0
	 * @param beta1 value for beta1
	 * @return partial derivative with respect to beta0
	 */
	public static double partialWithBeta0(int[] y, int[] x, 
			double summation0, double beta0, double beta1) {

		// calculation to find Partial derivative	
		for (int i = 0; i < y.length; i++) {
			double term0 = beta0 + (beta1*x[i]) - y[i];

			summation0 += term0;

		}
		double mseB0 = 2*summation0 / (y.length);
		return mseB0;
	}

}
