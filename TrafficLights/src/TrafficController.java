import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

//import javafx.application.Application.Parameters;


//import static com.sun.javafx.application.ParametersImpl.getParameters;

public class TrafficController {


    @FXML
    private VBox v_box;
    @FXML
    private BorderPane pane;

    public void initialize() {

//        Parameters parameters = Parameters.getParameters().getRaw();
//        if (parameters.getRaw().size() > 0) {
//            System.out.println("The following arguments were passed:");
//            for (String arg : parameters.getRaw()) {
//                System.out.println(arg);
//            }
//        } else {
//            System.out.println("No arguments were passed.");
//        }

        TrafficLight a1 = new TrafficLight((int)(150), 40);

        a1.vehicleGoGreen();

        TrafficLight a2 = new TrafficLight((int)(40), 150);
        a2.vehicleGoRed();

        TrafficLight a3 = new TrafficLight((int)(150), 300);
        a3.vehicleGoGreen();

        TrafficLight a4 = new TrafficLight((int)(300 - 40), 150);
        a4.vehicleGoRed();

        ArrayList<TrafficLight> lights = new ArrayList<>();
        lights.add(a1);
        lights.add(a2);
        lights.add(a3);
        lights.add(a4);

        for (TrafficLight light : lights) {
            pane.getChildren().add(light.getGreenCircle());
            pane.getChildren().add(light.getRedCircle());
            pane.getChildren().add(light.getRedRectangle());
            pane.getChildren().add(light.getGreenRectangle());
        }

        MyTimer timer = new MyTimer(lights);
        timer.start();

//        a1.vehicleGoGreen();
    }

//    @FXML
//    void comboBoxPressed(ActionEvent event, Item item) {
//        ComboBox comboBox = (ComboBox) event.getSource();
//        int selectedOption = (int) comboBox.getSelectionModel().getSelectedItem();
//
//        this.order.add_item(item, selectedOption);
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