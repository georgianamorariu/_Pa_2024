module org.example.comp6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.comp6 to javafx.fxml;
    exports org.example.comp6;
}