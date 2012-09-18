application {
    title = 'GimnasioAplicacion'
    startupGroups = ['gimnasioAplicacion']

    // Should Griffon exit when no Griffon created frames are showing?
    autoShutdown = false

    // If you want some non-standard application class, apply it here
    //frameClass = 'javax.swing.JFrame'
}
mvcGroups {
    // MVC Group for "pagarPanel"
    'pagarPanel' {
        model      = 'com.tronic.gimnasioapp.PagarPanelModel'
        view       = 'com.tronic.gimnasioapp.PagarPanelView'
        controller = 'com.tronic.gimnasioapp.PagarPanelController'
    }

    // MVC Group for "instructoresPanel"
    'instructoresPanel' {
        model      = 'com.tronic.gimnasioapp.InstructoresPanelModel'
        view       = 'com.tronic.gimnasioapp.InstructoresPanelView'
        controller = 'com.tronic.gimnasioapp.InstructoresPanelController'
    }

    // MVC Group for "avisoCliente"
    'avisoCliente' {
        model      = 'com.tronic.gimnasioapp.AvisoClienteModel'
        view       = 'com.tronic.gimnasioapp.AvisoClienteView'
        controller = 'com.tronic.gimnasioapp.AvisoClienteController'
    }

    // MVC Group for "usuario"
    'usuario' {
        model      = 'com.tronic.gimnasioapp.UsuarioModel'
        view       = 'com.tronic.gimnasioapp.UsuarioView'
        controller = 'com.tronic.gimnasioapp.UsuarioController'
    }

    // MVC Group for "usuariosPanel"
    'usuariosPanel' {
        model      = 'com.tronic.gimnasioapp.UsuariosPanelModel'
        view       = 'com.tronic.gimnasioapp.UsuariosPanelView'
        controller = 'com.tronic.gimnasioapp.UsuariosPanelController'
    }

    // MVC Group for "exitDialog"
    'exitDialog' {
        model      = 'com.tronic.gimnasioapp.ExitDialogModel'
        view       = 'com.tronic.gimnasioapp.ExitDialogView'
        controller = 'com.tronic.gimnasioapp.ExitDialogController'
    }

    // MVC Group for "actualizarCliente"
    'actualizarCliente' {
        model      = 'com.tronic.gimnasioapp.ActualizarClienteModel'
        view       = 'com.tronic.gimnasioapp.ActualizarClienteView'
        controller = 'com.tronic.gimnasioapp.ActualizarClienteController'
    }

    // MVC Group for "actualizarActividad"
    'actualizarActividad' {
        model      = 'com.tronic.gimnasioapp.ActualizarActividadModel'
        view       = 'com.tronic.gimnasioapp.ActualizarActividadView'
        controller = 'com.tronic.gimnasioapp.ActualizarActividadController'
    }

    // MVC Group for "newActivity"
    'newActivity' {
        model      = 'com.tronic.gimnasioapp.NewActivityModel'
        view       = 'com.tronic.gimnasioapp.NewActivityView'
        controller = 'com.tronic.gimnasioapp.NewActivityController'
    }

    // MVC Group for "clientes"
    'clientes' {
        model      = 'com.tronic.gimnasioapp.ClientesModel'
        view       = 'com.tronic.gimnasioapp.ClientesView'
        controller = 'com.tronic.gimnasioapp.ClientesController'
    }

    // MVC Group for "instructor"
    'instructor' {
        model      = 'com.tronic.gimnasioapp.InstructorModel'
        view       = 'com.tronic.gimnasioapp.InstructorView'
        controller = 'com.tronic.gimnasioapp.InstructorController'
    }

    // MVC Group for "actividad"
    'actividad' {
        model      = 'com.tronic.gimnasioapp.ActividadModel'
        view       = 'com.tronic.gimnasioapp.ActividadView'
        controller = 'com.tronic.gimnasioapp.ActividadController'
    }

    // MVC Group for "actividades"
    'actividades' {
        model      = 'com.tronic.gimnasioapp.ActividadesModel'
        view       = 'com.tronic.gimnasioapp.ActividadesView'
        controller = 'com.tronic.gimnasioapp.ActividadesController'
    }


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
