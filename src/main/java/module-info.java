module com.example.poczta {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.poczta to javafx.fxml;
    exports com.example.poczta;
}