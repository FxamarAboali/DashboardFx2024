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

package io.github.gleidsonmt.dashboardfx.views.controls;

import io.github.gleidsonmt.dashboardfx.core.Context;
import io.github.gleidsonmt.dashboardfx.core.controls.GNButton;
import io.github.gleidsonmt.dashboardfx.core.controls.GNIconButton;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.IconContainer;
import io.github.gleidsonmt.dashboardfx.core.controls.icon.Icons;
import io.github.gleidsonmt.dashboardfx.core.view.layout.creators.TutorialCreator;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.Arrays;
import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  21/01/2023
 */
public class ButtonPresCreator extends TutorialCreator {

    public ButtonPresCreator(Context context) {
        super(context);
        this    .title("Button")
                .text("""
                        A simple button control. The button control can contain text and/or a graphic.
                        """)
                .multCode(List.of(
                            createButton("Default"),
                            createCancel("Cancel"),
                            createBtnFlat("Flat"),
                            createIconized("Iconized")

                        ),
                        """
                        // Default
                        Button button = new Button("Button");
                        // cancel
                        button.setCancelButton(true);
                        // Flat
                        buttonFlat.getStyleClass("btn-flat");
                        // Icon
                         button.setGraphic(new IconContainer(Icons.ANALYTICS));
                       
                        """,
                        """
                                <!-- Default -->
                                <Button text="button"></Button>
                                <!-- Main button/gave action from enter -->
                                <Button text="button" defaultButton="true"></Button>
                                <!-- Cancel button/gave action using scape -->
                                <Button text="button" cancelButton="true"></Button>
                                <!-- Only add inline styleClass -->
                                <Button text="button" styleClass="btn-flat""></Button>
                                <!-- Using a graphic -->
                                <Button text="button">
                                    <graphic>
                                        <IconContainer icon="DISCOUNT">
                                    </graphic> 
                                </Button>
                                """)
                .title("Dash Buttons")
                .multCode(List.of(
                        createGNButton("GNButton"),
                        createFloatingButton()
                        ),
                        """
                            // GNButton
                            GNButton gnButton = new GNButton();
                            // GNbutton with icon
                            GNIconButton gnButton = new GNIconButton();
                                """, """
                                <GNButton text=\"button\"/>
                                <!-- Icon button -->
                                <GNIconButton icon="DISCOUNT"/>
                            """
                )
                .title("Links")
                .footer(createDefaultControl());
        build();


    }

    public List<Node> listIconized() {
        Button button = createButton("Iconized");
        List<Node> list = Arrays.asList(button);
        button.setGraphic(new IconContainer(Icons.ANALYTICS));
        return list;
    }

    public Button createButton(String title) {
        return new Button(title);
    }

    public GNButton createGNButton(String title) {
        return new GNButton(title);
    }

    public GNIconButton createFloatingButton() {
        GNIconButton button = new GNIconButton(Icons.ADD);
        button.getStyleClass().addAll("btn-float", "raised");
//        button.setGraphic(new IconContainer(Icons.ADD));
        return button;
    }

    public Button createBtnFlat(String title) {
        Button button = new Button(title);
        button.getStyleClass().add("btn-flat");
        return button;
    }

    public Button createIconized(String title) {
        Button button = new Button(title);
        button.setGraphic(new IconContainer(Icons.ANALYTICS));
        return button;
    }

    public Button createCancel(String title) {
        Button button = new Button(title);
        button.setCancelButton(true);
        return button;
    }
}
