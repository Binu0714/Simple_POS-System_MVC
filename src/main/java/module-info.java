module org.example.simple_pos_mvc {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.simple_pos_mvc to javafx.fxml;
    exports org.example.simple_pos_mvc;
}