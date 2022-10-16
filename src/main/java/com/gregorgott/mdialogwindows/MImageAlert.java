package com.gregorgott.mdialogwindows;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;

import java.util.Objects;

/**
 * The {@code MImageAlert} shows an alert without any text, but only one image in the center. The alert tries to show
 * the image in the original resolution, however you can change that with the {@code setImageSize()} method
 * (recommended: before showing the alert).
 *
 * <p> A use of this alert could look like this:
 * <pre>
 *     Image image = new Image(...);
 *     MImageAlert imageAlert = new MImageAlert(image, "My Alert");
 *     imageAlert.setImageSize(200, 150); // width, height
 *     imageAlert.addButton("Cancel", x -> System.out.println("Cancel"), false);
 *     imageAlert.addButton("Share", x -> System.out.println("Share"), true);
 *     imageAlert.show();
 * </pre>
 *
 * @author GregorGott
 * @version 1.1.0
 * @since 2022-10-16 (YYYY-MM-DD)
 */
public class MImageAlert extends MDialogWindow {
    private final Scene scene;
    private final ImageView imageView;
    private Image image;

    /**
     * Creates a {@code MImageAlert} with an image, but without a title and window owner.
     *
     * @param image The image of the {@code MImageAlert}.
     * @since 1.0.0
     */
    public MImageAlert(Image image) {
        this(image, null, null);
    }

    /**
     * Creates a {@code MImageAlert} with an image, but without a title and window owner.
     *
     * @param image The image of the {@code MImageAlert}.
     * @param title The title.
     * @since 1.0.0
     */
    public MImageAlert(Image image, String title) {
        this(image, title, null);
    }

    /**
     * Creates a {@code MImageAlert} with an image, but without a title and window owner.
     *
     * @param image The image of the {@code MImageAlert}.
     * @param title The title.
     * @param root  The window owner.
     * @since 1.0.0
     */
    public MImageAlert(Image image, String title, Window root) {
        super(0, 0, title, root);
        Objects.requireNonNull(image);

        setButtonSpacing(0);

        imageView = new ImageView();
        setAlertImage(image);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(imageView);
        borderPane.setBottom(getBorderPane().getBottom());

        scene = new Scene(borderPane);
        scene.getStylesheets().add(getStylesheet(getMAlertStyle()));
        getStage().setScene(scene);
    }

    /**
     * Sets the {@code MAlertStyle} which defines the used stylesheet.
     *
     * @param mAlertStyle The {@code MAlertStyle}.
     * @since 1.0.0
     */
    public void setMAlertStyle(MAlertStyle mAlertStyle) {
        scene.getStylesheets().add(getStylesheet(mAlertStyle));
    }

    /**
     * @return The image shown in the alert.
     * @since 1.0.0
     */
    public Image getAlertImage() {
        return image;
    }

    /**
     * Sets the alert image shown in the center.
     *
     * @param image The image in the alert.
     * @since 1.0.0
     */
    public void setAlertImage(Image image) {
        this.image = image;
        imageView.setImage(image);
    }

    /**
     * Sets the image dimensions (require greater than zero).
     *
     * @param height The height of the image.
     * @param width  The width of the image.
     * @since 1.0.0
     */
    public void setImageSize(int width, int height) {
        if (0 < width && 0 < height) {
            imageView.setFitHeight(height);
            imageView.setFitWidth(width);
        }
    }

    /**
     * Changes the stylesheet from the Scene by getting the {@code MAlertStyle}.
     *
     * @since 1.0.0
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
     * Sets the with of the buttons to the maximum possible ans shows the alert.
     * @since 1.0.0
     */
    public void show() {
        for (Button button : getButtons()) button.setMinWidth(imageView.getFitWidth() / getButtons().size());
        super.show();
    }
}
