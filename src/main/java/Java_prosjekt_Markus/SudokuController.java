package Java_prosjekt_Markus;

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
    private TextField[][] allTextFields = new TextField[9][9];
    
    @FXML private TextField usernameField, livesField, statusField;
    @FXML private Button setButton, startNewGameButton, submitButton, saveGameButton, restoreGameButton;
    @FXML private GridPane largeGrid;

    //Putt så mye av logikken til metodene inn i en annen klasse.
    //Husk på innkapsling og validering!!!

    @FXML
    public void makeTextFieldArray() {
        int row1 = 0;
        int column1 = 0;
        for (Node childNode1 : largeGrid.getChildren()) {
            if (childNode1 instanceof TextField) {
                TextField textField1 = (TextField) childNode1;
                this.allTextFields[row1][column1] = textField1;
                column1 = (column1 + 1) % 9;
                if (column1 == 0) {
                    row1++;
                }
            }
        }
    }

    @FXML
    public void generateGrid() {
        this.makeTextFieldArray();
        this.game = new SudokuGame();
        this.gameGrid = game.getGameGrid();
        this.submitButton.setDisable(false);
        this.saveGameButton.setDisable(false);
        this.livesField.setText("3");
        this.statusField.setText("");
        for (int row2 = 0; row2 < SudokuGame.ROW_SIZE; row2++) {
            for (int column2 = 0; column2 < SudokuGame.COLUMN_SIZE; column2++) {
                TextField field = allTextFields[row2][column2];
                String gridNumber = "";
                if (gameGrid[row2][column2] != 0) {
                    gridNumber = String.valueOf(gameGrid[row2][column2]);
                    field.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
                }
                else {
                    field.setEditable(true);
                }
                field.setText(gridNumber);
            }
        }
        game.generator.printBoard(game.solutionGrid);
    }

    private void retrieveGrid() {
        //Må iterere gjennom allTextFields, hente verdien til tekstfeltet, og sette denne verdien inn i gameGrid.
        for (int row = 0; row < SudokuGame.ROW_SIZE; row++) {
            for (int column = 0; column < SudokuGame.COLUMN_SIZE; column++) {
                String UIGridValue = this.allTextFields[row][column].getText();
                //Dersom brukeren ikke skriver inn noe tall, vil isNumeric være false, og gameGrid vil beholde 0 som verdi.
                if (SudokuGame.isNumeric(UIGridValue)) {
                    this.gameGrid[row][column] = Integer.parseInt(UIGridValue);
                }
            }
        }
        this.game.gridValidator(gameGrid);
    }

    @FXML
    public void submitGrid() {
        int livesLeft = Integer.parseInt(this.livesField.getText());
        this.retrieveGrid();
        boolean validGrid = this.game.gridValidator(gameGrid);
        if (! validGrid) {
            if (livesLeft > 1) {
                livesLeft--;
                this.livesField.setText(String.valueOf(livesLeft));
            }
            else {
                this.statusField.setText("GAME OVER");
                this.livesField.setText("0");
                this.submitButton.setDisable(true);
                this.saveGameButton.setDisable(true);
            }
        }
        else {
            this.statusField.setText("SUCCESS!");
        }
    }

    @FXML
    public void saveGame() {
        this.retrieveGrid();
        SudokuFileManager.writeGridToFile(gameGrid);
    }

    @FXML 
    public void restoreGame() {

    }

    public static void main(String[] args) {
        System.out.println(SudokuGame.isNumeric("0"));
    }
}
