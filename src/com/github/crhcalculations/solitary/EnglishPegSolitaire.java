package com.github.crhcalculations.solitary;

/**
 * TODO Comment here ???
 * 
 * @author $Author: Christian $
 * @version $Revision: 1.1 $ ($Date: 25.07.2010 13:37:59 $)
 */
public class EnglishPegSolitaire {
  /** Version number. */
  public static final String VER = "$Revision: 1.1 $";

  private final int[][] field = new int[7][7];

  public EnglishPegSolitaire() {
    initField();
  }

  /**
   * @param dir: 0: top, 1:left, 2:down, 3:right
   */
  public boolean moveStone(int y, int x, int dir) {
    boolean possible = isPossibleMove(y, x, dir);
    if (possible) {
      this.field[y][x] = 0;
      switch (dir) {
        case 0:
          this.field[y - 1][x] = 0;
          this.field[y - 2][x] = 1;
          break;
        case 1:
          this.field[y][x - 1] = 0;
          this.field[y][x - 2] = 1;
          break;
        case 2:
          this.field[y + 1][x] = 0;
          this.field[y + 2][x] = 1;
          break;
        case 3:
          this.field[y][x + 1] = 0;
          this.field[y][x + 2] = 1;
          break;
      }
      return true;
    }
    return false;
  }

  private boolean isPossibleMove(int y, int x, int dir) {
    if (this.field[y][x] != 1) {
      return false;
    }
    if (dir == 0) { //top
      if (y < 2 || (x < 2 || x > 4) && y != 4) {
        return false;
      }
      if (this.field[y - 1][x] == 1 && this.field[y - 2][x] == 0) {
        return true;
      }
      return false;
    }
    if (dir == 1) { //left
      if (x < 2 || (y < 2 || y > 4) && x != 4) {
        return false;
      }
      if (this.field[y][x - 1] == 1 && this.field[y][x - 2] == 0) {
        return true;
      }
      return false;
    }
    if (dir == 2) { //down
      if (y > 4 || (x < 2 || x > 4) && y != 2) {
        return false;
      }
      if (this.field[y + 1][x] == 1 && this.field[y + 2][x] == 0) {
        return true;
      }
      return false;
    }
    if (dir == 3) { //right
      if (x > 4 || (y < 2 || y > 4) && x != 2) {
        return false;
      }
      if (this.field[y][x + 1] == 1 && this.field[y][x + 2] == 0) {
        return true;
      }
      return false;
    }
    return false;
  }

  public boolean isFinished() {
    for (int i = 0; i < 7; ++i) {
      for (int j = 0; j < 7; ++j) {
        if (i >= 2 && i <= 4 || j >= 2 && j <= 4) {
          if (i != 3 || j != 3) {
            if (this.field[i][j] != 0) {
              return false;
            }
          } else {
            if (this.field[i][j] != 1) {
              return false;
            }
          }
        } else {
          if (this.field[i][j] != -1) {
            return false;
          }
        }
      }
    }

    return true;
  }

  private void initField() {
    for (int i = 0; i < 7; ++i) {
      for (int j = 0; j < 7; ++j) {
        if (i >= 2 && i <= 4 || j >= 2 && j <= 4) {
          if (i != 3 || j != 3) {
            this.field[i][j] = 1;
          } else {
            this.field[i][j] = 0;
          }
        } else {
          this.field[i][j] = -1;
        }
      }
    }
  }

  public void printField() {
    for (int i = 0; i < 7; ++i) {
      for (int j = 0; j < 7; ++j) {
        if (this.field[i][j] == 1) {
          System.out.print("X ");
        } else if (this.field[i][j] == 0) {
          System.out.print("O ");
        } else {
          System.out.print("  ");
        }
      }
      System.out.println();
    }
    System.out.println("\n");
  }

  private boolean isPossibleRecoverMove(int y, int x, int dir) {
    if (this.field[y][x] != 0) {
      return false;
    }
    if (dir == 0) { //top
      if (y < 2 || (x < 2 || x > 4) && y != 4) {
        return false;
      }
      if (this.field[y - 1][x] == 0 && this.field[y - 2][x] == 1) {
        return true;
      }
      return false;
    }
    if (dir == 1) { //left
      if (x < 2 || (y < 2 || y > 4) && x != 4) {
        return false;
      }
      if (this.field[y][x - 1] == 0 && this.field[y][x - 2] == 1) {
        return true;
      }
      return false;
    }
    if (dir == 2) { //down
      if (y > 4 || (x < 2 || x > 4) && y != 2) {
        return false;
      }
      if (this.field[y + 1][x] == 0 && this.field[y + 2][x] == 1) {
        return true;
      }
      return false;
    }
    if (dir == 3) { //right
      if (x > 4 || (y < 2 || y > 4) && x != 2) {
        return false;
      }
      if (this.field[y][x + 1] == 0 && this.field[y][x + 2] == 1) {
        return true;
      }
      return false;
    }
    return false;
  }

  public boolean recoverMove(int y, int x, int dir) {
    boolean possible = isPossibleRecoverMove(y, x, dir);
    if (possible) {
      this.field[y][x] = 1;
      switch (dir) {
        case 0:
          this.field[y - 1][x] = 1;
          this.field[y - 2][x] = 0;
          break;
        case 1:
          this.field[y][x - 1] = 1;
          this.field[y][x - 2] = 0;
          break;
        case 2:
          this.field[y + 1][x] = 1;
          this.field[y + 2][x] = 0;
          break;
        case 3:
          this.field[y][x + 1] = 1;
          this.field[y][x + 2] = 0;
          break;
      }
      return true;
    }
    return false;
  }

}
