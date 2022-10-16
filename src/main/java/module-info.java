module com.gregorgott.mdialogwindows {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens com.gregorgott.mdialogwindows to javafx.fxml;
    exports com.gregorgott.mdialogwindows;
}