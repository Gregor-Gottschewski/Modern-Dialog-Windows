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

/**
 * MWelcomeAlert is an alert which can show e.g. the latest features of an app in widgets.
 * These widgets contain an image, header and content text.
 *
 * @author GregorGott
 * @version 0.1.0
 * @since 2022-06-11
 */
public class MWelcomeAlert extends MDialogWindow {
    private final Stage stage;
    private final VBox centerVBox;

    /**
     * Calls the parent constructor, sets the <code>MAlertStyle</code> to defaults (<code>LIGHT_ROUNDED</code>),
     * initializes objects and sets the Stage with the dimension 400x350.
     *
     * @since 0.0.1
     */
    public MWelcomeAlert() {
        super(350, 400);

        stage = super.getStage();

        // VBox contains all widgets
        centerVBox = new VBox();
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

        stage.initOwner(root);
        stage.initModality(Modality.WINDOW_MODAL);
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
     * @since 0.0.1
     */
    private void setStage() {
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

        // Scroll pane contains the v box
        ScrollPane widgetScrollPane = new ScrollPane(centerVBox);
        widgetScrollPane.setFitToWidth(true);
        widgetScrollPane.setPadding(new Insets(5));

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(15));
        borderPane.setBottom(getButtons(60, 10));
        borderPane.setCenter(widgetScrollPane);

        if (getHeadline() != null || getContentText() != null) {
            borderPane.setTop(topVBox);
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
     * @since 0.1.0
     */
    public void show() {
        setStage();
        stage.show();
    }
}