import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;

public class RemindersController {
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

    public void initialize() {

        this.reminders = new HashMap<>();

        this.remindersFileName = JOptionPane.showInputDialog("Enter Reminders file name");
        loadDataFromFile(remindersFileName);

        dayBox.setPromptText("day");

        /* adding options for the day choice. */
        for (int i = 1; i < 32; i++) {
            dayBox.getItems().add(i);
        }

        monthBox.setPromptText("month");

        /* adding options for the month choice. */
        for (int i = 1; i < 13; i++) {
            monthBox.getItems().add(i);
        }

        yearBox.setPromptText("year");

        /* adding options for the year choice. */
        for (int i = 2022; i < 2040; i++) {
            yearBox.getItems().add(i);
        }

    }

    protected void writeDataToFile(String fileName){
        try {
            FileOutputStream myWriter = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(myWriter);
            out.writeObject(this.reminders);
            myWriter.close();
            out.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File IO error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void loadDataFromFile(String fileName) {
        try {
            FileInputStream myReader = new FileInputStream(fileName);
            ObjectInputStream input = new ObjectInputStream(myReader);
            this.reminders = (HashMap<Date, String>) input.readObject();
            myReader.close();
            input.close();
        } catch (IOException ignored) {
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            System.exit(-1);
        }
    }
    @FXML
    protected void saveReminder() {
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
        String reminder;

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