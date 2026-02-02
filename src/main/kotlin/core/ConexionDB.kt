package edu.gva.es.core

import edu.gva.es.data.Usuarios
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object ConexionDB {

    private const val HOST = "pearly.cch8km4gcrmo.us-east-1.rds.amazonaws.com"
    private const val PORT = 3306
    private const val DATABASE = "proyecto"
    private const val USER = "Administrator"
    private const val PASSWORD = "PI2026dam"

    private const val SSL_CERT_PATH = "src/main/resources/global-bundle.pem"

    private val URL =
        "jdbc:mysql://$HOST:$PORT/$DATABASE" +
                "?sslMode=VERIFY_CA" +
                "&sslCa=C:/Users/manmorpar/Documents/API-Pearly-AWS/src/main/resources/global-bundle.pem" +
                "&serverTimezone=Europe/Madrid"

    lateinit var db: Database

    fun conectar() {
        try {
            db = Database.connect(
                url = URL,
                user = USER,
                password = PASSWORD
            )

            println("Conexión establecida con éxito")

            transaction(db) {
                SchemaUtils.create(Usuarios)
                println("Esquema de la tabla 'Usuarios' verificado/creado.")
            }

        } catch (e: Exception) {
            println("Error DB: ${e.message}")
        }
    }
}
