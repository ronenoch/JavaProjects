import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
//import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;

public class MenuController {


    @FXML
    private VBox v_box;
    @FXML
    private BorderPane pane;

//    private ArrayList<CheckBox> checkBoxes;

    public void initialize() {
//        this.checkBoxes = new ArrayList<CheckBox>();
//        Circle circle1 = new Circle(50, 50, 40, Color.BLUE);
//        Circle circle2 = new Circle(50, 110, 20, Color.RED);

        TrafficLight a1 = new TrafficLight((int)(150), 40);

        pane.getChildren().add(a1.getGreenCircle());
        pane.getChildren().add(a1.getRedCircle());
        pane.getChildren().add(a1.getRedRectangle());
        pane.getChildren().add(a1.getGreenRectangle());

        a1.vehicleGoGreen();

        TrafficLight a2 = new TrafficLight((int)(40), 150);
        a2.vehicleGoRed();

        pane.getChildren().add(a2.getGreenCircle());
        pane.getChildren().add(a2.getRedCircle());
        pane.getChildren().add(a2.getRedRectangle());
        pane.getChildren().add(a2.getGreenRectangle());

        TrafficLight a3 = new TrafficLight((int)(150), 300);
        a3.vehicleGoGreen();

        pane.getChildren().add(a3.getGreenCircle());
        pane.getChildren().add(a3.getRedCircle());
        pane.getChildren().add(a3.getRedRectangle());
        pane.getChildren().add(a3.getGreenRectangle());

        TrafficLight a4 = new TrafficLight((int)(300 - 40), 150);
        a4.vehicleGoRed();

        pane.getChildren().add(a4.getGreenCircle());
        pane.getChildren().add(a4.getRedCircle());
        pane.getChildren().add(a4.getRedRectangle());
        pane.getChildren().add(a4.getGreenRectangle());

        ArrayList<TrafficLight> lights = new ArrayList<>();
        lights.add(a1);
        lights.add(a2);
        lights.add(a3);
        lights.add(a4);
        MyTimer timer = new MyTimer(lights);
        timer.start();


        a1.vehicleGoGreen();
//        InitializeOrder();
    }

//    @FXML
//    void comboBoxPressed(ActionEvent event, Item item) {
//        ComboBox comboBox = (ComboBox) event.getSource();
//        int selectedOption = (int) comboBox.getSelectionModel().getSelectedItem();
//
//        this.order.add_item(item, selectedOption);
//    }

//    private void InitializeOrder() {
//        for (Map.Entry<String, ArrayList<Item>> entry : this.order.getMenu().getItems().entrySet()) {
//            Label type = new Label(entry.getKey()) ;
//            v_box.getChildren().add(type);
//            for (Item item : entry.getValue()) {
//                HBox h_box = new HBox(2);
//                h_box.setSpacing(30);
//                CheckBox checkBox = new CheckBox(item.getDescription() + "   price: " + String.valueOf(item.getPrice()));
//                checkBox.setOnAction(new EventHandler<ActionEvent>() {
//                    @Override public void handle(ActionEvent e) {
//                        checkBoxPressed(e, item);
//                    }
//                });
//
//                this.checkBoxes.add(checkBox);
//                h_box.getChildren().add(checkBox);
//
//                v_box.getChildren().add(h_box);
//            }
//        }
//    }

    /* clears the checkboxes and combo-boxes, and create a new order */
//    protected void clearOrder() {
//        HBox parentHBox;
//
//        for (CheckBox checkBox : this.checkBoxes) {
//            parentHBox = (HBox) checkBox.getParent();
//            if (1 < parentHBox.getChildren().size()) {
//                parentHBox.getChildren().remove(1);
//            }
//            checkBox.setSelected(false);
//        }
//    }

//    @FXML
//    protected void onPLaceOrderButtonClick() {
//        String[] options = {"Confirm", "Edit", "Cancel"};
//        String userNameAndID;
//
//        /* default action is edit. */
//        int choice = JOptionPane.showOptionDialog(null, "Is the order OK?", "Order confirmation",
//                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
//
//        switch (choice) {
//            case 0:
//                userNameAndID = JOptionPane.showInputDialog("Enter your name + id Please.");
//                writeDataToFile(userNameAndID + ".txt", this.order.toString());
//                break;
//            case 1:
//                /* continue editing */
//                break;
//            case 2:
//                /* cancel the order. start a new one. */
//                break;
//            default:
////                JOptionPane.showMessageDialog(null, "No option selected");
//                break;
//        }
//    }
}