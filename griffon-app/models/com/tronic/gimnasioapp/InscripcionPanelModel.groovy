package com.tronic.gimnasioapp

import groovyx.javafx.beans.FXBindable

class InscripcionPanelModel {
    @FXBindable String message

    void mvcGroupInit(Map args) {
        message = 'InscripcionPanel Group'
    }
}
