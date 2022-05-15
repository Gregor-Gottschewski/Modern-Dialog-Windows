package com.gregorgott.malert;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.ArrayList;
import java.util.Objects;

/**
 * MAlert is a simple way to create a modern-looking error, warning and information Stages.
 * These Stages can contain a header, content text, buttons and an image.
 *
 * @author GregorGott
 * @version 0.0.2
 * @since 2022-05-15
 */
public class MAlert {
    private final MAlertType mAlertType;
    private final Stage stage;
    private final Scene scene;
    private final BorderPane borderPane;
    private final ArrayList<Button> buttonArrayList;
    private MAlertStyle mAlertStyle;
    private Window root;
    private Label headlineLabel;
    private Label contentTextLabel;
    private ImageView alertImageView;
    private String alertTitle, headline, contentText;

    /**
     * Sets the alert image to the suitable <code>MAlertType</code>. Defines a Scene and apply the <i>Classic</i> stylesheet.
     * Creates a not resizeable 310 by 190 pixels Stage.
     *
     * @param mAlertType the <code>MAlertType</code> to set the alert image.
     * @since 0.0.1
     */
    public MAlert(MAlertType mAlertType) {
        // Initialize variables
        buttonArrayList = new ArrayList<>();
        borderPane = new BorderPane();

        // Get the MAlertType to set image
        this.mAlertType = mAlertType;
        setAlertImage();

        scene = new Scene(borderPane);

        // Set the alert style to 'CLASSIC' and apply the stylesheet
        mAlertStyle = MAlertStyle.LIGHT_CLASSIC;
        setStylesheet();

        stage = new Stage();
        stage.setWidth(350);
        stage.setHeight(190);
        stage.setResizable(false);
        stage.setScene(scene);
    }

    /**
     * Calls the first constructor and sets the title to the given parameters.
     *
     * @param mAlertType the alert type to show the suitable icon in the Scene.
     * @param alertTitle the title of the Stage.
     * @since 0.0.1
     */
    public MAlert(MAlertType mAlertType, String alertTitle) {
        this(mAlertType);

        setAlertTitle(alertTitle);
    }

    /**
     * Calls the second constructor and sets the MAlertType, title and the parent Stage.
     *
     * @param mAlertType the alert type to show the suitable icon in the Scene.
     * @param alertTitle the title of the Stage.
     * @param root       the parent Stage.
     * @since 0.0.1
     */
    public MAlert(MAlertType mAlertType, String alertTitle, Window root) {
        this(mAlertType, alertTitle);
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
     * Changes the stylesheet from the Scene by getting the <code>mAlertType</code>.
     *
     * @since 0.0.1
     */
    private void setStylesheet() {
        switch (mAlertStyle) {
            case LIGHT_CLASSIC ->
                    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(
                            "stylesheets/stylesheet-light-classic.css")).toExternalForm());
            case LIGHT_ROUNDED ->
                    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(
                            "stylesheets/stylesheet-light-rounded.css")).toExternalForm());
            case DARK_CLASSIC ->
                    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(
                            "stylesheets/stylesheet-dark-classic.css")).toExternalForm());
            case DARK_ROUNDED ->
                    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(
                            "stylesheets/stylesheet-dark-rounded.css")).toExternalForm());
        }
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
        stage.setTitle(alertTitle);
    }

    /**
     * Sets the alert image shown in the Scene on the top.
     *
     * @param image the image to show on the top.
     * @since 0.0.1
     */
    public void setAlertImageView(Image image) {
        this.alertImageView = new ImageView(image);
        alertImageView.setFitWidth(50);
        alertImageView.setFitHeight(50);
    }

    /**
     * Sets the alert image to the defaults if no custom image is given.
     *
     * @since 0.0.1
     */
    private void setAlertImage() {
        switch (mAlertType) {
            case INFORMATION -> setAlertImageView(new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("images/information-image.png"))));
            case ERROR -> setAlertImageView(new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("images/warning-image.png"))));
            case CONFIRMATION -> setAlertImageView(new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("images/question-mark-image.png"))));
            case NONE -> setAlertImageView(null);
        }
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

        headlineLabel = new Label(headline);
        headlineLabel.setFont(new Font("Helvetica", 16));
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

        contentTextLabel = new Label(contentText);
        contentTextLabel.setWrapText(true);
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
     * Creates an HBox with the alert image and title and adds it to the border pane. Then it shows the stage.
     *
     * @since 0.0.1
     */
    public Stage getStage() {
        HBox topHBox = new HBox();
        topHBox.getChildren().addAll(alertImageView, headlineLabel);
        topHBox.setAlignment(Pos.CENTER_LEFT);
        topHBox.setSpacing(18);
        topHBox.setPadding(new Insets(5));
        topHBox.setId("header-box");

        borderPane.setPadding(new Insets(15, 15, 15, 15));
        borderPane.setTop(topHBox);
        borderPane.setCenter(contentTextLabel);

        createButtons();

        stage.initOwner(root);
        stage.initModality(Modality.WINDOW_MODAL);

        return stage;
    }

    /**
     * Creates a HBox with right alignment and add every button in <code>buttonArrayList</code>
     *
     * @since 0.0.1
     */
    private void createButtons() {
        HBox bottomHBox = new HBox();
        bottomHBox.setAlignment(Pos.CENTER_RIGHT);
        bottomHBox.setSpacing(10);

        for (Button button : buttonArrayList) {
            button.setMinWidth(60);
            bottomHBox.getChildren().add(button);
        }

        borderPane.setBottom(bottomHBox);
    }

    /**
     * Closes the Stage.
     * @since 0.0.1
     */
    public void closeStage() {
        stage.close();
    }

    public enum MAlertStyle {
        LIGHT_ROUNDED,
        LIGHT_CLASSIC,
        DARK_ROUNDED,
        DARK_CLASSIC
    }
}