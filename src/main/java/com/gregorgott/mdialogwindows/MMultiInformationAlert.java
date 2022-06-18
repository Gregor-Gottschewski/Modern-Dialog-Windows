package com.gregorgott.mdialogwindows;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.stage.Window;

/**
 * The MMultiInformationAlert is based on the MScrollPane, because it has a scroll pane with titled panes in
 * the center. These titled panes can contain a text or a node.
 *
 * @author GregorGott
 * @version 0.0.1
 * @since 2022-06-13
 */
public class MMultiInformationAlert extends MScrollPaneAlert {
    private final Accordion accordion;

    /**
     * Calls the third constructor with null values.
     *
     * @since 0.0.1
     */
    public MMultiInformationAlert() {
        this(null, null);
    }

    /**
     * Calls the third constructor with the Stage title.
     *
     * @param title the title of the Stage.
     * @since 0.0.1
     */
    public MMultiInformationAlert(String title) {
        this(title, null);
    }

    /**
     * This constructor is called by every other constructor and sets the title and root window.
     *
     * @param title the title of the Stage.
     * @param root  the parent window of the Stage.
     * @since 0.0.1
     */
    public MMultiInformationAlert(String title, Window root) {
        super(title, root);

        accordion = new Accordion();

        ScrollPane scrollPane = getScrollPane();
        scrollPane.setContent(accordion);
    }

    /**
     * Adds a titled pane with a header and a content text.
     *
     * @param header      the titled pane header as String.
     * @param contentText the content text as string.
     * @since 0.0.1
     */
    public void addWidget(String header, String contentText) {
        Label contentTextLabel = new Label(contentText);
        contentTextLabel.setWrapText(true);
        contentTextLabel.setPadding(new Insets(10));

        TitledPane titledPane = new TitledPane(header, contentTextLabel);

        accordion.getPanes().add(titledPane);
    }

    /**
     * Adds a titled pane with a header and a content node.
     *
     * @param header the titled pane header as String.
     * @param node   the content node.
     * @since 0.0.1
     */
    public void addWidget(String header, Node node) {
        TitledPane titledPane = new TitledPane(header, node);

        accordion.getPanes().add(titledPane);
    }
}
