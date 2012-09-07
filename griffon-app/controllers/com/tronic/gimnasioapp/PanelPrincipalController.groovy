package com.tronic.gimnasioapp

class PanelPrincipalController {
    def model
    def view

    void mvcGroupInit(Map args) {
    }

    // void mvcGroupDestroy() {
    //    // this method is called when the group is destroyed
    // }

    def inscribir = { evt = null ->
        view.inscribirButton.disable = true
        def mvc = buildMVCGroup("inscripcionPanel")
        def pantalla = mvc.view.inscripcionPantalla
        execInsideUIAsync {
            view.grupoDespliegue.getChildren().add(pantalla)
        }
    }

}
