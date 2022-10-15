package com.gregorgott.mdialogwindows;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Window;

/**
 * {@code MWebAlert} shows a {@code WebView} in the center of the alert. The {@code WebView} is accessible via the
 * {@code getWebView()} method.
 *
 * @author GregorGott
 * @version X.X.X
 * @since XXXX-XX-XX (YYYY-MM-DD)
 */
public class MWebAlert extends MDialogWindow {
    private final WebView webView;

    /**
     * Creates a {@code MWebAlert} with a URL, but without a title and window owner.
     *
     * @param url The website/file to be shown in the {@code WebView}.
     * @since 1.0.0
     */
    public MWebAlert(String url) {
        this(url, null, null);
    }

    /**
     * Creates a {@code MWebAlert} with a URL and title, but without a window owner.
     *
     * @param url   The website/file to be shown in the {@code WebView}.
     * @param title The title.
     * @since 1.0.0
     */
    public MWebAlert(String url, String title) {
        this(url, title, null);
    }

    /**
     * Creates a {@code MWebAlert} with a URL, title and window owner.
     *
     * @param url   The website/file to be shown in the {@code WebView}.
     * @param title The title.
     * @param root  The window owner.
     */
    public MWebAlert(String url, String title, Window root) {
        super(500, 450, title, root);

        webView = new WebView();
        webView.getEngine().load(url);
        BorderPane.setMargin(webView, new Insets(10, 0, 10, 0));

        setButtonSpacing(10);
        getBorderPane().setCenter(webView);
    }

    /**
     * @return The {@code WebView}.
     * @since 1.0.0
     */
    public WebView getWebView() {
        return webView;
    }
}
