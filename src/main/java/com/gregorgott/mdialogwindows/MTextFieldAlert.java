package com.gregorgott.mdialogwindows;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Window;

/**
 * The {@code MTextFieldAlert} is a text input alert with a {@code TextField}.
 * The content of the text field is returned by the {@code getText()} method.
 *
 * @author GregorGott
 * @version X.X.X
 * @since XXXX-XX-XX (YYYY-MM-DD)
 */
public class MTextFieldAlert extends MDialogWindow {
    private final TextField textField;
    private final Label infoTextLabel;

    /**
     * Creates a {@code MTextFieldAlert} without title and owner.
     *
     * @since 1.0.0
     */
    public MTextFieldAlert() {
        this(null, null);
    }


    /**
     * Creates a {@code MTextFieldAlert} with a title, but without an owner.
     *
     * @param title The title.
     * @since 1.0.0
     */
    public MTextFieldAlert(String title) {
        this(title, null);
    }

    /**
     * Creates a {@code MTextFieldAlert} with a title and owner.
     *
     * @param title The title.
     * @param root  The owner window.
     * @since 1.0.0
     */
    public MTextFieldAlert(String title, Window root) {
        super(350, 190, title, root);

        infoTextLabel = new Label();
        infoTextLabel.setWrapText(true);

        textField = new TextField();
        textField.setMinWidth(150);

        HBox centerHBox = new HBox();
        centerHBox.setSpacing(5);
        centerHBox.setAlignment(Pos.CENTER);
        centerHBox.getChildren().addAll(infoTextLabel, textField);
        HBox.setHgrow(textField, Priority.ALWAYS);

        setButtonSpacing(10);
        getBorderPane().setCenter(centerHBox);
    }

    /**
     * @return The {@code infoTextLabel} text.
     * @since 1.0.0
     */
    public String getInfoText() {
        return infoTextLabel.getText();
    }

    /**
     * Sets the {@code infoTextLabel}, which is shown beside the <code>TextField</code>.
     *
     * @param infoText The information text.
     * @since 1.0.0
     */
    public void setInfoText(String infoText) {
        infoTextLabel.setText(infoText);
    }

    /**
     * @return The {@code textField} to e.g. change the prompt text.
     * @since 1.0.0
     */
    public TextField getTextField() {
        return textField;
    }

    /**
     * @return The text in the {@code textField}.
     * @since 1.0.0
     */
    public String getText() {
        return textField.getText();
    }
}
