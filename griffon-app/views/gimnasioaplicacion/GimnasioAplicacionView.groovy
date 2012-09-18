package gimnasioaplicacion

import javafx.scene.image.ImageView
import javafx.stage.StageStyle

authStage = application(title: 'BodyCareFitness', sizeToScene: true, centerOnScreen: true, resizable: false,onCloseRequest:controller.salir) {
    scene(id: "authScene", fill: "#4169e1", width: 344.0, height: 207.0) {
        anchorPane(prefHeight: 187.0, prefWidth: 344.0) {
            text(text: "Bienvenido", font: "24", textFill = "radial-gradient(focus-angle 0.0deg, focus-distance 0.0% , center 100.0px 100.0px, radius 200.0px, 0xff0000ff 0.0%, 0x0000ffff 30.0%, 0x000000ff 100.0%)", leftAnchor: 14.0, topAnchor: 19.0) {
                effect dropShadow()
            }
            anchorPane(bottomAnchor: 65.0, leftAnchor: 32.0, rightAnchor: 47.0, topAnchor: 67.0) {
                hbox(alignment: "CENTER", layoutX: 19.0, spacing: 5.0) {
                    label(text: "Usuario") {
                        effect dropShadow()
                    }
                    textField(id: "userField", prefWidth: 200.0, text: bind(model.userProperty)) {
                        effect dropShadow()
                    }
                    label(id: "avisoUser", prefWidth: 14, prefHeight: 14, graphic: new ImageView(model.avisoImage), visible: bind(model.avisoUserVisibleProperty)) {
                        tooltip(text: bind(model.tooltipUserProperty))
                    }
                }
                hbox(alignment: "CENTER", layoutY: 33.0, spacing: 5.0) {
                    label(text: "Contraseña") {
                        effect dropShadow()
                    }
                    passwordField(id: "passwordField", prefWidth: 200.0, text: bind(model.passwordProperty)) {
                        effect dropShadow()
                    }
                    label(id: "avisoPassword", prefWidth: 5, prefHeight: 5, graphic: new ImageView(model.avisoImage), visible: bind(model.avisoPasswordVisibleProperty)) {
                        tooltip(text: bind(model.tooltipPasswordProperty))
                    }
                }
            }
            label(id: "avisoLabel", layoutY: 136.0, prefWidth: 140.0, leftAnchor: 127.0, textFill: "MAROON", opacity: 0.0, rightAnchor: 77.0, text: bind(model.resultadoProperty)) {
                effect dropShadow()
            }
            hbox(id: "HBox", alignment: "CENTER", spacing: 5.0, bottomAnchor: 11.0, rightAnchor: 14.0) {
                button(id: "aceptarButton", mnemonicParsing: true, defaultButton: true, text: "_Aceptar", onAction: controller.aceptar) {
                    effect dropShadow()
                }
                button(id: "cancelarButton", mnemonicParsing: true, cancelButton: true, text: "_Cancelar", onAction: controller.salir) {
                    effect dropShadow()
                }
            }
        }
    }
}
//authStage.initStyle(StageStyle.UNDECORATED)
fadein = fadeTransition(2000.ms, node: avisoLabel, from: 0.0, to: 1.0)
fadein2 = fadeTransition(2000.ms, node: avisoLabel, from: 1.0, to: 0.0)




