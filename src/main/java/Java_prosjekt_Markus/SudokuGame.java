package Java_prosjekt_Markus;

import java.util.Random;

public class SudokuGame implements SudokuGameInterface {
    //Deklarerer 2 konstante variabler for dimensjonene til en sudoku grid for å gjøre operasjoner mer oversiktlige.
    final static int ROW_SIZE = 9;
    final static int COLUMN_SIZE = 9;
    private SudokuGenerator generator = new SudokuGenerator();
    private int[][] gameGrid;

    /**
     * Hver gang et SudokuGame-objekt blir laget ved konstruktøren, henter programmet en ny grid fra generatoren,
    og kaller cellRemover, slik at gameGrid blir en gyldig grid, der et visst antall felt har blitt fjernet.
     */
    public SudokuGame() {
        this.gameGrid = cellRemover(this.generator.getGrid());
    }

    /**
     * Denne metoden blir brukt i konstruktøren. Det den gjør er å trekke 2 tilfeldige indekser, 1 for rad og 1 for kolonne.
    Dersom verdien på denne posisjonen ikke allerede har blitt brukt, settes verdien til 0. Ellers gjentar den prosessen.
    Dette repeteres 40 ganger, slik at grid-en som returneres vil tilsvare en grid med 40 ukjente verdier som må fylles ut av brukeren.
     * @param grid
     * @return
     */
    private int[][] cellRemover(int[][] grid) {
        Random rand = new Random();
        for (int i = 0; i < 40; i++) {
            int randomRow = rand.nextInt(9);
            int randomColumn = rand.nextInt(9);
            while (grid[randomRow][randomColumn] == 0) {
                randomRow = rand.nextInt(9);
                randomColumn = rand.nextInt(9);
            }
            grid[randomRow][randomColumn] = 0;
        }
        return grid;
    }

    /**
     * Denne metoden returnerer en kopi av gameGrid for å opprettholde riktig innkapsling. Dette gjøres ved å iterere gjennom alle verdier,
    og sette riktig verdi i riktig posisjon i kopien.
     */
    public int[][] getGameGrid() {
        int[][] gameGridCopy = new int[9][9];
        for (int row = 0; row < ROW_SIZE; row++) {
            for (int column = 0; column < COLUMN_SIZE; column++) {
                gameGridCopy[row][column] = this.gameGrid[row][column];
            }
        }
        return gameGridCopy;
    }

    /**
     * Denne metoden er en hjelpemetode som brukes av metoden retrieveGrid i kontroller-klassen og readGridFromFile i SudokuFileManager-klassen. 
    Metoden tar inn en streng, og returnerer
    true dersom strengen kun består av tallverdier, og false ellers. Denne er nyttig under valideringen av bruker-input.
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        }
        catch(NumberFormatException e){  
          return false;  
        }  
      }

      /**
       * Denne metoden brukes for å validere en grid, altså sjekke om den er gyldig mht. sudoku-reglene.
    Aller først validerer den input-grid-en ved å sjekke at den har riktige dimensjoner.
    Deretter itererer den gjennom hver verdi i grid-en. Siden den skal sjekke om grid-en er fullstendig eller ikke,
    Sjekker den først om en verdi er 0. Dersom den er det, betyr det at ikke alle feltene har blitt fylt inn.
    Deretter sjekker den om verdien er gyldig i henhold til reglene ved å kalle isValidCell-metoden.
    Dersom alle cellene i grid-en overholder testene, returnerer metoden true, som betyr at grid-en er gyldig og komplett.
       * @param grid
       * @return
       */
    public static boolean gridValidator(int[][] grid) {
        if (grid.length != 9 || grid[0].length != 9) {
            throw new IllegalArgumentException("The input must be a 9x9-grid");
        }
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