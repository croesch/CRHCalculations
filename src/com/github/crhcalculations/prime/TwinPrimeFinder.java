package com.github.crhcalculations.prime;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is able to find twin prime pairs and prints it out.
 * 
 * @author croesch
 * @since Date: 25.07.2010 16:38:47
 */
public class TwinPrimeFinder {

  private final List<Integer> primeNumbers = new ArrayList<Integer>();

  public static void main(String[] args) {
    TwinPrimeFinder tpf = new TwinPrimeFinder();
    tpf.initPrimeNumbers(100000);
    int primeOld = -12;
    for (int prime : tpf.getPrimeNumbers()) {
      if (prime - primeOld == 2) {
        System.out.println(primeOld + " - " + prime);
      }
      primeOld = prime;
    }
  }

  public List<Integer> getPrimeNumbers() {
    return this.primeNumbers;
  }

  private void initPrimeNumbers(int max) {
    for (int i = 2; i <= max; ++i) {
      if (this.primeNumbers.size() == 0) {
        this.primeNumbers.add(i);
      } else {
        boolean newPrime = true;
        for (int prime : this.primeNumbers) {
          if (i % prime == 0) {
            newPrime = false;
            break;
          }
        }
        if (newPrime) {
          this.primeNumbers.add(i);
        }
      }
    }
  }
}
