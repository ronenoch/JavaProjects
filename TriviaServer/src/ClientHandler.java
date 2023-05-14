import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private ArrayList<Question> questions;

     public ClientHandler(Socket socket, ArrayList<Question> questions) {
         this.socket = socket;
         try {
             this.in = new BufferedReader(new InputStreamReader( this.socket.getInputStream()));
             this.out = new PrintWriter(this.socket.getOutputStream(), true);
             this.questions = questions;
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }


    @Override
    public void run() {
        super.run();

    }
}
