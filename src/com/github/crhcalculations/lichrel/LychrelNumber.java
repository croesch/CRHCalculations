package com.github.crhcalculations.lichrel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class is able to print possible lychrel numbers. It tests each number from 1 to the maximum number to test and
 * makes the maximum number of iterations to find a palindrome. If no palindrome can be build with the algorithm than
 * this number will be a possible lychrel number and written to the stdout stream.
 * 
 * @author croesch
 * @since Date: 30.01.2011 12:07:29
 */
public final class LychrelNumber {

  /** the name of the file that contains the help text for this command. */
  private static final String HELP_FILE = "help.txt";

  /** the maximum number of iterations to find a palindrome. */
  private static final int DEFAULT_MAX_ITERATIONS = 1000;

  /** the maximum number to test if its a lychrel number. */
  private static final int DEFAULT_MAX_NUMBER = 1000;

  /** this is a utility class, so the constructor is not wanted. */
  private LychrelNumber() {
  // hide this
  }

  /**
   * Program to test some numbers if they could be lychrel numbers. It's possible to see a help to use this program and
   * to define some parameters:<br>
   * <li>{@code -help} will print a help to use this program</li> <li>{@code -iterations n}, will define the maximum
   * number of iterations per number, where n is the number of iterations, by default 1000</li> <li>{@code -max-number},
   * will define the maximum number to test, where n is the highest number to test. By default 1000</li>
   * 
   * @author croesch
   * @since Date: 30.01.2011 12:08:55
   * @param args the argument given to the program
   */
  public static void main(final String[] args) {

    final int maxArg = 4;
    final int wrongArg = 3;
    boolean illegalArgument = args.length == wrongArg || args.length > maxArg;

    boolean help = false;

    int maxNumber = DEFAULT_MAX_NUMBER;
    int maxIterations = DEFAULT_MAX_ITERATIONS;

    if (!illegalArgument) {
      if (args.length == 1) {
        if ("-help".equals(args[0])) {
          help = true;
        } else {
          illegalArgument = true;
        }
      } else {
        for (int i = 0; i < args.length; ++i) {
          if ("-iterations".equals(args[i])) {
            try {
              maxIterations = Integer.valueOf(args[++i]).intValue();
            } catch (NumberFormatException nfe) {
              illegalArgument = true;
            }
          } else if ("-max-number".equals(args[i])) {
            try {
              maxNumber = Integer.valueOf(args[++i]).intValue();
            } catch (NumberFormatException nfe) {
              illegalArgument = true;
            }
          } else {
            illegalArgument = true;
          }
        }
      }
    }

    if (!illegalArgument && !help) {
      long start = System.currentTimeMillis();
      LychrelNumberFinder.findLychrelNumbers(maxNumber, maxIterations);
      System.out.println(System.currentTimeMillis() - start + " ms");
    } else {
      printHelp();
    }
  }

  /**
   * Print the help file to the stdout-stream.
   * 
   * @author croesch
   * @since Date: 29.01.2011 15:33:59
   */
  private static void printHelp() {
    try {
      InputStream in = LychrelNumber.class.getResourceAsStream(HELP_FILE);
      if (in != null) {
        InputStreamReader isr = new InputStreamReader(in, "utf-8");
        BufferedReader reader = new BufferedReader(isr);
        String line = null;
        while ((line = reader.readLine()) != null) {
          System.out.println(line);
        }
      } else {
        throw new FileNotFoundException("file not found: " + HELP_FILE);
      }
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }
  }

}