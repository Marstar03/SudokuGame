package Java_prosjekt_Markus;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SudokuGeneratorTest {

    @Test
    public void generateTest() {
        SudokuGenerator generator = new SudokuGenerator();
        int[][] generatedGrid = generator.getGrid();

        assertTrue(SudokuGame.gridValidator(generatedGrid));
    }
}