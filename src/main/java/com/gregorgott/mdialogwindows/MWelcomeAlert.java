package com.gregorgott.mdialogwindows;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Window;

/**
 * MWelcomeAlert is an MScrollPaneAlert based alert which can show e.g. the latest features of an app in widgets.
 * These widgets contain an image, header and a short content text.
 *
 * @author GregorGott
 * @version 1.0.0
 * @since 2022-07-17
 */
public class MWelcomeAlert extends MScrollPaneAlert {
    private final VBox centerVBox;

    public MWelcomeAlert() {
        this(null, null);
    }

    public MWelcomeAlert(String title) {
        this(title, null);
    }

    /**
     * Calls the superclass with the title and root window, initializes a <code>VBox</code> which contains all widgets
     * and sets this <code>VBox</code> as the content of the <code>ScrollPane</code>.
     *
     * @param title the title of the Stage.
     * @param root  the parent window.
     * @since 0.0.1
     */
    public MWelcomeAlert(String title, Window root) {
        super(title, root);

        // VBox contains all widgets
        centerVBox = new VBox();
        centerVBox.setSpacing(15);
        getScrollPane().setContent(centerVBox);
    }

    /**
     * A widget contains an image, headline and a small info text.
     *
     * @param image    an 60x60 image.
     * @param headline a headline of the widget (with text wrap).
     * @param infoText a small info text (with text wrap).
     * @since 0.0.1
     */
    public void addWidget(Image image, String headline, String infoText) {
        Label headlineLabel = new Label(headline);
        headlineLabel.setWrapText(true);
        headlineLabel.setFont(new Font("Helvetica", 15));

        Label infoLabel = new Label(infoText);
        infoLabel.setWrapText(true);

        VBox textVBox = new VBox(headlineLabel, infoLabel);
        textVBox.setAlignment(Pos.CENTER_LEFT);
        textVBox.setSpacing(5);

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(60);
        imageView.setFitWidth(60);

        HBox widgetHBox = new HBox();
        widgetHBox.setSpacing(10);
        widgetHBox.setAlignment(Pos.CENTER_LEFT);
        if (image != null) {
            widgetHBox.getChildren().add(imageView);
        }
        widgetHBox.getChildren().add(textVBox);

        centerVBox.getChildren().add(widgetHBox);
    }
}
