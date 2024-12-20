package com.example

import com.example.features.data.MongoClient
import com.example.features.forecast.ForecastRepository
import com.example.features.forecast.forecastRouting
import com.example.features.geocoding.GeocodingRepository
import com.example.features.geocoding.geocodingRoutingV2
import io.ktor.server.application.*

fun main(args: Array<String>) {
    System.setProperty("javax.net.ssl.trustStore", "<URI>")
    System.setProperty("javax.net.ssl.trustStorePassword", "<Password>")

    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    geocodingRoutingV2(GeocodingRepository(MongoClient))
    forecastRouting(ForecastRepository(MongoClient))
}
