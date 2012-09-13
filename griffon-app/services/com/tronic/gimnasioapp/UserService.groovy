package com.tronic.gimnasioapp

import groovy.sql.Sql

class UserService {

    def validateUser(def user) {
        withSql {datasourcename, Sql sql ->
            def valor
            try {
                valor = sql.firstRow("SELECT * FROM usuario WHERE login = ${user}")
            } finally {
                sql.close()
            }
            return valor
        }
    }

    def getPassword(def user, def password) {
        withSql {datasourcename, Sql sql ->
            def valor
            try {
                valor = sql.firstRow("SELECT * FROM usuario WHERE login = ${user} AND clave = ${password}")
            } finally {
                sql.close()
            }
            return valor
        }
    }
}