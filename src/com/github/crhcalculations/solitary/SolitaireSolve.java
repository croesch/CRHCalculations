package com.github.crhcalculations.solitary;

/**
 * This class solves the english peg solitaire and prints the solution. It will find the solution with brute force.
 * 
 * @author croesch
 * @since Date: 25.07.2010 13:49:59
 */
public final class SolitaireSolve {

  /** this is a utility class, so the constructor is not wanted. */
  private SolitaireSolve() {
  // hide this
  }

  /**
   * Solves the English Peg Solitaire. There are no arguments to pass, just call the method and the program will run and
   * print the results.
   * 
   * @author croesch
   * @since Date: 30.01.2011 15:47:53
   * @param args the parameter for the program, but they aren't read
   */
  public static void main(final String[] args) {
    EnglishPegSolitaire.findSolution();
  }

}
