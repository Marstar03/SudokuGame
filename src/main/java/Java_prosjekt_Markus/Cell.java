package Java_prosjekt_Markus;

public class Cell {
    private int value = 0;
    private SmallGrid smallGrid;

    public Cell(SmallGrid smallGrid) {
        this.smallGrid = smallGrid;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int newValue) {
        this.value = newValue;
    }
}
