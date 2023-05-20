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

public class TriviaController {
    @FXML
    private TextField textF;

    @FXML
    private VBox v_box;

    private Socket s;
    private OutputStream outputStream;
    private PrintWriter printWriter;
    private ObjectInputStream objInputStream;
    private Question question;
    private int counterQuestions;
    private final int questionsNumber = 2;
    private Timer timer;
    private boolean isQuestionTimeout;
    private int score;


    @FXML
    private ChoiceBox<String> answerBox;

    @FXML
    private Label scoreLabel;

//    private HashMap<Date, String> reminders;

    private final int questionTimeout = 5000;
    public void initialize() {

//        this.getAndShowMessage();
        this.startNewGame();
    }

//    protected void writeDataToFile(String fileName){
//        try {
//            FileOutputStream myWriter = new FileOutputStream(fileName);
//            ObjectOutputStream out = new ObjectOutputStream(myWriter);
////            out.writeObject(this.reminders);
//            myWriter.close();
//            out.close();
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "File IO error", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    protected void loadDataFromFile(String fileName) {
//        try {
//            FileInputStream myReader = new FileInputStream(fileName);
//            ObjectInputStream input = new ObjectInputStream(myReader);
////            this.reminders = (HashMap<Date, String>) input.readObject();
//            myReader.close();
//            input.close();
//        } catch (IOException ignored) {
////        } catch (ClassNotFoundException e) {
////            throw new RuntimeException(e);
//        } catch (NullPointerException e) {
//            System.exit(-1);
//        }
//    }

    protected void startNewGame() {
        this.counterQuestions = 0;
        this.score = 0;
        try {
            if (null != s) {
                s.close();
            }
            s = new Socket("127.0.0.1", 3333);
            s.setSoTimeout(1000);
            this.objInputStream = new ObjectInputStream(s.getInputStream());
            this.printWriter = new PrintWriter(s.getOutputStream(), true);
            this.outputStream = s.getOutputStream();
            this.timer = new Timer(this.questionTimeout, new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent actionEvent) {
                    System.out.println("timeout question");
                    isQuestionTimeout = true;
                }

            });
        } catch (IOException e) {
            /* TODO add a msgbox */
            System.out.println("error in the socket init");
            throw new RuntimeException(e);
        }

        getAndShowMessage();
    }

    protected void getAndShowMessage() {
        this.scoreLabel.setText(Integer.toString(this.score));

        if (this.questionsNumber <= this.counterQuestions) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Platform.runLater(() -> {

                int input = JOptionPane.showConfirmDialog(null, "should start a new game?");
                if (0 == input) {
                    startNewGame();
                }
            });
            return;
        }

        try {
//            this.objOutputStream.write("ready\n".getBytes());
//            this.outputStream.write("ready\n".getBytes());
//            this.printWriter.write("ready\n");
            this.printWriter.println("ready\n");
            this.question = (Question) this.objInputStream.readObject();
        } catch (IOException e) {
            System.exit(-1);
//            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

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

        try {
            String answer = this.answerBox.getSelectionModel().getSelectedItem();
            timer.stop();
            if (Objects.equals(answer, this.question.getAnswers()[this.question.getCorrectAnswer()])
                    && !this.isQuestionTimeout) {
//                JOptionPane.showMessageDialog(null, "correct");
                score += 10;
            } else {
                score -= 5;
            }
        } catch (NullPointerException exception) {
            return;
        }
        this.counterQuestions++;

        getAndShowMessage();

    }

}
