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
 * @version 0.2.0
 * @since 2022-06-11
 */
public class MAlert extends MDialogWindow {
    private final Stage stage;
    private final MAlertType mAlertType;

    /**
     * Initializes a Stage (dimensions: 350x190) and sets the alert image.
     *
     * @param mAlertType the alert type to set the alert image.
     */
    public MAlert(MAlertType mAlertType) {
        super(350, 190);

        stage = super.getStage();

        this.mAlertType = mAlertType;
        this.setAlertImage();
    }

    /**
     * Calls the first constructor and sets the Stage title.
     *
     * @param mAlertType the alert type to set the alert image.
     * @param title      the title of the Stage.
     */
    public MAlert(MAlertType mAlertType, String title) {
        this(mAlertType);

        // Set Stage title
        setAlertTitle(title);
    }

    /**
     * Calls the second constructor and sets the parent window to set the modality.
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
     * If no custom image is given sets the alert image to the defaults.
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
     * Creates an HBox with the alert image and title and adds it to the border pane.
     *
     * @since 0.2.0
     */
    private void setStage() {
        // Header with alert image
        Label headlineLabel = new Label(getHeadline());
        headlineLabel.setFont(new Font("Helvetica", 16));

        HBox topHBox = new HBox();
        topHBox.setAlignment(Pos.CENTER_LEFT);
        topHBox.setSpacing(18);
        topHBox.setPadding(new Insets(5));
        topHBox.setId("header-box");

        // Only add image to alert when it is not empty
        if (getAlertImageView().getImage() != null) {
            topHBox.getChildren().add(getAlertImageView());
        }

        topHBox.getChildren().add(headlineLabel);

        // Content text
        Label contentText = new Label(getContentText());
        contentText.setWrapText(true);

        // Border pane
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(15));
        borderPane.setCenter(contentText);
        borderPane.setBottom(getButtons(60, 10));

        if (getHeadline() != null) {
            borderPane.setTop(topHBox);
        }

        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add(getStylesheet(getMAlertStyle()));

        stage.setTitle(getAlertTitle());
        stage.setScene(scene);
    }

    /**
     * @return the Stage with all elements.
     * @since 0.1.0
     */
    public Stage getStage() {
        setStage();
        return stage;
    }

    /**
     * Sets the Stage and shows it.
     *
     * @since 0.2.0
     */
    public void show() {
        setStage();
        stage.show();
    }

    public enum MAlertType {
        ERROR,
        INFORMATION,
        CONFIRMATION,
        NONE
    }
}