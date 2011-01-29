package com.github.crhcalculations.lichrel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO Comment here ???
 * 
 * @author $Author: Christian $
 * @version $Revision: 1.1 $ ($Date: 25.07.2010 15:32:41 $)
 */
public class LychrelTester {
  /** Version number. */
  public static final String VER = "$Revision: 1.1 $";

  private static final int MAX_ITERATIONS = 100;

  private static final int MAX_ITERATIONS2 = 10000;

  public static void main(String[] args) {
    List<Integer> possibilities = new ArrayList<Integer>();
    for (int i = 1; i <= 1000; ++i) {
      if (findPalindrom(i, MAX_ITERATIONS) == -1) {
        System.out.println(i);
        possibilities.add(i);
      }
    }
    System.out.println("----------");
    for (int i : possibilities) {
      if (findPalindrom(i, MAX_ITERATIONS2) == -1) {
        System.out.println(i);
        //        possibilities.add(i);
      }
    }

  }

  private static long findPalindrom(long z, int end) {
    BigInteger p = new BigInteger(String.valueOf(z));
    long i = 0;
    do {
      p = p.add(reverseNumber(p));
      ++i;
      if (i >= end) {
        return -1;
        //      } else if( i % 1000 == 0) {
        //        System.out.println(i + ": " + p);
      }
      //      System.out.println("\t\tp:"+p);
    } while (!p.equals(reverseNumber(p)));
    //    System.out.println(z + "("+i+"): " + p);
    return i;
  }

  private static BigInteger reverseNumber(BigInteger z) {
    String s = String.valueOf(z);
    StringBuffer sb = new StringBuffer(s.length());
    for (int i = s.length(); i > 0; --i) {
      sb.append(s.charAt(i - 1));
    }
    return new BigInteger(sb.toString());
  }
}
