package com.tronic.gimnasioapp

import com.tronic.gimnasioapp.beans.Actividades
import javafx.animation.TranslateTransition
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.scene.Group


class InscripcionPanelController {
    def model
    def view
    def inscripcionService
    def actividadesService
    def clientesService


    void mvcGroupInit(Map args) {
        view.cedulaField.textProperty().addListener({ ObservableValue observableValue, Object t, Object t1 ->
            String cedula = view.cedulaField.getText()
            if ((cedula ==~ /\d{1,2}\d{3}\d{3}/) == false) {
                model.clasesItems.clear()
                view.nombreField.text = ""
                view.apellidoField.text = ""
                view.telefonoMovilField.text = ""
                view.telefonoFijoField.text = ""
                view.direccionField.text = ""
                view.sexoCombo.value = ""
                view.emailField.text = ""
                view.cedulaField.style = '-fx-background-color:#2343f4'
                model.cedulaAvisoVIsible = true
                model.inscribirVisible = false
                model.retirarVisible = false
                model.modificarVisible = false
            } else if (cedula.isEmpty()) {
                view.cedulaField.style = '-fx-background-color:#2343f4'
                model.cedulaAvisoVIsible = true
            } else {
                view.cedulaField.style = '-fx-background-color:white'
                model.cedulaAvisoVIsible = false
                def valor = inscripcionService.corroborarCedula(view.cedulaField.text)

                if (valor != null) {
                    def cliente = clientesService.getCliente(valor.id)
                    List<Actividades> clases = actividadesService.getClasesPorCliente(cliente.id)
                    model.clasesItems.addAll(clases)
                    view.nombreField.text = cliente.nombre
                    view.apellidoField.text = cliente.apellido
                    view.telefonoMovilField.text = cliente.telf_movil
                    view.telefonoFijoField.text = cliente.telf_fijo
                    view.direccionField.text = cliente.direccion
                    view.sexoCombo.value = cliente.sexo
                    view.emailField.text = cliente.email
                    model.modificarVisible = true
                    model.inscribirVisible = false
                    model.retirarVisible = true
                } else {
                    view.cedulaField.style = '-fx-background-color:white'
                    model.cedulaAvisoVIsible = true
                    model.modificarVisible = false
                    model.inscribirVisible = true
                    model.retirarVisible = false
                }
            }

        } as ChangeListener)

        view.nombreField.textProperty().addListener({ ObservableValue o, Object t, Object t1 ->
            String nombre = view.nombreField.text
            if (nombre.isEmpty()) {
                view.nombreField.style = '-fx-background-color:#2343f4'
                model.nombreAvisoVIsible = true
            } else if ((nombre ==~ /[a-z-A-Z(\s)+]+/) == false) {
                view.nombreField.style = '-fx-background-color:#2343f4'
                model.nombreAvisoVIsible = true
            } else {
                view.nombreField.style = "-fx-background-color:white"
                model.nombreAvisoVIsible = false
            }

        } as ChangeListener)

        view.apellidoField.textProperty().addListener({ ObservableValue o, Object t, Object t1 ->
            String apellido = view.apellidoField.text
            if (apellido.isEmpty()) {
                view.apellidoField.style = '-fx-background-color:#2343f4'
                model.apellidoAvisoVIsible = true
            } else if ((apellido ==~ /[a-z-A-Z(\s)+]+/) == false) {
                view.apellidoField.style = '-fx-background-color:#2343f4'
                model.apellidoAvisoVIsible = true
            } else {
                view.apellidoField.style = "-fx-background-color:white"
                model.apellidoAvisoVIsible = false
            }

        } as ChangeListener)

        view.telefonoMovilField.textProperty().addListener({ ObservableValue o, Object t, Object t1 ->
            String telefonoMovil = view.telefonoMovilField.text
            if (telefonoMovil.isEmpty()) {
                view.telefonoMovilField.style = '-fx-background-color:#2343f4'
                model.telfMovilAvisoVIsible = true
            } else if ((telefonoMovil ==~ /0\d{3}\d{7}/) == false) {
                view.telefonoMovilField.style = "-fx-background-color:#2343f4"
                model.telfMovilAvisoVIsible = true
            } else {
                view.telefonoMovilField.style = '-fx-background-color:white'
                model.telfMovilAvisoVIsible = false
            }

        } as ChangeListener)

        view.telefonoFijoField.textProperty().addListener({ ObservableValue o, Object t, Object t1 ->
            String telefonoFijo = view.telefonoFijoField.text
            if ((telefonoFijo ==~ /0\d{3}\d{7}/) == false) {
                view.telefonoFijoField.style = "-fx-background-color:#2343f4"
                model.telfFijoAvisoVIsible = true
            } else {
                view.telefonoFijoField.style = '-fx-background-color:white'
                model.telfFijoAvisoVIsible = false
            }

        } as ChangeListener)

        view.emailField.textProperty().addListener({ ObservableValue o, Object t, Object t1 ->
            String email = view.emailField.text
            if ((email ==~ /^[_a-z-A-Z0-9-]+(\.[_a-z-A-Z-0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/) == false) {
                view.emailField.style = '-fx-background-color:#2343f4'
                model.emailAvisoVIsible = true
            } else {
                view.emailField.style = '-fx-background-color:white'
                model.emailAvisoVIsible = false
            }

        } as ChangeListener)
    }

    // void mvcGroupDestroy() {
    //    // this method is called when the group is destroyed
    // }

    def inscribir = { evt = null ->
        model.modificarVisible = false
        view.nombreField.editable = true
        view.apellidoField.editable = true
        view.telefonoMovilField.editable = true
        view.telefonoFijoField.editable = true
        view.emailField.editable = true
        view.direccionField.editable = true
        view.sexoCombo.disable = false
        model.aceptarCancelar = true
        model.inscribir = true
        model.aceptarCancelar = true
        model.inscribirVisible = false
    }

    def modificar = { evt = null ->
        model.modificarVisible = false
        view.nombreField.editable = true
        view.apellidoField.editable = true
        view.telefonoMovilField.editable = true
        view.telefonoFijoField.editable = true
        view.emailField.editable = true
        view.direccionField.editable = true
        view.sexoCombo.disable = false
        model.aceptarCancelar = true
    }

    def cancelar = {
        model.modificarVisible = false
        view.nombreField.editable = false
        view.apellidoField.editable = false
        view.telefonoMovilField.editable = false
        view.telefonoFijoField.editable = false
        view.emailField.editable = false
        view.direccionField.editable = false
        view.sexoCombo.disable = true
        model.aceptarCancelar = false


        view.nombreField.text = ""
        view.apellidoField.text = ""
        view.telefonoMovilField.text = ""
        view.telefonoFijoField.text = ""
        view.emailField.text = ""
        view.direccionField.text = ""

        model.modificarVisible = false
        model.inscribirVisible = false
        model.retirarVisible = false
    }

    def retirar = { evt = null ->

    }

    def aceptar = {
        println "inscribir " + model.inscribir
        if (model.inscribir) {
            println "entro"
            String cedula = view.cedulaField.getText()
            String nombre = view.nombreField.text
            String apellido = view.apellidoField.text
            String telefonoMovil = view.telefonoMovilField.text
            String telefonoFijo = view.telefonoFijoField.text
            String email = view.emailField.text


            if ((cedula ==~ /\d{1,2}\d{3}\d{3}/) == false) {
                view.cedulaField.style = '-fx-background-color:#2343f4'
                model.cedulaAvisoVIsible = true
            } else if (cedula.isEmpty()) {
                view.cedulaField.style = '-fx-background-color:#2343f4'
                model.cedulaAvisoVIsible = true
            } else if (nombre.isEmpty()) {
                view.nombreField.style = '-fx-background-color:#2343f4'
                model.nombreAvisoVIsible = true
            } else if ((nombre ==~ /[a-z-A-Z(\s)+]+/) == false) {
                view.nombreField.style = '-fx-background-color:#2343f4'
                model.nombreAvisoVIsible = true
            } else if (apellido.isEmpty()) {
                view.apellidoField.style = '-fx-background-color:#2343f4'
                model.apellidoAvisoVIsible = true
            } else if ((apellido ==~ /[a-z-A-Z(\s)+]+/) == false) {
                view.apellidoField.style = '-fx-background-color:#2343f4'
                model.apellidoAvisoVIsible = true
            } else if (telefonoMovil.isEmpty()) {
                view.telefonoMovilField.style = '-fx-background-color:#2343f4'
                model.telfMovilAvisoVIsible = true
            } else if ((telefonoMovil ==~ /0\d{3}\d{7}/) == false) {
                view.telefonoMovilField.style = "-fx-background-color:#2343f4"
                model.telfMovilAvisoVIsible = true
            } else {

                //cedula = f.parse(view.cedulaField.text).toString()

                def valor = inscripcionService.corroborarCedula(cedula)

                if (!valor) {
                    inscripcionService.inscribirCliente(cedula, view.nombreField.text, view.apellidoField.text
                            , view.sexoCombo.getValue(), view.telefonoMovilField.text,
                            view.telefonoFijoField.text, view.direccionField.text, view.emailField.text, new Date(), "nuevo")

                    valor = inscripcionService.corroborarCedula(cedula)

                    model.clasesItems.each {Actividades actividades ->
                        inscripcionService.inscribirClasesDelCliente(actividades.id, valor.id)
                    }
                    /*  try {
                   if (app.views.newActivity.view.newActivityPanel.isVisible()) {
                       app.views.newActivity.view.newActivityPanel.visible = false
                       destroyMVCGroup('newActivity')
                   }
               } catch (Exception e) {}
               TranslateTransition tr = view.tran2
               tr.play()
               destroyMVCGroup('inscripcionPanel')
               app.views.panelPrincipal.view.inscribirButton.disable = false
               execOutsideUI {
                   Group g = app.views.panelPrincipal.view.grupoDespliegue
                   Thread.sleep(800)
                   execInsideUIAsync {
                       //println g.getChildren().contains(view.inscripcionPantalla)
                       try {
                           if (g.getChildren().contains(view.inscripcionPantalla)) {
                               g.getChildren().remove(view.inscripcionPantalla)
                           }
                       } catch (Exception e) {

                       }
                   }
    //                    app.views.clientes.view.clientesPane.visible = true
                   // app.views.panelPrincipal.view.grupoDespliegue.getChildren().remove(view.inscripcionPantalla)
               }     */

                    view.nombreField.editable = false
                    view.apellidoField.editable = false
                    view.telefonoMovilField.editable = false
                    view.telefonoFijoField.editable = false
                    view.emailField.editable = false
                    view.direccionField.editable = false
                    view.sexoCombo.disable = true
                    model.aceptarCancelar = false

                    app.event("cleanClientesList")
                } else {
                    println "ya esta registrada la cedula"
                }
            }
            model.inscribir = false
        } else {
            model.aceptarCancelar = false
            String cedula = view.cedulaField.getText()
            String nombre = view.nombreField.text
            String apellido = view.apellidoField.text
            String telefonoMovil = view.telefonoMovilField.text
            String telefonoFijo = view.telefonoFijoField.text
            String email = view.emailField.text


            if ((cedula ==~ /\d{1,2}\d{3}\d{3}/) == false) {
                view.cedulaField.style = '-fx-background-color:#2343f4'
                model.cedulaAvisoVIsible = true
            } else if (cedula.isEmpty()) {
                view.cedulaField.style = '-fx-background-color:#2343f4'
                model.cedulaAvisoVIsible = true
            } else if (nombre.isEmpty()) {
                view.nombreField.style = '-fx-background-color:#2343f4'
                model.nombreAvisoVIsible = true
            } else if ((nombre ==~ /[a-z-A-Z(\s)+]+/) == false) {
                view.nombreField.style = '-fx-background-color:#2343f4'
                model.nombreAvisoVIsible = true
            } else if (apellido.isEmpty()) {
                view.apellidoField.style = '-fx-background-color:#2343f4'
                model.apellidoAvisoVIsible = true
            } else if ((apellido ==~ /[a-z-A-Z(\s)+]+/) == false) {
                view.apellidoField.style = '-fx-background-color:#2343f4'
                model.apellidoAvisoVIsible = true
            } else if (telefonoMovil.isEmpty()) {
                view.telefonoMovilField.style = '-fx-background-color:#2343f4'
                model.telfMovilAvisoVIsible = true
            } else if ((telefonoMovil ==~ /0\d{3}\d{7}/) == false) {
                view.telefonoMovilField.style = "-fx-background-color:#2343f4"
                model.telfMovilAvisoVIsible = true
            } else {

                def valor = inscripcionService.corroborarCedula(cedula)


                inscripcionService.actualizarCliente(cedula, view.nombreField.text, view.apellidoField.text, view.sexoCombo.getValue(), view.telefonoMovilField.text,
                        view.telefonoFijoField.text, view.direccionField.text, view.emailField.text, "nuevo")

                valor = inscripcionService.corroborarCedula(cedula)

                model.clasesItems.each {Actividades actividades ->
                    inscripcionService.inscribirClasesDelCliente(actividades.id, valor.id)
                }
                /*  try {
               if (app.views.newActivity.view.newActivityPanel.isVisible()) {
                   app.views.newActivity.view.newActivityPanel.visible = false
                   destroyMVCGroup('newActivity')
               }
           } catch (Exception e) {}
           TranslateTransition tr = view.tran2
           tr.play()
           destroyMVCGroup('inscripcionPanel')
           app.views.panelPrincipal.view.inscribirButton.disable = false
           execOutsideUI {
               Group g = app.views.panelPrincipal.view.grupoDespliegue
               Thread.sleep(800)
               execInsideUIAsync {
                   //println g.getChildren().contains(view.inscripcionPantalla)
                   try {
                       if (g.getChildren().contains(view.inscripcionPantalla)) {
                           g.getChildren().remove(view.inscripcionPantalla)
                       }
                   } catch (Exception e) {

                   }
               }
                //app.views.clientes.view.clientesPane.visible = true
               // app.views.panelPrincipal.view.grupoDespliegue.getChildren().remove(view.inscripcionPantalla)
           }     */

                view.nombreField.editable = false
                view.apellidoField.editable = false
                view.telefonoMovilField.editable = false
                view.telefonoFijoField.editable = false
                view.emailField.editable = false
                view.direccionField.editable = false
                view.sexoCombo.disable = true
                model.aceptarCancelar = false

                app.event("cleanClientesList")
            }
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
                try {
                    if (g.getChildren().contains(view.inscripcionPantalla)) {
                        g.getChildren().remove(view.inscripcionPantalla)
                    }
                } catch (Exception e) {}
            }
            app.views.inscripcionPanel.view.inscripcionPantalla.visible = true
            // app.views.panelPrincipal.view.grupoDespliegue.getChildren().remove(view.inscripcionPantalla)
        }
    }

    def cerrar = {
        try {
            if (app.views.newActivity.view.newActivityPanel.isVisible()) {
                app.views.newActivity.view.newActivityPanel.visible = false
                destroyMVCGroup('newActivity')
            }
        } catch (Exception e) {}
        TranslateTransition tr = view.tran2
        tr.play()
        destroyMVCGroup('inscripcionPanel')
        app.views.panelPrincipal.view.inscribirButton.disable = false
        execOutsideUI {
            Group g = app.views.panelPrincipal.view.grupoDespliegue
            Thread.sleep(800)
            execInsideUIAsync {
                //println g.getChildren().contains(view.inscripcionPantalla)
                try {
                    if (g.getChildren().contains(view.inscripcionPantalla)) {
                        g.getChildren().remove(view.inscripcionPantalla)
                    }
                } catch (Exception e) {

                }
            }
            //  app.views.inscripcionPanel.view.inscripcionPantalla.visible = true
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
