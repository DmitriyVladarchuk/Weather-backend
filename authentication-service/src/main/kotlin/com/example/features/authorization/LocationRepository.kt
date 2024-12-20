package com.example.features.authorization

import com.example.database.Locations
import com.example.features.data.ReceiveUserLocations
import com.example.features.data.UserLocation
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class LocationRepository {
    fun addUserLocation(location: ReceiveUserLocations) {
        return transaction {
            Locations.insert {
                it[userId] = location.userId
                it[name] = location.name
                it[latitude] = location.latitude
                it[longitude] = location.longitude
                it[country] = location.country
                it[admin1] = location.admin1
                it[isSelected] = location.isSelected
            }
        }
    }

    fun getUserLocations(userId: Int): List<UserLocation> {
        return transaction {
            Locations.select { Locations.userId eq userId }.map {
                UserLocation(
                    id = it[Locations.id].value,
                    userId = it[Locations.userId],
                    name = it[Locations.name],
                    latitude = it[Locations.latitude],
                    longitude = it[Locations.longitude],
                    country = it[Locations.country],
                    admin1 = it[Locations.admin1],
                    isSelected = it[Locations.isSelected]
                )
            }
        }
    }
}