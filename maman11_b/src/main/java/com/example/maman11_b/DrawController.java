package com.example.maman11_b;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
//
//package com.avivn.mmn11.q2;

//import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

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

    final static String austria = "Austria";
    final static String brazil = "Brazil";
    final static String france = "France";
    final static String italy = "Italy";
    final static String usa = "USA";

    private int temp_by_month[][] = {
            {5, 5, 5, 5, 6},
            {4, 5, 5, 6, 6},
            {2, 5, 7, 5, 9},
            {6, 7, 5, 3, 7},
            {5, 8, 5, 5, 8},
            {2, 4, 5, 3, 9},
            {5, 8, 5, 5, 6},
            {1, 1, 3, 5, 3},
            {3, 7, 5, 3, 4},
            {4, 5, 4, 3, 1},
            {5, 5, 5, 5, 2},
            {7, 5, 5, 3, 5},
    };
    private String months[] = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};

    @FXML
    // triggers when clicking the button to draw, and draws 10 random shapes.
    protected void onDrawButtonClick() {
        // remove all existing drawings first
        int start_x = 30;
        int start_y = 460;

        int single_value_height = (start_y - start_x) / 20;
        int current_start_draw_pos = (start_y + start_x) / 12;
//        int current_y = (start_y - start_x) / 12 / 2;
        int current_value;
        int min = 100000;
        int max = 0;

        canvas.getGraphicsContext2D()
                .clearRect(0,0,canvas.getWidth(), canvas.getHeight());
        // draw N_SHAPES_TO_GEN random shapes
//        for (int i = 0; i < N_SHAPES_TO_GEN; i++) {
//            drawRandomShape();
//        }

        GraphicsContext graphics = canvas.getGraphicsContext2D();
        graphics.strokeLine(start_x, start_y, start_y, start_y);
        graphics.strokeLine(start_x, start_y, start_x, start_x);

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
//          drawRandomShape();
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
            graphics.strokeText(months[i], current_start_draw_pos, start_y + 20);
            current_start_draw_pos += (start_y - start_x) / 12;
        }

//        drawRect();
        year = (year + 1) % 5;
//        draw_graph_example();
    }
    @FXML
    void drawRect() {
        canvas.getGraphicsContext2D().setFill(Color.RED);
        canvas.getGraphicsContext2D().fillRect(50, 50, 200, 200);
    }

    void drawGraph() {
//        canvas.getWidth();
        canvas.getGraphicsContext2D().setFill(Color.GREY);
        canvas.getGraphicsContext2D().fillRect(50, 50, 200, 200);
    }

    // Draws a single random shape
    private void drawRandomShape() {
        GraphicsContext graphics = canvas.getGraphicsContext2D();

        // generate y/x positions & sizes (at most 25% of the height/width)
        double xPos = generator.nextDouble(0, canvas.getWidth());
        double yPos = generator.nextDouble(0, canvas.getHeight());

        double width = generator.nextDouble(1, canvas.getWidth() / 4);
        double height = generator.nextDouble(1, canvas.getHeight() / 4);

        // generate a random color (random RGB 0-1 scales in the color constructor)
        graphics.setStroke(Color.color(
                generator.nextDouble(),
                generator.nextDouble(),
                generator.nextDouble()));

        // pick a random shape - switch random 0/1/2
        switch (generator.nextInt(3)) {
            case 0 -> graphics.strokeOval(xPos, yPos, width, height);
            case 1 -> graphics.strokeLine(xPos, yPos, width, height);
            case 2 -> graphics.strokeRect(xPos, yPos, width, height);
        }
    }

//    private void draw_graph_example()
//    {
//
////        stage.setTitle("Bar Chart Sample");
//        final CategoryAxis xAxis = new CategoryAxis();
//        final NumberAxis yAxis = new NumberAxis();
//        final BarChart<String,Number> bc =
//                new BarChart<String,Number>(xAxis,yAxis);
//        bc.setTitle("Country Summary");
//        xAxis.setLabel("Country");
//        yAxis.setLabel("Value");
//
//        XYChart.Series series1 = new XYChart.Series();
//        series1.setName("2003");
//        series1.getData().add(new XYChart.Data(austria, 25601.34));
//        series1.getData().add(new XYChart.Data(brazil, 20148.82));
//        series1.getData().add(new XYChart.Data(france, 10000));
//        series1.getData().add(new XYChart.Data(italy, 35407.15));
//        series1.getData().add(new XYChart.Data(usa, 12000));
//
//        XYChart.Series series2 = new XYChart.Series();
//        series2.setName("2004");
//        series2.getData().add(new XYChart.Data(austria, 57401.85));
//        series2.getData().add(new XYChart.Data(brazil, 41941.19));
//        series2.getData().add(new XYChart.Data(france, 45263.37));
//        series2.getData().add(new XYChart.Data(italy, 117320.16));
//        series2.getData().add(new XYChart.Data(usa, 14845.27));
//
//        XYChart.Series series3 = new XYChart.Series();
//        series3.setName("2005");
//        series3.getData().add(new XYChart.Data(austria, 45000.65));
//        series3.getData().add(new XYChart.Data(brazil, 44835.76));
//        series3.getData().add(new XYChart.Data(france, 18722.18));
//        series3.getData().add(new XYChart.Data(italy, 17557.31));
//        series3.getData().add(new XYChart.Data(usa, 92633.68));
//
////        Scene scene  = new Scene(bc,800,600);
////        bc.getData().addAll(series1, series2, series3);
////        stage.setScene(scene);
////        stage.show();
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