package com.github.crhcalculations.chess;

/**
 * TODO Comment here
 * 
 * @author $Author: Christian $
 * @version $Revision: 1.1 $ ($Date: 09.07.2010 13:17:22 $)
 */
public class QueensProblem {

  private static final int MAX_SIZE = 8;

  private final boolean[][] schachbrett = new boolean[MAX_SIZE][MAX_SIZE];

  private int anzahl = 0;

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    new QueensProblem();
    System.out.println(System.currentTimeMillis() - start + " ms");
  }

  public QueensProblem() {

    clearField();

    find_solution(0);

    System.out.println(this.anzahl + " Lösungen gefunden");
  }

  private void find_solution(int dameNr) {
    if (dameNr < MAX_SIZE) {
      for (int i = 0; i < MAX_SIZE; ++i) {
        if (place_queen(dameNr, i)) {
          find_solution(dameNr + 1);
          clear(dameNr, i);
        }
      }
    } else {
      //      printField();
      ++this.anzahl;
    }
  }

  private void clear(int ln, int col) {
    this.schachbrett[ln][col] = false;
  }

  private boolean place_queen(int i, int j) {
    if (isPossiblePlace(i, j)) {
      this.schachbrett[i][j] = true;
      return true;
    }
    return false;
  }

  //  private boolean place_queen(int start) {
  //    for (int i = 0; i < 8; ++i) {
  //      for (int j = 0; j < 8; ++j) {
  //        if (isPossiblePlace(i, j)) {
  //          this.Schachbrett[i][j] = true;
  //          return true;
  //        }
  //      }
  //    }
  //    return false;
  //  }

  private boolean isPossiblePlace(int ln, int col) {
    //diagonal search - top left
    int j = col;
    int i = ln;
    while (i >= 0 && j >= 0) {
      if (this.schachbrett[i][j]) {
        return false;
      }
      --i;
      --j;
    }
    //diagonal search - top right
    j = col;
    i = ln;
    while (i >= 0 && j < MAX_SIZE) {
      if (this.schachbrett[i][j]) {
        return false;
      }
      --i;
      ++j;
    }

    //vertical search
    for (i = 0; i < MAX_SIZE; ++i) {
      if (this.schachbrett[i][col]) {
        return false;
      }
    }

    //diagonal search - bottom right
    j = col;
    i = ln;
    while (i < MAX_SIZE && j < MAX_SIZE) {
      if (this.schachbrett[i][j]) {
        return false;
      }
      ++i;
      ++j;
    }
    //diagonal search - bottom left
    j = col;
    i = ln;
    while (i < MAX_SIZE && j >= 0) {
      if (this.schachbrett[i][j]) {
        return false;
      }
      ++i;
      --j;
    }

    //horizontal search
    for (i = 0; i < MAX_SIZE; ++i) {
      if (this.schachbrett[ln][i] == true) {
        return false;
      }
    }

    return true;
  }

  private void clearField() {
    for (int i = 0; i < MAX_SIZE; ++i) {
      for (int j = 0; j < MAX_SIZE; ++j) {
        if (this.schachbrett[i][j]) {
          this.schachbrett[i][j] = false;
        }
      }
    }
  }

  private void printField() {
    for (int i = 0; i < MAX_SIZE; ++i) {
      for (int j = 0; j < MAX_SIZE; ++j) {
        System.out.print("[" + (this.schachbrett[i][j] ? "X" : " ") + "]");
      }
      System.out.print("\n");
    }
    System.out.print("\n");
  }

}