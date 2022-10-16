package com.gregorgott.mdialogwindows;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.stage.Window;

/**
 * The {@code MMultiInformationAlert} is based on the {@link MScrollPaneAlert}, because of the {@code ScrollPane}
 * which contains an {@code Accordion} with widgets.
 *
 * @author GregorGott
 * @version 1.1.0
 * @since 2022-10-16 (YYYY-MM-DD)
 */
public class MMultiInformationAlert extends MScrollPaneAlert {
    private final Accordion accordion;

    /**
     * Creates an alert without a title and owner.
     *
     * @since 1.0.0
     */
    public MMultiInformationAlert() {
        this(null, null);
    }

    /**
     * Creates an alert with a title, but without owner.
     *
     * @param title The title of the Stage.
     * @since 1.0.0
     */
    public MMultiInformationAlert(String title) {
        this(title, null);
    }

    /**
     * Creates an alert with title and owner.
     *
     * @param title The title of the Stage.
     * @param root  The owner of the Stage.
     * @since 1.0.0
     */
    public MMultiInformationAlert(String title, Window root) {
        super(title, root);

        accordion = new Accordion();
        getScrollPane().setContent(accordion);
    }

    /**
     * Adds a {@code TitledPane} with a header and a content text.
     *
     * @param header      The header of the widget.
     * @param contentText the content text of the widget.
     * @since 1.0.0
     */
    public void addWidget(String header, String contentText) {
        Label contentTextLabel = new Label(contentText);
        contentTextLabel.setWrapText(true);
        contentTextLabel.setPadding(new Insets(10));

        TitledPane titledPane = new TitledPane(header, contentTextLabel);
        accordion.getPanes().add(titledPane);
    }

    /**
     * Adds a {@code TitledPane} with a header and a content node.
     *
     * @param header The header of the widget.
     * @param node   The content node.
     * @since 1.0.0
     */
    public void addWidget(String header, Node node) {
        TitledPane titledPane = new TitledPane(header, node);
        accordion.getPanes().add(titledPane);
    }
}
