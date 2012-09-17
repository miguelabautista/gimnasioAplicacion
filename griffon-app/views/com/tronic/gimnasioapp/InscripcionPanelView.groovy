package com.tronic.gimnasioapp

import javafx.scene.image.ImageView

anchorPane(id: "inscripcionPantalla", prefHeight: 510.0, prefWidth: 800.0, bottomAnchor: 0.0, leftAnchor: 0.0, rightAnchor: 0.0, topAnchor: 90.0) {
    titledPane(animated: false, collapsible: false, prefHeight: 356.0, prefWidth: 289.0, text: "Datos Personales", leftAnchor: 20.0, topAnchor: 14.0) {
        anchorPane(minHeight: 0.0, minWidth: 0.0, prefHeight: 180.0, prefWidth: 200.0) {
            hbox(alignment: "CENTER", layoutX: 14.0, layoutY: 12.0, spacing: 5.0) {
                label(text: "Cedula")
                choiceBox(id: "tipoCedulaCombo", minHeight: 16.0, prefHeight: 21.0, prefWidth: 37.0, items: ['V', 'E'], value: 'V')
                textField(id: "cedulaField", prefWidth: 106.0)
                label(id: "avisoCedula", graphic: new ImageView(model.caution), visible: bind(model.cedulaAvisoVIsibleProperty))
            }
            hbox(alignment: "CENTER", layoutX: 23.0, layoutY: 46.0, spacing: 5.0) {
                label(text: "Nombre")
                textField(id: "nombreField", editable: false, prefWidth: 143.0)
                label(id: "avisoNombre", graphic: new ImageView(model.caution), visible: bind(model.nombreAvisoVIsibleProperty))
            }
            hbox(alignment: "CENTER", layoutX: 23.0, layoutY: 83.0, spacing: 5.0) {
                label(text: "Apellido")
                textField(id: "apellidoField", editable: false, prefWidth: 140.0)
                label(id: "avisoApellido", graphic: new ImageView(model.caution), visible: bind(model.apellidoAvisoVIsibleProperty))
            }
            hbox(alignment: "CENTER", layoutX: 43.0, layoutY: 119.0, spacing: 5.0) {
                label(text: "Sexo")
                choiceBox(id: "sexoCombo", disable: true, prefWidth: 106.0, items: ['Masculino', 'Femenino'], value: 'Masculino')
            }
            hbox(alignment: "CENTER", layoutX: 14.0, layoutY: 155.0, spacing: 5.0) {
                label(text: "Telefono Movil")
                textField(id: "telefonoMovilField", editable: false, prefWidth: 140.0)
                label(id: "avisoMovil", graphic: new ImageView(model.caution), visible: bind(model.telfMovilAvisoVIsibleProperty))
            }
            hbox(alignment: "CENTER", layoutX: 25.0, layoutY: 188.0, spacing: 5.0) {
                label(text: "Telefono Fijo")
                textField(id: "telefonoFijoField", editable: false, prefWidth: 140.0)
                label(id: "avisoFijo", graphic: new ImageView(model.caution), visible: bind(model.telfFijoAvisoVIsibleProperty))
            }
            hbox(alignment: "CENTER", layoutX: 45.0, layoutY: 227.0, spacing: 5.0) {
                label(text: "Direccion")
                textArea(id: "direccionField", editable: false, prefHeight: 46.0, prefWidth: 140.0, wrapText: true)
            }
            hbox(alignment: "CENTER", layoutX: 67.0, layoutY: 279.0, spacing: 5.0) {
                label(text: "email")
                textField(id: "emailField", editable: false, prefWidth: 140.0)
                label(id: "avisoEmail", graphic: new ImageView(model.caution), visible: bind(model.emailAvisoVIsibleProperty))
            }
            label(id: "avisoGeneralDatos", layoutY: 308.0, prefWidth: 137.0, leftAnchor: 101.0, rightAnchor: 47.0, text: 'debe llenar correctamente los campos requeridos', visible: bind(model.avisoGeneralProperty))
        }
    }
    titledPane(animated: false, collapsible: false, prefHeight: 356.0000999999975, prefWidth: 343.0, text: "Inscripciones Adicionales", rightAnchor: 133.0, topAnchor: 14.0) {
        anchorPane(minHeight: 0.0, minWidth: 0.0, prefHeight: 180.0, prefWidth: 200.0) {
            tableView(id: "inscripcionesTabla", layoutX: 14.0, layoutY: 14.0, prefHeight: 259.0, prefWidth: 313.0) {
                inscripcionesTabla.setItems(model.clasesItems)
                tableColumn(maxWidth: 90.0, prefWidth: 90.0, text: "Nombre", property: 'nombre')
                tableColumn(maxWidth: 90.0, prefWidth: 90.0, text: "Dia", property: 'dia')
                tableColumn(maxWidth: 90.0, prefWidth: 90.0, text: "Horario", property: 'desde')
                tableColumn(maxWidth: 90.0, prefWidth: 90.0, text: "Instructor", property: 'instructor')
            }
            button(id: "aceptarInscripcion", layoutX: 96.0, layoutY: 294.0, mnemonicParsing: false, text: "Agregar", onAction: controller.agregarNewActivity)
            button(id: "cancelarInscripcion", layoutX: 172.0, layoutY: 294.0, mnemonicParsing: false, text: "Quitar", onAction: controller.quitarNewActivity)
        }
    }
    titledPane(animated: false, layoutX: 18.0, layoutY: 379.0, prefHeight: 117.0, prefWidth: 359.0, text: "Cuenta") {
        anchorPane(minHeight: 0.0, minWidth: 0.0, prefHeight: 180.0, prefWidth: 200.0) {
            label(layoutX: 223.0, layoutY: 14.0, text: "Status")
            textField(editable: false, layoutX: 256.0, layoutY: 11.0, prefWidth: 95.0)
            label(layoutX: 12.0, layoutY: 50.0, text: "Saldo")
            textField(editable: false, layoutX: 49.0, layoutY: 47.0, prefWidth: 120.0)
            label(layoutX: 12.0, layoutY: 14.0, text: "Fecha ingreso")
            textField(editable: false, layoutX: 89.0, layoutY: 11.0, prefWidth: 127.0)
            button(layoutX: 178.0, layoutY: 48.0, mnemonicParsing: false, text: "Pagar",visible:bind(model.pagarVisibleProperty))
        }
    }
    hbox(alignment: "CENTER", layoutX: 533.0, layoutY: 475.0, spacing: 5.0) {
        button(defaultButton: true, text: "_Inscribir", onAction: controller.inscribir,visible:bind(model.inscribirVisibleProperty))
        button(text: "_Modificar", onAction: controller.modificar,visible:bind(model.modificarVisibleProperty))
        button(text: '_Retirar', onAction: controller.retirar,visible:bind(model.retirarVisibleProperty))
        button(cancelButton: true, text: "_Salir", onAction: controller.cerrar)
    }
    hbox(alignment: "CENTER", layoutX: 386.0, layoutY: 475.0, spacing: 5.0,visible:bind(model.aceptarCancelarProperty)) {
        button(mnemonicParsing: false, text: "Aceptar",onAction:controller.aceptar)
        button(mnemonicParsing: false, text: "Cancelar",onAction: controller.cancelar)
    }
}
tran = translateTransition(node: inscripcionPantalla, duration: 500.ms, fromX: -800, toX: 0.0, autoReverse: true).play()
tran2 = translateTransition(node: inscripcionPantalla, duration: 500.ms, fromX: 0.0, toX: -800.0, autoReverse: true)