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

  public static void main(String[] args) {
    EnglishPegSolitaire.findSolution();
  }

}
