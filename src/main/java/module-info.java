module com.example.cse360project3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cse360project3 to javafx.fxml;
    exports com.example.cse360project3;
}