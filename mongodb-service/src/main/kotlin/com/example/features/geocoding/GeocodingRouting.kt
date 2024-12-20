package com.example.features.geocoding

import com.example.features.data.GeocodingInfo
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.geocodingRoutingV2(repository: GeocodingRepository) {
    routing {
        post("/save/geocoding") {
            val parameters = call.receiveParameters()
            val name = parameters["name"] ?: return@post call.respond(HttpStatusCode.BadRequest, "Missing name parameter")
            val language = parameters["language"] ?: return@post call.respond(HttpStatusCode.BadRequest, "Missing language parameter")

            try {
                repository.addGeocoding(GeocodingInfo(name, language))
                call.respond(HttpStatusCode.OK)
            } catch (ex: IllegalStateException) {
                call.respond(HttpStatusCode.BadRequest)
            } catch (ex: JsonConvertException) {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}