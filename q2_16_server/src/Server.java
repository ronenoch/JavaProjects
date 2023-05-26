import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Server {
    private DatagramSocket s;
    private final int port;

    public Server(int port) {
        try {
            s = new DatagramSocket();
            this.port = port;
        } catch (IOException e) {
            /* TODO write a message and exit. */
            System.out.println("server ctor failed.");
            throw new RuntimeException(e);
        }
    }

    public void serverRun() {
//        Socket newClient;
        String message = new String();
        Scanner scanner = new Scanner(System.in);
        InetAddress address = null;
        try {
            address = InetAddress.getByName("230.0.0.1");
        } catch (UnknownHostException e) {
            System.out.println("unknown host");
            System.exit(-1);
        }

        while (true) {
            System.out.println("Enter new message:");
            message = scanner.nextLine();
            DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), address, this.port);
            try {
                s.send(packet);
            } catch (IOException e) {
                System.out.println("io exception in packet send.");
                throw new RuntimeException(e);
            }

//            System.out.println("accepted a client");
//            ClientHandler handler = new ClientHandler(newClient, new ArrayList<Question>(this.questions));
//            handler.start();
        }
    }

}
