package Java_prosjekt_Markus;
import java.util.*;

public class CellValidator {
    //private List<List<Integer>> gridList = new ArrayList<List<Integer>>(); 

    public CellValidator() {
/*         List<Integer> list = Arrays.asList(0,0,0,0,0,0,0,0,0);
        for (int i = 0; i < 9; i++) {
            this.gridList.add(list);
        } */
    }
    
    public Boolean isValidCell(List<List<Integer>> gridList, int value, int rowIndex, int columnIndex) {
        return isValidRow(gridList, value, rowIndex) && isValidColumn(gridList, value, columnIndex) && isValidSmallGrid(gridList, value, rowIndex, columnIndex);
    }

    public Boolean isValidRow(List<List<Integer>> gridList, int value, int rowIndex) {
        for (int j = 0; j < 9; j++) {
            if (gridList.get(rowIndex).get(j) == value) {
                return false;
            }
        }
        return true;
    }

    public Boolean isValidColumn(List<List<Integer>> gridList, int value, int columnIndex) {
        for (int k = 0; k < 9; k++) {
            if (gridList.get(k).get(columnIndex) == value) {
                return false;
            }
        }
        return true;
    }

    public Boolean isValidSmallGrid(List<List<Integer>> gridList, int value, int rowIndex, int columnIndex) {
        List<Integer> smallGrid = new ArrayList<>();
        if (rowIndex < 3) {
            if (columnIndex < 3) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        smallGrid.add(gridList.get(i).get(j));
                    }
                }
            }
            else if (columnIndex < 6) {
                for (int i = 3; i < 6; i++) {
                    for (int j = 0; j < 3; j++) {
                        smallGrid.add(gridList.get(i).get(j));
                    }
                }
            }
            else {
                for (int i = 6; i < 9; i++) {
                    for (int j = 0; j < 3; j++) {
                        smallGrid.add(gridList.get(i).get(j));
                    }
                }
            }
        }
        else if (rowIndex < 6) {
            if (columnIndex < 3) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 3; j < 6; j++) {
                        smallGrid.add(gridList.get(i).get(j));
                    }
                }
            }
            else if (columnIndex < 6) {
                for (int i = 3; i < 6; i++) {
                    for (int j = 3; j < 6; j++) {
                        smallGrid.add(gridList.get(i).get(j));
                    }
                }
            }
            else {
                for (int i = 6; i < 9; i++) {
                    for (int j = 3; j < 6; j++) {
                        smallGrid.add(gridList.get(i).get(j));
                    }
                }
            }
        }
        else {
            if (columnIndex < 3) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 6; j < 9; j++) {
                        smallGrid.add(gridList.get(i).get(j));
                    }
                }
            }
            else if (columnIndex < 6) {
                for (int i = 3; i < 6; i++) {
                    for (int j = 6; j < 9; j++) {
                        smallGrid.add(gridList.get(i).get(j));
                    }
                }
            }
            else {
                for (int i = 6; i < 9; i++) {
                    for (int j = 6; j < 9; j++) {
                        smallGrid.add(gridList.get(i).get(j));
                    }
                }
            }
        }
        if (smallGrid.contains(value)) {
            return false;
        }
        return true;
    }
}
