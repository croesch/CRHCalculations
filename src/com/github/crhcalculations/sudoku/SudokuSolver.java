package com.github.crhcalculations.sudoku;

/**
 * TODO Comment here
 * 
 * @author croesch
 * @since Date: 09.07.2010 16:43:15
 */
public class SudokuSolver {

  private final int[][] field = { {0, 0, 8,/**/0, 0, 6,/**/9, 1, 0},
                                 {6, 1, 0,/**/5, 4, 0,/**/0, 0, 0},
                                 {2, 9, 0,/**/1, 8, 0,/**/0, 0, 0},
                                 //****************************************************//
                                 {9, 0, 0,/**/4, 0, 0,/**/0, 0, 0},
                                 {0, 6, 2,/**/0, 9, 1,/**/0, 0, 0},
                                 {0, 0, 0,/**/0, 0, 0,/**/4, 7, 9},
                                 //****************************************************//
                                 {7, 0, 0,/**/2, 6, 0,/**/0, 0, 0},
                                 {0, 0, 0,/**/0, 0, 0,/**/6, 2, 3},
                                 {0, 2, 6,/**/0, 1, 5,/**/0, 0, 0}
  //                           {0, 0, 0,/**/0, 0, 0,/**/0, 0, 0},
  };

  private int loesungen = 0;

  public static void main(String[] args) {
    new SudokuSolver();
  }

  public SudokuSolver() {
    findSolution(0);

    System.out.println(this.loesungen + " Lösungen gefunden ...");
  }

  private void findSolution(int offs) {
    if (offs < 81) {
      if (this.field[offs / 9][offs % 9] != 0) {
        findSolution(offs + 1);
      } else {
        for (int i = 0; i < 9; ++i) {
          if (placeNumber(i + 1, offs / 9, offs % 9)) {
            findSolution(offs + 1);
            clear(offs);
          }
        }
      }
    } else {
      System.out.print("L�sung #" + ++this.loesungen + ":");
      printField();
    }
  }

  private void printField() {
    for (int i = 0; i < 9; ++i) {
      System.out.print((i % 3 == 0 ? "\n" : "") + "\n");
      for (int j = 0; j < 9; ++j) {
        System.out.print((j % 3 == 0 ? " " : "") + this.field[i][j]);
      }
    }
    System.out.println("\n");
  }

  private void clear(int i) {
    this.field[i / 9][i % 9] = 0;
  }

  private boolean placeNumber(int n, int row, int col) {

    int i, j;
    //horizontal and vertical check
    for (i = 0; i < 9; ++i) {
      if (this.field[row][i] == n || this.field[i][col] == n) {
        return false;
      }
    }

    //check the square
    int h = col / 3 * 3; //horizontal start
    int v = row / 3 * 3; //vertical start
    for (i = 0; i < 3; ++i) {
      for (j = 0; j < 3; ++j) {
        if (this.field[v + j][h + i] == n) {
          return false;
        }
      }
    }

    this.field[row][col] = n;
    return true;
  }
}
