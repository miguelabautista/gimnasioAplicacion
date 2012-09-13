package com.tronic.gimnasioapp

import javafx.animation.TranslateTransition
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.event.Event
import javafx.event.EventType
import javafx.scene.Group
import javafx.scene.control.TextField
import javafx.scene.input.InputMethodEvent

import java.text.NumberFormat
import com.tronic.gimnasioapp.beans.Actividades

class InscripcionPanelController {
    def model
    def view
    def inscripcionService
    def actividadesService
    NumberFormat f = NumberFormat.getInstance(new Locale("es", "ve"))


    void mvcGroupInit(Map args) {
        view.cedulaField.textProperty().addListener({ ObservableValue observableValue, Object t, Object t1 ->
            String result = view.cedulaField.getText()
            if (result ==~ /\d{8}/) {
                view.cedulaField.text = f.format result.toInteger()
                view.cedulaField.style = '-fx-background-color:white'
                model.cedulaAvisoVIsible = false
            } else if (result ==~ /\d{2}[.]\d{3}[.]\d{3}/) {
                view.cedulaField.style = '-fx-background-color:white'
                model.cedulaAvisoVIsible = false
            } else if (result.isEmpty()) {
                view.cedulaField.style = '-fx-background-color:#2343f4'
                model.cedulaAvisoVIsible = true
            } else {
                view.cedulaField.style = '-fx-background-color:#2343f4'
                model.cedulaAvisoVIsible = true
            }

        } as ChangeListener)

        view.nombreField.textProperty().addListener({ ObservableValue o, Object t, Object t1 ->
            String result = view.nombreField.text
            if (result.isEmpty()) {
                view.nombreField.style = '-fx-background-color:#2343f4'
                model.nombreAvisoVIsible = true
            } else {
                view.nombreField.style = "-fx-background-color:white"
                model.nombreAvisoVIsible = false
            }

        } as ChangeListener)

        view.apellidoField.textProperty().addListener({ ObservableValue o, Object t, Object t1 ->
            String result = view.apellidoField.text
            if (result.isEmpty()) {
                view.apellidoField.style = '-fx-background-color:#2343f4'
                model.apellidoAvisoVIsible = true
            } else {
                view.apellidoField.style = "-fx-background-color:white"
                model.apellidoAvisoVIsible = false
            }

        } as ChangeListener)

        view.telefonoMovilField.textProperty().addListener({ ObservableValue o, Object t, Object t1 ->
            String result = view.telefonoMovilField.text
            if (result.isEmpty()) {
                view.telefonoMovilField.style = '-fx-background-color:#2343f4'
                model.telfMovilAvisoVIsible = true
            } else if (result ==~ /0\d{3}\d{7}/) {
                view.telefonoMovilField.style = "-fx-background-color:white"
                model.telfMovilAvisoVIsible = false
            } else {
                view.telefonoMovilField.style = '-fx-background-color:#2343f4'
                model.telfMovilAvisoVIsible = true
            }

        } as ChangeListener)

        view.telefonoFijoField.textProperty().addListener({ ObservableValue o, Object t, Object t1 ->
            String result = view.telefonoFijoField.text
            if (result ==~ /0\d{3}\d{7}/) {
                view.telefonoFijoField.style = "-fx-background-color:white"
                model.telfFijoAvisoVIsible = false
            } else if (result.isEmpty()) {
                view.telefonoFijoField.style = "-fx-background-color:white"
                model.telfFijoAvisoVIsible = false
            } else {
                view.telefonoFijoField.style = '-fx-background-color:#2343f4'
                model.telfFijoAvisoVIsible = true
            }

        } as ChangeListener)

        view.emailField.textProperty().addListener({ ObservableValue o, Object t, Object t1 ->
            String result = view.emailField.text
            if (result ==~ /^[_a-z-A-Z0-9-]+(\.[_a-z-A-Z-0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/) {
                view.emailField.style = '-fx-background-color:white'
                model.emailAvisoVIsible = false
            } else {
                view.emailField.style = '-fx-background-color:#2343f4'
                model.emailAvisoVIsible = true
            }

        } as ChangeListener)
    }

    // void mvcGroupDestroy() {
    //    // this method is called when the group is destroyed
    // }

    def aceptar = { evt = null ->
        TextField te = view.nombreField

        model.clasesItems.each{
            println it.id
        }

        String cedula = f.parse(view.cedulaField.text).toString()

        def valor = inscripcionService.corroborarCedula(cedula)

        if (!valor) {
            inscripcionService.inscribirCliente(cedula, view.nombreField.text, view.apellidoField.text, view.sexoCombo.getValue(), view.telefonoMovilField.text)

            valor = inscripcionService.corroborarCedula(cedula)

            model.clasesItems.each{Actividades actividades ->
                inscripcionService.inscribirClasesDelCliente(actividades.id,valor.id)
            }
            try{
                if (app.views.newActivity.view.newActivityPanel.isVisible()) {
                    app.views.newActivity.view.newActivityPanel.visible = false
                    destroyMVCGroup('newActivity')
                }
            }catch(Exception e){}
            TranslateTransition tr = view.tran2
            tr.play()
            destroyMVCGroup('inscripcionPanel')
            app.views.panelPrincipal.view.inscribirButton.disable = false
            execOutsideUI {
                Group g = app.views.panelPrincipal.view.grupoDespliegue
                Thread.sleep(800)
                execInsideUIAsync {
                    //println g.getChildren().contains(view.inscripcionPantalla)
                    try{
                        if (g.getChildren().contains(view.inscripcionPantalla)) {
                            g.getChildren().remove(view.inscripcionPantalla)
                        }
                    }catch(Exception e){

                    }
                }
                app.views.clientes.view.clientesPane.visible = true
                // app.views.panelPrincipal.view.grupoDespliegue.getChildren().remove(view.inscripcionPantalla)
            }

            app.event("cleanClientesList")
        } else {
            println "ya esta registrada la cedula"
        }

    }

    def onCloseInscripcion = {
        TranslateTransition tr = view.tran2
        tr.play()
        destroyMVCGroup('inscripcionPanel')
        app.views.panelPrincipal.view.inscribirButton.disable = false
        execOutsideUI {
            Group g = app.views.panelPrincipal.view.grupoDespliegue
            Thread.sleep(800)
            execInsideUIAsync {
                println g.getChildren().contains(view.inscripcionPantalla)
                if (g.getChildren().contains(view.inscripcionPantalla)) {
                    g.getChildren().remove(view.inscripcionPantalla)
                }
            }
            app.views.clientes.view.clientesPane.visible = true
            // app.views.panelPrincipal.view.grupoDespliegue.getChildren().remove(view.inscripcionPantalla)
        }
    }

    def cerrar = {
        try{
        if (app.views.newActivity.view.newActivityPanel.isVisible()) {
            app.views.newActivity.view.newActivityPanel.visible = false
            destroyMVCGroup('newActivity')
        }
        }catch(Exception e){}
        TranslateTransition tr = view.tran2
        tr.play()
        destroyMVCGroup('inscripcionPanel')
        app.views.panelPrincipal.view.inscribirButton.disable = false
        execOutsideUI {
            Group g = app.views.panelPrincipal.view.grupoDespliegue
            Thread.sleep(800)
            execInsideUIAsync {
                //println g.getChildren().contains(view.inscripcionPantalla)
                try{
                if (g.getChildren().contains(view.inscripcionPantalla)) {
                    g.getChildren().remove(view.inscripcionPantalla)
                }
                }catch(Exception e){

                }
            }
            app.views.clientes.view.clientesPane.visible = true
            // app.views.panelPrincipal.view.grupoDespliegue.getChildren().remove(view.inscripcionPantalla)
        }
    }

    def agregarNewActivity = { evt = null ->

        def mvc = buildMVCGroup('newActivity')

        def pantalla = mvc.view.newActivityPanel

        execInsideUIAsync {
            app.views.panelPrincipal.view.grupoDespliegue.getChildren().add(pantalla)
        }
    }

    def quitarNewActivity = { evt = null ->
        model.clasesItems.remove view.inscripcionesTabla.selectionModel.selectedItem
    }


}
