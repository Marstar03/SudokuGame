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

    /**
     * Denne metoden brukes for å generere nye lovlige sudoku-grids ved hjelp av en backtracking-algoritme.
    Den går gjennom en 2D int-grid som i utgangspunktet kun består av 0-ere.
    Hver gang den kommer over en 0-verdi, setter den verdien lik et tilfeldig tall mellom 1 og 9, og går så videre til neste 0-verdi.
    Dersom den kommer til en 0-verdi der ingen tall mellom 1 og 9 fører til en gyldig tilstand, går den et skritt tilbake
    og endrer verdien på forrige verdi til et annet tall mellom 1 og 9 osv. Til slutt, når hele grid-en er fylt ut,
    vil vi ende med en gyldig sudoku-grid.
     * @return
     */
    private boolean generate() {
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
    
    /**
     * Denne metoden kaller først på generate-metoden som genererer en ny gyldig grid. Deretter returnerer den en kopi av den genererte grid-en
    for å opprettholde riktig innkapsling.
     * @return
     */
    public int[][] getGrid() {
        this.generate();
        int[][] gridCopy = new int[9][9];
        for (int row = 0; row < SudokuGame.ROW_SIZE; row++) {
            for (int column = 0; column < SudokuGame.COLUMN_SIZE; column++) {
                gridCopy[row][column] = this.grid[row][column];
            }
        }
        return gridCopy;
    }
}