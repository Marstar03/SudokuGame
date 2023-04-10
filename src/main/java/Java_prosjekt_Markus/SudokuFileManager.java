package Java_prosjekt_Markus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Denne klassen består av metoder for å skrive til og lese fra fil (lagring og henting av progress).
 */
public class SudokuFileManager {

    /**
     * Denne metoden tar inn en grid som den skal lagre til fil. Dette valgte jeg å gjøre ved å opprette en PrintWriter,
    og dermed også opprette tekstfilen "gridfile.txt", med mindre den allerede eksisterer.
    Deretter itererer metoden gjennom grid-en rad for rad. Den konkatenerer så alle verdiene i hver rad sammen i én
    streng med komma mellom hver verdi. Deretter skrives denne strengen til filen, og blir en egen linje i filen.
     * @param grid
     * @throws IOException
     */
    public static void writeGridToFile(int[][] grid) throws IOException {
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
    }

    /**
     * Denne metoden har som funksjon å lese en grid fra en fil, og returnere denne grid-en i form av en 2D int-array.
    Aller først opprettes det en scanner som blir brukt for å lese fra filen "gridFile.txt".
    Deretter itererer metoden gjennom filen linje for linje, henter ut linjen og lagrer verdiene i en array ved å splitte linjen på komma.
    Metoden itererer så gjennom alle verdiene i streng-array-et, endrer hver streng til en int-verdi og legger verdiene i en int-array.
    Her kalles også metoden isNumeric for å forsikre at hver streng faktisk kun er tall.
    Til slutt legges int-arrayet til i en 2D int-array, og blir dermed lik raden i en grid.
     * @return
     * @throws FileNotFoundException
     */
    public static int[][] readGridFromFile() throws FileNotFoundException {
        int[][] savedGrid = new int[9][9];
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
        scanner.close();
        return savedGrid;
    }
}