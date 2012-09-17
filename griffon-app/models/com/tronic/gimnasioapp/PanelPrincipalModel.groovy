package com.tronic.gimnasioapp

import groovyx.javafx.beans.FXBindable
import javafx.scene.image.Image

class PanelPrincipalModel {
    double mouseDragOffsetX = 0
    double mouseDragOffsetY = 0
    @FXBindable String message
    @FXBindable boolean isAdmin

    Image exitIcon = new Image(getClass().getResourceAsStream("/search-clear.png"))
    Image minimizeIcon = new Image(getClass().getResourceAsStream("/window-min.png"))
    Image userIcon = new Image(getClass().getResourceAsStream('/user.png'))
    Image instructorIcon = new Image(getClass().getResourceAsStream('/user_beach_lifeguard.png'))

    void mvcGroupInit(Map args) {
        isAdmin = args.isAdmin
    }
}
