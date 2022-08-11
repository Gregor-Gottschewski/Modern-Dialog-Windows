package com.gregorgott.mdialogwindows;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Window;

/**
 * Shows an alert with a title, content text and a <code>WebView</code> in the centre. The <code>WebView</code>
 * can show HTML files and websites. To access the <code>WebView</code> the method <code>getWebView()</code> returns
 * the <code>WebView</code>.
 *
 * @author GregorGott
 * @version 1.0.0
 * @since 2022-07-17
 */
public class MWebAlert extends MDialogWindow {

    private final WebView webView;


    public MWebAlert(String url) {
        this(url, null, null);
    }

    public MWebAlert(String url, String title) {
        this(url, title, null);
    }

    /**
     * Calls the superclass constructor and sets the <code>Stage</code> init owner and title. The <code>WebView</code> loads the
     * given URL.
     *
     * @param url   the url, which may be load in the webview.
     * @param title the title of the <code>Stage</code>.
     * @param root  the root window of the <code>Stage</code>.
     */
    public MWebAlert(String url, String title, Window root) {
        super(500, 450, root);

        webView = new WebView();
        webView.getEngine().load(url);

        setAlertTitle(title);
    }

    /**
     * Gets the superclass <code>BorderPane</code> and sets the <code>WebView</code> to the centre of it.
     * To space the <code>WebView</code> and the header the <code>WebView</code> gets insets.
     *
     * @since 1.0.0
     */
    private void setStage() {
        getBorderPane().setTop(getHeader());
        getBorderPane().setCenter(webView);
        getBorderPane().setBottom(getButtons(60, 10));

        BorderPane.setMargin(webView, new Insets(10, 0, 10, 0));

        Scene scene = new Scene(getBorderPane());
        scene.getStylesheets().add(getStylesheet(getMAlertStyle()));

        getStage().setScene(scene);
    }

    /**
     * To control the <code>WebView</code> this method returns it.
     *
     * @return the centre <code>WebView</code>.
     * @since 1.0.0
     */
    public WebView getWebView() {
        return webView;
    }

    /**
     * Shows the alert with all UI elements.
     * @since 1.0.0
     */
    public void show() {
        setStage();
        getStage().show();
    }
}
