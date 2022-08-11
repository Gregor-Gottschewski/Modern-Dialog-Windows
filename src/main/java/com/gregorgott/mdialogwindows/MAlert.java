package com.gregorgott.mdialogwindows;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Window;

import java.util.Objects;

/**
 * The MAlert is a Stage with buttons, title, content text and info text.
 * When a <code>MAlertType</code> is given it also shows an icon to the corresponding <code>MAlertType</code>
 * on the top.
 *
 * @author GregorGott
 * @version 1.0.0
 * @since 2022-07-17
 */
public class MAlert extends MDialogWindow {
    private final MAlertType mAlertType;
    private String infoText;

    public MAlert(MAlertType mAlertType) {
        this(mAlertType, null, null);
    }

    public MAlert(MAlertType mAlertType, String title) {
        this(mAlertType, title, null);
    }

    /**
     * Initializes a <code>Stage</code> (dimensions: 350x190), sets the alert image, title and root window.
     *
     * @param mAlertType the alert type to set the alert image.
     * @param title      the title of the Stage.
     * @param root       the parent window.
     */
    public MAlert(MAlertType mAlertType, String title, Window root) {
        super(350, 190, root);

        this.mAlertType = mAlertType;
        this.setMAlertImage();

        setAlertTitle(title);
    }

    /**
     * If no custom image is given sets the alert image to the defaults.
     *
     * @since 0.0.1
     */
    private void setMAlertImage() {
        switch (mAlertType) {
            case INFORMATION -> setAlertImage(new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("images/information-image.png"))));
            case ERROR -> setAlertImage(new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("images/warning-image.png"))));
            case CONFIRMATION -> setAlertImage(new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("images/question-mark-image.png"))));
            case NONE -> setAlertImage(null);
        }
    }

    /**
     * @return the info text.
     * @since 1.0.0
     */
    public String getInfoText() {
        return infoText;
    }

    /**
     * Sets the info text which is shown in the centre of the alert.
     *
     * @param infoText info text as string.
     * @since 1.0.0
     */
    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    /**
     * Sets the <code>infoText</code> as the centre of the <code>BorderPane</code> and sets top header and bottom
     * buttons.
     *
     * @since 0.2.0
     */
    private void setStage() {
        Label infoTextLabel = new Label(infoText);
        infoTextLabel.setWrapText(true);

        getBorderPane().setTop(getHeader());
        getBorderPane().setCenter(infoTextLabel);
        getBorderPane().setBottom(getButtons(60, 10));

        Scene scene = new Scene(getBorderPane());
        scene.getStylesheets().add(getStylesheet(getMAlertStyle()));

        getStage().setScene(scene);
    }

    /**
     * Sets the Stage and shows it.
     *
     * @since 0.2.0
     */
    public void show() {
        setStage();
        getStage().show();
    }

    public enum MAlertType {
        ERROR,
        INFORMATION,
        CONFIRMATION,
        NONE
    }
}
