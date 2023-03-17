package Java_prosjekt_Markus;
import java.util.*;

public class SudokuGenerator {
    private List<List<Integer>> gridList = new ArrayList<List<Integer>>();
    private CellValidator validator = new CellValidator();

    public SudokuGenerator() {
        List<Integer> list = Arrays.asList(0,0,0,0,0,0,0,0,0);
        for (int i = 0; i < 9; i++) {
            this.gridList.add(list);
        }
    }

    public List<List<Integer>> getGridList() {
        return new ArrayList<>(this.gridList);
    }

    private Integer makeLegalInteger(int r, int k) {
        List<Integer> illegalValues = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            if (! this.validator.isValidCell(this.gridList, i, r, k)) {
                illegalValues.add(i);
            }
        }
        if (illegalValues.size() == 9) {
            return 0;
        }
        Random rand = new Random();
        int randomInt = rand.nextInt(9) + 1;
        while (illegalValues.contains(randomInt)) {
            randomInt = rand.nextInt(9) + 1;
        }
        return randomInt;
    }

    public boolean solve(int r, int k) {
        if (r == 9) {
            return true;
        }
        else if (k == 9) {
            return solve(r+1, 0);
        }
        else if (k == -1) {
            if (r <= 0) {
                return false;
            }
            return solve(r-1, 8);
        }
/*         else if (this.gridList.get(k).get(r) != 0) {
            return solve(r, k+1);
        } */
        else {
            int value = this.makeLegalInteger(r, k);
            if (value != 0) {
                this.gridList.get(k).set(r, value);
                System.out.println(this.gridList);
                return solve(r, k + 1);
            }
            else {
                return solve(r, k - 1);
            }
        }
    }

/*     public void generator() {
        //  Bruker backtracking
        for (int k = 0; k < 9; k++) {
            for (int r = 0; r < 9; r++) {
                this.solve(r, k)
            }
        }
    } */

    public static void main(String[] args) {
        SudokuGenerator generator = new SudokuGenerator();
        //CellValidator validator = new CellValidator();
        System.out.println(generator.gridList);
        generator.solve(0, 0);
        System.out.println(generator.gridList);


        //System.out.println(validator.isValidCell(generator.getGridList(), 5, 6, 4));
    }
}
 