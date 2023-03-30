package Java_prosjekt_Markus;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class SudokuFileManager {
    //Her skriver du metoder for å skrive og lese til fil (lagring og henting av progress)

    public static void writeGridToFile(int[][] grid) {
        try {
            PrintWriter writer = new PrintWriter(new File());
        } catch (IOException e) {
            System.out.println("Couldn't save to file");
        }
    }

/*     public static int[][] readGridFromFile() {

    } */

}
