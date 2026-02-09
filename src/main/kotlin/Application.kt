package edu.gva.es

import edu.gva.es.core.ConexionDB
import edu.gva.es.plugins.configureRouting
import edu.gva.es.plugins.configureSecurity
import edu.gva.es.plugins.configureSerialization
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.cors.routing.*
import io.ktor.http.*
import io.ktor.server.application.install

fun main() {
    println("Iniciando conexión con la base de datos...")
    ConexionDB.conectar()

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}

fun Application.module() {
    install(CORS) {
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)

        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Authorization)

        anyHost() // SOLO para desarrollo
    }

    configureSerialization()
    configureSecurity()
    configureRouting()
}
