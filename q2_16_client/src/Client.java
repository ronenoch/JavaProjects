import javafx.application.Platform;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.*;

public class Client extends Thread {
    private MulticastSocket socket;
//    private int port;
    private String ip;
    private boolean isConnected;
    private TextField textField;

    public Client(String ip, int port, TextField textField) {
        try {
            this.ip = ip;
//            this.port = port;
            this.socket = new MulticastSocket(port);
            this.socket.setSoTimeout(100);
            this.textField = textField;
            this.isConnected = false;
//            joinSession();
            this.start();
        } catch (IOException e) {
            System.out.println("io exception in client constructor");
//            System.out.println(e);
        }
    }

    @Override
    public void run() {
        super.run();
        byte[] buf = new byte[256];
        DatagramPacket packet;

        while (true) {
            /* TODO add a mutex or something */
            if (!this.isConnected) {
                try {
                    Thread.sleep(100);
                    continue;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            packet = new DatagramPacket(buf, buf.length);
            try {
                this.socket.receive(packet);
            } catch (SocketTimeoutException e) {
                continue;
            } catch (IOException e) {

                System.out.println("io exception in client run loop");
                System.out.println(e);
                throw new RuntimeException(e);
            }
            buf = packet.getData();
            int len = packet.getLength();
            String received = (new String(buf)).substring(0, len);
            Platform.runLater(() -> {
                this.textField.setText(received);
            });
            System.out.println(received);
        }

    }

    public void joinSession() {
        try {
            InetAddress group = InetAddress.getByName(this.ip);
//            this.socket = new MulticastSocket(port);
            this.socket.joinGroup(group);
            this.isConnected = true;
//            start();
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("io exception in client connect");
//            System.out.println(e);
        }
    }

    public void leaveSession() {
        try {
            this.socket.leaveGroup(InetAddress.getByName(this.ip));
//            this.socket.close();
            this.isConnected = false;

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
