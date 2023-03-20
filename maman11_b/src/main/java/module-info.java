module com.example.maman11_b {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.maman11_b to javafx.fxml;
    exports com.example.maman11_b;
}