module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;

    opens model to javafx.base;
    opens org.openjfx to javafx.fxml;
    exports org.openjfx;
}
