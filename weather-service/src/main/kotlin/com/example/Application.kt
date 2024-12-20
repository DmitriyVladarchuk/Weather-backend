package com.example

import com.example.features.forecast.forecastRouting
import com.example.features.geocoding.geocodingRouting
import io.ktor.server.application.*

fun main(args: Array<String>) {

    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    geocodingRouting()
    forecastRouting()
}
