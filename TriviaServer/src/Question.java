import java.io.Serializable;
import java.util.Random;

public class Question implements Serializable {
    private String content;
    private String[] answers;
    private int correctAnswer;
    private int insertedAnswersNumber;

    /**
     * a class that represent a question
     * @param content the question itself.
     */
    public Question(String content) {
        this.content = content;
        this.answers = new String[4];
        this.insertedAnswersNumber = 0;
    }

    /**
     * add an answer to the option answers. Randomize the answers order.
     * @param content the content of the answer
     * @param isCorrect is the answer correct
     */
    public void addAnswer(String content, boolean isCorrect) {
        int index = new Random().nextInt(4 - this.insertedAnswersNumber);
        int i = 0;
        while (i < 4) {
            if (null == this.answers[i]) {
                if (0 == index) {
                    break;
                }
                index--;
            }
            i++;
        }
        this.answers[i] = content;
        if (isCorrect) {
            this.correctAnswer = i;
        }
        this.insertedAnswersNumber++;

    }

    /**
     * getter of answers
     * @return answers
     */
    public String[] getAnswers() {
        return this.answers;
    }

    /**
     * getter of the content of the question
     * @return the content
     */
    public String getContent() {
        return this.content;
    }

    /**
     * getter for the correct answer
     * @return the correct answer
     */
    public int getCorrectAnswer() {
        return this.correctAnswer;
    }

}
