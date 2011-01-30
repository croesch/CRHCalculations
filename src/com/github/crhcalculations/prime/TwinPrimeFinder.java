package com.github.crhcalculations.prime;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is able to find twin prime pairs and prints it out.
 * 
 * @author croesch
 * @since Date: 25.07.2010 16:38:47
 */
public final class TwinPrimeFinder {

  /** the list of prime numbers. */
  private final List<Integer> primeNumbers = new ArrayList<Integer>();

  /** this is a utility class, so the constructor is not wanted. */
  private TwinPrimeFinder() {
  // hide this
  }

  /**
   * Prints all twin primes to the stdout stream. First the program defines all primes to the maximum number and then it
   * looks for pairs.
   * 
   * @author croesch
   * @since Date: 30.01.2011 12:27:03
   * @param max the maximum number to test if its a prime number
   */
  public static void findTwinPrimes(final int max) {
    TwinPrimeFinder tpf = new TwinPrimeFinder();
    tpf.initPrimeNumbers(max);
    int primeOld = -1;
    for (int prime : tpf.getPrimeNumbers()) {
      if (prime - primeOld == 2) {
        System.out.println(primeOld + " - " + prime);
      }
      primeOld = prime;
    }
  }

  /**
   * Returns the list of prime numbers.
   * 
   * @author croesch
   * @since Date: 30.01.2011 12:29:23
   * @return a list that contains all prime numbers up to the maximum number
   */
  private List<Integer> getPrimeNumbers() {
    return this.primeNumbers;
  }

  /**
   * Initialises the list of prime numbers. Therefore it tests each number up to the maximum number if its a prime. If
   * so, it will add it to the list.
   * 
   * @author croesch
   * @since Date: 30.01.2011 12:30:28
   * @param max the highest number to test if it's a prime
   */
  private void initPrimeNumbers(final int max) {
    for (int number = 2; number <= max; ++number) {
      if (this.primeNumbers.size() == 0) {
        this.primeNumbers.add(Integer.valueOf(number));
      } else {
        boolean newPrime = true;
        for (int prime : this.primeNumbers) {
          if (number % prime == 0) {
            newPrime = false;
            break;
          }
        }
        if (newPrime) {
          this.primeNumbers.add(Integer.valueOf(number));
        }
      }
    }
  }
}
