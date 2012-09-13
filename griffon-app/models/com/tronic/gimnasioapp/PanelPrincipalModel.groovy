package com.tronic.gimnasioapp

import groovyx.javafx.beans.FXBindable
import javafx.scene.image.Image

class PanelPrincipalModel {
    double mouseDragOffsetX = 0
    double mouseDragOffsetY = 0
    @FXBindable String message

    Image exitIcon = new Image(getClass().getResourceAsStream("/search-clear.png"))
    Image minimizeIcon = new Image(getClass().getResourceAsStream("/window-min.png"))

    void mvcGroupInit(Map args) {
    }
}
