import java.util.ArrayList;
import java.util.Random;

public class Matrix {
    private int rowSize;
    private int columnSize;
    private ArrayList<ArrayList<Integer>> data;

    public Matrix(int rowSize, int columnSize) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        this.data = new ArrayList<>(rowSize);
        this.generateRandomMatrix();
    }

    private void generateRandomMatrix() {
        for (int i = 0; i < this.rowSize; i++) {
            ArrayList<Integer> row = new ArrayList<Integer>(this.columnSize);
            for (int j = 0; j < this.columnSize; j++) {
                row.add(new Random().nextInt(10));
            }
            this.data.add(row);
        }
    }

    public int getRowSize() {
        return this.rowSize;
    }

    public int getColumnSize() {
        return this.columnSize;
    }

    public ArrayList<ArrayList<Integer>> getData() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.rowSize; i++) {
            ArrayList<Integer> row = this.data.get(i);
            for (int j = 0; j < this.columnSize; j++) {
                sb.append(row.get(j)).append(",");
            }
            sb.append('\n');
//            this.data.add(row);
        }
        return sb.toString();
    }
}
