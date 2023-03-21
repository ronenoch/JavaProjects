package com.temperature.maman11_b;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GraphApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GraphApplication.class.getResource("graph-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("MAMAN 11 Q 2 - Graph");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}