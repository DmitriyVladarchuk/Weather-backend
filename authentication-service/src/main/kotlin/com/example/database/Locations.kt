package com.example.database

import org.jetbrains.exposed.dao.id.IntIdTable

object Locations : IntIdTable()  {
    val userId = integer("user_id").references(Users.id)
    val name = varchar("name", 255)
    val latitude = float("latitude")
    val longitude = float("longitude")
    val country = varchar("country", 255).nullable()
    val admin1 = varchar("admin1", 255).nullable()
    val isSelected = bool("is_selected").default(false)
}