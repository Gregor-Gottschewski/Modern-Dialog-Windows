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

import java.util.Objects;

/**
 * The MTextFieldAlert is text input alert with customized buttons, title, header and content text.
 * It can return the entered text.
 *
 * @author GregorGott
 * @version 0.0.3
 * @since 2022-06-08
 */
public class MTextFieldAlert extends MDialogWindow {
    private final Stage stage;
    private final Scene scene;
    private final BorderPane borderPane;
    private final TextField textField;
    private Window root;
    private MAlertStyle mAlertStyle;

    /**
     * Initializes a Stage (dimensions: 350x190), which contains a border pane, sets the alert image and
     * initializes a text field.
     *
     * @since 0.0.1
     */
    public MTextFieldAlert() {
        super();

        borderPane = new BorderPane();

        textField = new TextField();
        textField.setMinWidth(150);

        scene = new Scene(borderPane);

        setAlertStyle(MAlertStyle.LIGHT_ROUNDED);

        stage = new Stage();
        stage.setWidth(350);
        stage.setHeight(190);
        stage.setResizable(false);
        stage.setScene(scene);
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

        this.root = root;
    }

    /**
     * Set the alert style and switch the stylesheet.
     *
     * @param mAlertStyle The alert style.
     * @since 0.0.1
     */
    public void setAlertStyle(MAlertStyle mAlertStyle) {
        this.mAlertStyle = mAlertStyle;

        setStylesheet();
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

    /**
     * Changes the stylesheet from the Scene by getting the <code>mAlertType</code>.
     *
     * @since 0.0.1
     */
    private void setStylesheet() {
        switch (mAlertStyle) {
            case LIGHT_CLASSIC -> scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(
                    "stylesheets/stylesheet-light-classic.css")).toExternalForm());
            case LIGHT_ROUNDED -> scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(
                    "stylesheets/stylesheet-light-rounded.css")).toExternalForm());
            case DARK_CLASSIC -> scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(
                    "stylesheets/stylesheet-dark-classic.css")).toExternalForm());
            case DARK_ROUNDED -> scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(
                    "stylesheets/stylesheet-dark-rounded.css")).toExternalForm());
        }
    }

    /**
     * Creates an HBox with the alert image and title and adds it to the border pane. Then it shows the stage.
     *
     * @since 0.0.1
     */
    public Stage getStage() {
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

        borderPane.setPadding(new Insets(15));
        borderPane.setCenter(textFieldHBox);
        borderPane.setBottom(getButtons(60, 10));

        if (getHeadline() != null) {
            borderPane.setTop(headerLabel);
        }

        stage.setTitle(getAlertTitle());
        stage.setScene(scene);
        stage.initOwner(root);
        stage.initModality(Modality.WINDOW_MODAL);

        return stage;
    }

    /**
     * Closes the Stage.
     *
     * @since 0.0.1
     */
    public void closeAlert() {
        stage.close();
    }

    public enum MAlertStyle {
        LIGHT_ROUNDED,
        LIGHT_CLASSIC,
        DARK_ROUNDED,
        DARK_CLASSIC
    }
}