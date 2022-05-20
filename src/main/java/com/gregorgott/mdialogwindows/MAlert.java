package com.gregorgott.mdialogwindows;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.Objects;

/**
 * The MAlert is a Stage with customized buttons, title and content text.
 * When a <code>MAlertType</code> is chosen it also shows a small icon to the corresponding <code>MAlertType</code>.
 *
 * @author GregorGott
 * @version 0.1.1
 * @since 2022-05-20
 */
public class MAlert extends MDialogWindow {
    private final Stage stage;
    private final Scene scene;
    private final BorderPane borderPane;
    private final MAlertType mAlertType;
    private MAlertStyle mAlertStyle;

    /**
     * Initializes a Stage (dimensions: 350x190), which contains a border pane and sets the alert image.
     *
     * @param mAlertType the alert type to set the alert image.
     */
    public MAlert(MAlertType mAlertType) {
        super();

        this.mAlertType = mAlertType;
        this.setAlertImage();

        mAlertStyle = MAlertStyle.LIGHT_ROUNDED;

        borderPane = new BorderPane();

        scene = new Scene(borderPane);

        stage = new Stage();
        stage.setWidth(350);
        stage.setHeight(190);
        stage.setResizable(false);
        stage.setScene(scene);
    }

    /**
     * Calls the first constructor and sets the Stage title.
     *
     * @param mAlertType the alert type to set the alert image.
     * @param text       the title of the Stage.
     */
    public MAlert(MAlertType mAlertType, String text) {
        this(mAlertType);

        // Set Stage title
        setAlertTitle(text);
    }

    /**
     * Calls the second constructor and sets the parent window to set the <code>initOwner</code>.
     *
     * @param mAlertType the alert type to set the alert image.
     * @param text       the title of the Stage.
     * @param root       the parent window.
     */
    public MAlert(MAlertType mAlertType, String text, Window root) {
        this(mAlertType, text);

        // Set parent window
        stage.initOwner(root);
        stage.initModality(Modality.WINDOW_MODAL);
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
     * Sets the alert image to the defaults if no custom image is given.
     *
     * @since 0.0.1
     */
    private void setAlertImage() {
        switch (mAlertType) {
            case INFORMATION -> super.setAlertImage(new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("images/information-image.png"))));
            case ERROR -> super.setAlertImage(new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("images/warning-image.png"))));
            case CONFIRMATION -> super.setAlertImage(new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("images/question-mark-image.png"))));
            case NONE -> super.setAlertImage(null);
        }
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
        // Header with alert image
        Label headlineLabel = new Label(getHeadline());
        headlineLabel.setFont(new Font("Helvetica", 16));

        HBox topHBox = new HBox();
        topHBox.getChildren().addAll(getAlertImageView(), headlineLabel);
        topHBox.setAlignment(Pos.CENTER_LEFT);
        topHBox.setSpacing(18);
        topHBox.setPadding(new Insets(5));
        topHBox.setId("header-box");

        // Content text
        Label contentText = new Label(getContentText());
        contentText.setWrapText(true);

        // Border pane
        borderPane.setPadding(new Insets(15));
        borderPane.setTop(topHBox);
        borderPane.setCenter(contentText);
        borderPane.setBottom(getButtons(60, 10));

        stage.setTitle(getAlertTitle());
        stage.setScene(scene);

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

    public enum MAlertType {
        ERROR,
        INFORMATION,
        CONFIRMATION,
        NONE
    }
}