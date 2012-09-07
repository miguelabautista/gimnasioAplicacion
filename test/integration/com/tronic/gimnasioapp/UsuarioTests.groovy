package com.tronic.gimnasioapp

import com.tronic.gimnasioapp.UserService
import griffon.test.GriffonUnitTestCase

class UsuarioTests extends GriffonUnitTestCase {
    GriffonApplication app
    UserService userService = new UserService()

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testUser() {

        def valor = userService.validateUser("User1")

        assertNotNull( valor)
    }

    void testPassword(){

        def valor = userService.getPassword("User1","1234")

        assertEquals("miguel",valor.nombre)
    }
}
