import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.Objects;


public class TriviaController extends Thread {
    @FXML
    private TextField textF;

    @FXML
    private VBox v_box;

    private Question question;
    private int counterQuestions;
    private final int questionsNumber = 20;
    private Timer timer;
    private boolean isQuestionTimeout;
    private int score;
    private String serverAddress;
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
        this.serverAddress = args[0];
        this.startNewGame();
    }

    @Override
    public void run() {
        super.run();
        startNewGame();
    }

    protected void startNewGame() {
        this.counterQuestions = 0;
        this.score = 0;
        this.timer = new Timer(this.questionTimeout, new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent actionEvent) {
                System.out.println("timeout question");
                isQuestionTimeout = true;
            }

        });
        /* restarting the client (needed when starting a new game) */
        this.client = new Client(this.serverAddress, this, true);
        this.client.getNextQuestion();
    }

//    protected void getAndShowMessage() {
    protected void getAndShowMessage(Question question) {
        this.scoreLabel.setText(Integer.toString(this.score));

        this.question = question;

        this.answerBox.getSelectionModel().clearSelection();
        this.answerBox.getItems().clear();
        this.answerBox.valueProperty().set(null);
        for (String answer: this.question.getAnswers()) {

            this.answerBox.getItems().add(answer);
        }
        this.textF.setText(this.question.getContent());
        this.isQuestionTimeout = false;
        this.timer.start();
    }

    @FXML
    protected void confirmAnswer() {
        if (null == question) {
            return;
        }
        try {
            String answer = this.answerBox.getSelectionModel().getSelectedItem();
            timer.stop();
            if (Objects.equals(answer, this.question.getAnswers()[this.question.getCorrectAnswer()])
                    && !this.isQuestionTimeout) {
                score += 10;
            } else {
                score -= 5;
            }
        } catch (NullPointerException exception) {
            return;
        }
        this.counterQuestions++;
        this.question = null;

//        getAndShowMessage();
        this.client = new Client(this.serverAddress, this, false);
        this.client.getNextQuestion();

    }

}
