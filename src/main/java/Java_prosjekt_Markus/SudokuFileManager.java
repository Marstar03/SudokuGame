package Java_prosjekt_Markus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.scene.transform.Scale;

public class SudokuFileManager {
    //Her skriver du metoder for å skrive og lese til fil (lagring og henting av progress)

    public static void writeGridToFile(int[][] grid) {
        try {
            PrintWriter writer = new PrintWriter("gridFile.txt");
            for (int[] row: grid) {
                String rowString = "";
                for (int value: row) {
                    rowString += ("," + value);
                }
                rowString = rowString.substring(1);
                writer.println(rowString);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public static int[][] readGridFromFile() {
        int[][] savedGrid = new int[9][9];
        try {
            Scanner scanner = new Scanner(new File("gridFile.txt"));
            int rowCount = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] stringRow = line.split(",");
                int[] intRow = new int[9];
                for (int i = 0; i < 9; i++) {
                    if (SudokuGame.isNumeric(stringRow[i])) {
                        intRow[i] = Integer.parseInt(stringRow[i]);
                    }
                }
                savedGrid[rowCount] = intRow;
                rowCount++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return savedGrid;
    }

    public static void main(String[] args) {
        SudokuGame game = new SudokuGame();
        SudokuFileManager.writeGridToFile(game.getGameGrid());
        SudokuGenerator.printBoard(SudokuFileManager.readGridFromFile());
    }
}
