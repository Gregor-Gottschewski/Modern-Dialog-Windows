package com.gregorgott.mdialogwindows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
 * MDialogWindow is the parent class of all alerts.
 * This class creates a Stage with a title and the MAlertStyle. Also, the header and content text gets set by this class.
 *
 * @author GregorGott
 * @version 1.0.1
 * @since 2022-09-11
 */
public class MDialogWindow {
    private final Window root;
    private final Stage stage;
    private final BorderPane borderPane;
    private final ArrayList<Button> buttonArrayList;
    private ImageView alertImageView;
    private String headline;
    private String contentText;
    private MAlertStyle mAlertStyle;

    /**
     * Initializes the <code>buttonArrayList</code> which will contains all buttons and a Stage with given dimensions.
     * If the width or height is null, it will be automatically calculated.
     *
     * @param height the height of the Stage (null oder bigger).
     * @param width  the width of the Stage (null or bigger).
     * @param root   the root window used to set alert icons.
     * @since 0.0.1
     */
    public MDialogWindow(int width, int height, Window root) {
        this.root = root;
        // Initialize variables
        buttonArrayList = new ArrayList<>();

        mAlertStyle = MAlertStyle.LIGHT_ROUNDED;

        stage = new Stage();

        // If the width or height is null, the size will be automatically set
        if (height > 0)
            stage.setHeight(height);

        if (width > 0)
            stage.setWidth(width);

        stage.setResizable(false);
        stage.initOwner(root);
        stage.initModality(Modality.WINDOW_MODAL);
        useRootWindowIcon(true);

        borderPane = new BorderPane();
        borderPane.setPadding(new Insets(15));
    }

    /**
     * @return the Stage with all elements.
     * @since 0.1.0
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * @return the MAlertStyle to set the correct stylesheet.
     * @since 0.1.0
     */
    public MAlertStyle getMAlertStyle() {
        return mAlertStyle;
    }

    /**
     * Sets the MAlertStyle.
     *
     * @param mAlertStyle the MAlertStyle.
     * @since 0.0.1
     */
    public void setMAlertStyle(MAlertStyle mAlertStyle) {
        this.mAlertStyle = mAlertStyle;
    }

    /**
     * Sets the <code>alertTitle</code>.
     *
     * @param alertTitle the title of the Stage.
     * @since 0.0.1
     */
    public void setAlertTitle(String alertTitle) {
        stage.setTitle(alertTitle);
    }

    /**
     * @return the alert image if given.
     * @since 0.0.1
     */
    public ImageView getAlertImageView() {
        return alertImageView;
    }

    /**
     * Sets the alert image, which is shown on the top of the Scene width the dimensions 50x50.
     *
     * @param image the image to show on the top.
     * @since 0.0.1
     */
    public void setAlertImage(Image image) {
        this.alertImageView = new ImageView(image);
        alertImageView.setFitWidth(50);
        alertImageView.setFitHeight(50);
    }

    /**
     * @return the headline from the alert.
     * @since 0.0.1
     */
    public String getHeadline() {
        return headline;
    }

    /**
     * Sets the headline text.
     *
     * @param headline the headline.
     * @since 0.0.1
     */
    public void setHeadline(String headline) {
        this.headline = headline;
    }

    /**
     * @return the content text of the alert.
     * @since 0.0.1
     */
    public String getContentText() {
        return contentText;
    }

    /**
     * Sets the <code>contentText</code> of the alert.
     *
     * @param contentText the content text of the alert.
     * @since 0.0.1
     */
    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    /**
     * @return all buttons in the <code>buttonArrayList</code> array list.
     * @since 0.0.1
     */
    public ArrayList<Button> getButtonArrayList() {
        return buttonArrayList;
    }

    /**
     * Adds a button to the <code>buttonArrayList</code> with a text, an <code>ActionEvent</code>, and a boolean
     * which sets the button default or not.
     *
     * @param text          the button text.
     * @param onClickEvent  called action event when the button is pushed.
     * @param defaultButton boolean if the button is the default button.
     * @since 0.0.1
     */
    public void addButton(String text, EventHandler<ActionEvent> onClickEvent, boolean defaultButton) {
        Button button = new Button(text);
        button.setOnAction(onClickEvent);
        button.setDefaultButton(defaultButton);

        buttonArrayList.add(button);
    }

    /**
     * Clears the Stage icons when the alert should not contain the {@code root} window icon.
     *
     * @param b a boolean used to set the alert image.
     * @since 1.0.1
     */
    public void useRootWindowIcon(boolean b) {
        if (b)
            stage.getIcons().addAll(((Stage) root).getIcons());
        else
            stage.getIcons().clear();
    }

    /**
     * Creates a HBox with header, content and icon and returns it.
     *
     * @return the basic alert header.
     * @since 1.0.0
     */
    protected Node getHeader() {
        // return the top Node only when headline or content text is not null
        if (getHeadline() != null || getContentText() != null) {
            Label headlineLabel = new Label(getHeadline());
            headlineLabel.setWrapText(true);
            headlineLabel.setFont(new Font("Helvetica", 16));
            Label contentLabel = new Label(getContentText());
            contentLabel.setWrapText(true);
            contentLabel.setFont(new Font("Helvetica", 13));

            VBox headerVBox = new VBox();
            headerVBox.setSpacing(5);

            if (getHeadline() != null)
                headerVBox.getChildren().add(headlineLabel);

            if (getContentText() != null)
                headerVBox.getChildren().add(contentLabel);

            HBox hBox = new HBox();
            hBox.setSpacing(18);
            hBox.setPadding(new Insets(5));
            hBox.setId("header-box");

            if (getAlertImageView() != null)
                hBox.getChildren().add(getAlertImageView());
            hBox.getChildren().add(headerVBox);

            return hBox;
        } else {
            return null;
        }
    }

    /**
     * @return the {@code borderPane} to set the alert body.
     * @since 1.0.0
     */
    protected BorderPane getBorderPane() {
        return borderPane;
    }

    /**
     * Creates a HBox with every button in <code>buttonArrayList</code>.
     *
     * @return an HBox with all buttons.
     * @since 0.0.1
     */
    public Node getButtons(int width, int spacing) {
        HBox bottomHBox = new HBox();
        bottomHBox.setAlignment(Pos.CENTER_RIGHT);
        bottomHBox.setSpacing(spacing);

        for (Button button : buttonArrayList) {
            button.setMinWidth(width);
            bottomHBox.getChildren().add(button);
        }

        return bottomHBox;
    }

    /**
     * Changes the stylesheet from the Scene by returning the belonging MAlertTypes stylesheet path.
     *
     * @param mAlertStyle the MAlertType.
     * @since 0.0.3
     */
    public String getStylesheet(MAlertStyle mAlertStyle) {
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
     * Closes the Stage.
     *
     * @since 0.0.1
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