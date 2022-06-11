package com.gregorgott.mdialogwindows;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.Objects;

/**
 * Shows a Stage with an image in the center and buttons at the bottom.
 *
 * @author GregorGott
 * @version 0.1.0
 * @since 2022-06-11
 */
public class MImageAlert extends MDialogWindow {
    private final Stage stage;
    private final Scene scene;
    private final BorderPane borderPane;
    private final ImageView imageView;
    private Window root;
    private Image image;

    /**
     * Basic constructor which initializes variables and sets the Stage Scene.
     *
     * @since 0.0.1
     */
    public MImageAlert() {
        super(0, 0);

        stage = super.getStage();

        borderPane = new BorderPane();
        imageView = new ImageView();

        scene = new Scene(borderPane);
        scene.getStylesheets().add(getStylesheet(getMAlertStyle()));
    }

    /**
     * Calls the first constructor and sets the alert title.
     *
     * @param title title of the Stage.
     * @since 0.0.1
     */
    public MImageAlert(String title) {
        this();

        setAlertTitle(title);
    }

    /**
     * Calls the second constructor and sets the parent window.
     *
     * @param title title of the alert.
     * @param root  root window of the alert.
     * @since 0.0.1
     */
    public MImageAlert(String title, Window root) {
        this(title);

        this.root = root;
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
    public Image getImage() {
        return image;
    }

    /**
     * Sets the alert image shown in the center.
     *
     * @param image image in the alert.
     * @since 0.0.1
     */
    public void setImage(Image image) {
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
     * @return the Stage.
     * @since 0.1.0
     */
    public Stage getStage() {
        setStage();
        return stage;
    }

    /**
     * Sets a Stage with the image in the center and the buttons at the bottom.
     *
     * @since 0.1.0
     */
    public void setStage() {
        borderPane.setCenter(imageView);
        borderPane.setBottom(getButtons((int) (imageView.getFitWidth() / getButtonArrayList().size()), 0));

        stage.setTitle(getAlertTitle());
        stage.initOwner(root);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
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