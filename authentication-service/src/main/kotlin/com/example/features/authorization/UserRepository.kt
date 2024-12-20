package com.example.features.authorization

import com.example.database.Users
import com.example.features.data.User
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepository {
    fun registerUser (email: String, password: String): User? {
        return transaction {
            val userId = Users.insertAndGetId {
                it[Users.email] = email
                it[Users.password] = password
            }
            User(id = userId.value, email = email, password = password)
        }
    }

    fun authorizeUser (email: String, password: String): User? {
        return transaction {
            Users.select { Users.email eq email and (Users.password eq password) }.map {
                User(id = it[Users.id].value, email = it[Users.email], password = password)
            }.singleOrNull()
        }
    }
}