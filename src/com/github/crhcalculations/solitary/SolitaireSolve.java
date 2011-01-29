package com.github.crhcalculations.solitary;

/**
 * TODO Comment here ???
 * 
 * @author croesch
 * @since Date: 25.07.2010 13:49:59
 */
public class SolitaireSolve {

  private static boolean findSolution(EnglishPegSolitaire es) {
    if (!es.isFinished()) {
      for (int i = 0; i < 7; ++i) {
        for (int j = 0; j < 7; ++j) {
          if (i >= 2 && i <= 4 || j >= 2 && j <= 4) {
            for (int k = 0; k < 4; ++k) {
              if (es.moveStone(i, j, k)) {
                if (findSolution(es)) {
                  es.printField();
                  es.recoverMove(i, j, k);
                  return true;
                }
                es.recoverMove(i, j, k);
              }
            }
          }
        }
      }
    } else {
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    EnglishPegSolitaire es = new EnglishPegSolitaire();
    //    es.moveStone(3, 1, 3);
    //    es.moveStone(5, 2, 0);
    //    es.moveStone(4, 4, 1);
    findSolution(es);
  }

}
