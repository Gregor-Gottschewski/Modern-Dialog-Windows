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
 * @version 0.0.1
 * @since 2022-05-20
 */
public class MImageAlert extends MDialogWindow {
    private final Stage stage;
    private final Scene scene;
    private final BorderPane borderPane;
    private final ImageView imageView;
    private Window root;
    private Image image;
    private MAlertStyle mAlertStyle;

    /**
     * Basic constructor which initializes variables and sets the Stage Scene.
     *
     * @since 0.0.1
     */
    public MImageAlert() {
        super();

        borderPane = new BorderPane();
        imageView = new ImageView();

        scene = new Scene(borderPane);

        setAlertStyle(MAlertStyle.LIGHT);

        stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
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
    public void setAlertStyle(MAlertStyle mAlertStyle) {
        this.mAlertStyle = mAlertStyle;
        setStylesheet();
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
    private void setStylesheet() {
        switch (mAlertStyle) {
            case LIGHT -> scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(
                    "stylesheets/stylesheet-image-alert-light.css")).toExternalForm());
            case DARK -> scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(
                    "stylesheets/stylesheet-image-alert-dark.css")).toExternalForm());
        }
    }

    /**
     * Adds the border pane image to the center and adds button add the bottom.
     *
     * @return the Stage with all elements.
     * @since 0.0.1
     */
    public Stage getStage() {
        borderPane.setCenter(imageView);
        borderPane.setBottom(getButtons((int) (imageView.getFitWidth() / getButtonArrayList().size()), 0));

        stage.setTitle(getAlertTitle());
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
        DARK,
        LIGHT
    }
}