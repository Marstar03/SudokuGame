package Java_prosjekt_Markus;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class SudokuController {
    private SudokuGame game = new SudokuGame();
    private int[][] gameGrid = game.getGameGrid();
    private TextField[][] allTextFields = new TextField[9][9];
    
    @FXML private TextField usernameField;
    @FXML private Button setButton, generateGridButton, submitButton;
    @FXML private GridPane largeGrid;

    @FXML
    public void activateGenerateButton() {
        this.generateGridButton.setDisable(false);
    }

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
        this.generateGridButton.setDisable(true);
    }

    public void retrieveGrid() {
        //Må iterere gjennom allTextFields, hente verdien til tekstfeltet, og sette denne verdien inn i gameGrid.
        for (int row = 0; row < SudokuGame.ROW_SIZE; row++) {
            for (int column = 0; column < SudokuGame.COLUMN_SIZE; column++) {
                String UIGridValue = this.allTextFields[row][column].getText();
                if (SudokuGame.isNumeric(UIGridValue)) {
                    this.gameGrid[row][column] = Integer.parseInt(UIGridValue);
                }
            }
        }
    }
}
