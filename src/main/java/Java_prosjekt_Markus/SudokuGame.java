package Java_prosjekt_Markus;

import java.util.Random;

public class SudokuGame implements SudokuGameInterface {
    //Deklarerer 2 konstante variabler for dimensjonene til en sudoku grid for å gjøre operasjoner mer oversiktlige.
    final static int ROW_SIZE = 9;
    final static int COLUMN_SIZE = 9;

    //Her putter du grid-en som er generert og oppdaterer den når brukeren skriver inn et tall.
    //Her fjerner du også x antall tall fra grid-en før spillet begynner (basert på vanskelighetsgrad hvis du får tid)
    //Når brukeren trykker submit, itererer klassen gjennom grid-en og validerer hver enkelt verdi opp mot resten av verdiene.
    //Hvis alle verdiene får true, er spillet ferdig. Ellers mister brukeren et liv, og må prøve på nytt.

    private SudokuGenerator generator = new SudokuGenerator();
    private int[][] gameGrid;

    public SudokuGame() {
        this.gameGrid = cellRemover(this.generator.getGrid());
    }

    private int[][] cellRemover(int[][] fullGrid) {
        Random rand = new Random();
        for (int i = 0; i < 40; i++) {
            int randomRow = rand.nextInt(9);
            int randomColumn = rand.nextInt(9);
            fullGrid[randomRow][randomColumn] = 0;
        }
        return fullGrid;
    }
//Denne metoden returnerer en kopi av gameGrid for å opprettholde riktig innkapsling.
    public int[][] getGameGrid() {
        int[][] gameGridCopy = new int[9][9];
        for (int row = 0; row < ROW_SIZE; row++) {
            for (int column = 0; column < COLUMN_SIZE; column++) {
                gameGridCopy[row][column] = this.gameGrid[row][column];
            }
        }
        return gameGridCopy;
    }

    //Denne metoden er en hjelpemetode som 
    public static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        }
        catch(NumberFormatException e){  
          return false;  
        }  
      }

    public static boolean gridValidator(int[][] grid) {
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
}