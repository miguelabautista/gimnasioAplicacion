package com.tronic.gimnasioapp

import javafx.scene.image.ImageView
import javafx.scene.layout.Priority

principalStage = application(title: 'GimnasioAplicacion', sizeToScene: true, centerOnScreen: true, resizable: true, onCloseRequest: controller.salir) {
    scene(id: "panelPrincipalScene", fill: "#4169e1", width: 800, height: 600) {
        panelPrincipalScene.stylesheets.add(getClass().getResource("/SearchBox.css").toString())
        anchorPane(id: "anchor", prefHeight: 600, prefWidth: 800) {
            anchorPane(prefHeight: 59.0, prefWidth: 800.0, leftAnchor: 0.0, rightAnchor: 0.0, topAnchor: 0.0) {
                text(layoutX: 14.0, layoutY: 38.0, text: "Gimnasio", font: '28px') {
                    fill linearGradient(endX: 0, stops: [red, purple])
                    effect dropShadow(color: purple, radius: 25, spread: 0.25)
                }
                hyperlink(id: "minimizeButton", contentDisplay: "GRAPHIC_ONLY", layoutX: 766.0, layoutY: 35.0, graphic: new ImageView(model.exitIcon), onAction: controller.minimizar)
                hyperlink(id: "salirButton", contentDisplay: "GRAPHIC_ONLY", layoutX: 766.0, layoutY: 6.0, graphic: new ImageView(model.exitIcon), onAction: controller.salir)
                hbox(id: 'Hbox', alignment: "CENTER", layoutY: 59, spacing: 5.0, prefWidth: 800.0, leftAnchor: 0.0, rightAnchor: 0.0) {
                    toolBar(id: 'tool', cache: true, prefWidth: 1024.0, hgrow: Priority.ALWAYS) {
                        button(id: "inscribirButton", mnemonicParsing: false, text: "Clientes", onAction: controller.inscribir)
                        // button(mnemonicParsing: false, text: "Buscar")
                        // button(mnemonicParsing: false, text: "Actualizar")
                        button(id: "actividadesButton", mnemonicParsing: false, text: "Actividades", onAction: controller.actividades)
                        button(mnemonicParsing: false, text: "Admin clientes" , onAction: controller.clientes, visible:bind(model.isAdminProperty))
                        button(id:'usuariosButton' ,mnemonicParsing: false , text:'Usuarios',graphic:new ImageView(model.userIcon),onAction: controller.usuarios, visible:bind(model.isAdminProperty)){
                            tooltip(text:'Administrar usuarios')
                        }
                        button(id:'instructoresButton' ,mnemonicParsing: false , text:'Instructores',graphic:new ImageView(model.instructorIcon),onAction: controller.instructores, visible:bind(model.isAdminProperty)){
                            tooltip(text:'Administrar Instructores')
                        }
                    }
                }
            }
            anchorPane(layoutX: 4.0, layoutY: 95.0, prefHeight: 510.0, prefWidth: 800.0, bottomAnchor: 0.0, leftAnchor: 0.0, rightAnchor: 0.0, topAnchor: 90.0) {
                group(id: "grupoDespliegue") {

                }
            }
        }
    }
}
//principalStage.style = "UNDECORATED"