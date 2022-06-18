package com.gregorgott.mdialogwindows;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * The MScrollPaneAlert is an alert which contains a scroll pane in the center, which can be edited.
 *
 * @author GregorGott
 * @version 0.0.2
 * @since 2022-06-18
 */
public class MScrollPaneAlert extends MDialogWindow {
    private final Stage stage;
    private final ScrollPane scrollPane;

    /**
     * Calls the parent constructor (dimensions 350x400), initializes the Stage and the scroll pane.
     *
     * @since 0.0.1
     */
    public MScrollPaneAlert() {
        super(350, 400);

        stage = super.getStage();

        // VBox contains all widgets
        scrollPane = new ScrollPane();
    }

    /**
     * Calls the first constructor and sets the Stage title.
     *
     * @param title the title of the Stage.
     * @since 0.0.1
     */
    public MScrollPaneAlert(String title) {
        this();

        setAlertTitle(title);
    }

    /**
     * Calls the second constructor and sets the parent window.
     *
     * @param text the title of the Stage.
     * @param root the parent window.
     * @since 0.0.1
     */
    public MScrollPaneAlert(String text, Window root) {
        this(text);

        stage.initOwner(root);
        stage.initModality(Modality.WINDOW_MODAL);
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
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

        // VBox contains header and content text
        VBox topVBox = new VBox();
        topVBox.setSpacing(5);

        if (getHeadline() != null) {
            topVBox.getChildren().add(headerLabel);
        }

        if (getContentText() != null) {
            topVBox.getChildren().add(contentTextLabel);
        }

        // HBox contains topVBox and alert image
        HBox topHBox = new HBox();
        topHBox.setSpacing(18);
        topHBox.setPadding(new Insets(5));
        topHBox.setId("header-box");

        if (getAlertImageView() != null) {
            topHBox.getChildren().add(getAlertImageView());
        }

        topHBox.getChildren().add(topVBox);

        // Scroll pane contains the v box
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10, 0, 10, 0));

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(15));
        borderPane.setBottom(getButtons(60, 10));
        borderPane.setCenter(scrollPane);

        if (getHeadline() != null || getContentText() != null || getAlertImageView() != null) {
            borderPane.setTop(topHBox);
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