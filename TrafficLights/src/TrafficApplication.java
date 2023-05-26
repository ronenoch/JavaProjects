import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class TrafficApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TrafficApplication.class.getResource("traffic-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 350, 450);
//        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Traffic");
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        if (2 != args.length) {
            System.out.println("USAGE: <program> <red traffic light timeout> <green traffic light timeout>");
            System.exit(-1);
        }
        System.setProperty("javafx.application.args", String.join(",", args));

        launch();
    }
}