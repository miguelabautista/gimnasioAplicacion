package com.tronic.gimnasioapp.beans

import groovyx.javafx.beans.FXBindable

/**
 * Created with IntelliJ IDEA.
 * User: MIGUEL
 * Date: 10/09/12
 * Time: 08:37 PM
 * To change this template use File | Settings | File Templates.
 */
@FXBindable
class Actividades {
    def id
    String nombre
    String dia
    String instructor
    String desde
    String hasta
    BigDecimal mensualidad
    BigDecimal costo_diario
}
