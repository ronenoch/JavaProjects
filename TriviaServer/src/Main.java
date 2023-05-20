import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Server server = new Server(3333);
        server.serverRun();

    }


}