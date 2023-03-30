package Java_prosjekt_Markus;

import java.util.Random;

public class SudokuGame implements SudokuGameInterface {
    public final static int ROW_SIZE = 9;
    public final static int COLUMN_SIZE = 9;
    //Her putter du grid-en som er generert og oppdaterer den når brukeren skriver inn et tall.
    //Her fjerner du også x antall tall fra grid-en før spillet begynner (basert på vanskelighetsgrad hvis du får tid)
    //Når brukeren trykker submit, itererer klassen gjennom grid-en og validerer hver enkelt verdi opp mot resten av verdiene.
    //Hvis alle verdiene får true, er spillet ferdig. Ellers mister brukeren et liv, og må prøve på nytt.

    public SudokuGenerator generator = new SudokuGenerator();
    int[][] solutionGrid;
    int[][] gameGrid;

    public SudokuGame() {
        this.solutionGrid = this.generator.getGrid();
        this.gameGrid = cellRemover(this.solutionGrid);
    }

    public int[][] cellRemover(int[][] fullGrid) {
        Random rand = new Random();
        for (int i = 0; i < 40; i++) {
            int randomRow = rand.nextInt(9);
            int randomColumn = rand.nextInt(9);
            fullGrid[randomRow][randomColumn] = 0;
        }
        return fullGrid;
    }
//Returner kopi istedenfor det faktiske objektet
    public int[][] getGameGrid() {
        return this.gameGrid;
    }

    public static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        }
        catch(NumberFormatException e){  
          return false;  
        }  
      }

    public boolean gridValidator(int[][] grid) {
        for (int row = 0; row < ROW_SIZE; row++) {
            for (int column = 0; column < COLUMN_SIZE; column++) {
                int value = grid[row][column];
                if (value == 0) {
                    return false;
                }
                if (! CellValidator.isValidCell(grid, value, row, column)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        
    }
}
