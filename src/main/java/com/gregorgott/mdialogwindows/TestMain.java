package com.gregorgott.mdialogwindows;

import javafx.application.Application;
import javafx.stage.Stage;

public class TestMain extends Application {
    @Override
    public void start(Stage stage) {
        MAlert mAlert = new MAlert(MAlert.MAlertType.INFORMATION, "Hello");
        mAlert.setAlertStyle(MAlert.MAlertStyle.DARK_ROUNDED);
        mAlert.setHeadline("My Headline");
        mAlert.setContentText("This is a beautiful content message.");
        mAlert.addButton("Close", x -> mAlert.closeAlert(), true);
        Stage stage1 = mAlert.getStage();
        stage1.showAndWait();

        MTextFieldAlert mTextFieldAlert = new MTextFieldAlert("Cool to see you!");
        mTextFieldAlert.setHeadline("Very important question.");
        mTextFieldAlert.setContentText("Which Pizza do you love?:");
        mTextFieldAlert.setAlertStyle(MTextFieldAlert.MAlertStyle.LIGHT_ROUNDED);
        mTextFieldAlert.addButton("Send", x -> {
            System.out.println(mTextFieldAlert.getText());
            mTextFieldAlert.closeAlert();
        }, true);
        Stage stage2 = mTextFieldAlert.getStage();
        stage2.showAndWait();
    }
}
