package com.github.crhcalculations.solitary;

/**
 * Represents a field of the English Peg solitaire. And is able to find a solution to solve the puzzle.
 * 
 * @author croesch
 * @since Date: 25.07.2010 13:37:59
 */
public final class EnglishPegSolitaire {

  /** the size of the field number of fields in one row/column. */
  private static final int FIELD_SIZE = 7;

  /** the number of maximal possible directions to move a stone. */
  private static final int MAX_DIRECTIONS = 4;

  /** the minimal y position that is possible if the x position is outside the borders. */
  private static final int Y_MIN = 2;

  /** the maximal y position that is possible if the x position is outside the borders. */
  private static final int Y_MAX = 4;

  /** the minimal x position that is possible if the y position is outside the borders. */
  private static final int X_MIN = 2;

  /** the maximal x position that is possible if the y position is outside the borders. */
  private static final int X_MAX = 4;

  /** the direction to move the stone in direction top. */
  private static final int TOP = 0;

  /** the direction to move the stone in direction left. */
  private static final int LEFT = 1;

  /** the direction to move the stone in direction down. */
  private static final int DOWN = 2;

  /** the direction to move the stone in direction right. */
  private static final int RIGHT = 3;

  /** the position in the field that represents the center. */
  private static final int CENTER_POSITION = 3;

  /** the field that represents where stones are placed. */
  private final int[][] field = new int[FIELD_SIZE][FIELD_SIZE];

  /**
   * Constructs the field and initialises the stones that are in the field from beginning.
   * 
   * @author croesch
   * @since Date: 30.01.2011 12:54:53
   */
  private EnglishPegSolitaire() {
    initField();
  }

  /**
   * Solves the puzzle via brute force.
   * 
   * @author croesch
   * @since Date: 30.01.2011 12:55:26
   */
  public static void findSolution() {
    findSolution(new EnglishPegSolitaire());
  }

  /**
   * Solves the puzzle recursively displays the possible moves to solve the puzzle.
   * 
   * @author croesch
   * @since Date: 30.01.2011 12:58:13
   * @param es the field to solve.
   * @return {@code true} if the current step leads to the correct solution
   */
  public static boolean findSolution(final EnglishPegSolitaire es) {
    if (!es.isFinished()) {
      for (int y = 0; y < FIELD_SIZE; ++y) {
        for (int x = 0; x < FIELD_SIZE; ++x) {
          if (y >= Y_MIN && y <= Y_MAX || x >= X_MIN && x <= X_MAX) {
            for (int direction = 0; direction < MAX_DIRECTIONS; ++direction) {
              if (es.moveStone(y, x, direction)) {
                if (findSolution(es)) {
                  es.printField();
                  es.undoMove(y, x, direction);
                  return true;
                }
                es.undoMove(y, x, direction);
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

  /**
   * Moves a stone of the given position in the given direction.
   * 
   * @param y the y position >=0
   * @param x the x position >=0
   * @param dir the direction: 0: top, 1:left, 2:down, 3:right
   * @return {@code true}, if the move is possible and has been done
   */
  private boolean moveStone(final int y, final int x, final int dir) {

    boolean possible = isPossibleMove(y, x, dir);
    if (possible) {
      this.field[y][x] = 0;
      switch (dir) {
        case TOP:
          this.field[y - 1][x] = 0;
          this.field[y - 2][x] = 1;
          break;
        case LEFT:
          this.field[y][x - 1] = 0;
          this.field[y][x - 2] = 1;
          break;
        case DOWN:
          this.field[y + 1][x] = 0;
          this.field[y + 2][x] = 1;
          break;
        case RIGHT:
          this.field[y][x + 1] = 0;
          this.field[y][x + 2] = 1;
          break;
        default:
          return false;
      }
      return true;
    }
    return false;
  }

  /**
   * Tests if in the current field this move is a valid one. So if there is no stone to move or no stone to jump over or
   * a stone at the destination position, then this is not valid.
   * 
   * @author croesch
   * @since Date: 30.01.2011 13:33:40
   * @param y the y position of the stone to move >= 0
   * @param x the x position of the stone to move >= 0
   * @param dir the direction in which to move
   * @return {@code true}, if this would be a valid/possible move
   */
  private boolean isPossibleMove(final int y, final int x, final int dir) {
    if (this.field[y][x] != 1) {
      return false;
    }
    if (dir == TOP) { //top
      if (y < Y_MIN || (x < X_MIN || x > X_MAX) && y != Y_MAX) {
        return false;
      }
      if (this.field[y - 1][x] == 1 && this.field[y - 2][x] == 0) {
        return true;
      }
      return false;
    }
    if (dir == LEFT) { //left
      if (x < X_MIN || (y < Y_MIN || y > Y_MAX) && x != X_MAX) {
        return false;
      }
      if (this.field[y][x - 1] == 1 && this.field[y][x - 2] == 0) {
        return true;
      }
      return false;
    }
    if (dir == DOWN) { //down
      if (y > Y_MAX || (x < X_MIN || x > X_MAX) && y != Y_MIN) {
        return false;
      }
      if (this.field[y + 1][x] == 1 && this.field[y + 2][x] == 0) {
        return true;
      }
      return false;
    }
    if (dir == RIGHT) { //right
      if (x > X_MAX || (y < Y_MIN || y > Y_MAX) && x != X_MIN) {
        return false;
      }
      if (this.field[y][x + 1] == 1 && this.field[y][x + 2] == 0) {
        return true;
      }
      return false;
    }
    return false;
  }

  /**
   * Tests if the field is in the finished state.
   * 
   * @author croesch
   * @since Date: 30.01.2011 13:35:19
   * @return {@code true}, if it's finished
   */
  private boolean isFinished() {

    for (int y = 0; y < FIELD_SIZE; ++y) {
      for (int x = 0; x < FIELD_SIZE; ++x) {
        if (y >= Y_MIN && y <= Y_MAX || x >= X_MIN && x <= X_MAX) {
          if (y != CENTER_POSITION || x != CENTER_POSITION) {
            if (this.field[y][x] != 0) {
              return false;
            }
          } else {
            if (this.field[y][x] != 1) {
              return false;
            }
          }
        } else {
          if (this.field[y][x] != -1) {
            return false;
          }
        }
      }
    }

    return true;
  }

  /**
   * Initialises the field with the start values.
   * 
   * @author croesch
   * @since Date: 30.01.2011 13:35:53
   */
  private void initField() {
    for (int y = 0; y < FIELD_SIZE; ++y) {
      for (int x = 0; x < FIELD_SIZE; ++x) {
        if (y >= Y_MIN && y <= Y_MAX || x >= X_MIN && x <= X_MAX) {
          if (y != CENTER_POSITION || x != CENTER_POSITION) {
            this.field[y][x] = 1;
          } else {
            this.field[y][x] = 0;
          }
        } else {
          this.field[y][x] = -1;
        }
      }
    }
  }

  /**
   * Prints the field to the stdout stream.
   * 
   * @author croesch
   * @since Date: 30.01.2011 13:36:11
   */
  private void printField() {
    for (int y = 0; y < FIELD_SIZE; ++y) {
      for (int x = 0; x < FIELD_SIZE; ++x) {
        if (this.field[y][x] == 1) {
          System.out.print("X ");
        } else if (this.field[y][x] == 0) {
          System.out.print("O ");
        } else {
          System.out.print("  ");
        }
      }
      System.out.println();
    }
    System.out.println("\n");
  }

  /**
   * Tests if this stone can be moved backwards, to undo the previous move. Normally if everything is right should be
   * ever true.
   * 
   * @author croesch
   * @since Date: 30.01.2011 13:36:34
   * @param y the y position from which the move started >=0
   * @param x the x position from which the move started >=0
   * @param dir the direction in which the move has been made
   * @return {@code true}, if its possible to undo this move
   */
  private boolean canUndoMove(final int y, final int x, final int dir) {
    if (this.field[y][x] != 0) {
      return false;
    }
    if (dir == TOP) { //top
      if (y < Y_MIN || (x < X_MIN || x > X_MAX) && y != Y_MAX) {
        return false;
      }
      if (this.field[y - 1][x] == 0 && this.field[y - 2][x] == 1) {
        return true;
      }
      return false;
    } else if (dir == LEFT) { //left
      if (x < X_MIN || (y < Y_MIN || y > Y_MAX) && x != X_MAX) {
        return false;
      }
      if (this.field[y][x - 1] == 0 && this.field[y][x - 2] == 1) {
        return true;
      }
      return false;
    } else if (dir == DOWN) { //down
      if (y > Y_MAX || (x < X_MIN || x > X_MAX) && y != Y_MIN) {
        return false;
      }
      if (this.field[y + 1][x] == 0 && this.field[y + 2][x] == 1) {
        return true;
      }
      return false;
    } else if (dir == RIGHT) { //right
      if (x > X_MAX || (y < Y_MIN || y > Y_MAX) && x != X_MIN) {
        return false;
      }
      if (this.field[y][x + 1] == 0 && this.field[y][x + 2] == 1) {
        return true;
      }
      return false;
    }
    return false;
  }

  /**
   * Tries to undo the given move.
   * 
   * @author croesch
   * @since Date: 30.01.2011 13:38:55
   * @param y the y position from which the move started >=0
   * @param x the x position from which the move started >=0
   * @param dir the direction in which the move has been done
   * @return {@code true}, if the move has been undone
   */
  private boolean undoMove(final int y, final int x, final int dir) {
    boolean possible = canUndoMove(y, x, dir);
    if (possible) {
      this.field[y][x] = 1;
      switch (dir) {
        case TOP:
          this.field[y - 1][x] = 1;
          this.field[y - 2][x] = 0;
          break;
        case LEFT:
          this.field[y][x - 1] = 1;
          this.field[y][x - 2] = 0;
          break;
        case DOWN:
          this.field[y + 1][x] = 1;
          this.field[y + 2][x] = 0;
          break;
        case RIGHT:
          this.field[y][x + 1] = 1;
          this.field[y][x + 2] = 0;
          break;
        default:
          return false;
      }
      return true;
    }
    return false;
  }

}
