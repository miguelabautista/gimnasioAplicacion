package com.tronic.gimnasioapp

import javafx.scene.layout.Priority

principalStage = application(title: 'GimnasioAplicacion', sizeToScene: true, centerOnScreen: true, resizable: true) {
    scene(id: "panelPrincipalScene", fill: gray, width: 800, height: 600) {
        anchorPane(id: "anchor", prefHeight: 600, prefWidth: 800) {
            anchorPane(prefHeight: 59.0, prefWidth: 800.0, leftAnchor: 0.0, rightAnchor: 0.0, topAnchor: 0.0) {
                label(layoutX: 14.0, layoutY: 14.0, text: "Gimnasio", font: '24px')
                hbox(id: 'Hbox', alignment: "CENTER", layoutY: 59, spacing: 5.0, prefWidth: 800.0, leftAnchor: 0.0, rightAnchor: 0.0) {
                    toolBar(id: 'tool', cache: true, prefWidth: 1024.0, hgrow: Priority.ALWAYS) {
                        button(id: "inscribirButton", mnemonicParsing: false, text: "Inscribir", onAction: controller.inscribir)
                        button(mnemonicParsing: false, text: "Buscar")
                        button(mnemonicParsing: false, text: "Actualizar")
                        button(mnemonicParsing: false, text: "Retirar")
                    }
                }
            }
            anchorPane(layoutX: 4.0, layoutY: 95.0, prefHeight: 510.0, prefWidth: 800.0,bottomAnchor:0.0 ,leftAnchor:0.0 ,rightAnchor:0.0 ,topAnchor:90.0) {
                group(id: "grupoDespliegue") {

                }
            }
        }
    }
}