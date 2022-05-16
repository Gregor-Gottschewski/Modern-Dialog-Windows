module com.gregorgott.mdialogwindows {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.gregorgott.mdialogwindows to javafx.fxml;
    exports com.gregorgott.mdialogwindows;
}