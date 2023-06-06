import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DrawController {

    @FXML
    private Canvas canvas;

    private static int year = 0;

    private int tempByMonth[][] = {
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
    /**
     *  triggers when clicking the button to draw, and draws a graph of temperature by month.
     */
    protected void onDrawButtonClick() {
        int startX = 40;
        int startY = (int) canvas.getHeight() - 40;
        int width = (int) canvas.getWidth() - 40;

        int singleValueHeight = (startY - startX) / 15;
        int currentStartDrawPos = (width + startX) / 12;
        int currentTemperature;
        int min = 100000;
        int max = 0;

        canvas.getGraphicsContext2D()
                .clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        GraphicsContext graphics = canvas.getGraphicsContext2D();
        graphics.strokeLine(startX, startY, width, startY);
        graphics.strokeLine(startX, startY, startX, startX);

        Font header_font = new Font(20);
        Font coordinate_font = new Font(16);
        Font last_font = graphics.getFont();

        graphics.setFont(coordinate_font);
        /* adding description texts */
        graphics.rotate(-90.0);
        graphics.strokeText("Temperature", -(startY + startX) / 2, 20);
        graphics.rotate(90.0);
        graphics.strokeText("months", (width + startX) / 2, startY + 35);

        graphics.setFont(header_font);
        graphics.strokeText(
                "average temperature in " + String.valueOf(2017 + year),
                (width + startX) / 2,
                20);
        graphics.setFont(last_font);

        for (int i = 0; i < 15; i++) {
            graphics.strokeText(String.valueOf(i), startX - 15, startY - singleValueHeight * i);
        }

        for (int i = 0; i < 12; i++) {
            if (tempByMonth[i][year] < min) {
                min = tempByMonth[i][year];
            }
            if (tempByMonth[i][year] > max) {
                max = tempByMonth[i][year];
            }
        }

        for (int i = 0; i < 12; i++) {
            currentTemperature = tempByMonth[i][year];
            if (tempByMonth[i][year] == min) {
                canvas.getGraphicsContext2D().setFill(Color.BLUE);
            } else if (tempByMonth[i][year] == max) {
                canvas.getGraphicsContext2D().setFill(Color.RED);
            } else {
                canvas.getGraphicsContext2D().setFill(Color.GREY);
            }
            canvas.getGraphicsContext2D().fillRect(
                    currentStartDrawPos,
                    startY - currentTemperature * singleValueHeight,
                    (startY - startX) / 12 / 2,
                    currentTemperature * singleValueHeight);

            graphics.strokeText(String.valueOf(i), currentStartDrawPos + 5, startY + 20);
            currentStartDrawPos += (width - startX) / 12;
        }

        year = (year + 1) % 5;

    }

}
