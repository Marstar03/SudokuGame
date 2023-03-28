package Java_prosjekt_Markus;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
import javafx.scene.control.Button;
//Fjern import nedenfor senere
import java.util.*;

public class SudokuController {
    
    @FXML private TextField 
    R0C0, R0C1, R0C2, R0C3, R0C4, R0C5, R0C6, R0C7, R0C8, 
    R1C0, R1C1, R1C2, R1C3, R1C4, R1C5, R1C6, R1C7, R1C8, 
    R2C0, R2C1, R2C2, R2C3, R2C4, R2C5, R2C6, R2C7, R2C8, 
    R3C0, R3C1, R3C2, R3C3, R3C4, R3C5, R3C6, R3C7, R3C8, 
    R4C0, R4C1, R4C2, R4C3, R4C4, R4C5, R4C6, R4C7, R4C8, 
    R5C0, R5C1, R5C2, R5C3, R5C4, R5C5, R5C6, R5C7, R5C8, 
    R6C0, R6C1, R6C2, R6C3, R6C4, R6C5, R6C6, R6C7, R6C8, 
    R7C0, R7C1, R7C2, R7C3, R7C4, R7C5, R7C6, R7C7, R7C8, 
    R8C0, R8C1, R8C2, R8C3, R8C4, R8C5, R8C6, R8C7, R8C8, username;
    @FXML private ToggleButton easyButton, mediumButton, hardButton;
    @FXML private Button setButton, generateGridButton;
    @FXML private GridPane cellGrid;


    //private TextField[] TextFieldList = {R0C0, this.R0C1, this.R0C2, this.R0C3, this.R0C4, this.R0C5, this.R0C6, this.R0C7, this.R0C8};


    //Test-metode:
    @FXML
    public void changeValue() {
        this.R0C0.setText("Hei");
        this.R0C0.setEditable(false);
    }
    @FXML
    public void generateGrid() {
        Node[][] gridPaneNodes = new Node[3][2];
        int row1 = 0;
        int column1 = 0;
        for (Node child : cellGrid.getChildren()) {
            gridPaneNodes[row1][column1] = child;
            if (column1 == 1) {
                column1 = 0;
                row1++;
            }
            else {
                column1++;
            }
        }
        for (int row2 = 0; row2 < 3; row2++) {
            for (int column2 = 0; column2 < 2; column2++) {
                //Husk validering. Altså sjekk om noden er TextField før casting
                TextField field = (TextField) gridPaneNodes[row2][column2];
                field.setText("Halla");
            }
        }
    }
    public static void main(String[] args) {
        SudokuController controller = new SudokuController();
        controller.generateGrid();
    }
    
}
