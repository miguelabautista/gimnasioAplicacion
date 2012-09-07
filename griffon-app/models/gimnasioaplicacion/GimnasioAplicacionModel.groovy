package gimnasioaplicacion

import groovyx.javafx.beans.FXBindable
import javafx.scene.image.Image
import javafx.scene.image.ImageView

class GimnasioAplicacionModel {
    @FXBindable String resultado
    @FXBindable String user = "user"
    @FXBindable boolean avisoUserVisible
    @FXBindable String tooltipUser
    @FXBindable String password = '123'
    @FXBindable boolean avisoPasswordVisible
    @FXBindable String tooltipPassword

    Image avisoImage = new Image(getClass().getResourceAsStream("/caution_biohazard.png"))


}
