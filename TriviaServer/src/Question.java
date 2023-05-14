import java.io.Serializable;
import java.util.Random;

public class Question implements Serializable {
    private String content;
    private String[] answers;
    private int correctAnswer;
    private int insertedAnswersNumber;

    public Question(String content) {
        this.content = content;
//        this.answers = new ArrayList<>(4);
        this.answers = new String[4];
        this.insertedAnswersNumber = 0;
    }

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
}
