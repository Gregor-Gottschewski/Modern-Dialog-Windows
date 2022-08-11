package com.gregorgott.mdialogwindows;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Window;

/**
 * The MTextFieldAlert is text input alert with customized buttons, title, header and content text.
 * It can return the entered text.
 *
 * @author GregorGott
 * @version 1.0.0
 * @since 2022-07-17
 */
public class MTextFieldAlert extends MDialogWindow {
    private final TextField textField;
    private String infoText;

    public MTextFieldAlert() {
        this(null, null);
    }

    public MTextFieldAlert(String title) {
        this(title, null);
    }

    /**
     * Calls the superclass with the dimensions 350x190, initializes a <code>TextField</code>, sets the
     * <code>Stage</code> parent window and the title.
     *
     * @param title the title of the Stage.
     * @param root  the parent window.
     * @since 0.0.1
     */
    public MTextFieldAlert(String title, Window root) {
        super(350, 190, root);

        textField = new TextField();
        textField.setMinWidth(150);

        setAlertTitle(title);
    }

    /**
     * @return the info text as string.
     * @since 1.0.0
     */
    public String getInfoText() {
        return infoText;
    }

    /**
     * Sets the info text, which is shown beside the <code>TextField</code>.
     * @param infoText the info text.
     * @since 1.0.0
     */
    public void setInfoText(String infoText) {
        this.infoText = infoText;
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
     * Gets the superclass <code>BorderPane</code> and sets the header as top and an HBox with the info text (if given)
     * and an <code>TextField</code> as center.
     *
     * @since 0.0.1
     */
    private void setStage() {
        Label infoTextLabel = new Label(infoText);
        infoTextLabel.setWrapText(true);

        HBox centerHBox = new HBox();
        centerHBox.setSpacing(5);
        centerHBox.setAlignment(Pos.CENTER);

        if (infoText != null) {
            centerHBox.getChildren().add(infoTextLabel);
        }
        centerHBox.getChildren().add(textField);
        HBox.setHgrow(textField, Priority.ALWAYS);

        getBorderPane().setTop(getHeader());
        getBorderPane().setCenter(centerHBox);
        getBorderPane().setBottom(getButtons(60, 10));

        Scene scene = new Scene(getBorderPane());
        scene.getStylesheets().add(getStylesheet(getMAlertStyle()));

        getStage().setScene(scene);
    }

    /**
     * Sets the Stage and shows it.
     *
     * @since 0.1.0
     */
    public void show() {
        setStage();
        getStage().show();
    }
}
