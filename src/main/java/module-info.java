module org.example.simple_pos_mvc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires jdk.jdi;


    opens org.example.simple_pos_mvc.Controller to javafx.fxml;
    opens org.example.simple_pos_mvc.DTO.TM to javafx.base;
    exports org.example.simple_pos_mvc;
}