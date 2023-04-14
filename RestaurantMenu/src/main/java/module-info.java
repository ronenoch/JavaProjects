module ronenoch.maman13.restaurantmenu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ronenoch.maman13.restaurantmenu to javafx.fxml;
    exports ronenoch.maman13.restaurantmenu;
}