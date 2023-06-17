import java.io.IOException;
import java.net.*;

public class Server {
    private DatagramSocket s;
    private int port;
    private InetAddress address;

    /**
     * a udp multicast server
     *
     * @param port port to listen to.
     */
    public Server(int port) {
        try {
            s = new DatagramSocket();
            this.port = port;
            this.address = InetAddress.getByName("230.0.0.1");

        } catch (UnknownHostException e) {
            System.out.println("unknown host");
            System.exit(-1);
        } catch (IOException e) {
            /* TODO write a message and exit. */
            System.out.println("server ctor failed.");
            System.exit(-1);
        }
    }

    /**
     * sends a message to the multicast socket.
     *
     * @param message the message to send
     */
    public void sendMessage(String message) {
        DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), this.address, this.port);
        try {
            s.send(packet);
        } catch (IOException e) {
            System.out.println("io exception in packet send.");
            throw new RuntimeException(e);
        }
    }

}
