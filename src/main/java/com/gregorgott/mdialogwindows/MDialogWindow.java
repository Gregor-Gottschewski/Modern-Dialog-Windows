package com.gregorgott.mdialogwindows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

/**
 * MDialogWindow is the parent class for all Modern-Dialog-Windows.
 * It controls the button actions, header and content text.
 *
 * @author GregorGott
 * @version 0.1.0
 * @since 2022-06-11
 */
public class MDialogWindow {
    private final Stage stage;
    private final ArrayList<Button> buttonArrayList;
    private ImageView alertImageView;
    private String alertTitle;
    private String headline;
    private String contentText;
    private MAlertStyle mAlertStyle;

    /**
     * Initializes the <code>buttonArrayList</code> which will contains all buttons and the Stage.
     *
     * @since 0.0.1
     */
    public MDialogWindow(int width, int height) {
        // Initialize variables
        buttonArrayList = new ArrayList<>();

        mAlertStyle = MAlertStyle.LIGHT_ROUNDED;

        stage = new Stage();

        // If the width or height is null, the size will be automatically set
        if (height > 0) {
            stage.setHeight(height);
        }
        if (width > 0) {
            stage.setWidth(width);
        }

        stage.setResizable(false);
    }

    /**
     * @return the Stage with all elements.
     * @since 0.1.0
     */
    public Stage getStage() {
        return stage;
    }

    public MAlertStyle getMAlertStyle() {
        return mAlertStyle;
    }

    /**
     * Set the alert style and switch the stylesheet.
     *
     * @param mAlertStyle The alert style.
     * @since 0.0.1
     */
    public void setMAlertStyle(MAlertStyle mAlertStyle) {
        this.mAlertStyle = mAlertStyle;
    }

    /**
     * @return The title of the alert.
     * @since 0.0.1
     */
    public String getAlertTitle() {
        return alertTitle;
    }

    /**
     * Sets the title of the Stage and sets the <code>alertTitle</code>.
     *
     * @param alertTitle the title of the Stage.
     * @since 0.0.1
     */
    public void setAlertTitle(String alertTitle) {
        this.alertTitle = alertTitle;
    }

    /**
     * @return the alert image if given.
     * @since 0.0.1
     */
    public ImageView getAlertImageView() {
        return alertImageView;
    }

    /**
     * Sets the alert image shown in the Scene on the top.
     *
     * @param image the image to show on the top.
     * @since 0.0.1
     */
    public void setAlertImage(Image image) {
        this.alertImageView = new ImageView(image);
        alertImageView.setFitWidth(50);
        alertImageView.setFitHeight(50);
    }

    /**
     * @return the headline from the alert.
     * @since 0.0.1
     */
    public String getHeadline() {
        return headline;
    }

    /**
     * Sets the headline by creating a label with the text size of 16 px.
     *
     * @param headline the headline.
     * @since 0.0.1
     */
    public void setHeadline(String headline) {
        this.headline = headline;
    }

    /**
     * @return the content text of the alert.
     * @since 0.0.1
     */
    public String getContentText() {
        return contentText;
    }

    /**
     * Sets the content text of the alert.
     *
     * @param contentText the content text of the alert.
     * @since 0.0.1
     */
    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    /**
     * @return All buttons in the <code>buttonArrayList</code> array list.
     * @since 0.0.1
     */
    public ArrayList<Button> getButtonArrayList() {
        return buttonArrayList;
    }

    /**
     * Adds a button to the <code>buttonArrayList</code> with a text and an <code>ActionEvent</code>.
     *
     * @param text          the button text.
     * @param onClickEvent  called action event when the button is pushed.
     * @param defaultButton boolean if the button is the default button.
     * @since 0.0.1
     */
    public void addButton(String text, EventHandler<ActionEvent> onClickEvent, boolean defaultButton) {
        Button button = new Button(text);
        button.setOnAction(onClickEvent);
        button.setDefaultButton(defaultButton);

        buttonArrayList.add(button);
    }

    /**
     * Creates a HBox with right alignment and add every button in <code>buttonArrayList</code>
     *
     * @since 0.0.1
     */
    public Node getButtons(int width, int spacing) {
        HBox bottomHBox = new HBox();
        bottomHBox.setAlignment(Pos.CENTER_RIGHT);
        bottomHBox.setSpacing(spacing);

        for (Button button : buttonArrayList) {
            button.setMinWidth(width);
            bottomHBox.getChildren().add(button);
        }

        return bottomHBox;
    }

    /**
     * Changes the stylesheet from the Scene by getting the <code>mAlertType</code>.
     *
     * @since 0.0.3
     */
    public String getStylesheet(MAlertStyle mAlertStyle) {
        String css = null;

        switch (mAlertStyle) {
            case LIGHT_CLASSIC -> css = Objects.requireNonNull(getClass().getResource(
                    "stylesheets/stylesheet-light-classic.css")).toExternalForm();
            case LIGHT_ROUNDED -> css = Objects.requireNonNull(getClass().getResource(
                    "stylesheets/stylesheet-light-rounded.css")).toExternalForm();
            case DARK_CLASSIC -> css = Objects.requireNonNull(getClass().getResource(
                    "stylesheets/stylesheet-dark-classic.css")).toExternalForm();
            case DARK_ROUNDED -> css = Objects.requireNonNull(getClass().getResource(
                    "stylesheets/stylesheet-dark-rounded.css")).toExternalForm();
        }

        return css;
    }

    /**
     * Closes the Stage.
     *
     * @since 0.0.1
     */
    public void closeAlert() {
        getStage().close();
    }

    public enum MAlertStyle {
        LIGHT_CLASSIC,
        LIGHT_ROUNDED,
        DARK_CLASSIC,
        DARK_ROUNDED
    }
}