package com.tronic.gimnasioapp

import groovy.sql.Sql

class UserService {

    def validateUser(def user) {
        withSql{datasourcename, Sql sql ->
            def valor = sql.firstRow("SELECT * FROM usuario WHERE login = ${user}")
        }
    }

    def getPassword(def user,def password) {
        withSql{datasourcename, Sql sql ->
          def valor = sql.firstRow("SELECT * FROM usuario WHERE login = ${user} AND clave = ${password}")
        }
    }
}