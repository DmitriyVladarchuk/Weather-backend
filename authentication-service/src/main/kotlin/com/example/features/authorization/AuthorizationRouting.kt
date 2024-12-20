package com.example.features.authorization

import com.example.features.data.ReceiveUserLocations
import com.example.features.data.UserLocation
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.authorizationRouting() {
    routing {
        post("/register") {
            // Логика регистрации
            val parameters = call.receiveParameters()
            val email = parameters["email"] ?: return@post call.respondText("Email не указан")
            val password = parameters["password"] ?: return@post call.respondText("Пароль не указан")
            val userRepository = UserRepository()
            val user = userRepository.registerUser(email, password)
            call.respond(user?.id  ?: -1)
        }

        post("/login") {
            // Логика авторизации
            val parameters = call.receiveParameters()
            val email = parameters["email"] ?: return@post call.respondText("Email не указан")
            val password = parameters["password"] ?: return@post call.respondText("Пароль не указан")
            val userRepository = UserRepository()
            val user = userRepository.authorizeUser(email, password)
            call.respond(user?.id  ?: -1)
        }

        post("/save/location") {
            // Логика добавления новой локации
            val parameters = call.receiveParameters()
            val userId = parameters["userId"]?.toIntOrNull() ?: return@post call.respondText("ID пользователя не указан или неверен")
            val name = parameters["name"] ?: return@post call.respondText("Имя локации не указано")
            val latitude = parameters["latitude"]?.toFloatOrNull() ?: return@post call.respondText("Широта не указана или неверна")
            val longitude = parameters["longitude"]?.toFloatOrNull() ?: return@post call.respondText("Долгота не указана или неверна")
            val country = parameters["country"] ?: return@post call.respondText("Страна не указана")
            val admin1 = parameters["admin1"] ?: return@post call.respondText("Регион не указан")
            val isSelected = parameters["isSelected"]?.toBoolean() ?: false

            val locationRepository = LocationRepository()
            val userLocation = ReceiveUserLocations(userId, name, latitude, longitude, country, admin1, isSelected)
            locationRepository.addUserLocation(userLocation)
            call.respond(userLocation)
        }

        get("/locations/{id}") {
            // Логика получения всех локаций для конкретного пользователя
            //val parameters = call.receiveParameters()
            val userId = call.parameters["id"]?.toIntOrNull() ?: return@get call.respondText("ID пользователя не указан или неверен")

            val locationRepository = LocationRepository()
            val locations = locationRepository.getUserLocations(userId)
            call.respond(locations)
        }
    }
}