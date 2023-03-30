package Java_prosjekt_Markus;
import java.util.*;

public class SudokuGenerator {
    private int[][] grid = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0} 
      };

    //fjern denne metoden etter du er ferdig. Er kopiert fra John
    public static void printBoard(int[][] board) {
        for (int row = 0; row < SudokuGame.ROW_SIZE; row++) {
          if (row % 3 == 0 && row != 0) {
            System.out.println("-----------");
          }
          for (int column = 0; column < SudokuGame.COLUMN_SIZE; column++) {
            if (column % 3 == 0 && column != 0) {
              System.out.print("|");
            }
            System.out.print(board[row][column]);
          }
          System.out.println();
        }
      }

    public boolean generate() {
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Collections.shuffle(numberList);
        for (int row = 0; row < SudokuGame.ROW_SIZE; row++) {
            for (int column = 0; column < SudokuGame.COLUMN_SIZE; column++) {
                if (grid[row][column] == 0) {
                    for (int value: numberList) {
                        if (CellValidator.isValidCell(grid, value, row, column)) {
                            grid[row][column] = value;
                            if (generate()) {
                                return true;
                              }
                              else {
                                grid[row][column] = 0;
                              }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    //Husk å endre slik at getGrid kun returnerer en kopi av grid (innkapsling)
    public int[][] getGrid() {
        this.generate();
        return this.grid;
    }


    public static void main(String[] args) {
        SudokuGenerator generator = new SudokuGenerator();
        generator.printBoard(generator.grid);
        generator.generate();
        System.out.println("Ny");
        generator.printBoard(generator.grid);
    }
}
 