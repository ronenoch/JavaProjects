import javafx.application.Platform;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.*;
import java.util.Date;

public class Client extends Thread {
    private MulticastSocket socket;
    private String ip;
    private boolean isConnected;
    private TextField textField;

    public Client(String ip, int port, TextField textField) {
        try {
            this.ip = ip;
            this.socket = new MulticastSocket(port);
            this.socket.setSoTimeout(100);
            this.textField = textField;
            this.isConnected = false;
            this.start();
        } catch (IOException e) {
            System.out.println("io exception in client constructor");
        }
    }

    @Override
    public void run() {
        super.run();
        byte[] buf = new byte[256];
        DatagramPacket packet;

        while (true) {
            if (!this.getIsConnected()) {
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
                throw new RuntimeException(e);
            }
            Date date = new Date();
            buf = packet.getData();
            int len = packet.getLength();
            String received = (new String(buf)).substring(0, len);
            Platform.runLater(() -> {
                this.textField.setText("[ " + date + "] " + received);
            });
        }

    }

    public synchronized void joinSession() {
        try {
            InetAddress group = InetAddress.getByName(this.ip);
            this.socket.joinGroup(group);
            this.isConnected = true;
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("io exception in client connect");
        }
    }

    public synchronized void leaveSession() {
        try {
            this.socket.leaveGroup(InetAddress.getByName(this.ip));
            this.isConnected = false;

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public synchronized boolean getIsConnected() {
        return this.isConnected;
    }
}
