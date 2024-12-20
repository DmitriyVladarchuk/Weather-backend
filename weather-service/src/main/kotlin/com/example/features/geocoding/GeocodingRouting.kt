package com.example.features.geocoding

import com.example.features.geocoding.data.GeocodingReceived
import com.example.features.geocoding.data.GeocodingResponse
import com.example.features.geocoding.data.LocationResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*

@Serializable
data class GeocodingInfo(
    val name: String,
    val language: String,
)

fun Application.geocodingRouting() {
    val client = HttpClient(CIO)
    val clientMongo = HttpClient(CIO) {}

    routing {
        post("/geocoding") {
            val parameters = call.receiveParameters()
            val name = parameters["name"] ?: return@post call.respond(HttpStatusCode.BadRequest, "Missing name parameter")
            val language = parameters["language"] ?: return@post call.respond(HttpStatusCode.BadRequest, "Missing language parameter")

            val responseMongoDB: HttpResponse = clientMongo.submitForm(
                url = "http://localhost:8080/save/geocoding",
                formParameters = parameters {
                    append("name", name)
                    append("language", language)
                }
            )

            if (responseMongoDB.status != HttpStatusCode.OK) {
                return@post call.respond(HttpStatusCode.InternalServerError, "Failed to save geocoding info")
            }

            val response: HttpResponse = client.get("https://geocoding-api.open-meteo.com/v1/search?name=$name&count=10&language=$language&format=json")
            val responseBody = response.bodyAsText()
            val result = Json.decodeFromString<GeocodingReceived>(responseBody)
            call.respond(transformGeocodingReceivedToResponse(result))
        }

    }
}

private fun transformGeocodingReceivedToResponse(geocodingReceived: GeocodingReceived): GeocodingResponse {
    val results = geocodingReceived.results.map { locationReceived ->
        LocationResponse(
            name = locationReceived.name,
            latitude = locationReceived.latitude,
            longitude = locationReceived.longitude,
            country = locationReceived.country,
            admin1 = locationReceived.admin1
        )
    }
    return GeocodingResponse(results = results)
}
