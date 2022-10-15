package com.gregorgott.mdialogwindows;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.ArrayList;
import java.util.Objects;

/**
 * MDialogWindow is the parent class of all alerts. It controls the basic structure of the alert (header and buttons).
 *
 * <p> A Stage is created in the constructor, accessible via {@code getStage()} to apply custom configurations
 * like stylesheet or size. This stage contains a Scene with a border pane. This border pane is accessible through
 * {@code getBorderPane()} to set the center of the alert.
 *
 * <pre>
 *     // 200x150 big alert with the title "My Alert" and no root window
 *     MDialogWindow parent = new MDialogWindow(200, 150, "My Alert", null);
 *     parent.setCenter(new Label("Hello World"));
 *     parent.show();
 * </pre>
 *
 * <p> It is recommended to not using this class directly but the child classes, however you can use this class if
 * there is no suitable alert for your need.
 *
 * @author GregorGott
 * @version X.X.X
 * @since XXXX-XX-XX (YYYY-MM-DD)
 */
public class MDialogWindow {
    private final Window root;
    private final Stage stage;
    private final BorderPane borderPane;
    private final ArrayList<Button> buttonArrayList;
    private final ImageView alertImageView;
    private final Label headlineLabel;
    private final Label secondHeadlineLabel;
    private final HBox headerBox;
    private final HBox bottomBox;
    private MAlertStyle mAlertStyle;

    /**
     * Sets the alert basic structure with a headline, second headline and an image. A Stage with given dimensions, title
     * and owner. If the width or height is zero, the window is automatically resized to the smallest possible size.
     *
     * @param height Height of the Stage (0 or +).
     * @param width  Width of the Stage (0 or +).
     * @param root   Owner used to set the alert icon.
     * @since 1.0.0
     */
    public MDialogWindow(int width, int height, String title, Window root) {
        this.root = root;

        // ----- Initialize variables ----- //
        buttonArrayList = new ArrayList<>();
        mAlertStyle = MAlertStyle.LIGHT_ROUNDED;
        alertImageView = new ImageView();
        // ----- Initialize variables ----- //
        // ----- header labels ----- //
        headlineLabel = new Label();
        headlineLabel.setWrapText(true);
        headlineLabel.setFont(new Font("Helvetica", 16));

        secondHeadlineLabel = new Label();
        secondHeadlineLabel.setWrapText(true);
        secondHeadlineLabel.setFont(new Font("Helvetica", 13));

        VBox headerLabelsVBox = new VBox();
        headerLabelsVBox.setAlignment(Pos.CENTER_LEFT);
        headerLabelsVBox.setSpacing(5);
        headerLabelsVBox.getChildren().addAll(headlineLabel, secondHeadlineLabel);
        // ----- header labels ----- //
        // ----- header box ----- //
        headerBox = new HBox();
        headerBox.setSpacing(15);
        headerBox.setPadding(new Insets(5));
        headerBox.setId("header-box");
        headerBox.getChildren().addAll(headerLabelsVBox);
        // ----- header box ----- //
        // ----- bottom box ----- //
        bottomBox = new HBox();
        bottomBox.setAlignment(Pos.CENTER_RIGHT);
        // ----- bottom box ----- //
        borderPane = new BorderPane();
        borderPane.setPadding(new Insets(15));
        borderPane.setTop(headerBox);
        borderPane.setBottom(bottomBox);

        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add(getStylesheet(mAlertStyle));

        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.initOwner(root);
        stage.initModality(Modality.WINDOW_MODAL);
        // If the width or height is null, the size will be automatically set
        if (height > 0) stage.setHeight(height);
        if (width > 0) stage.setWidth(width);

        useRootWindowIcon(true);
    }

    /**
     * @return The Stage with all elements.
     * @since 1.0.0
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * @return The {@code MAlertStyle} to set the correct stylesheet.
     * @since 1.0.0
     */
    public MAlertStyle getMAlertStyle() {
        return mAlertStyle;
    }

    /**
     * Sets the {@code mAlertStyle}.
     *
     * @param mAlertStyle The {@code MAlertStyle}.
     * @since 1.0.0
     */
    public void setMAlertStyle(MAlertStyle mAlertStyle) {
        this.mAlertStyle = mAlertStyle;
    }

    /**
     * Sets the {@code alertTitle}.
     *
     * @param alertTitle The title of the Stage.
     * @since 1.0.0
     */
    public void setAlertTitle(String alertTitle) {
        stage.setTitle(alertTitle);
    }

    /**
     * @return The alert image (can be null).
     * @since 1.0.0
     */
    public ImageView getAlertImageView() {
        return alertImageView;
    }

    /**
     * Sets the alert image. If the param {@code image} is null the size of the {@code alertImageView} is set to zero.
     *
     * @param image The alert/top image.
     * @since 1.0.0
     */
    public void setAlertImage(Image image) {
        if (image != null) {
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            headerBox.getChildren().add(0, imageView);
        } else {
            if (headerBox.getChildren().get(0).getClass() == ImageView.class) headerBox.getChildren().remove(0);
        }
    }

    /**
     * @return The {@code headlineLabel} text.
     * @since 1.0.0
     */
    public String getHeadline() {
        return headlineLabel.getText();
    }

    /**
     * Sets the {@code headlineLabel} text.
     *
     * @param headline The headline text.
     * @since 1.0.0
     */
    public void setHeadline(String headline) {
        headlineLabel.setText(headline);
    }

    /**
     * @return The {@code secondHeadlineLabel} text.
     * @since 1.0.0
     */
    public String getSecondHeadline() {
        return secondHeadlineLabel.getText();
    }

    /**
     * Sets the {@code contentText} of the alert.
     *
     * @param contentText The content text of the alert.
     * @since 1.0.0
     */
    public void setContentText(String contentText) {
        secondHeadlineLabel.setText(contentText);
    }

    /**
     * @return An {@code ObservableList} with all buttons.
     * @since X.X.X
     */
    public ObservableList<Button> getButtons() {
        return FXCollections.observableList(buttonArrayList);
    }

    /**
     * Adds a button to the {@code bottomBox} and {@code buttonArrayList} with a text, an {@link ActionEvent} and a
     * default button boolean.
     *
     * @param text          The button text.
     * @param onClickEvent  The called ActionEvent when the button is pushed.
     * @param defaultButton The default button boolean.
     * @since 1.0.0
     */
    public void addButton(String text, EventHandler<ActionEvent> onClickEvent, boolean defaultButton) {
        Button button = new Button(text);
        button.setMinWidth(60);
        button.setOnAction(onClickEvent);
        button.setDefaultButton(defaultButton);
        bottomBox.getChildren().add(button);
        buttonArrayList.add(button);
    }

    /**
     * Clears the Stage icons or uses the owners window icon.
     *
     * @param b The boolean which defines if the owners icon should be used or not.
     * @since X.X.X
     */
    public void useRootWindowIcon(boolean b) {
        if (root != null) {
            if (b) stage.getIcons().addAll(((Stage) root).getIcons());
            else stage.getIcons().clear();
        }
    }

    /**
     * @return The {@code borderPane} to set the alert body.
     * @since 1.0.0
     */
    protected BorderPane getBorderPane() {
        return borderPane;
    }

    /**
     * Sets the spacing of the {@code bottomBox}.
     *
     * @param spacing The spacing between the alert buttons.
     * @since X.X.X
     */
    protected void setButtonSpacing(double spacing) {
        bottomBox.setSpacing(spacing);
    }

    /**
     * Changes the stylesheet from the Scene by returning the belonging MAlertTypes stylesheet path.
     *
     * @param mAlertStyle The MAlertType.
     * @since 1.0.0
     */
    protected String getStylesheet(MAlertStyle mAlertStyle) {
        String css = null;
        switch (mAlertStyle) {
            case LIGHT_CLASSIC -> css = Objects.requireNonNull(getClass().getResource(
                    "stylesheets/stylesheet-light-classic.css")).toExternalForm();
            case LIGHT_ROUNDED -> css = Objects.requireNonNull(getClass().getResource(
                    "stylesheets/stylesheet-light-rounded.css")).toExternalForm();
            case DARK_CLASSIC -> css = Objects.requireNonNull(getClass().getResource(
                    "stylesheets/stylesheet-dark-classic.css")).toExternalForm();
            case DARK_ROUNDED -> css = Objects.requireNonNull(getClass().getResource(
                    "stylesheets/stylesheet-dark-rounded.css")).toExternalForm();
        }
        return css;
    }

    /**
     * Shows the alert.
     *
     * @since X.X.X
     */
    public void show() {
        stage.show();
    }

    /**
     * Closes the Stage.
     *
     * @since 1.0.0
     */
    public void closeAlert() {
        getStage().close();
    }

    public enum MAlertStyle {
        LIGHT_CLASSIC,
        LIGHT_ROUNDED,
        DARK_CLASSIC,
        DARK_ROUNDED
    }
}
