package Java_prosjekt_Markus;
import java.util.*;

public class CellValidator {



    // Possible-metoden er kopiert fra youtube video
/*     public boolean possible(int y, int x, int n, List<List<Integer>> grid) {
        for (int i = 0; i < 9; i++) {
            if (grid.get(y).get(i) == n) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (grid.get(i).get(x) == n) {
                return false;
            }
        }
        int x0 = (Math.floorDiv(x, 3))*3;
        int y0 = (Math.floorDiv(y, 3))*3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid.get(y0 + i).get(x0 + i) == n) {
                    return false;
                }
            }
        }
        return true;
    } */
    
    public static Boolean isValidCell(int[][] grid, int value, int row, int column) {
        return isValidRow(grid, value, row) && isValidColumn(grid, value, column) && isValidSmallGrid(grid, value, row, column);
    }

    public static Boolean isValidRow(int[][] grid, int value, int row) {
        for (int column = 0; column < 9; column++) {
            if (grid[row][column] == value) {
                return false;
            }
        }
        return true;
    }

    public static Boolean isValidColumn(int[][] grid, int value, int column) {
        for (int row = 0; row < 9; row++) {
            if (grid[row][column] == value) {
                return false;
            }
        }
        return true;
    }

    public static Boolean isValidSmallGrid(int[][] grid, int value, int row, int column) {
        List<Integer> smallGrid = new ArrayList<>();
        if (row < 3) {
            if (column < 3) {
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        smallGrid.add(grid[r][c]);
                    }
                }
            }
            else if (column < 6) {
                for (int r = 0; r < 3; r++) {
                    for (int c = 3; c < 6; c++) {
                        smallGrid.add(grid[r][c]);
                    }
                }
            }
            else {
                for (int r = 0; r < 3; r++) {
                    for (int c = 6; c < 9; c++) {
                        smallGrid.add(grid[r][c]);
                    }
                }
            }
        }
        else if (row < 6) {
            if (column < 3) {
                for (int r = 3; r < 6; r++) {
                    for (int c = 0; c < 3; c++) {
                        smallGrid.add(grid[r][c]);
                    }
                }
            }
            else if (column < 6) {
                for (int r = 3; r < 6; r++) {
                    for (int c = 3; c < 6; c++) {
                        smallGrid.add(grid[r][c]);
                    }
                }
            }
            else {
                for (int r = 3; r < 6; r++) {
                    for (int c = 6; c < 9; c++) {
                        smallGrid.add(grid[r][c]);
                    }
                }
            }
        }
        else {
            if (column < 3) {
                for (int r = 6; r < 9; r++) {
                    for (int c = 0; c < 3; c++) {
                        smallGrid.add(grid[r][c]);
                    }
                }
            }
            else if (column < 6) {
                for (int r = 6; r < 9; r++) {
                    for (int c = 3; c < 6; c++) {
                        smallGrid.add(grid[r][c]);
                    }
                }
            }
            else {
                for (int r = 6; r < 9; r++) {
                    for (int c = 6; c < 9; c++) {
                        smallGrid.add(grid[r][c]);
                    }
                }
            }
        }

        if (smallGrid.contains(value)) {
            return false;
        }
        return true;
    }
}
