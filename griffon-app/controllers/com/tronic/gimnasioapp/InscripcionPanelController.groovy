package com.tronic.gimnasioapp

import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.scene.control.TextField

import java.text.NumberFormat

class InscripcionPanelController {
    def model
    def view
    NumberFormat f = NumberFormat.getInstance(new Locale("es", "ve"))


    void mvcGroupInit(Map args) {
        TextField te = view.cedulaField
        te.textProperty().addListener({ ObservableValue observableValue, Object t, Object t1 ->
            String result = te.getText()
            if (result ==~ /\d{8}/) {
                te.text = f.format result.toInteger()
                te.style = '-fx-background-color:white'
            } else if (result ==~ /\d{2}[.]\d{3}[.]\d{3}/) {
                te.style = '-fx-background-color:white'
            } else if (result.isEmpty()) {
                te.style = '-fx-background-color:yellow'
            }
            else {
                te.style = '-fx-background-color:red'
            }

        } as ChangeListener)

        view.nombreField.textProperty().addListener({ ObservableValue o, Object t, Object t1 ->
            String result = view.nombreField.text
            if (result.isEmpty()) {
                view.nombreField.style = "-fx-border-radius: 3px 6px"
            } else {
                view.nombreField.style = "-fx-background-color:white"
            }

        } as ChangeListener)

        view.apellidoField.textProperty().addListener({ ObservableValue o, Object t, Object t1 ->
            String result = view.apellidoField.text
            if (result.isEmpty()) {
                view.apellidoField.style = "-fx-border-radius:  5, 5, 4, 3"
            } else {
                view.apellidoField.style = "-fx-background-color:white"
            }

        } as ChangeListener)

        view.telefonoMovilField.textProperty().addListener({ ObservableValue o, Object t, Object t1 ->
            String result = view.telefonoMovilField.text
            if (result.isEmpty()) {
                view.telefonoMovilField.style = "-fx-border-radius:  5, 5, 4, 3"
            } else if (result ==~ /0\d{3}\d{7}/) {
                view.telefonoMovilField.style = "-fx-background-color:white"
            } else {
                view.telefonoMovilField.style = "-fx-background-color:blue"
            }

        } as ChangeListener)

        view.telefonoFijoField.textProperty().addListener({ ObservableValue o, Object t, Object t1 ->
            String result = view.telefonoFijoField.text
            if (result.isEmpty()) {
                view.telefonoFijoField.style = "-fx-background-color:red"
            } else if (result ==~ /0\d{3}\d{7}/) {
                view.telefonoFijoField.style = "-fx-background-color:white"
            } else {
                view.telefonoFijoField.style = "-fx-background-color:blue"
            }

        } as ChangeListener)

        view.emailField.textProperty().addListener({ ObservableValue o, Object t, Object t1 ->
            String result = view.emailField.text

        } as ChangeListener)


    }

    // void mvcGroupDestroy() {
    //    // this method is called when the group is destroyed
    // }

    def cerrar = {
        execInsideUIAsync {
            app.views.panelPrincipal.view.grupoDespliegue.getChildren().remove(view.inscripcionPantalla)
        }
        destroyMVCGroup('inscripcionPanel')
        app.views.panelPrincipal.view.inscribirButton.disable = false
    }


}
