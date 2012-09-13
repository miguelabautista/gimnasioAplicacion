package com.tronic.gimnasioapp

import griffon.transform.Threading
import javafx.scene.input.MouseEvent
import javafx.event.EventHandler

class PanelPrincipalController {
    def model
    def view

    void mvcGroupInit(Map args) {
        view.anchor.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    //windowButtons.toogleMaximized();
                }
            }
        });
        // add window dragging
        view.anchor.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                model.mouseDragOffsetX = event.getSceneX();
                model.mouseDragOffsetY = event.getSceneY();
            }
        });
        view.anchor.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //if (!windowButtons.isMaximized())
                view.principalStage.setX(event.getScreenX() - model.mouseDragOffsetX);
                view.principalStage.setY(event.getScreenY() - model.mouseDragOffsetY);
                //}
            }
        });
    }

    // void mvcGroupDestroy() {
    //    // this method is called when the group is destroyed
    // }

    def inscribir = { evt = null ->
        view.inscribirButton.disable = true
        def mvc = buildMVCGroup("inscripcionPanel")
        try {
            if (app.views.actividades.view.actividadesPane.isVisible() != null && true) {
                app.event('closeActividades')
            }
        } catch (Exception e) {}
        def pantalla = mvc.view.inscripcionPantalla
        execInsideUIAsync {
            view.grupoDespliegue.getChildren().add(pantalla)
        }
    }

    def clientes = { evt = null ->
        view.inscribirButton.disable = true
        def mvc = buildMVCGroup("clientes")
        try {
            if (app.views.actividades.view.actividadesPane.isVisible() != null && true) {
                app.event('closeActividades')
            }
        } catch (Exception e) {}
        def pantalla = mvc.view.clientesPane
        execInsideUIAsync {
            view.grupoDespliegue.getChildren().add(pantalla)
        }
    }

    def actividades = { evt = null ->
        view.actividadesButton.disable = true
        def mvc = buildMVCGroup("actividades")
        try {
            if (app.views.clientes.view.clientesPane.isVisible() != null && true) {
                app.event('closeClientes')
            }
        } catch (Exception e) {}
        def pantalla = mvc.view.actividadesPane
        execInsideUIAsync {
            view.grupoDespliegue.getChildren().add(pantalla)
        }
    }

    def salir = {
        withMVCGroup('exitDialog') { m, v, c ->
            c.show(view.principalStage)
        }
    }

    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    def minimizar = {  evt = null ->
        view.principalStage.setIconified(true)
    }

}
