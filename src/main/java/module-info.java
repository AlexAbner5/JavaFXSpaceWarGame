module com.example.spacewar {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires javafx.media;

    opens com.example.spacewar to javafx.fxml;
    exports com.example.spacewar;
}