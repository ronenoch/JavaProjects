import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


import javax.swing.*;

public class MulticastServerController {
    @FXML
    private TextField textF;

    private Server server;

    /**
     * the controller initializer.
     */
    public void initialize() {

        String[] args = System.getProperty("javafx.application.args").split(",");
        if (1 != args.length) {
            JOptionPane.showMessageDialog(null, "Usage: <program> <server ip / host name>");
        }
        this.server = new Server(6666);
        textF.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                handleInsert(e);
            }
        });
    }

    /**
     * Sends the message to the clients after it was received from the user.
     *
     * @param textField jfx text object that has the text to send.
     */
    private void innerHandleInsert(TextField textField) {
        String input_text = (textField.getText());

        if (input_text.isEmpty()) {
            return;
        }
        this.server.sendMessage(input_text);

    }

    /**
     * handler for events of the textfield of the server.
     *
     * @param e input event
     */
    private void handleInsert(ActionEvent e) {
        innerHandleInsert((TextField) e.getSource());
    }

}
