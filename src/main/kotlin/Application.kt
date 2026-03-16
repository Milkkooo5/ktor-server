package com.example

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    configureMonitoring()
    configureSerialization()
    configureDatabases()
    configureRouting()
}
