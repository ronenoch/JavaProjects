module ronenoch.sudoku {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ronenoch.sudoku to javafx.fxml;
    exports ronenoch.sudoku;
}