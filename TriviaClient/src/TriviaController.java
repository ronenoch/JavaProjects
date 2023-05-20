import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Objects;

public class TriviaController {
    @FXML
    private TextField textF;

    @FXML
    private VBox v_box;

    private Socket s;
    private OutputStream outputStream;
    private ObjectOutputStream objOutputStream;
    private PrintWriter printWriter;
    private ObjectInputStream objInputStream;
    private Question question;
    private Timer timer;
    private boolean isQuestionTimeouted;
    private int score;


    @FXML
    private ChoiceBox<String> answerBox;

    @FXML
    private Label scoreLabel;

//    private HashMap<Date, String> reminders;

    private final int questionTimeout = 5000;
    public void initialize() {
        try {
            s = new Socket("127.0.0.1", 3333);
            s.setSoTimeout(1000);
//            s.connect();
//            outputStream = s.getOutputStream();
//            this.objOutputStream = new ObjectOutputStream(outputStream);
            this.objInputStream = new ObjectInputStream(s.getInputStream());
            this.printWriter = new PrintWriter(s.getOutputStream(), true);
            this.outputStream = s.getOutputStream();
            this.score = 0;
            this.timer = new Timer(this.questionTimeout, new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent actionEvent) {
                    System.out.println("timeout question");
                    isQuestionTimeouted = true;
                }

            });
        } catch (IOException e) {
            /* TODO add a msgbox */
            System.out.println("error in the socket init");
            throw new RuntimeException(e);
        }
    this.getAndShowMessage();
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

    protected void getAndShowMessage() {
        this.scoreLabel.setText(Integer.toString(this.score));
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
        this.isQuestionTimeouted = false;
        this.timer.start();
    }

    @FXML
    protected void confirmAnswer() {

        try {
            String answer = this.answerBox.getSelectionModel().getSelectedItem();
            timer.stop();
            if (Objects.equals(answer, this.question.getAnswers()[this.question.getCorrectAnswer()])) {
//                JOptionPane.showMessageDialog(null, "correct");
                if (!this.isQuestionTimeouted) {
                    score += 5;
                }
            }
        } catch (NullPointerException exception) {
            return;
        }

        getAndShowMessage();

//        Date date = new Date(day, month, year);
//        reminder = this.reminders.get(date);


//        if (null == reminder) {
//            JOptionPane.showMessageDialog(null, "No reminder found");
//            return;
//        }

//        this.textF.setText(reminder);

    }
}
