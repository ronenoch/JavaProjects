import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
//import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MenuController {
    @FXML
    private TextField textF;

    @FXML
    private VBox v_box;

    @FXML
    private ComboBox<Integer> dayBox;

    @FXML
    private ComboBox<Integer> monthBox;

    @FXML
    private ComboBox<Integer> yearBox;

    private HashMap<Date, String> reminders;

    private String remindersFileName;

    public void initialize() throws IOException {
//        this.order = new Order();
//        this.checkBoxes = new ArrayList<CheckBox>();
        this.reminders = new HashMap<>();

        this.remindersFileName = JOptionPane.showInputDialog("Enter Reminders file name");
        loadDataFromFile(remindersFileName);


//        InitializeOrder();

        dayBox = new ComboBox<Integer>();
        dayBox.setPromptText("day");
        dayBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                /* Giving the item to the handler to make it be able to update the order */
                dayBoxPressed(e);
            }
        });

        /* adding options for the amount choice. */
        for (int i = 1; i < 32; i++) {
            dayBox.getItems().add(i);
        }
        v_box.getChildren().add(dayBox);

        monthBox = new ComboBox<Integer>();
        monthBox.setPromptText("month");
        monthBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                /* Giving the item to the handler to make it be able to update the order */
                monthBoxPressed(e);
            }
        });

        /* adding options for the amount choice. */
        for (int i = 1; i < 13; i++) {
            monthBox.getItems().add(i);
        }
        v_box.getChildren().add(monthBox);


        yearBox = new ComboBox<Integer>();
        yearBox.setPromptText("year");
        yearBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                /* Giving the item to the handler to make it be able to update the order */
                yearBoxPressed(e);
            }
        });

        /* adding options for the amount choice. */
        for (int i = 2022; i < 2040; i++) {
            yearBox.getItems().add(i);
        }
        v_box.getChildren().add(yearBox);

    }

    private void monthBoxPressed(ActionEvent event) {
        ComboBox comboBox = (ComboBox) event.getSource();
        int selectedOption = (int) comboBox.getSelectionModel().getSelectedItem();
    }

    private void yearBoxPressed(ActionEvent event) {
        ComboBox comboBox = (ComboBox) event.getSource();
        int selectedOption = (int) comboBox.getSelectionModel().getSelectedItem();
        
    }

    private void dayBoxPressed(ActionEvent event) {
        ComboBox comboBox = (ComboBox) event.getSource();
        int selectedOption = (int) comboBox.getSelectionModel().getSelectedItem();
    }

    private void InitializeOrder() {
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
    }

    protected void writeDataToFile(String fileName){
        try {
//            FileWriter myWriter = new FileWriter(fileName);
            FileOutputStream myWriter = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(myWriter);
            out.writeObject(this.reminders);
            myWriter.close();
            out.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File IO error", "Error", JOptionPane.ERROR_MESSAGE);
//            throw e;
        }
    }

    protected void loadDataFromFile(String fileName){
        try {
            FileInputStream myReader = new FileInputStream(fileName);
            ObjectInputStream input = new ObjectInputStream(myReader);
            this.reminders = (HashMap<Date, String>) input.readObject();
            myReader.close();
            input.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File IO error", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    protected void saveReminder() throws IOException {
        int day = 0;
        int month = 0;
        int year  = 0;
        String reminder;

        try {

            day = this.dayBox.getSelectionModel().getSelectedItem();
            month = this.monthBox.getSelectionModel().getSelectedItem();
            year = this.yearBox.getSelectionModel().getSelectedItem();
            reminder = this.textF.getText();
        } catch (NullPointerException exception) {
            JOptionPane.showMessageDialog(null, "save reminder failed");
            return;
        }

        Date date = new Date(day, month, year);
        this.reminders.put(date, reminder);

        this.writeDataToFile(this.remindersFileName);

    }

    @FXML
    protected void showReminder() {
        int day = 0;
        int month = 0;
        int year  = 0;
        String reminder = new String();

        try {
            day = this.dayBox.getSelectionModel().getSelectedItem();
            month = this.monthBox.getSelectionModel().getSelectedItem();
            year = this.yearBox.getSelectionModel().getSelectedItem();
        } catch (NullPointerException exception) {
            return;
        }

        Date date = new Date(day, month, year);
        reminder = this.reminders.get(date);


        if (null == reminder) {
            JOptionPane.showMessageDialog(null, "No reminder found");
            return;
        }

        this.textF.setText(reminder);
        
    }
}