package com.gregorgott.mdialogwindows;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.Objects;

/**
 * MWelcomeAlert is an alert which can show e.g. the latest features of an app in widgets.
 * These widgets contain an image, header and content text.
 *
 * @author GregorGott
 * @version 0.0.3
 * @since 2022-06-08
 */
public class MWelcomeAlert extends MDialogWindow {
    private final Stage stage;
    private final Scene scene;
    private final BorderPane borderPane;
    private final VBox centerVBox;
    private Window root;
    private MAlertStyle mAlertStyle;

    /**
     * Calls the parent constructor, sets the <code>MAlertStyle</code> to defaults (<code>LIGHT_ROUNDED</code>),
     * initializes objects and sets the Stage with the dimension 400x350.
     *
     * @since 0.0.1
     */
    public MWelcomeAlert() {
        super();

        // VBox contains all widgets
        centerVBox = new VBox();

        // Scroll pane contains the v box
        ScrollPane widgetScrollPane = new ScrollPane(centerVBox);
        widgetScrollPane.setFitToWidth(true);
        widgetScrollPane.setPadding(new Insets(5));

        borderPane = new BorderPane();
        borderPane.setCenter(widgetScrollPane);

        scene = new Scene(borderPane);

        setAlertStyle(MAlertStyle.LIGHT_ROUNDED);

        stage = new Stage();
        stage.setWidth(350);
        stage.setHeight(400);
        stage.setResizable(false);
        stage.setScene(scene);
    }

    /**
     * Calls the first constructor and sets the Stage title.
     *
     * @param title the title of the Stage.
     * @since 0.0.1
     */
    public MWelcomeAlert(String title) {
        this();

        super.setAlertTitle(title);
    }

    /**
     * Calls the second constructor and sets the parent window.
     *
     * @param text the title of the Stage.
     * @param root the parent window.
     * @since 0.0.1
     */
    public MWelcomeAlert(String text, Window root) {
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
     * Changes the stylesheet from the Scene by getting the <code>mAlertType</code>.
     *
     * @since 0.0.1
     */
    private void setStylesheet() {
        switch (mAlertStyle) {
            case LIGHT_ROUNDED -> scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(
                    "stylesheets/stylesheet-light-rounded.css")).toExternalForm());
            case DARK_ROUNDED -> scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(
                    "stylesheets/stylesheet-dark-rounded.css")).toExternalForm());
            case LIGHT_CLASSIC -> scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(
                    "stylesheets/stylesheet-light-classic.css")).toExternalForm());
            case DARK_CLASSIC -> scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(
                    "stylesheets/stylesheet-dark-classic.css")).toExternalForm());
        }
    }

    /**
     * A widget contains an image, headline and a small info text. New features can be described here.
     *
     * @param image    an 60x60 image.
     * @param headline a headline of the widget.
     * @param infoText a small info text (with text wrap).
     * @since 0.0.1
     */
    public void addWidget(Image image, String headline, String infoText) {
        Label headlineLabel = new Label(headline);
        headlineLabel.setFont(new Font("Helvetica", 15));

        Label infoLabel = new Label(infoText);
        infoLabel.setWrapText(true);

        VBox vBox = new VBox(headlineLabel, infoLabel);
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setPadding(new Insets(5));

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(60);
        imageView.setFitWidth(60);

        BorderPane widgetPane = new BorderPane();
        widgetPane.setCenter(vBox);
        widgetPane.setLeft(imageView);

        centerVBox.getChildren().add(widgetPane);
    }

    /**
     * Adds the headline and content text for the Stage than adds a center VBox. The buttons a shown in the bottom.
     *
     * @return a Stage with all elements
     * @since 0.0.1
     */
    public Stage getStage() {
        Label headerLabel = new Label(getHeadline());
        headerLabel.setFont(new Font("Helvetica", 16));
        Label contentTextLabel = new Label(getContentText());
        contentTextLabel.setFont(new Font("Helvetica", 13));
        contentTextLabel.setWrapText(true);

        VBox topVBox = new VBox();
        topVBox.setPadding(new Insets(10));
        topVBox.setId("header-box");

        if (getHeadline() != null) {
            topVBox.getChildren().add(headerLabel);
        }

        if (getContentText() != null) {
            topVBox.getChildren().add(contentTextLabel);
        }

        centerVBox.setSpacing(15);
        centerVBox.setPadding(new Insets(10, 0, 0, 0));

        borderPane.setPadding(new Insets(15));
        borderPane.setBottom(getButtons(60, 10));

        if (getHeadline() != null || getContentText() != null) {
            borderPane.setTop(topVBox);
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