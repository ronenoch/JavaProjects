public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

//        Matrix a = new Matrix(10, 5);
//        Matrix b = new Matrix(5, 10);

        if (3 != args.length) {
            System.out.println("USAGE: <prog name> <A rows count> <A column count / B rows count> <B column count>");
            System.exit(-1);
        }

        Matrix a = new Matrix(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        Matrix b = new Matrix(Integer.parseInt(args[1]), Integer.parseInt(args[2]));

        System.out.println("A matrix:");
        System.out.println(a);
        System.out.println();
        System.out.println("B matrix:");
        System.out.println(b);

        System.out.println("A x B matrix:");
        for (int i = 0; i < a.getRowSize(); i++) {
            for (int j = 0; j < b.getColumnSize(); j++) {
                MultiplyMatrix m = new MultiplyMatrix(a, b, i, j);
                m.run();
            }
        }
    }
}