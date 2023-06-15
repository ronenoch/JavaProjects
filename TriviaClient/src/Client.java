import javafx.application.Platform;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {
    private static OutputStream outputStream;
    private static PrintWriter printWriter;
    private static ObjectInputStream objInputStream;
    private static Socket s;
    private String serverAddress;
    private TriviaController controller;


    public Client(String address, TriviaController controller, boolean shouldReconnect) {
        this.serverAddress = address;
        this.controller = controller;
        this.restartClient(shouldReconnect);

    }

    public void restartClient(boolean shouldReconnect) {
        try {
            if (null == s || shouldReconnect) {
                s = new Socket(this.serverAddress, 3333);
                s.setSoTimeout(1000);
                objInputStream = new ObjectInputStream(s.getInputStream());
                printWriter = new PrintWriter(s.getOutputStream(), true);
                outputStream = s.getOutputStream();
            }
        } catch (IOException e) {
            /* TODO add a msgbox */
            System.out.println("error in the socket init");
            throw new RuntimeException(e);
        }
    }

    public void getNextQuestion() {
        this.start();
    }
    @Override
    public void run() {
        super.run();
        Question question = null;
        try {
            printWriter.println("ready\n");
            question = (Question) objInputStream.readObject();
        } catch (IOException e) {
            System.out.println("io exception");
            try {
                Thread.sleep(500);
            } catch (InterruptedException err) {
                throw new RuntimeException(err);
            }

            Platform.runLater(() -> {

                int input = JOptionPane.showConfirmDialog(null, "should start a new game?");
                if (JOptionPane.YES_OPTION == input) {
                    Platform.runLater(() -> {
                        this.controller.startNewGame();
                    });
                } else {
                    System.exit(0);
                }
            });
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (null != question) {

            Question finalQuestion = question;
            Platform.runLater(() -> {
                        this.controller.getAndShowMessage(finalQuestion);
            });
        }
//        return question;
    }
}
