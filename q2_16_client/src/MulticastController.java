import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class MulticastController {
    @FXML
    private TextField textF;

    private Client client;

    @FXML
    private ChoiceBox<String> answerBox;

    @FXML
    private Label scoreLabel;

    private final int questionTimeout = 5000;

    public void initialize() {

//        this.getAndShowMessage();
        String[] args = System.getProperty("javafx.application.args").split(",");
        if (1 != args.length) {
            JOptionPane.showMessageDialog(null, "Usage: <program> <server ip / host name>");
        }
        this.client = new Client(args[0], 6666, this.textF);
//        this.startNewGame();
//        this.client.joinSession();
    }

    @FXML
    protected void clearMessage() {
        this.textF.clear();
    }

    @FXML
    protected void leavePressed() {
        this.client.leaveSession();
    }

    @FXML
    protected void connectPressed() {
        this.client.joinSession();
    }

}
