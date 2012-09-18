package com.tronic.gimnasioapp.beans

import groovyx.javafx.beans.FXBindable

/**
 * Created with IntelliJ IDEA.
 * User: MIGUEL
 * Date: 13/09/12
 * Time: 08:02 AM
 * To change this template use File | Settings | File Templates.
 */
@FXBindable
class Clientes {
    String cedula
    String nombre
    String apellido
    String sexo
    String status
    String email
    String direccion
    String telefFijo
    String telefMovil
    def mensualidad
}