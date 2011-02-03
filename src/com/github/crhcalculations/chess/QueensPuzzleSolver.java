package com.github.crhcalculations.chess;

/**
 * Solves the n queens problem via brute force and prints the number of solutions and if wanted the solutions itself.
 * 
 * @author croesch
 * @since Date: 29.01.2011 14:46:03
 */
class QueensPuzzleSolver {

  /** the size of the field for rows and columns and the number of queens. */
  private final int fieldSize;

  /** the chessboard on which the operations will be calculated. */
  private final boolean[][] chessboard;

  /** stores the number of solutions found. */
  private int numOfSolutions = 0;

  /** if the solutions that are found should be printed to the stdout stream. */
  private final boolean printSolutions;

  /**
   * Solves the n queens problem via brute force and may print every solution to the outputstream.
   * 
   * @author croesch
   * @since Date: 29.01.2011 15:54:19
   * @param size the size of the field and so the number of queens
   * @param show if every solution should be printed
   */
  QueensPuzzleSolver(final int size, final boolean show) {
    this.fieldSize = size;
    this.printSolutions = show;
    this.chessboard = new boolean[this.fieldSize][this.fieldSize];

    findSolution(0);

    System.out.println(this.numOfSolutions + " solutions");
  }

  /**
   * Starts to solve the problem via brute force and recursive.
   * 
   * @author croesch
   * @since Date: 29.01.2011 15:50:11
   * @param queenNr the number of queen to find a solution for >=0
   */
  private void findSolution(final int queenNr) {
    if (queenNr < this.fieldSize) {
      for (int i = 0; i < this.fieldSize; ++i) {
        if (placeQueen(queenNr, i)) {
          findSolution(queenNr + 1);
          clear(queenNr, i);
        }
      }
    } else {
      if (this.printSolutions) {
        printField();
      }
      ++this.numOfSolutions;
    }
  }

  /**
   * Clears the position in the field.
   * 
   * @author croesch
   * @since Date: 29.01.2011 15:43:27
   * @param row the row of the position >=0
   * @param col the column of the position >=0
   */
  private void clear(final int row, final int col) {
    this.chessboard[row][col] = false;
  }

  /**
   * Tries to place the queen on the given position.
   * 
   * @author croesch
   * @since Date: 29.01.2011 15:44:42
   * @param row the row of the position >=0
   * @param col the column of the position >=0
   * @return whether the queen could be placed.
   */
  private boolean placeQueen(final int row, final int col) {
    if (isPossiblePlace(row, col)) {
      this.chessboard[row][col] = true;
      return true;
    }
    return false;
  }

  /**
   * Checks if the given position is a possible place for a new queen.
   * 
   * @author croesch
   * @since Date: 29.01.2011 15:46:04
   * @param row the row of the position >=0
   * @param col the column of the position >=0
   * @return whether the given position is a possible place
   */
  private boolean isPossiblePlace(final int row, final int col) {
    //diagonal search - top left
    int tmpCol = col;
    int tmpRow = row;
    while (tmpRow >= 0 && tmpCol >= 0) {
      if (this.chessboard[tmpRow][tmpCol]) {
        return false;
      }
      --tmpRow;
      --tmpCol;
    }
    //diagonal search - top right
    tmpCol = col;
    tmpRow = row;
    while (tmpRow >= 0 && tmpCol < this.fieldSize) {
      if (this.chessboard[tmpRow][tmpCol]) {
        return false;
      }
      --tmpRow;
      ++tmpCol;
    }

    //vertical search
    for (tmpRow = 0; tmpRow < this.fieldSize; ++tmpRow) {
      if (this.chessboard[tmpRow][col]) {
        return false;
      }
    }

    //diagonal search - bottom right
    tmpCol = col;
    tmpRow = row;
    while (tmpRow < this.fieldSize && tmpCol < this.fieldSize) {
      if (this.chessboard[tmpRow][tmpCol]) {
        return false;
      }
      ++tmpRow;
      ++tmpCol;
    }
    //diagonal search - bottom left
    tmpCol = col;
    tmpRow = row;
    while (tmpRow < this.fieldSize && tmpCol >= 0) {
      if (this.chessboard[tmpRow][tmpCol]) {
        return false;
      }
      ++tmpRow;
      --tmpCol;
    }

    //horizontal search
    for (tmpRow = 0; tmpRow < this.fieldSize; ++tmpRow) {
      if (this.chessboard[row][tmpRow]) {
        return false;
      }
    }

    return true;
  }

  /**
   * Prints the field to the output stream. X will mean a queen and a space will mean an empty field.
   * 
   * @author croesch
   * @since Date: 29.01.2011 15:55:56
   */
  private void printField() {
    for (int i = 0; i < this.fieldSize; ++i) {
      for (int j = 0; j < this.fieldSize; ++j) {
        System.out.print("[");

        if (this.chessboard[i][j]) {
          System.out.print("X");
        } else {
          System.out.print(" ");
        }

        System.out.print("]");
      }
      System.out.print("\n");
    }
    System.out.print("\n");
  }

}
