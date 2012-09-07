package com.tronic.gimnasioapp

anchorPane(id: "inscripcionPantalla", prefHeight: 600.0, prefWidth: 800.0) {
    titledPane(animated: false, collapsible: false, prefHeight: 356.0, prefWidth: 289.0, text: "Datos Personales", leftAnchor: 14.0, topAnchor: 14.0) {
        anchorPane(minHeight: 0.0, minWidth: 0.0, prefHeight: 180.0, prefWidth: 200.0) {
            hbox(alignment: "CENTER", layoutX: 14.0, layoutY: 12.0, spacing: 5.0) {
                label(text: "Cedula")
                choiceBox(id: "tipoCedulaCombo", minHeight: 16.0, prefHeight: 21.0, prefWidth: 37.0,items:['V','E'],value:'V')
                textField(id: "cedulaField", prefWidth: 106.0)
                label(id: "avisoCedula")
            }
            hbox(alignment: "CENTER", layoutX: 23.0, layoutY: 46.0, spacing: 5.0) {
                label(text: "Nombre")
                textField(id: "nombreField",prefWidth: 143.0)
                label(id: "avisoNombre")
            }
            hbox(alignment: "CENTER", layoutX: 23.0, layoutY: 83.0, spacing: 5.0) {
                label(text: "Apellido")
                textField(id: "apellidoField", prefWidth: 140.0)
                label(id: "avisoApellido")
            }
            hbox(alignment: "CENTER", layoutX: 43.0, layoutY: 119.0, spacing: 5.0) {
                label(text: "Sexo")
                choiceBox(id: "sexoCombo", prefWidth: 106.0,items:['Masculino','Femenino'],value:'Masculino')
            }
            hbox(alignment: "CENTER", layoutX: 14.0, layoutY: 155.0, spacing: 5.0) {
                label(text: "Telefono Movil")
                textField(id: "telefonoMovilField", prefWidth: 140.0)
                label(id: "avisoMovil")
            }
            hbox(alignment: "CENTER", layoutX: 25.0, layoutY: 188.0, spacing: 5.0) {
                label(text: "Telefono Fijo")
                textField(id: "telefonoFijoField", prefWidth: 140.0)
                label(id: "avisoFijo")
            }
            hbox(alignment: "CENTER", layoutX: 45.0, layoutY: 227.0, spacing: 5.0) {
                label(text: "Direccion")
                textArea(id: "direccionField", prefHeight: 46.0, prefWidth: 140.0, wrapText: true)
            }
            hbox(alignment: "CENTER", layoutX: 67.0, layoutY: 279.0, spacing: 5.0) {
                label(text: "email")
                textField(id:"emailField",prefWidth: 140.0)
                label(id: "avisoEmail")
            }
            label(id: "avisoGeneralDatos", layoutY: 308.0, prefWidth: 137.0, leftAnchor: 101.0, rightAnchor: 47.0)
        }
    }
    titledPane(animated: false, collapsible: false, prefHeight: 356.0000999999975, prefWidth: 323.0, text: "Inscripciones Adicionales", rightAnchor: 133.0, topAnchor: 14.0) {
        anchorPane(minHeight: 0.0, minWidth: 0.0, prefHeight: 180.0, prefWidth: 200.0) {
            tableView(id: "inscripcionesTabla", layoutX: 14.0, layoutY: 14.0, prefHeight: 259.0, prefWidth: 293.0) {

            }
            button(id: "aceptarInscripcion", layoutX: 96.0, layoutY: 294.0, mnemonicParsing: false, text: "Agregar")
            button(id: "cancelarInscripcion", layoutX: 172.0, layoutY: 294.0, mnemonicParsing: false, text: "Quitar")
        }
    }
    hbox(layoutX: 87, layoutY: 250){
        button(id:'salir',onAction:controller.cerrar)
    }
}