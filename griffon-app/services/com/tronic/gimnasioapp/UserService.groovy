package com.tronic.gimnasioapp

import groovy.sql.DataSet
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

    def getUsers() {
        withSql { datasourcename, Sql sql ->
            def lista = []
            try {
                lista = sql.rows("SELECT * FROM usuario")
            } finally {
                sql.close()
            }
            return lista
        }
    }

    def getUser(def login) {
        withSql { datasourcename, Sql sql ->
            def valor
            try {
                valor = sql.firstRow("SELECT * FROM usuario WHERE login = ${login}")
            } finally {
                sql.close()
            }
            return valor
        }
    }

    def updateUser(def id, def cedula, def nombre, def apellido, def login, def clave, def cuenta) {
        withSql {d, Sql sql ->
            try {
                sql.execute("UPDATE usuario SET cedula = $cedula , nombre = $nombre, apellido = $apellido , login = $login , clave = $clave , tipo_de_cuenta = $cuenta WHERE id = $id")
            } finally {
                sql.close()
            }
        }
    }

    def deleteUser(def id) {
        withSql { d, Sql sql ->
            try {
                sql.execute("DELETE FROM usuario WHERE id = $id")
            } finally {
                sql.close()
            }
        }
    }

    def createUser(def cedula, def nombre, def apellido, def login, def clave, def cuenta) {
        withSql {s, Sql sql ->
            DataSet dataSet = sql.dataSet('usuario')
            try {
                dataSet.add(apellido: apellido, clave: clave, login: login, nombre: nombre, tipo_de_cuenta: cuenta, cedula: cedula)
            } finally {
                dataSet.close()
            }
        }
    }
}