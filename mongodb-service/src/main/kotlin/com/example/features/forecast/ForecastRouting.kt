package com.example.features.forecast

import com.example.features.forecast.data.ForecastResponse
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.forecastRouting(repository: ForecastRepository) {
    routing {
        post("/save/forecast") {
            try {
                val parameter = call.receive<ForecastResponse>()
                repository.addForecast(parameter)
                call.respond(HttpStatusCode.OK)
            } catch (ex: IllegalStateException) {
                call.respond(HttpStatusCode.BadRequest)
            } catch (ex: JsonConvertException) {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}