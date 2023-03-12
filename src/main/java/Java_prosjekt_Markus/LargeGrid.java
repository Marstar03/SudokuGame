package Java_prosjekt_Markus;

import java.util.ArrayList;
import java.util.List;

public class LargeGrid implements Grid {
    private List<SmallGrid> smallGrids = new ArrayList<>();

    public void updateCell(int index1, int index2, int newValue) {
        this.smallGrids.get(index1).updateCell(index2, newValue);
    }
}
