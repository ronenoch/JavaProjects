import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Server {
    private ServerSocket s;
    private ArrayList<Question> questions;

    public Server(int port) {
        try {
            s = new ServerSocket(port);
            this.questions = new ArrayList<>();
            this.loadDataFromFile("questions.txt");
        } catch (IOException e) {
            /* TODO write a message and exit. */
            System.out.println("server ctor failed.");
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

    public void serverRun() {
        Socket newClient;
        while (true) {
            try {
                newClient = this.s.accept();
            } catch (IOException e) {
                System.out.println("cant accept.");
                throw new RuntimeException(e);
            }
            System.out.println("accepted a client");
            ClientHandler handler = new ClientHandler(newClient, new ArrayList<Question>(this.questions));
            handler.start();
        }
    }

}
