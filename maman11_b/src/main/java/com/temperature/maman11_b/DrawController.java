package com.temperature.maman11_b;

import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//
//package com.avivn.mmn11.q2;

//import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Random;

public class DrawController {
    // constant for the number of shapes to generate on each click
    final int N_SHAPES_TO_GEN = 10;

    // The drawing destination
    @FXML
    private Canvas canvas;

    // The random number generator - no need to create again and again
    private final Random generator;
    private static int year = 0;

    public DrawController() {
        generator = new Random();
    }

    private int temp_by_month[][] = {
            {5, 3, 5, 4, 6},
            {4, 5, 5, 6, 6},
            {2, 5, 7, 5, 9},
            {6, 7, 6, 3, 7},
            {5, 8, 3, 5, 8},
            {2, 4, 8, 3, 9},
            {5, 8, 5, 5, 6},
            {1, 1, 3, 5, 3},
            {3, 7, 5, 3, 4},
            {4, 5, 4, 3, 1},
            {5, 5, 1, 5, 2},
            {7, 5, 5, 3, 5},
    };
//    private String months[] = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};

    @FXML
    // triggers when clicking the button to draw, and draws 10 random shapes.
    protected void onDrawButtonClick() {
        // remove all existing drawings first
        int start_x = 40;
        int start_y = (int)canvas.getHeight() - 40;
        int weidth = (int)canvas.getWidth() - 40;

        int single_value_height = (start_y - start_x) / 20;
        int current_start_draw_pos = (weidth + start_x) / 12;
//        int current_y = (start_y - start_x) / 12 / 2;
        int current_value;
        int min = 100000;
        int max = 0;

        canvas.getGraphicsContext2D()
                .clearRect(0,0,canvas.getWidth(), canvas.getHeight());

        GraphicsContext graphics = canvas.getGraphicsContext2D();
        graphics.strokeLine(start_x, start_y, weidth, start_y);
        graphics.strokeLine(start_x, start_y, start_x, start_x);

        Font header_font = new Font(20);
        Font last_font = graphics.getFont();

        Font coordinate_font = new Font(16);
        graphics.setFont(coordinate_font);

        graphics.rotate(-90.0);
        graphics.strokeText("Temperature", -(start_y + start_x) / 2, 20);
        graphics.rotate(90.0);
        graphics.strokeText("months", (weidth + start_x) / 2, start_y + 35);

        graphics.setFont(header_font);
        graphics.strokeText("average temperature in " + String.valueOf(2017 + year), (weidth + start_x) / 2, 20);
        graphics.setFont(last_font);

        for (int i = 0; i < 20; i++)
        {
            graphics.strokeText(String.valueOf(i), start_x - 15, start_y - single_value_height * i);
        }

        for (int i = 0; i < 12; i++) {
            if (temp_by_month[i][year] < min) {
                min = temp_by_month[i][year];
            }
            if (temp_by_month[i][year] > max) {
                max = temp_by_month[i][year];
            }
        }

        for (int i = 0; i < 12; i++) {
            current_value = temp_by_month[i][year];
            if (temp_by_month[i][year] == min)
            {
                canvas.getGraphicsContext2D().setFill(Color.BLUE);
            }
            else if (temp_by_month[i][year] == max)
            {
                canvas.getGraphicsContext2D().setFill(Color.RED);
            }
            else
            {
                canvas.getGraphicsContext2D().setFill(Color.GREY);
            }
            canvas.getGraphicsContext2D().fillRect(current_start_draw_pos, start_y - current_value * single_value_height, (start_y - start_x) / 12 / 2, current_value * single_value_height);
//            canvas.getGraphicsContext2D().fillRect(start_x  - current_value * single_value_height, start_y, (start_y - start_x) / 12 / 2, current_value * single_value_height);
//            graphics.strokeText(months[i], current_start_draw_pos, start_y + 20);
            graphics.strokeText(String.valueOf(i), current_start_draw_pos + 5, start_y + 20);
            current_start_draw_pos += (weidth - start_x) / 12;
        }

        year = (year + 1) % 5;

    }

//    void drawGraph() {
////        canvas.getWidth();
//        canvas.getGraphicsContext2D().setFill(Color.GREY);
//        canvas.getGraphicsContext2D().fillRect(50, 50, 200, 200);
//    }

}

//public class HelloController {
//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
//}