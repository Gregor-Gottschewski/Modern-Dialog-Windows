module com.gregorgott.malert {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.gregorgott.malert to javafx.fxml;
    exports com.gregorgott.malert;
}