package com.gregorgott.mdialogwindows;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.stage.Window;

/**
 * The {@code MScrollPaneAlert} is an alert which contains a {@code ScrollPane} in the center, which can be called via
 * {@code getScrollPane()}.
 *
 * <p> An {@code MScrollPaneAlert} with content can be created that way:
 * <pre>
 *     MScrollPaneAlert scrollPaneAlert = new MScrollPaneAlert("My Alert");
 *     scrollPaneAlert.getScrollPane().setContent(new Label("Content"));
 *     scrollPaneAlert.show();
 * </pre>
 *
 * @author GregorGott
 * @version 1.1.0
 * @since 2022-10-16 (YYYY-MM-DD)
 */
public class MScrollPaneAlert extends MDialogWindow {
    private final ScrollPane scrollPane;

    /**
     * Creates an {@code MScrollPaneAlert} without title and root window.
     *
     * @since 1.0.0
     */
    public MScrollPaneAlert() {
        this(null, null);
    }

    /**
     * Creates an {@code MScrollPaneAlert} with a title, but without window owner.
     *
     * @param title The title of the {@code MScrollPaneAlert}.
     * @since 1.0.0
     */
    public MScrollPaneAlert(String title) {
        this(title, null);
    }

    /**
     * Creates an {@code MScrollPaneAlert} with a title and window owner.
     *
     * @param title The title of the {@code MScrollPaneAlert}.
     * @param root  The owner of the alert.
     * @since 1.0.0
     */
    public MScrollPaneAlert(String title, Window root) {
        super(350, 400, title, root);

        scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10, 0, 10, 0));

        setButtonSpacing(10);
        getBorderPane().setCenter(scrollPane);
    }

    /**
     * @return The center scrollPane.
     * @since 1.0.0
     */
    public ScrollPane getScrollPane() {
        return scrollPane;
    }
}
