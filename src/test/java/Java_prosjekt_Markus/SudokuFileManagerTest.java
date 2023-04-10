package Java_prosjekt_Markus;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SudokuFileManagerTest {
    private SudokuGame game;
    
    @BeforeEach
    public void setUp() {
        this.game = new SudokuGame();
    }
    
    @Test
    public void fileManagerTest() {
        int[][] grid = game.getGameGrid();
        try {
            SudokuFileManager.writeGridToFile(grid);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[][] fileGrid = new int[9][9];
        try {
            fileGrid = SudokuFileManager.readGridFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertArrayEquals(grid, fileGrid);
    }

    @AfterEach
    public void cleanUp() {
        //Slett filen gridFile.txt
    }
}
