package com.github.crhcalculations.chess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.github.crhcalculations.lichrel.LychrelNumber;

/**
 * This class is able to solve the eight queens puzzle. But you can define the number of queens/rows you have.
 * 
 * @author croesch
 * @since Date: 09.07.2010 13:17:22
 */
public final class QueensPuzzle {

  /** the name of the file that contains the help text for this command. */
  private static final String HELP_FILE = "help.txt";

  /** the default size of the chessboard and the default number of queens. */
  private static final int DEFAULT_SIZE = 8;

  /**
   * This is a utility class, so the constructor is not wanted.
   * 
   * @author croesch
   * @since Date: 08.02.2011 20:33:43
   */
  private QueensPuzzle() {
    throw new AssertionError();
  }

  /**
   * Provides the ability to solve the eight queens problem or the n queens problem. Therefore it is possible to just
   * calculate the number of solutions or to show the solutions. A help text is also available.<br>
   * <li>{@code -help} will show the help text</li> <li>{@code -n} will calculate the number of solutions, {@code n} is
   * the number of queens in the field and the size of the field.</li> <li>{@code -show n} the same as {@code -n} except
   * that it prints all possible solutions.</li><br>
   * <br>
   * If no argument is given, only the number of solutions are calculated and there will be eight queens placed into an
   * 8x8 field.
   * 
   * @author croesch
   * @since Date: 29.01.2011 15:29:20
   * @param args the arguments for the program.
   */
  public static void main(final String[] args) {

    boolean illegalArgument = args.length > 2;
    boolean help = false;
    boolean print = false;

    int number = DEFAULT_SIZE;

    if (!illegalArgument) {
      if (args.length == 1) {
        if ("-help".equals(args[0])) {
          help = true;
        } else {
          try {
            number = Integer.valueOf(args[0].substring(1)).intValue();
          } catch (final NumberFormatException nfe) {
            illegalArgument = true;
          }
        }
      } else if (args.length == 2) {
        if ("-show".equals(args[0])) {
          try {
            number = Integer.valueOf(args[1]).intValue();
            print = true;
          } catch (final NumberFormatException nfe) {
            illegalArgument = true;
          }
        } else {
          illegalArgument = true;
        }
      }
    }

    if (!illegalArgument && !help) {
      final long start = System.currentTimeMillis();
      new QueensPuzzleSolver(number, print);
      System.out.println(System.currentTimeMillis() - start + " ms");
    } else {
      if (illegalArgument) {
        System.out.println("Wrong usage of '" + LychrelNumber.class.getSimpleName() + "' see help for correct usage:");
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
      final InputStream in = QueensPuzzle.class.getResourceAsStream(HELP_FILE);
      if (in != null) {
        reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
        String line = null;
        while ((line = reader.readLine()) != null) {
          System.out.println(line);
        }
      } else {
        throw new FileNotFoundException("file not found: " + HELP_FILE);
      }
    } catch (final IOException ioe) {
      System.out.println(ioe.getMessage());
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (final IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

}
