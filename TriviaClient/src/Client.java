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


    /**
     * client thread that connects to the server once and then each question handling is in a different thread
     * @param address address of the server
     * @param controller the fx controller
     * @param shouldReconnect should the client reconnect to the server (in a new game)
     */
    public Client(String address, TriviaController controller, boolean shouldReconnect) {
        this.serverAddress = address;
        this.controller = controller;
        if (shouldReconnect) {

            this.restartClient();
        }

    }

    /**
     * restart the client socket.
     */
    public void restartClient() {
        try {
            closeSocket();
            s = new Socket(this.serverAddress, 3333);
            s.setSoTimeout(1000);
            objInputStream = new ObjectInputStream(s.getInputStream());
            printWriter = new PrintWriter(s.getOutputStream(), true);
            outputStream = s.getOutputStream();
        } catch (IOException e) {
            /* TODO add a msgbox ? */
            System.out.println("error in the socket init");
            System.exit(-1);
        }
    }

    /**
     * close the socket and streams.
     */
    private void closeSocket() {
        try {
            if (s != null) {
                s.close();
            }
            objInputStream.close();
            printWriter.close();
            outputStream.close();
        } catch (Exception ignored) {}
    }

    /**
     * starts the thread to get another question.
     */
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
            System.out.println(e.getStackTrace());
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
            /* should never happen */
            throw new RuntimeException(e);
        }

        if (null != question) {

            Question finalQuestion = question;
            Platform.runLater(() -> {
                        this.controller.showMessage(finalQuestion);
            });
        }
    }
}
