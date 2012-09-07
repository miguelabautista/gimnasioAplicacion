application {
    title = 'GimnasioAplicacion'
    startupGroups = ['gimnasioAplicacion']

    // Should Griffon exit when no Griffon created frames are showing?
    autoShutdown = false

    // If you want some non-standard application class, apply it here
    //frameClass = 'javax.swing.JFrame'
}
mvcGroups {

    // MVC Group for "inscripcionPanel"
    'inscripcionPanel' {
        model      = 'com.tronic.gimnasioapp.InscripcionPanelModel'
        view       = 'com.tronic.gimnasioapp.InscripcionPanelView'
        controller = 'com.tronic.gimnasioapp.InscripcionPanelController'
    }

    // MVC Group for "panelPrincipal"
    'panelPrincipal' {
        model      = 'com.tronic.gimnasioapp.PanelPrincipalModel'
        view       = 'com.tronic.gimnasioapp.PanelPrincipalView'
        controller = 'com.tronic.gimnasioapp.PanelPrincipalController'
    }

    // MVC Group for "gimnasioAplicacion"
    'gimnasioAplicacion' {
        model      = 'gimnasioaplicacion.GimnasioAplicacionModel'
        view       = 'gimnasioaplicacion.GimnasioAplicacionView'
        controller = 'gimnasioaplicacion.GimnasioAplicacionController'
    }

}
