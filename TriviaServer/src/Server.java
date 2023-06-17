import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Server {
    private ServerSocket s;

    /**
     * the server class that handles multiple client. Reads the questions from a file.
     * @param port server port
     */
    public Server(int port) {
        try {
            s = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("server ctor failed.");
            System.exit(-1);
        }
    }

    /**
     * runs the server. each accepted client is handles in a different thread
     */
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
//            ClientHandler handler = new ClientHandler(newClient, new ArrayList<Question>(this.questions));
            ClientHandler handler = new ClientHandler(newClient);
            handler.start();
        }
    }

}
