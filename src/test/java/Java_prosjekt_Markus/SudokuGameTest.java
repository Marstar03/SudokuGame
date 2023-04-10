package Java_prosjekt_Markus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SudokuGameTest {
    private SudokuGame game;
    
    @BeforeEach
    public void setUp() {
        this.game = new SudokuGame();
    }

    @Test
    public void cellRemoverTest() {
        int i = 0;
        for (int j = 0; j < SudokuGame.ROW_SIZE; j++) {
            for (int k = 0; k < SudokuGame.COLUMN_SIZE; k++) {
                if (game.getGameGrid()[j][k] == 0) {
                    i++;
                }
            }
        }
        assertEquals(40, i);
    }

    @Test
    public void isNumericTest() {
        assertTrue(SudokuGame.isNumeric("42"));
        assertTrue(SudokuGame.isNumeric("0"));
        assertFalse(SudokuGame.isNumeric(""));
        assertFalse(SudokuGame.isNumeric("grid"));
        assertFalse(SudokuGame.isNumeric("3H8"));
    }

    @Test
    public void validGridTest() {
        int[] randomRow1 = game.getGameGrid()[3];
        int[] randomRow2 = game.getGameGrid()[8];
        Arrays.sort(randomRow1);
        Arrays.sort(randomRow2);
        boolean valid = true;
        for (int i = 0; i < 8; i++) {
            if (randomRow1[i]!=0 && randomRow1[i] == randomRow1[i+1] || randomRow2[i] != 0 && randomRow2[i] == randomRow2[i+1]) {
                valid = false;
            }
        }
        assertTrue(valid);
    }

    @Test
    public void gridValidatorTest() {
        //Dette er en lovlig grid
        int[][] grid = {
            {7, 5, 3, 4, 8, 2, 9, 1, 6},
            {2, 9, 4, 3, 6, 1, 7, 5, 8},
            {1, 8, 6, 5, 9, 7, 4, 3, 2},
            {5, 6, 7, 9, 3, 8, 1, 2, 4},
            {4, 1, 2, 6, 7, 5, 8, 9, 3},
            {9, 3, 8, 1, 2, 4, 5, 6, 7},
            {6, 4, 1, 7, 5, 3, 2, 8, 9},
            {3, 2, 5, 8, 4, 9, 6, 7, 1},
            {8, 7, 9, 2, 1, 6, 3, 4, 5} 
        };

        assertTrue(SudokuGame.gridValidator(grid));
        //Endrer en verdi slik at grid-en blir ulovlig
        grid[0][0] = 8;
        assertFalse(SudokuGame.gridValidator(grid));
    }
}
