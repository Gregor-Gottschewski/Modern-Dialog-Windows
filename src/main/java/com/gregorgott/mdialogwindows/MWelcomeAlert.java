package com.gregorgott.mdialogwindows;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Window;

/**
 * MWelcomeAlert is an MScrollPaneAlert based alert which can show e.g. the latest features of an app in widgets.
 * These widgets contain an image, header and a short content text.
 *
 * @author GregorGott
 * @version 0.2.0
 * @since 2022-06-13
 */
public class MWelcomeAlert extends MScrollPaneAlert {
    private final VBox centerVBox;

    /**
     * If no title and root window is given, this constructor is called. It calls the third constructor with null
     * values.
     *
     * @since 0.0.1
     */
    public MWelcomeAlert() {
        this(null, null);
    }

    /**
     * Calls the third constructor and sets the Stage title.
     *
     * @param title the title of the Stage.
     * @since 0.0.1
     */
    public MWelcomeAlert(String title) {
        this(title, null);
    }

    /**
     * This constructor is called by any other secondary constructors and calls the superclass constructor with
     * title and parent window.
     *
     * @param text the title of the Stage.
     * @param root the parent window.
     * @since 0.0.1
     */
    public MWelcomeAlert(String text, Window root) {
        super(text, root);

        // VBox contains all widgets
        centerVBox = new VBox();
        ScrollPane scrollPane = getScrollPane();
        scrollPane.setContent(centerVBox);
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
}