/*
 *
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package io.github.gleidsonmt.dashboardfx.views;

import io.github.gleidsonmt.dashboardfx.core.app.services.Context;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.options.SnackColors;
import io.github.gleidsonmt.gncontrols.controls.GNButton;
import io.github.gleidsonmt.gncontrols.material.icon.IconContainer;
import io.github.gleidsonmt.gncontrols.material.icon.Icons;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Worker;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.SVGPath;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.net.URL;
import java.util.Objects;

public class BlockCode extends StackPane {

    private final StringProperty content = new SimpleStringProperty();

    public BlockCode(Context context, String text) {
        this(context, text, false);
    }

    public BlockCode(Context context, String text, boolean fxml) {
        content.setValue(text);
        this.setMinHeight(150);
        this.setAlignment(Pos.TOP_RIGHT);
        GNButton btn = new GNButton();
        btn.setPrefSize(37, 30);
        SVGPath path = new SVGPath();
        btn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        path.setContent("M5 22q-.825 0-1.413-.587Q3 20.825 3 20V6h2v14h11v2Zm4-4q-.825 0-1.412-.587Q7 16.825 7 16V4q0-.825.588-1.413Q8.175 2 9 2h9q.825 0 1.413.587Q20 3.175 20 4v12q0 .825-.587 1.413Q18.825 18 18 18Zm0-2h9V4H9v12Zm0 0V4v12Z");
        btn.getStyleClass().add("btn-flat");
        btn.getStyleClass().addAll("flat","btn-flat");
        btn.setGraphic(path);
        this.setStyle("-fx-border-color : -light-gray-2; -fx-background-color : -light-gray;");
        WebView webView = new WebView();
        webView.setContextMenuEnabled(false);
        webView.getEngine().setJavaScriptEnabled(true);

        URL url = getClass().getResource("/web/index.html");
        webView.getEngine().load(Objects.requireNonNull(url).toExternalForm());

        webView.getEngine().getLoadWorker().stateProperty()
                .addListener((obs, oldValue, newValue) -> {
                    if (newValue == Worker.State.SUCCEEDED) {
                        Platform.runLater(() -> {
                            if (!text.isEmpty() && !text.isBlank()) {

                                Document doc = webView.getEngine().getDocument();
                                Element el = doc.getElementById("block");

                                if (fxml)
                                    el.setAttribute("class", "language-html");

                                el.setTextContent(text);

                                webView.getEngine().executeScript("hljs.highlightAll();");
                            }
                            this.getChildren().setAll(webView, btn);
                        });
                    }
                }); // addListener()


        webView.setOnScroll(event -> {
        });

        this.getChildren().addAll(
               new SimpleLoadCircle()
        );

        btn.setOnAction(event -> {
            ClipboardContent content = new ClipboardContent();
            content.putString(this.content.get());
            content.putHtml("<b>Bold</b> text");
            Clipboard.getSystemClipboard().setContent(content);

            context.root()
                    .createSnackBar()
                    .icon(new IconContainer(Icons.DONE))
                    .color(SnackColors.SUCCESS)
                    .message("Copied!")
                    .show();
        });
    }

    public String getContent() {
        return content.get();
    }

    public StringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }
}
