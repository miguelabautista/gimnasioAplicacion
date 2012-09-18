package com.tronic.gimnasioapp

import com.tronic.gimnasioapp.beans.Actividades
import javafx.animation.TranslateTransition
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.collections.ListChangeListener
import javafx.scene.Group
import javafx.scene.control.ChoiceBox

class InscripcionPanelController {
    def model
    def view
    def inscripcionService
    def actividadesService
    def clientesService
    Timer timer
    List<Actividades> eliminados = []

    void mvcGroupInit(Map args) {
        timer = new Timer()
        timer.schedule(new CorroborarStatus(), 1000, 1000)
        ChoiceBox cho = view.tipoCedulaCombo
        view.tipoCedulaCombo.getItems().addListener(new ListChangeListener<>() {

            @Override
            void onChanged(ListChangeListener.Change change) {
                println "cambio 1"
                def valor = view.tipoCedulaCombo.value
                println "valor " + valor
            }
        })

        view.tipoCedulaCombo.selectionModelProperty().addListener(new ChangeListener<>() {

            @Override
            void changed(ObservableValue observableValue, Object t, Object t1) {
                println "cambio 2"
                def valor = view.tipoCedulaCombo.value
                println "valor2 " + valor
            }
        })

        view.tipoCedulaCombo.itemsProperty().addListener(new ChangeListener<>() {

            @Override
            void changed(ObservableValue observableValue, Object t, Object t1) {
                println "cambio 3"
                def valor = view.tipoCedulaCombo.value
                println "valor3 " + valor
            }
        })



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
                view.fechaIngresoField.text = ""
                view.statusField.text = ""
                view.saldoField.text = ""
                view.cedulaField.style = '-fx-background-color:#2343f4'
                model.cedulaAvisoVIsible = true
                model.inscribirVisible = false
                model.retirarVisible = false
                model.modificarVisible = false
            } else if (cedula.isEmpty()) {
                view.cedulaField.style = '-fx-background-color:#2343f4'
                model.cedulaAvisoVIsible = true
            } else if (view.tipoCedulaCombo.value == "E" && (cedula ==~ /[8]+[0,1,2]+(\d){6}+/) == false) {
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
            } else {
                view.cedulaField.style = '-fx-background-color:white'
                model.cedulaAvisoVIsible = false
                def valor = inscripcionService.corroborarCedula(view.cedulaField.text)

                if (valor != null) {
                    model.cedulaAvisoVIsible = false
                    def cliente = clientesService.getCliente(valor.id)
                    List<Actividades> clases = actividadesService.getClasesPorCliente(cliente.id)

                    def proximoPago = cliente.fecha_egreso

                    def fechaActual = new Date()
                    if (fechaActual == proximoPago) {
                        view.statusField.text = "deudor"
                        inscripcionService.actualizarCliente(cliente.cedula, view.nombreField.text, view.apellidoField.text, view.sexoCombo.getValue(), view.telefonoMovilField.text,
                                view.telefonoFijoField.text, view.direccionField.text, view.emailField.text, view.statusField.text)
                    } else {
                        view.statusField.text = cliente.status
                    }
                    model.clasesItems.addAll(clases)
                    view.nombreField.text = cliente.nombre
                    view.apellidoField.text = cliente.apellido
                    view.telefonoMovilField.text = cliente.telf_movil
                    view.telefonoFijoField.text = cliente.telf_fijo
                    view.direccionField.text = cliente.direccion
                    view.sexoCombo.value = cliente.sexo
                    view.emailField.text = cliente.email
                    view.fechaIngresoField.text = cliente.fecha_ingreso
                    view.saldoField.text = cliente.mensualidad
                    model.modificarVisible = true
                    model.inscribirVisible = false
                    model.retirarVisible = true
                } else {
                    model.cedulaAvisoVIsible = true
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
        model.botonesClase = false
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
        model.botonesClase = false
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

        model.botonesClase = true

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

    def pagar = { evt = null ->
        def monto = view.saldoField.text
        def mvc = buildMVCGroup('pagarPanel', [monto: monto, cedula: view.cedulaField.text])

        mvc.controller.show(app.views.panelPrincipal.view.principalStage)
    }

    def aceptar = {
        println "inscribir " + model.inscribir
        if (model.inscribir) {
            println "entro a inscribir"
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
            } else if (view.tipoCedulaCombo.value == "E" && (cedula ==~ /[8]+[0,1,2]+(\d){6}+/) == false) {
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
                def fechaDeIngreso = new Date()
                def mensual = 0

                model.clasesItems.each {Actividades actividades ->
                    mensual += actividades.mensualidad
                }

                def proximoPago = new Date() + 35

                if (!valor) {
                    inscripcionService.inscribirCliente(cedula, view.nombreField.text, view.apellidoField.text
                            , view.sexoCombo.getValue(), view.telefonoMovilField.text,
                            view.telefonoFijoField.text, view.direccionField.text, view.emailField.text, fechaDeIngreso, "deudor", mensual, proximoPago)

                    valor = inscripcionService.corroborarCedula(cedula)

                    println "tamano de lista " + model.clasesItems.size()

                    //TODO Corroborar redundancia
                    model.clasesItems.each {Actividades actividades ->

                        inscripcionService.inscribirClasesDelCliente(actividades.id, valor.id)

                    }

                    view.fechaIngresoField.text = fechaDeIngreso.toString()

                    view.statusField.text = "deudor"

                    view.saldoField.text = String.valueOf(mensual)
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
                    model.botonesClase = true

                    model.inscribir = false

                    app.event("cleanClientesList")
                } else {
                    println "ya esta registrada la cedula"
                }
            }
        } else {
            println "entro a modificar"
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
            } else if (view.tipoCedulaCombo.value == "E" && (cedula ==~ /[8]+[0,1,2]+(\d){6}+/) == false) {
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

                def mensual = 0
                def valor = inscripcionService.corroborarCedula(cedula)


                valor = inscripcionService.corroborarCedula(cedula)

                if (eliminados.size() > 0) {
                    eliminados.each {Actividades actividades ->
                        println actividades.id
                        inscripcionService.borrarClasesDelCliente(actividades.id, valor.id)
                    }
                }

                if (model.ItemsAgregados.size() > 0) {
                    view.statusField.text = "deudor"
                    model.ItemsAgregados.each {Actividades actividades ->
                        try {
                            inscripcionService.inscribirClasesDelCliente(actividades.id, valor.id)
                        } catch (Exception e) {
                            execInsideUIAsync {
                                view.avisoGeneralDatos.text = "la actividad: ${actividades.nombre} ya existe"
                                view.fadein.play()
                                view.fadein2.play()
                                model.clasesItems.remove actividades
                            }
                        }
                    }
                }


                model.clasesItems.each { Actividades actividades ->
                    mensual += actividades.mensualidad
                }

                if (eliminados == 0) {
                    inscripcionService.actualizarCliente(cedula, view.nombreField.text, view.apellidoField.text, view.sexoCombo.getValue(), view.telefonoMovilField.text,
                            view.telefonoFijoField.text, view.direccionField.text, view.emailField.text, view.statusField.text, valor.mensualidad, valor.fecha_egreso)
                } else {
                    inscripcionService.actualizarCliente(cedula, view.nombreField.text, view.apellidoField.text, view.sexoCombo.getValue(), view.telefonoMovilField.text,
                            view.telefonoFijoField.text, view.direccionField.text, view.emailField.text, view.statusField.text, mensual, valor.fecha_egreso)
                }

                view.saldoField.text = String.valueOf(mensual)
                /*  if (model.clasesItems.size() != 0) {
                   println "inscribiendo"
                   model.clasesItems.each {Actividades actividades ->
                       inscripcionService.inscribirClasesDelCliente(actividades.id, valor.id)
                   }
               } */
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
                model.botonesClase = true
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
        timer.cancel()
        timer.purge()
        destroyMVCGroup('inscripcionPanel')
        try {
            app.views.panelPrincipal.view.inscribirButton.disable = false
            app.views.panelPrincipal.view.clientesButton.disable = false
            app.views.panelPrincipal.view.actividadesButton.disable = false
        } catch (Exception e) {}
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

        def mvc

        if (!model.inscribir) {
            println "entro a inscribir nuesds "
            mvc = buildMVCGroup('newActivity', [inscribir: true])
        } else {
            mvc = buildMVCGroup('newActivity')
        }

        def pantalla = mvc.view.newActivityPanel

        execInsideUIAsync {
            app.views.panelPrincipal.view.grupoDespliegue.getChildren().add(pantalla)
        }
    }

    def quitarNewActivity = { evt = null ->
        eliminados.add view.inscripcionesTabla.selectionModel.selectedItem
        model.clasesItems.remove view.inscripcionesTabla.selectionModel.selectedItem
    }

    class CorroborarStatus extends TimerTask {

        @Override
        void run() {
            def valor
            try {
                valor = view.saldoField.text
            } catch (Exception e) { println e}
            if (valor.size() > 0) {
                execInsideUIAsync {
                    model.pagarVisible = true
                }
            } else if (valor == "0") {
                execInsideUIAsync {
                    model.pagarVisible = false
                }
            } else {
                execInsideUIAsync {
                    model.pagarVisible = false
                }
            }
        }
    }

}
