public class MultiplyMatrix extends Thread{
    private Matrix firstMatrix;
    private Matrix secondMatrix;
    private int row;
    private int column;

    static int lastFinishedThread = 0;

    public MultiplyMatrix(Matrix first, Matrix second, int row, int column) {
        this.firstMatrix = first;
        this.secondMatrix = second;
        this.row = row;
        this.column = column;
    }

    private void calculateValue() {
        int value = 0;
        for (int i = 0; i < this.firstMatrix.getColumnCount(); i++) {
            value += this.firstMatrix.getData().get(this.row).get(i) *
                    this.secondMatrix.getData().get(i).get(this.column);
        }
        waitForMyTurn(value);
    }

    public synchronized void waitForMyTurn(int numberToPrint) {
        while (lastFinishedThread + 1 <= this.row * this.secondMatrix.getColumnCount() + this.column) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }

        System.out.print(numberToPrint);
        if (this.column == this.secondMatrix.getColumnCount() - 1) {
            System.out.println(", ");

        } else {
            System.out.print(", ");
        }
        lastFinishedThread++;
        notifyAll();
    }

    @Override
    public void run() {
        super.run();
        calculateValue();
    }
}
