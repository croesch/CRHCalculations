package com.github.crhcalculations.prime;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class is able to find twin prime pairs and prints it out. Therefore you can define the maximum number to test if
 * it's a prime.
 * 
 * @author croesch
 * @since Date: 30.01.2011 14:38:51
 */
public final class TwinPrime {

  /** the name of the file that contains the help text for this command. */
  private static final String HELP_FILE = "help.txt";

  /** the highest number to test if it's a prime number. */
  private static final int DEFAULT_MAX_NUMBER = 1000;

  /** this is a utility class, so the constructor is not wanted. */
  private TwinPrime() {
    throw new AssertionError();
  }

  /**
   * Is able to present all twin prime numbers from 1 to the given maximum. You can also view a help text and tehrefore
   * there are parameters:<br>
   * <li>{@code -help} prints the help file</li><li>{@code -maximum-number n} defines the highest number to test if it's
   * a prime. If this option is not called, the default is 1000.</li>
   * 
   * @author croesch
   * @since Date: 30.01.2011 14:39:35
   * @param args the parameters for this program
   */
  public static void main(final String[] args) {

    final int maxArg = 2;
    boolean illegalArgument = args.length > maxArg;

    boolean help = false;

    int maxNumber = DEFAULT_MAX_NUMBER;

    if (!illegalArgument) {
      if (args.length == 1) {
        if ("-help".equals(args[0])) {
          help = true;
        } else {
          illegalArgument = true;
        }
      } else if (args.length == 2) {
        if ("-max-number".equals(args[0])) {
          try {
            maxNumber = Integer.valueOf(args[1]).intValue();
          } catch (NumberFormatException nfe) {
            illegalArgument = true;
          }
        } else {
          illegalArgument = true;
        }
      }
    }

    if (!illegalArgument && !help) {
      long start = System.currentTimeMillis();
      TwinPrimeFinder.findTwinPrimes(maxNumber);
      System.out.println(System.currentTimeMillis() - start + " ms");
    } else {
      if (illegalArgument) {
        System.out.println("Wrong usage of '" + TwinPrime.class.getSimpleName() + "' see help for correct usage:");
      }
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
    BufferedReader reader = null;
    try {
      InputStream in = TwinPrime.class.getResourceAsStream(HELP_FILE);
      if (in != null) {
        reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
        String line = null;
        while ((line = reader.readLine()) != null) {
          System.out.println(line);
        }
      } else {
        throw new FileNotFoundException("file not found: " + HELP_FILE);
      }
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

}