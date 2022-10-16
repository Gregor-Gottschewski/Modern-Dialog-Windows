package com.gregorgott.mdialogwindows;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Window;

import java.util.Objects;

/**
 * The MAlert is an alert with buttons, title, content text and info text.
 * It also shows a corresponding alert image to the {@code MAlertType} when a <code>MAlertType</code> is given.
 *
 * <p> You have three layers of information fo the user: title, headline, second headline and information.
 * You can use these layers that way:
 * <ul>
 *     <li><b>title</b>: What's the content of the alert? - "Error", "File Corrupt" or "License Information"</li>
 *     <li><b>headline</b>: Main information/question. - "Error while reading file" or "Do you want to continue?"</li>
 *     <li><b>second headline</b>: Error code or second information. - "Error E404" or "This action can not be undo."</li>
 *     <li><b>information</b>: How can the user solve the problem or what happens if button pushed?. - "Send log to admin."</li>
 * </ul>
 *
 * <p> The {@code MAlert} class only has one setter method. It is the {@code setInfoText()} method to set the
 * information label. To add a button the {@code MDialogWindow}s {@code addButton()} is used:
 *
 * <pre>
 *     MAlert alert = new MAlert(MAlertType.INFORMATION, "First Alert"); // information alert with "First Alert" as title
 *     alert.setHeadline("Hello World!"); // MDialogWindow method
 *     alert.setContentText("This is my first alert!"); // MDialogWindow method
 *     alert.setInfoText("It is simple, but looks great."); // sets the information text
 *     alert.addButton("Let's start", x -> System.out.println("Start"), true); // MDialogWindow method
 *     alert.show(); // MDialogWindow method
 * </pre>
 *
 * @author GregorGott
 * @version 1.1.0
 * @since 2022-10-16 (YYYY-MM-DD)
 */
public class MAlert extends MDialogWindow {
    private final Label infoTextLabel;

    /**
     * Creates a {@code MAlert} with a {@code MAlertType}, but without title and owner.
     *
     * @param mAlertType The {@code MAlertType}.
     * @since 1.0.0
     */
    public MAlert(MAlertType mAlertType) {
        this(mAlertType, null, null);
    }

    /**
     * Creates a {@code MAlert} with a {@code MAlertType} and title, but without an owner.
     *
     * @param mAlertType The {@code MAlertType}.
     * @param title      The title of the alert.
     * @since 1.0.0
     */
    public MAlert(MAlertType mAlertType, String title) {
        this(mAlertType, title, null);
    }

    /**
     * Creates a {@code MAlert} with a {@code MAlertType}, title and owner.
     *
     * @param mAlertType The alert type to set the alert image.
     * @param title      The title of the Stage.
     * @param root       The parent window.
     * @since 1.0.0
     */
    public MAlert(MAlertType mAlertType, String title, Window root) {
        super(350, 190, title, root);

        switch (mAlertType) {
            case INFORMATION -> setAlertImage(new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("images/information-image.png"))));
            case ERROR -> setAlertImage(new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("images/warning-image.png"))));
            case CONFIRMATION -> setAlertImage(new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("images/question-mark-image.png"))));
            case NONE -> setAlertImage(null);
        }

        infoTextLabel = new Label();
        infoTextLabel.setWrapText(true);
        getBorderPane().setCenter(infoTextLabel);
        setButtonSpacing(10);
    }

    /**
     * @return The information text.
     * @since 1.0.0
     */
    public String getInfoText() {
        return infoTextLabel.getText();
    }

    /**
     * Sets the {@code infoTextLabel} which is shown in the center of the alert.
     *
     * @param infoText The information text.
     * @since 1.0.0
     */
    public void setInfoText(String infoText) {
        infoTextLabel.setText(infoText);
    }

    public enum MAlertType {
        ERROR,
        INFORMATION,
        CONFIRMATION,
        NONE
    }
}
