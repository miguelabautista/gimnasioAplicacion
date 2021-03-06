package com.tronic.gimnasioapp

import groovyx.javafx.beans.FXBindable
import javafx.scene.image.Image
import javafx.collections.ObservableList
import com.tronic.gimnasioapp.beans.Actividades
import javafx.collections.FXCollections

class InscripcionPanelModel {
    @FXBindable boolean avisoGeneral
    @FXBindable boolean cedulaAvisoVIsible
    @FXBindable boolean nombreAvisoVIsible
    @FXBindable boolean apellidoAvisoVIsible
    @FXBindable boolean telfMovilAvisoVIsible
    @FXBindable boolean telfFijoAvisoVIsible
    @FXBindable boolean emailAvisoVIsible

    @FXBindable boolean aceptarCancelar

    @FXBindable boolean pagarVisible

    @FXBindable boolean modificarVisible
    @FXBindable boolean inscribirVisible
    @FXBindable boolean retirarVisible
    @FXBindable boolean botonesClase = true

    boolean inscribir = false

    Image caution = new Image(getClass().getResourceAsStream("/caution_biohazard.png"))

    ObservableList<Actividades> clasesItems = FXCollections.observableArrayList([])

    List<Actividades> ItemsAgregados = []
}
