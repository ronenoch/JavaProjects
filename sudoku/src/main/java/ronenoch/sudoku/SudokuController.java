package ronenoch.sudoku;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.awt.*;

public class SudokuController {
//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
//
//    @FXML
//    void keyPressed(KeyEvent event) {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
    @FXML
    private GridPane grid;

    @FXML
    private TextField textF;

    private Boolean is_create_board_mode;

    private TextField[][] textFields;
//    private Dictionary d;

    private final int SIZE = 9;

    private void set_initial_text_fields_font(int i, int j, TextField current_square) {
        int block_x = i / 3;
        int block_y = j / 3;
        Boolean is_dark_square = false;

        is_dark_square = (((block_x % 3) % 2) == ((block_y % 3) % 2));
        if (is_dark_square) {
//            TextField current_square = textFields[i][j];
//                    current_square.setStyle("-fx-text-fill: grey;");
//                    current_square.setStyle("-fx-text-inner-color: grey;");
            current_square.setStyle("-fx-background-color: grey;");
//                    current_square.setStyle("-fx-border-color: grey;");
        } else {
//            current_square.setStyle("-fx-text-fill: white;");
            current_square.setStyle("");
//            current_square.setStyle("-fx-background-color: white;");
        }
    }

    public void initialize() {
        textFields = new TextField[SIZE][SIZE];
//        Boolean is_dark_square = false;
//        int block_x = 0;
//        int block_y = 0;
        int row_number_inside_block = 0;
        int col_number_inside_block = 0;

        grid.setVgap(1);
        grid.setHgap(1);
        is_create_board_mode = true;

        for (int i = 0; i < SIZE; i++) {
//            RowConstraints row = new RowConstraints(grid.getPrefWidth());
//            RowConstraints row = new RowConstraints(25);

//            RowConstraints row = new RowConstraints();
//            grid.getRowConstraints().add(row);
            for (int j = 0; j < SIZE; j++) {
//                is_dark_square = false;

                TextField current_square = new TextField();
                current_square.setId(String.valueOf(i * SIZE + j));
//                block_x = i / 3;
//                block_y = j / 3;
                set_initial_text_fields_font(i, j, current_square);

//                current_square.setPrefSize(grid.getWidth() / SIZE, grid.getHeight() / SIZE);
                current_square.setPrefSize(grid.getPrefWidth() / SIZE, grid.getPrefHeight() / SIZE);
//                current_square.textProperty().addListener(new ChangeListener<String>() {
//                    @Override
//                    public void changed(ObservableValue<? extends String> observable, String oldValue,
//                                        String newValue) {
//                        if (!newValue.matches("\\d*")) {
//                            current_square.setText(newValue.replaceAll("[^\\d]", ""));
//                        }
//                    }
//                });
                current_square.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        handleInsert(e);
                    }
                });

                grid.add(current_square, i, j);
                textFields[i][j] = current_square;
            }
        }
    }

    @FXML
    void clear_board(ActionEvent event) {
        is_create_board_mode = true;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!textFields[i][j].getText().isEmpty() && 1 == textFields[i][j].getText().length()) {
                    textFields[i][j].setEditable(false);

//                    textFields[i][j].setStyle(textFields[i][j].getStyle() + "-fx-border-color: grey;");
                    set_initial_text_fields_font(i, j, textFields[i][j]);
                    textFields[i][j].clear();
//                    textFields[i][j].setBorder(new Border());
                }
            }
        }
//        initialize();
    }
    @FXML
    void set_board(ActionEvent event) {
        if (!is_create_board_mode) {
            return;
        }
        is_create_board_mode = false;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!textFields[i][j].getText().isEmpty() && 1 == textFields[i][j].getText().length()) {
                    textFields[i][j].setEditable(false);

//                    textFields[i][j].setStyle(textFields[i][j].getStyle() + "-fx-border-color: blue;");
                    textFields[i][j].setStyle(textFields[i][j].getStyle() + "-fx-text-inner-color: red;");
//                    textFields[i][j].setBorder(new Border());
                }
            }
        }
    }

    @FXML
    void keyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
//            if (d.isLegal(textF.getText()))
//                JOptionPane.showConfirmDialog(null, "Legal word", "Legal word", JOptionPane.CLOSED_OPTION);
//            else
//                JOptionPane.showConfirmDialog(null, "Illegal word", "Illegal word", JOptionPane.CLOSED_OPTION);
//            JOptionPane.showConfirmDialog(null, "Illegal word", "Illegal word", JOptionPane.CLOSED_OPTION);

        }
    }

    private boolean is_insertion_legal(int x, int y, int value) {
        int block_size = SIZE / 3;

        for (int i = 0; i < SIZE; i++) {
            if (i != x && !textFields[i][y].getText().isEmpty() && value == Integer.valueOf(textFields[i][y].getText())) {
                return false;
            }
        }
        for (int j = 0; j < SIZE; j++) {
            if (j != y && !textFields[x][j].getText().isEmpty() && value == Integer.valueOf(textFields[x][j].getText())) {
                return false;
            }
        }

        for (int i = ((x) / block_size) * block_size; i < ((x + block_size) / block_size) * block_size; i++) {
            for (int j = ((y) / block_size) * block_size; j < ((y + block_size) / block_size) * block_size; j++) {
                if ((j != y || i != x) && !textFields[i][j].getText().isEmpty() && value == Integer.valueOf(textFields[i][j].getText())) {
                    return false;
                }

            }
        }


        return true;
    }
    private void show_error_and_clear_text_field(TextField textField) {
        JOptionPane.showConfirmDialog(null, "Illegal input", "Illegal input", JOptionPane.CLOSED_OPTION);
        (textField).clear();

    }

    private void handleInsert(ActionEvent e) {

        String id_str = ((TextField)e.getSource()).getId();
        int id = Integer.valueOf(id_str);
        String input_text = (((TextField)e.getSource()).getText());
        int numeric_input_value;
        int i;
        int j;

        try {
            if (input_text.length() >= 2) {
                show_error_and_clear_text_field((TextField)e.getSource());
                return;
            }
            if (input_text.isEmpty()) {
                /* remove the value ?? */
//                ((TextField) e.getSource()).setEditable(false);
                return;
            }
            i = id / SIZE;
            j = id % SIZE;
            numeric_input_value = Integer.valueOf(input_text);
            if (!is_insertion_legal(i, j, numeric_input_value)) {
                show_error_and_clear_text_field((TextField)e.getSource());
//                return;
            }
        }
        catch (NumberFormatException error) {
            show_error_and_clear_text_field((TextField)e.getSource());
//            return;
        }

    }
}