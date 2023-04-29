import java.util.ArrayList;
import java.util.Random;

public class Matrix {
    private int size;
    private ArrayList<ArrayList<Integer>> data;

    public Matrix(int size) {
        this.size = size;
        this.data = new ArrayList<>(size);
        this.generateRandomMatrix();
    }

    private void generateRandomMatrix() {
        for (int i = 0; i < this.size; i++) {
            ArrayList<Integer> row = new ArrayList<Integer>(this.size);
            for (int j = 0; j < this.size; j++) {
                row.add(new Random().nextInt(10));
            }
            this.data.add(row);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size; i++) {
            ArrayList<Integer> row = this.data.get(i);
            for (int j = 0; j < this.size; j++) {
                sb.append(row.get(j)).append(",");
            }
            sb.append('\n');
//            this.data.add(row);
        }
        return sb.toString();
    }
}
