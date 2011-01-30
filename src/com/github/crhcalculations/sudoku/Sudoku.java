package com.github.crhcalculations.sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class is able to solve a sudoku from the given field. Each solution will be printed on the screen and also the
 * time it took to calculate it.
 * 
 * @author croesch
 * @since Date: 30.01.2011 16:09:17
 */
public final class Sudoku {

  /** the name of the file that contains the help text for this command. */
  private static final String HELP_FILE = "help.txt";

  /** this is a utility class, so the constructor is not wanted. */
  private Sudoku() {
  // hide this
  }

  /**
   * This program solves the given sudoku field. There is only the possibility of one argument. Either {@code -help} to
   * view the help text or 81 characters representing the starting sudoku field. Where 0 stands for no value and 1-9 for
   * the numbers. It's important that this String doesn't contain any spaces or other characters than 0-9.
   * 
   * @author croesch
   * @since Date: 30.01.2011 16:09:58
   * @param args Either {@code -help} or the beginning field
   */
  public static void main(final String[] args) {

    final int requiredArgLength = 81;

    final int maxArg = 1;
    final int wrongArg = 0;
    boolean illegalArgument = args.length == wrongArg || args.length > maxArg;

    boolean help = false;

    if (!illegalArgument) {
      if ("-help".equals(args[0])) {
        help = true;
      } else if (args[0].length() != requiredArgLength) {
        illegalArgument = true;
      }
    }

    if (!illegalArgument && !help) {
      long start = System.currentTimeMillis();
      new SudokuSolver(args[0]);
      System.out.println(System.currentTimeMillis() - start + " ms");
    } else {
      if (illegalArgument) {
        System.out.println("Wrong usage of '" + Sudoku.class.getSimpleName() + "' see help for correct usage:");
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
    try {
      InputStream in = Sudoku.class.getResourceAsStream(HELP_FILE);
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