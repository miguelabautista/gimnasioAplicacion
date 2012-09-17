package gimnasioaplicacion

import griffon.transform.Threading
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.scene.input.KeyEvent

class GimnasioAplicacionController {
    def model
    def view
    def userService




    void mvcGroupInit(Map args) {
        view.passwordField.focusedProperty().addListener({ObservableValue observableValue, Object t, Object t1 ->
            // println Toolkit.defaultToolkit.getLockingKeyState(java.awt.event.KeyEvent.VK_CAPS_LOCK)
        } as ChangeListener)
        view.userField.textProperty().addListener({ ObservableValue observableValue, Object t, Object t1 ->
            validarUser()
        } as ChangeListener)
        view.passwordField.textProperty().addListener({ ObservableValue observableValue, Object t, Object t1 ->
            validatePassword()
        } as ChangeListener)
    }

    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    def validatePassword() {
        if (!model?.password?.size()) {
            model.avisoPasswordVisible = true
            model.tooltipPassword = "campo no puede estar vacio"
            view.passwordField.style = "-fx-background-color:#237593"
            return false
        } else {
            model.avisoPasswordVisible = false
            view.passwordField.style = "-fx-background-color:white"
            return true
        }
    }

    def tecla = { KeyEvent evt = null ->

    }

    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    def validarUser() {
        if (!model?.user?.size()) {
            model.avisoUserVisible = true
            model.tooltipUser = "campo no puede estar vacio"
            view.userField.style = "-fx-background-color:#237593"
            return false
        } else {
            model.avisoUserVisible = false
            view.userField.style = "-fx-background-color:white"
            return true
        }
    }

    // void mvcGroupDestroy() {
    //    // this method is called when the group is destroyed
    // }


    def aceptar = {  evt = null ->

        if (model.user.isEmpty()) {
            execInsideUIAsync {
                model.resultado = "Campo usuario no puede estar vacio"
                view.fadein.play()
                view.fadein2.play()
                model.user = ""
                view.userField.requestFocus()
            }
        } else if (model.password.isEmpty()) {
            execInsideUIAsync {
                model.resultado = "Campo de contraseña no puede estar vacio"
                view.fadein.play()
                view.fadein2.play()
                model.password = ""
                view.passwordField.requestFocus()
            }
        } else {

            def resultadoUser
            def resultadoPassword

            execOutsideUI {

                resultadoUser = userService.validateUser(model.user)

                resultadoPassword = userService.getPassword(model.user, model.password)

            }

            execInsideUIAsync {
                if (!resultadoUser) {
                    model.resultado = "Usuario no existe"
                    view.fadein.play()
                    view.fadein2.play()
                    model.user = ""
                    view.userField.requestFocus()
                } else if (!resultadoPassword) {
                    model.resultado = "contraseña invalida"
                    view.fadein.play()
                    view.fadein2.play()
                    model.password = ''
                    view.passwordField.requestFocus()
                } else if (resultadoPassword) {
                    def mvc

                    if(resultadoUser.tipo_de_cuenta.toLowerCase() == "administrador"){
                        mvc = buildMVCGroup("panelPrincipal",[isAdmin:true])
                    } else{
                        mvc = buildMVCGroup("panelPrincipal",[isAdmin:false])
                    }

                    def pantalla = mvc.view.principalStage

                    app.windowManager.hide(view.authStage)
                    app.windowManager.show(pantalla)

                    view.aceptarButton.setDisable(false)
                    view.userField.text = ""
                    view.passwordField.text = ""
                }
            }
        }
    }

    def salir = { evt = null ->
        System.exit(0)
    }
}
