package ronenoch.sudoku;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;


public class SudokuController {
    @FXML
    private GridPane grid;

    private Boolean is_create_board_mode;

    private TextField[][] textFields;

    private final int SIZE = 9;
    private final int SQUARE_SIDE_SIZE = (int) Math.sqrt(SIZE);

    private void set_initial_text_fields_font(int i, int j, TextField current_square) {
        int block_x = i / SQUARE_SIDE_SIZE;
        int block_y = j / SQUARE_SIDE_SIZE;
        Boolean is_dark_square = false;

        is_dark_square = (((block_x % SQUARE_SIDE_SIZE) % 2) == ((block_y % SQUARE_SIDE_SIZE) % 2));

        if (is_dark_square) {
//            current_square.setStyle("-fx-text-fill: grey;");
//            current_square.setStyle("-fx-text-inner-color: grey;");
            current_square.setStyle("-fx-background-color: silver;");
//                    current_square.setStyle("-fx-border-color: grey;");
        } else {
            current_square.setStyle("");
        }
    }

    public void initialize() {
        textFields = new TextField[SIZE][SIZE];

        grid.setVgap(1);
        grid.setHgap(1);
        is_create_board_mode = true;

        for (int i = 0; i < SIZE; i++) {
            RowConstraints row = new RowConstraints();
            ColumnConstraints col = new ColumnConstraints();

            row.setPercentHeight(100 / (double)SIZE);
            col.setPercentWidth(100 / (double)SIZE);
            col.setHgrow(Priority.SOMETIMES);
            row.setVgrow(Priority.SOMETIMES);
            grid.getRowConstraints().add(row);
            grid.getColumnConstraints().add(col);

            for (int j = 0; j < SIZE; j++) {
                TextField current_square = new TextField();
                current_square.setId(String.valueOf(i * SIZE + j));

//                current_square.setPrefSize(grid.getPrefWidth() / SIZE, grid.getPrefHeight() / SIZE);
                set_initial_text_fields_font(i, j, current_square);

                current_square.setAlignment(Pos.CENTER);
                GridPane.setVgrow(current_square, Priority.SOMETIMES);
                GridPane.setHgrow(current_square, Priority.SOMETIMES);

                current_square.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue,
                                        String newValue) {
                        /* for some reason, we are not allowed to use simple regex so I must re-implement regex on my own. */

//                        if (!newValue.matches("\\d*")) {
//                            current_square.setText(newValue.replaceAll("[^\\d]", ""));
//                        }

                        try {
                            if (newValue.isEmpty())
                            {
                                return;
                            }
                            int tester = Integer.valueOf(newValue);
                        } catch (NumberFormatException e) {
                            current_square.setText(oldValue);
                        }
                    }
                });

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
                if (!textFields[i][j].getText().isEmpty()) {
                    textFields[i][j].setEditable(true);

                    textFields[i][j].clear();
                    set_initial_text_fields_font(i, j, textFields[i][j]);
                }
            }
        }
    }
    @FXML
    void set_board(ActionEvent event) {
        if (!is_create_board_mode) {
            return;
        }
        is_create_board_mode = false;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!textFields[i][j].getText().isEmpty() && 1 <= textFields[i][j].getText().length()) {
                    textFields[i][j].setEditable(false);

//                    textFields[i][j].setStyle(textFields[i][j].getStyle() + "-fx-border-color: blue;");
                    textFields[i][j].setStyle(textFields[i][j].getStyle() + "-fx-text-inner-color: red;");
                }
            }
        }
    }

    private boolean is_insertion_legal(int x, int y, int value) {
        final int block_size = SQUARE_SIDE_SIZE;

        for (int i = 0; i < SIZE; i++) {
            /* if there is a square with the same value in the same column */
            if (i != x && !textFields[i][y].getText().isEmpty() && value == Integer.valueOf(textFields[i][y].getText())) {
                return false;
            }
        }
        for (int j = 0; j < SIZE; j++) {
            /* if there is a square with the same value in the same row */
            if (j != y && !textFields[x][j].getText().isEmpty() && value == Integer.valueOf(textFields[x][j].getText())) {
                return false;
            }
        }

        /*
         * checking for values in the same block-square
         * ((x) / block_size) * block_size     == the first row in the block
         * ((x / block_size) + 1) * block_size == the first row of the NEXT block
         **/
        for (int i = ((x) / block_size) * block_size; i < ((x / block_size) + 1) * block_size; i++) {
            for (int j = ((y) / block_size) * block_size; j < ((y / block_size) + 1) * block_size; j++) {
                if ((j != y || i != x) &&
                        !textFields[i][j].getText().isEmpty() &&
                        value == Integer.valueOf(textFields[i][j].getText())) {
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

    /* This function checks the value of the text in a textfield. The function was made for future use */
    private void inner_handle_insert(TextField textField) {
        String id_str = textField.getId();
        int id = Integer.valueOf(id_str);
        String input_text = (textField.getText());
        int numeric_input_value;
        int i;
        int j;

        try {
            if (input_text.isEmpty()) {
                return;
            }
            i = id / SIZE;
            j = id % SIZE;
            numeric_input_value = Integer.valueOf(input_text);

            /* input validation */
            if (numeric_input_value > SIZE ||
                    0 >= numeric_input_value ||
                    !is_insertion_legal(i, j, numeric_input_value)) {
                show_error_and_clear_text_field(textField);
            }
        }
        catch (NumberFormatException error) {
            show_error_and_clear_text_field(textField);
        }
    }

    private void handleInsert(ActionEvent e) {
        inner_handle_insert((TextField)e.getSource());
    }
}