package com.gregorgott.mdialogwindows;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Modality;
import javafx.stage.Window;

/**
 * The MScrollPaneAlert is an alert which contains a <code>ScrollPane</code> in the centre, which can be edited.
 *
 * @author GregorGott
 * @version 1.0.0
 * @since 2022-07-17
 */
public class MScrollPaneAlert extends MDialogWindow {
    private final ScrollPane scrollPane;

    public MScrollPaneAlert() {
        this(null, null);
    }

    public MScrollPaneAlert(String title) {
        this(title, null);
    }

    /**
     * Calls the parent constructor (dimensions 350x400) and sets the parent window and title.
     *
     * @param title the title of the Stage.
     * @param root  the parent window.
     * @since 0.0.1
     */
    public MScrollPaneAlert(String title, Window root) {
        super(350, 400);

        scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10, 0, 10, 0));

        setAlertTitle(title);

        getStage().initOwner(root);
        getStage().initModality(Modality.WINDOW_MODAL);
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
        getBorderPane().setTop(getHeader());
        getBorderPane().setCenter(scrollPane);
        getBorderPane().setBottom(getButtons(60, 10));

        Scene scene = new Scene(getBorderPane());
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
