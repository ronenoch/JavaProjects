import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
//import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class MenuController {

    private Order order;

    @FXML
    private VBox v_box;

    private ArrayList<CheckBox> checkBoxes;

    public void initialize() {
        this.order = new Order();
        this.checkBoxes = new ArrayList<CheckBox>();

        InitializeOrder();
    }

    @FXML
    void checkBoxPressed(ActionEvent event, Item item) {
        CheckBox checkBox = (CheckBox) event.getSource();
        HBox h_box = (HBox) checkBox.getParent();
        if (checkBox.isSelected()) {
            ComboBox<Integer> comboBox = new ComboBox<Integer>();
            comboBox.setPromptText("amount");
            comboBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    /* Giving the item to the handler to make it be able to update the order */
                    comboBoxPressed(e, item);
                }
            });

            /* adding options for the amount choice. */
            for (int i = 0; i < 30; i++) {
                comboBox.getItems().add(i);
            }
            h_box.getChildren().add(comboBox);
        } else {
            /* removing the combo-box if the checkbox is not selected. */
            h_box.getChildren().remove(1);
        }
    }

    @FXML
    void comboBoxPressed(ActionEvent event, Item item) {
        ComboBox comboBox = (ComboBox) event.getSource();
        int selectedOption = (int) comboBox.getSelectionModel().getSelectedItem();

        this.order.add_item(item, selectedOption);
    }

    private void InitializeOrder() {
        for (Map.Entry<String, ArrayList<Item>> entry : this.order.getMenu().getItems().entrySet()) {
            Label type = new Label(entry.getKey()) ;
            v_box.getChildren().add(type);
            for (Item item : entry.getValue()) {
                HBox h_box = new HBox(2);
                h_box.setSpacing(30);
                CheckBox checkBox = new CheckBox(item.getDescription() + "   price: " + String.valueOf(item.getPrice()));
                checkBox.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        checkBoxPressed(e, item);
                    }
                });

                this.checkBoxes.add(checkBox);
                h_box.getChildren().add(checkBox);

                v_box.getChildren().add(h_box);
            }
        }
    }

    /* clears the checkboxes and combo-boxes, and create a new order */
    protected void clearOrder() {
        HBox parentHBox;

        this.order = new Order();
        for (CheckBox checkBox : this.checkBoxes) {
            parentHBox = (HBox) checkBox.getParent();
            if (1 < parentHBox.getChildren().size()) {
                parentHBox.getChildren().remove(1);
            }
            checkBox.setSelected(false);
        }
    }

    protected void writeDataToFile(String fileName, String data) {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(data);
            myWriter.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File IO error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    @FXML
    protected void onPLaceOrderButtonClick() {
        String[] options = {"Confirm", "Edit", "Cancel"};
        String userNameAndID;

        /* default action is edit. */
        int choice = JOptionPane.showOptionDialog(null, "Is the order OK?", "Order confirmation",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);

        switch (choice) {
            case 0:
                userNameAndID = JOptionPane.showInputDialog("Enter your name + id Please.");
                writeDataToFile(userNameAndID + ".txt", this.order.toString());
                clearOrder();
                break;
            case 1:
                /* continue editing */
                break;
            case 2:
                /* cancel the order. start a new one. */
                clearOrder();
                break;
            default:
//                JOptionPane.showMessageDialog(null, "No option selected");
                break;
        }
    }
}