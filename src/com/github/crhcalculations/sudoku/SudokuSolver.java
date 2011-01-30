package com.github.crhcalculations.sudoku;

/**
 * This class is able to solve a sudoku, the algorithm is brute force.
 * 
 * @author croesch
 * @since Date: 09.07.2010 16:43:15
 */
public class SudokuSolver {

  /** number of fields per square and squares per game and possible numbers in one field. */
  private static final int SIZE = 9;

  /** the width and height of a square in fields. */
  private static final int SQUARE_SIZE = 3;

  /** the sudoku field. */
  private final int[][] field;

  /** the number of solutions found. */
  private int solutions = 0;

  /**
   * Constructs a sudoku field with the given initial value and tries to solve it. Prints the solution on screen.
   * 
   * @author croesch
   * @since Date: 30.01.2011 15:58:06
   * @param initialField the initial value for the field
   */
  public SudokuSolver(final String initialField) {
    if (initialField == null || initialField.length() != SIZE * SIZE) {
      throw new IllegalArgumentException();
    }
    this.field = new int[SIZE][SIZE];
    for (int i = 0; i < initialField.length(); ++i) {
      int num = Integer.valueOf(String.valueOf(initialField.charAt(i))).intValue();
      this.field[i / SIZE][i % SIZE] = num;
    }

    findSolution(0);

    System.out.println(this.solutions + " solutions found ...");
  }

  /**
   * Tries to solve the given sudoku field with brute force. Iterates over the fields and the possible values to find a
   * solution.
   * 
   * @author croesch
   * @since Date: 30.01.2011 13:48:47
   * @param offs the offset of the field to be filled
   */
  public final void findSolution(final int offs) {
    if (offs < SIZE * SIZE) {
      // continue if in the current field is already a value
      if (this.field[offs / SIZE][offs % SIZE] != 0) {
        findSolution(offs + 1);
      } else {
        for (int i = 0; i < SIZE; ++i) {
          if (placeNumber(i + 1, offs / SIZE, offs % SIZE)) {
            findSolution(offs + 1);
            clear(offs);
          }
        }
      }
    } else {
      System.out.print("solution #" + ++this.solutions + ":");
      printField();
    }
  }

  /**
   * Prints the current field to the stdout stream.
   * 
   * @author croesch
   * @since Date: 30.01.2011 13:55:18
   */
  private void printField() {
    for (int row = 0; row < SIZE; ++row) {
      // add a row between the single squares
      if (row % SQUARE_SIZE == 0) {
        System.out.println("\n");
      } else {
        System.out.println();
      }
      for (int col = 0; col < SIZE; ++col) {
        // add a column between the squares
        if (col % SQUARE_SIZE == 0) {
          System.out.print(" ");
        }
        System.out.print(this.field[row][col]);
      }
    }
    System.out.println("\n");
  }

  /**
   * Clears the given field.
   * 
   * @author croesch
   * @since Date: 30.01.2011 13:56:02
   * @param i the offset of the field
   */
  private void clear(final int i) {
    this.field[i / SIZE][i % SIZE] = 0;
  }

  /**
   * Places the given number to the given field, if possible.
   * 
   * @author croesch
   * @since Date: 30.01.2011 14:01:08
   * @param value the value to place into the field
   * @param row the row of the field >= 0
   * @param col the column of the field >= 0
   * @return {@code true}, if the value has been placed
   */
  private boolean placeNumber(final int value, final int row, final int col) {

    //horizontal and vertical check
    for (int i = 0; i < SIZE; ++i) {
      if (this.field[row][i] == value || this.field[i][col] == value) {
        return false;
      }
    }

    //check the square (use integer behaviour when dividing to define the start of the square)
    int squareXOffs = col / SQUARE_SIZE * SQUARE_SIZE; //horizontal start
    int squareYOffs = row / SQUARE_SIZE * SQUARE_SIZE; //vertical start

    for (int fieldXOffs = 0; fieldXOffs < SQUARE_SIZE; ++fieldXOffs) {
      for (int fieldYOffs = 0; fieldYOffs < SQUARE_SIZE; ++fieldYOffs) {
        if (this.field[squareYOffs + fieldYOffs][squareXOffs + fieldXOffs] == value) {
          return false;
        }
      }
    }

    this.field[row][col] = value;
    return true;
  }
}
