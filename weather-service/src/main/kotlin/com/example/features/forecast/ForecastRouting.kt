package com.example.features.forecast

import com.example.features.forecast.data.ForecastResponse
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun Application.forecastRouting() {
    val clientForecast = HttpClient(CIO)
    val clientMongo = HttpClient(CIO)

    routing {
        get("/forecast") {
            call.respondText("Forecast")
        }
        post("/forecast") {
            val parameters = call.receiveParameters()
            val latitude = parameters["latitude"] ?: return@post call.respond(HttpStatusCode.BadRequest, "Missing latitude parameter")
            val longitude = parameters["longitude"] ?: return@post call.respond(HttpStatusCode.BadRequest, "Missing longitude parameter")

            val response: HttpResponse = clientForecast.get("https://api.open-meteo.com/v1/forecast?latitude=${latitude}&longitude=${longitude}&hourly=temperature_2m,weather_code&daily=weather_code,temperature_2m_max,temperature_2m_min,sunrise,sunset&timezone=auto&current=temperature_2m,relative_humidity_2m,precipitation,weather_code,pressure_msl,wind_speed_10m&forecast_days=7")
            val responseBody = response.bodyAsText()
            val result = Json.decodeFromString<ForecastResponse>(responseBody)

            val responseMongoService: HttpResponse = clientMongo.post("http://localhost:8080/save/forecast") {
                contentType(ContentType.Application.Json)
                setBody(Json.encodeToString(result))
            }

            call.respond(result)
        }
    }
}