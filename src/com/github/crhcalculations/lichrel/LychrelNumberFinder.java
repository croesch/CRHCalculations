package com.github.crhcalculations.lichrel;

import java.math.BigInteger;

/**
 * This class is able to find lychrel numbers or possible lychrel numbers, since you can define the number of iterations
 * after a number is treated as a lychrel number.
 * 
 * @author croesch
 * @since Date: 25.07.2010 15:32:41
 */
public final class LychrelNumberFinder {

  /**
   * hides constructor, because this class is a utility class.
   * 
   * @author croesch
   * @since Date: 29.01.2011 22:57:50
   */
  private LychrelNumberFinder() {
  // hide constructor, because this class is a utility class
  }

  /**
   * Tries to define numbers that are potentially lychrel numbers. Therefore it iterates over numbers from one to the
   * maximum number and tries to build a palindrome but with a maximum number of iterations. If the maximum number of
   * iterations is reached and no palindrome has been build this number will be printed out.
   * 
   * @author croesch
   * @since Date: 29.01.2011 23:46:32
   * @param max the highest number to test
   * @param iterations the maximum number of iterations to build a palindrome
   */
  public static void findLychrelNumbers(final int max, final int iterations) {

    for (int i = 1; i <= max; ++i) {
      if (findPalindrom(i, iterations) == -1) {
        System.out.println(i);
      }
    }

  }

  /**
   * Tries to find the palindrome of the given number. If the palindrome isn't find after the given number of iterations
   * the method will return without having found the palindrome of the number.
   * 
   * @author croesch
   * @since Date: 29.01.2011 23:23:50
   * @param number the number that could be a lychrel number
   * @param maxIterations the maximum number of iterations
   * @return the number of iterations after a palindrome has been found or -1 if the maximum number of iterations were
   *         needed and no palindrome has been found.
   */
  private static long findPalindrom(final long number, final int maxIterations) {
    BigInteger tmpNumber = new BigInteger(String.valueOf(number));
    long iteration = 0;
    do {
      tmpNumber = tmpNumber.add(reverseNumber(tmpNumber));
      ++iteration;
      if (iteration >= maxIterations) {
        return -1;
      }
    } while (!tmpNumber.equals(reverseNumber(tmpNumber)));

    return iteration;
  }

  /**
   * Returns the reversed number of the given value. Converts the given number to a string, reverses it and returns the
   * number value of the created reverse string.
   * 
   * @author croesch
   * @since Date: 29.01.2011 23:20:21
   * @param z the number to reverse
   * @return the reversed number
   */
  private static BigInteger reverseNumber(final BigInteger z) {
    String s = String.valueOf(z);
    StringBuffer sb = new StringBuffer(s.length());
    for (int i = s.length() - 1; i >= 0; --i) {
      sb.append(s.charAt(i));
    }
    return new BigInteger(sb.toString());
  }
}
