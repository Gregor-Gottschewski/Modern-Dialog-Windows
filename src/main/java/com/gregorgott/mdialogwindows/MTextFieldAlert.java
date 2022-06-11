package com.gregorgott.mdialogwindows;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * The MTextFieldAlert is text input alert with customized buttons, title, header and content text.
 * It can return the entered text.
 *
 * @author GregorGott
 * @version 0.1.0
 * @since 2022-06-08
 */
public class MTextFieldAlert extends MDialogWindow {
    private final Stage stage;
    private final TextField textField;

    /**
     * Initializes a Stage (dimensions: 350x190), which contains a border pane, sets the alert image and
     * initializes a text field.
     *
     * @since 0.0.1
     */
    public MTextFieldAlert() {
        super(350, 190);

        textField = new TextField();
        textField.setMinWidth(150);

        stage = super.getStage();
    }

    /**
     * Calls the first constructor and sets the Stage title.
     *
     * @param title the title of the Stage.
     * @since 0.0.1
     */
    public MTextFieldAlert(String title) {
        this();

        setAlertTitle(title);
    }

    /**
     * Calls the second constructor and sets the parent window.
     *
     * @param text the title of the Stage.
     * @param root the parent window.
     * @since 0.0.1
     */
    public MTextFieldAlert(String text, Window root) {
        this(text);

        stage.initOwner(root);
        stage.initModality(Modality.WINDOW_MODAL);
    }

    /**
     * @return the text field.
     * @since 0.0.1
     */
    public TextField getTextField() {
        return textField;
    }

    /**
     * @return the text in the text field as String.
     * @since 0.0.1
     */
    public String getText() {
        return textField.getText();
    }

    public Stage getStage() {
        setStage();
        return stage;
    }

    /**
     * Creates an HBox with the alert image and title and adds it to the border pane. Then it shows the stage.
     *
     * @since 0.0.1
     */
    private void setStage() {
        Label headerLabel = new Label(getHeadline());
        headerLabel.setFont(new Font("Helvetica", 16));
        headerLabel.setAlignment(Pos.CENTER);
        headerLabel.setPrefWidth(Integer.MAX_VALUE);
        headerLabel.setPadding(new Insets(10));
        headerLabel.setId("header-box");

        Label contentText = new Label(getContentText());
        contentText.setWrapText(true);

        HBox textFieldHBox = new HBox();
        textFieldHBox.setSpacing(5);
        textFieldHBox.setAlignment(Pos.CENTER);

        if (getContentText() != null) {
            textFieldHBox.getChildren().add(contentText);
        }
        textFieldHBox.getChildren().add(textField);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(15));
        borderPane.setCenter(textFieldHBox);
        borderPane.setBottom(getButtons(60, 10));

        if (getHeadline() != null) {
            borderPane.setTop(headerLabel);
        }

        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add(getStylesheet(getMAlertStyle()));

        stage.setTitle(getAlertTitle());
        stage.setScene(scene);
    }

    /**
     * Sets the Stage and shows it.
     *
     * @since 0.1.0
     */
    public void show() {
        setStage();
        stage.show();
    }
}