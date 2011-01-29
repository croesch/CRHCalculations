package com.github.crhcalculations.chess;

/**
 * This class is able to solve the eight queens puzzle. But you can define the number of queens/rows you have.
 * 
 * @author croesch
 * @since Date: 09.07.2010 13:17:22
 */
public class QueensPuzzle {

  private static final int MAX_SIZE = 8;

  private final boolean[][] chessboard = new boolean[MAX_SIZE][MAX_SIZE];

  private int numOfSolutions = 0;

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    new QueensPuzzle();
    System.out.println(System.currentTimeMillis() - start + " ms");
  }

  public QueensPuzzle() {

    clearField();

    findSolution(0);

    System.out.println(this.numOfSolutions + " solutions");
  }

  private void findSolution(int queenNr) {
    if (queenNr < MAX_SIZE) {
      for (int i = 0; i < MAX_SIZE; ++i) {
        if (placeQueen(queenNr, i)) {
          findSolution(queenNr + 1);
          clear(queenNr, i);
        }
      }
    } else {
      //      printField();
      ++this.numOfSolutions;
    }
  }

  private void clear(int ln, int col) {
    this.chessboard[ln][col] = false;
  }

  private boolean placeQueen(int i, int j) {
    if (isPossiblePlace(i, j)) {
      this.chessboard[i][j] = true;
      return true;
    }
    return false;
  }

  private boolean isPossiblePlace(int ln, int col) {
    //diagonal search - top left
    int j = col;
    int i = ln;
    while (i >= 0 && j >= 0) {
      if (this.chessboard[i][j]) {
        return false;
      }
      --i;
      --j;
    }
    //diagonal search - top right
    j = col;
    i = ln;
    while (i >= 0 && j < MAX_SIZE) {
      if (this.chessboard[i][j]) {
        return false;
      }
      --i;
      ++j;
    }

    //vertical search
    for (i = 0; i < MAX_SIZE; ++i) {
      if (this.chessboard[i][col]) {
        return false;
      }
    }

    //diagonal search - bottom right
    j = col;
    i = ln;
    while (i < MAX_SIZE && j < MAX_SIZE) {
      if (this.chessboard[i][j]) {
        return false;
      }
      ++i;
      ++j;
    }
    //diagonal search - bottom left
    j = col;
    i = ln;
    while (i < MAX_SIZE && j >= 0) {
      if (this.chessboard[i][j]) {
        return false;
      }
      ++i;
      --j;
    }

    //horizontal search
    for (i = 0; i < MAX_SIZE; ++i) {
      if (this.chessboard[ln][i] == true) {
        return false;
      }
    }

    return true;
  }

  private void clearField() {
    for (int i = 0; i < MAX_SIZE; ++i) {
      for (int j = 0; j < MAX_SIZE; ++j) {
        if (this.chessboard[i][j]) {
          this.chessboard[i][j] = false;
        }
      }
    }
  }

  private void printField() {
    for (int i = 0; i < MAX_SIZE; ++i) {
      for (int j = 0; j < MAX_SIZE; ++j) {
        System.out.print("[" + (this.chessboard[i][j] ? "X" : " ") + "]");
      }
      System.out.print("\n");
    }
    System.out.print("\n");
  }

}