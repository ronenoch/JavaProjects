import java.util.ArrayList;
import java.util.Random;

public class Matrix {
    private int rowCount;
    private int columnCount;
    private ArrayList<ArrayList<Integer>> data;

    public Matrix(int rowsCount, int columnCount) {
        this.rowCount = rowsCount;
        this.columnCount = columnCount;
        this.data = new ArrayList<>(rowsCount);
        this.generateRandomMatrix();
    }

    private void generateRandomMatrix() {
        for (int i = 0; i < this.rowCount; i++) {
            ArrayList<Integer> row = new ArrayList<Integer>(this.columnCount);
            for (int j = 0; j < this.columnCount; j++) {
                row.add(new Random().nextInt(10));
            }
            this.data.add(row);
        }
    }

    public int getRowCount() {
        return this.rowCount;
    }

    public int getColumnCount() {
        return this.columnCount;
    }

    public ArrayList<ArrayList<Integer>> getData() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.rowCount; i++) {
            ArrayList<Integer> row = this.data.get(i);
            for (int j = 0; j < this.columnCount; j++) {
                sb.append(row.get(j)).append(",");
            }
            sb.append('\n');
//            this.data.add(row);
        }
        return sb.toString();
    }
}
