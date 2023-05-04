public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

//        Matrix a = new Matrix(10, 5);
//        Matrix b = new Matrix(5, 10);

        Matrix a = new Matrix(2, 2);
        Matrix b = new Matrix(2, 2);

        System.out.println(a);
        System.out.println();
        System.out.println(b);

        for (int i = 0; i < a.getRowSize(); i++) {
            for (int j = 0; j < b.getColumnSize(); j++) {
                MultiplyMatrix m = new MultiplyMatrix(a, b, i, j);
                m.run();
            }
        }
    }
}