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
}
