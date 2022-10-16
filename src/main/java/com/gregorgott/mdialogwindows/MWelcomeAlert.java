package com.gregorgott.mdialogwindows;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Window;

/**
 * {@code MWelcomeAlert} is an {@link MScrollPaneAlert} based alert which can show e.g. the latest features of an app
 * in widgets. These widgets contain an image, header and a short content text:
 *
 * <pre>
 *     Image image = ...;
 *     MWelcomeAlert mWelcomeAlert = ...;
 *     mWelcomeAlert.addWidget(image, "More Languages added", "With this update three new languages are added.");
 * </pre>
 *
 * @author GregorGott
 * @version 1.1.0
 * @since 2022-10-16 (YYYY-MM-DD)
 */
public class MWelcomeAlert extends MScrollPaneAlert {
    private final VBox centerVBox;

    /**
     * Creates a {@code MWelcomeAlert} without title and owner window.
     *
     * @since 1.0.0
     */
    public MWelcomeAlert() {
        this(null, null);
    }

    /**
     * Creates a {@code MWelcomeAlert} with a title, but without owner window.
     *
     * @param title The title.
     * @since 1.0.0
     */
    public MWelcomeAlert(String title) {
        this(title, null);
    }

    /**
     * Creates a {@code MWelcomeAlert} with a title and owner window.
     *
     * @param title The title.
     * @param root  The window owner.
     * @since 1.0.0
     */
    public MWelcomeAlert(String title, Window root) {
        super(title, root);

        centerVBox = new VBox();
        centerVBox.setSpacing(15);
        getScrollPane().setContent(centerVBox);
    }

    /**
     * @return All children of the {@code centerVBox}.
     * @since 1.1.0
     */
    public ObservableList<Node> getWidgets() {
        return centerVBox.getChildren();
    }

    /**
     * Adds a widget with an image, headline and a small info text.
     *
     * @param image    An 60x60 image.
     * @param headline A headline of the widget.
     * @param infoText A small info text.
     * @since 1.0.0
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
        if (image != null) widgetHBox.getChildren().add(imageView);
        widgetHBox.getChildren().add(textVBox);

        centerVBox.getChildren().add(widgetHBox);
    }
}
