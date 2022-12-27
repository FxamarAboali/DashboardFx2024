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

package io.github.gleidsonmt.dashboardfx.core.app.material.controls;

import io.github.gleidsonmt.dashboardfx.core.app.interfaces.View;

public class ControlViewPanelCreator implements BuildCreator {
    private ControlViewPanel body;

    public ControlViewPanelCreator() {
        this.body = new ControlViewPanel();
    }

    @Override
    public View build() {
        registerRoute(body, "Control IView", "control_view_pane");
        return null;
    }

    private void registerRoute(ControlViewPanel root, String title, String name) {
//        context.getRoutes().addView(
//                context.getRoutes().load(root, title, name)
//        );
    }
}