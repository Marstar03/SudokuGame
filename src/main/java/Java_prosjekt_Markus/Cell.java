package Java_prosjekt_Markus;

import java.util.Random;

public class Cell {
    private int value = 0;

    public Cell(SmallGrid smallGrid) {
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int newValue) {
        this.value = newValue;
    }
    public static void main(String[] args) {
        Random rand = new Random();
        int randomInt = rand.nextInt(9) + 1;
        System.out.println(randomInt);
    }
}
