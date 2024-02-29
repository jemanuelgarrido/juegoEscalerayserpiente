module com.example.parcial13 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.parcial13 to javafx.fxml;
    exports com.example.parcial13;
}