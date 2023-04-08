package Java_prosjekt_Markus;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class SudokuController {
    private SudokuGame game;
    private int[][] gameGrid;
    private TextField[][] textFieldArray = new TextField[9][9];
    
    @FXML private TextField livesField, statusField;
    @FXML private Button setButton, startNewGameButton, submitButton, saveGameButton, restoreGameButton;
    @FXML private GridPane largeGrid;

    /**
     * Dette er en hjelpemetode som lager en ny 2D-array med tekstfeltene i FXML-filen, som vil senere bli brukt av generateUIGrid.
     */
    private void makeTextFieldArray() {
        int row = 0;
        int column = 0;
        for (Node childNode : largeGrid.getChildren()) {
            if (childNode instanceof TextField) {
                TextField textField = (TextField) childNode;
                this.textFieldArray[row][column] = textField;
                column = (column + 1) % 9;
                if (column == 0) {
                    row++;
                }
            }
        }
    }

    /**
    * Denne metoden oppretter en ny instans av et SudokuGame, og får klassen til å opprette en ny grid. 
    Til slutt kalles generateUIGrid, som "oversetter" tallene i 2D int-array-et til tekst i de riktige tekstfeltene i UI-grid-en.
     */
    @FXML
    public void generateNewGrid() {
        this.game = new SudokuGame();
        this.gameGrid = game.getGameGrid();
        this.generateUIGrid(gameGrid);
    }

    /**
     * Denne metoden gjør det samme som generateNewGrid, bortsett fra at, istedenfor å hente et nytt 2D-array fra SudokuGame,
    kaller den SudokuFileManager sin readGridFromFile, som leser inn en lagret grid fra fil, og putter disse verdiene i en 2D int-array.
     */
    @FXML
    public void generateSavedGrid() {
        try {
            this.gameGrid = SudokuFileManager.readGridFromFile();
            this.generateUIGrid(gameGrid);
        } catch (FileNotFoundException e) {
            this.statusField.setText("Unable to restore game");
        }
    }

    /**
     * Dette er en hjelpemetode som blir brukt av begge metodene over. Det denne metoden gjør, er å iterere gjennom 2D int-array-et gameGrid.
    For hver int-verdi i array-et, settes teksten til det tilsvarende tekstfeltet i UI-grid-en til en streng-representasjon av verdien.
    Dersom verdien er 0, er dette et felt der verdien har blitt fjernet og skal puttes inn av brukeren.
    Derfor settes teksten til dette feltet til å være en tom streng.
     * @param grid
     */
    private void generateUIGrid(int[][] grid) {
        this.makeTextFieldArray();
        this.submitButton.setDisable(false);
        this.saveGameButton.setDisable(false);
        this.livesField.setText("3");
        this.statusField.setText("");

        for (int row = 0; row < SudokuGame.ROW_SIZE; row++) {
            for (int column = 0; column < SudokuGame.COLUMN_SIZE; column++) {
                TextField field = textFieldArray[row][column];
                field.setEditable(false);
                field.setFont(Font.font("Verdana", FontWeight.NORMAL, 16));
                String gridNumber = "";
                if (grid[row][column] != 0) {
                    gridNumber = String.valueOf(grid[row][column]);
                    field.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
                }
                else {
                    field.setEditable(true);
                }
                field.setText(gridNumber);
            }
        }
    }

    /**
     * Dette er en hjelpemetode som brukes av både submitGrid og saveGrid. Det denne metoden gjør, er å iterere gjennom alle
    tekstfeltene i UI-grid-en, oversette verdien fra streng til int, for så å sette denne verdien på rett plass i gameGrid-array-et.
    Må opprette en ny 2D-array, og sette det lik gameGrid til slutt for å sikre at gameGrid enten får inn alle verdiene eller ingen.
     */
    private void retrieveGrid() {
        int[][] retrievedGrid = new int[9][9];
        //Må iterere gjennom allTextFields, hente verdien til tekstfeltet, og sette denne verdien inn i gameGrid.
        for (int row = 0; row < SudokuGame.ROW_SIZE; row++) {
            for (int column = 0; column < SudokuGame.COLUMN_SIZE; column++) {
                String UIGridValue = this.textFieldArray[row][column].getText();
                //Dersom brukeren ikke skriver inn noe tall, vil isNumeric være false, og gameGrid vil beholde 0 som verdi.
                if (! SudokuGame.isNumeric(UIGridValue) && ! UIGridValue.equals("")) {
                    //throw new IllegalArgumentException("All cells must have a numeric value");
                    this.statusField.setText("All cells must have a numeric value");
                    return;
                }
                if (UIGridValue.length() > 1) {
                    //throw new IllegalArgumentException("Only single digit values are accepted");
                    this.statusField.setText("Only single digit values are accepted");
                    return;
                }
                if (SudokuGame.isNumeric(UIGridValue)) {
                    retrievedGrid[row][column] = Integer.parseInt(UIGridValue);
                }
            }
        }
        this.gameGrid = retrievedGrid;
    }

    /**
     * Det denne metoden gjør, er å først kalle retrieveGrid, slik at den har den nyeste versjonen av UI-grid-en.
    Så bruker den en ekstern valideringsmetode, som returnerer en boolsk verdi, som sier om grid-en er gyldig.
    Hvis grid-en ikke er gyldig, mister brukeren et liv, og dersom alle 3 forsøk har blitt brukt opp, er spillet over.
     */
    @FXML
    public void submitGrid() {
        int livesLeft = Integer.parseInt(this.livesField.getText());
        this.retrieveGrid();
        boolean validGrid = SudokuGame.gridValidator(gameGrid);
        if (! validGrid) {
            if (livesLeft > 1) {
                livesLeft--;
                this.livesField.setText(String.valueOf(livesLeft));
            }
            else {
                this.statusField.setText("GAME OVER :(");
                this.livesField.setText("0");
                this.submitButton.setDisable(true);
                this.saveGameButton.setDisable(true);
            }
        }
        else {
            this.statusField.setText("SUCCESS :)");
        }
    }

    /**
     * Denne metoden kaller først retrieveGrid, for å få oppdatert versjon av grid-en.
    Deretter kaller den SudokuFileManager sin writeGridToFile-metode som tar seg av jobben om å lagre grid-en til fil.
     */
    @FXML
    public void saveGame() {
        this.retrieveGrid();
        try {
            SudokuFileManager.writeGridToFile(gameGrid);
        } catch (IOException e) {
            this.statusField.setText("Saving unsuccessful");
        }
    }
}