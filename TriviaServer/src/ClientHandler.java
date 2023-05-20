import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ClientHandler extends Thread {
    private Socket socket;
//    private PrintWriter out;
    private ObjectOutputStream out;
    private BufferedReader in;
    private ArrayList<Question> questions;
    private int countSentQuestions;

     public ClientHandler(Socket socket, ArrayList<Question> questions) {
         this.socket = socket;
         this.countSentQuestions = 0;
         try {
             this.in = new BufferedReader(new InputStreamReader( this.socket.getInputStream()));
//             this.out = new PrintWriter(this.socket.getOutputStream(), true);
             this.out = new ObjectOutputStream(this.socket.getOutputStream());
//             this.questions = questions;
             this.questions = new ArrayList<>();
             this.loadDataFromFile("questions.txt");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }

    protected void loadDataFromFile(String fileName) {
        try {
            boolean isCorrect = false;
//            FileInputStream myReader = new FileInputStream(fileName);
//            ObjectInputStream input = new ObjectInputStream(myReader);
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("the questions file does not exist");
                System.exit(-1);
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String questionContent = scanner.nextLine();
                Question question = new Question(questionContent);
                for (int i = 0; i < 4; i++) {
                    isCorrect = (0 == i);
                    question.addAnswer(scanner.nextLine(), isCorrect);

                }
                this.questions.add(question);
            }

//            this.reminders = (HashMap<Date, String>) input.readObject();
//            myReader.close();
//            input.close();
            scanner.close();
        } catch (IOException ignored) {
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            System.out.println(e);
            System.exit(-1);
        }
    }

    @Override
    public void run() {
        super.run();
        int i = 0;
        int questionsCount = this.questions.size();
        String inputStr = "";

        /* TODO for loop ? */
        while (i < questionsCount) {
            try {
                inputStr = this.in.readLine();
            } catch (IOException e) {
                System.out.println("Could not read from client.");
                return;
//                throw new RuntimeException(e);
            }
            this.sendNextQuestion();
            i++;
        }

    }

    public void sendNextQuestion() {
        int index = new Random().nextInt(this.questions.size());
        try {
            this.out.writeObject(this.questions.get(index));
        } catch (IOException e) {
            System.out.println("Failed to write a question to the client");
            throw new RuntimeException(e);
        }
        this.questions.remove(index);
        this.countSentQuestions++;
    }
}
