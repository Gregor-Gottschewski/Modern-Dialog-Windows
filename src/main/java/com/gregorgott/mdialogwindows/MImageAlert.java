package com.gregorgott.mdialogwindows;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Window;

import java.util.Objects;

/**
 * Shows a <code>Stage</code> with an image in the centre and buttons at the bottom.
 *
 * @author GregorGott
 * @version 1.0.0
 * @since 2022-07-17
 */
public class MImageAlert extends MDialogWindow {
    private final Scene scene;
    private final BorderPane borderPane;
    private final ImageView imageView;
    private Image image;

    public MImageAlert(Image image) {
        this(image, null, null);
    }

    public MImageAlert(Image image, String title) {
        this(image, title, null);
    }

    /**
     * Calls the superclass constructor and initializes a <code>BorderPane</code> which overrides the
     * <code>MDialogWindow</code>s <code>BorderPane</code>. And sets the alert image, title and <code>Stage</code> root.
     *
     * @param image centre image.
     * @param title title of the alert.
     * @param root  root window of the alert.
     * @since 0.0.1
     */
    public MImageAlert(Image image, String title, Window root) {
        super(0, 0);

        borderPane = new BorderPane();
        scene = new Scene(borderPane);
        imageView = new ImageView();

        setAlertImage(image);
        setAlertTitle(title);

        getStage().initOwner(root);
        getStage().initModality(Modality.WINDOW_MODAL);
    }

    /**
     * Sets the <code>MAlertStyle</code> which defines the used stylesheet.
     *
     * @param mAlertStyle the <code>MAlertStyle</code>.
     * @since 0.0.1
     */
    public void setMAlertStyle(MAlertStyle mAlertStyle) {
        scene.getStylesheets().add(getStylesheet(mAlertStyle));
    }

    /**
     * @return the image shown in the alert.
     * @since 0.0.1
     */
    public Image getAlertImage() {
        return image;
    }

    /**
     * Sets the alert image shown in the center.
     *
     * @param image image in the alert.
     * @since 0.0.1
     */
    public void setAlertImage(Image image) {
        this.image = image;
        imageView.setImage(image);
    }

    /**
     * Sets the image dimension.
     *
     * @param height height of the image.
     * @param width  width of the image.
     * @since 0.0.1
     */
    public void setImageSize(int height, int width) {
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
    }

    /**
     * Changes the stylesheet from the Scene by getting the <code>mAlertType</code>.
     *
     * @since 0.0.1
     */
    public String getStylesheet(MAlertStyle mAlertStyle) {
        String css = null;

        switch (mAlertStyle) {
            case LIGHT_CLASSIC, LIGHT_ROUNDED -> css = Objects.requireNonNull(getClass().getResource(
                    "stylesheets/stylesheet-image-alert-light.css")).toExternalForm();
            case DARK_CLASSIC, DARK_ROUNDED -> css = Objects.requireNonNull(getClass().getResource(
                    "stylesheets/stylesheet-image-alert-dark.css")).toExternalForm();
        }

        return css;
    }

    /**
     * Sets a Stage with the image in the center and the buttons at the bottom.
     *
     * @since 0.1.0
     */
    public void setStage() {
        borderPane.setCenter(imageView);
        borderPane.setBottom(getButtons((int) (imageView.getFitWidth() / getButtonArrayList().size()), 0));

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
