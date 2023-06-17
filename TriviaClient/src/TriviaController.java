import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.awt.event.ActionListener;
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
//    private ChoiceBox<String> answerBox;
    private RadioButton firstAnswer;
    @FXML
    private RadioButton secondAnswer;
    @FXML
    private RadioButton thirdAnswer;
    @FXML
    private RadioButton forthAnswer;
    @FXML
    private ToggleGroup answerGroup;
//    private ArrayList<RadioButton> answers;

    @FXML
    private Label scoreLabel;

    private final int questionTimeout = 5000;
    public void initialize() {

        String[] args = System.getProperty("javafx.application.args").split(",");
        if (1 != args.length) {
            JOptionPane.showMessageDialog(null, "Usage: <program> <server ip / host name>");
        }
        this.serverAddress = args[0];
        answerGroup = new ToggleGroup();

        firstAnswer.setToggleGroup(answerGroup);
        secondAnswer.setToggleGroup(answerGroup);
        thirdAnswer.setToggleGroup(answerGroup);
        forthAnswer.setToggleGroup(answerGroup);
        
        answerGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                confirmAnswer();
            }
        });
        for (Toggle button : answerGroup.getToggles()) {
            ((RadioButton)button).setFocusTraversable(false);
        }
        this.startNewGame();
    }

    @Override
    public void run() {
        super.run();
        startNewGame();
    }

    /**
     * starts a new trivia game.
     */
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

    /**
     * Receives a question from the server and show it on the screen.
     * @param question the question from the server
     */
    protected void showMessage(Question question) {
        this.scoreLabel.setText(Integer.toString(this.score));
        this.question = question;
        int i = 0;

        try {
            ((RadioButton)this.answerGroup.getSelectedToggle()).setSelected(false);
        } catch (NullPointerException ignored) {}
        for (String answer: this.question.getAnswers()) {
            ((RadioButton)this.answerGroup.getToggles().get(i)).setText(answer);
            i++;
        }
        this.textF.setText(this.question.getContent());
        this.isQuestionTimeout = false;
        this.timer.start();
    }

    /**
     * Called from the listener of the radio buttons. checks the user's answer.
     */
    protected void confirmAnswer() {
        if (null == question) {
            return;
        }
        try {
            String answer = (String) ((RadioButton)this.answerGroup.getSelectedToggle()).getText();
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

        this.client = new Client(this.serverAddress, this, false);
        this.client.getNextQuestion();

    }

}
