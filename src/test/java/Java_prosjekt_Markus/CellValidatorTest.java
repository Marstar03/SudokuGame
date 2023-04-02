package Java_prosjekt_Markus;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class CellValidatorTest {

    @Test
    public void validateLegalGrid() {
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
        assertTrue(CellValidator.isValidCell(grid, 6, 4, 3));
        assertTrue(CellValidator.isValidCell(grid, 5, 8, 8));
        assertTrue(CellValidator.isValidCell(grid, 9, 5, 0));
        assertTrue(CellValidator.isValidCell(grid, 7, 0, 0));
    }

    @Test
    public void validateIllegalGrid() {
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
        assertFalse(CellValidator.isValidCell(grid, 5, 4, 3));
        assertFalse(CellValidator.isValidCell(grid, 4, 8, 8));
        assertFalse(CellValidator.isValidCell(grid, 8, 5, 0));
        assertFalse(CellValidator.isValidCell(grid, 6, 0, 0));

    }
}
