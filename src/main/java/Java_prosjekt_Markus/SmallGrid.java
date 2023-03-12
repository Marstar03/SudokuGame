package Java_prosjekt_Markus;

import java.util.ArrayList;
import java.util.List;

public class SmallGrid implements Grid {
    private List<Cell> cells = new ArrayList<>();

    public void updateCell(int index, int newValue) {
        this.cells.get(index).setValue(newValue);
    }
}
