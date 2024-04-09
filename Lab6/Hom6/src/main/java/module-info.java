module org.example.hom6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;


    opens org.example.hom6 to javafx.fxml;
    exports org.example.hom6;
}