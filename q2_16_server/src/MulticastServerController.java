import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


import javax.swing.*;

public class MulticastServerController {
    @FXML
    private TextField textF;

    private Server server;

//    private Client client;

    public void initialize() {

//        this.getAndShowMessage();
        String[] args = System.getProperty("javafx.application.args").split(",");
        if (1 != args.length) {
            JOptionPane.showMessageDialog(null, "Usage: <program> <server ip / host name>");
        }
//        this.client = new Client(args[0], 6666, this.textF);
        this.server = new Server(6666);
//        server.serverRun();
        textF.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                handleInsert(e);
            }
        });
    }

    /* This function checks the value of the text in a textfield. The function was made for future use */
    private void inner_handle_insert(TextField textField) {
//        String id_str = textField.getId();
//        int id = Integer.valueOf(id_str);
        String input_text = (textField.getText());

        if (input_text.isEmpty()) {
            return;
        }
        this.server.sendMessage(input_text);

    }

    private void handleInsert(ActionEvent e) {
        inner_handle_insert((TextField)e.getSource());
    }

//    @FXML
//    protected void clearMessage() {
//        this.textF.clear();
//    }
//
//    @FXML
//    protected void leavePressed() {
//        this.client.leaveSession();
//    }
//
//    @FXML
//    protected void connectPressed() {
//        this.client.joinSession();
//    }

}
