package com.example

import com.example.database.Locations
import com.example.database.Users
import com.example.features.authorization.authorizationRouting
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>) {
    initDatabase()

    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    authorizationRouting()
}

private fun initDatabase() {
    Database.connect("URL" +
            "&sslrootcert=<ПУть>",
        driver = "org.postgresql.Driver",
        user = "<User>",
        password = "<PASSWORD>")
    transaction {
        SchemaUtils.create(Users, Locations)
    }
}