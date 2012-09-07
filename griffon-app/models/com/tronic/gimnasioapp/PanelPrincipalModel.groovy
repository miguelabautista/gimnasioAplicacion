package com.tronic.gimnasioapp

import groovyx.javafx.beans.FXBindable

class PanelPrincipalModel {
    @FXBindable String message

    void mvcGroupInit(Map args) {
        message = 'PanelPrincipal Group'
    }
}
