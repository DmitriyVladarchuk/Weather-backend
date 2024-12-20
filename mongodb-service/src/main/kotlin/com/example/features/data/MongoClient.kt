package com.example.features.data

import com.mongodb.MongoClientSettings
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.Document

object MongoClient {

    private val dbHosts = listOf(ServerAddress("<Host>", 27018))
    private const val DB_NAME = "<Name>>"
    private const val DB_USER  = "<User>"
    private const val DB_PASS = "<Password>"
    private const val COLLECTION_GEOCODING_INFO = "geocodingInfo"
    private const val COLLECTION_FORECAST_INFO = "ForecastInfo"

    private val conn = MongoClients.create(
        MongoClientSettings.builder()
            .applyToClusterSettings { it.hosts(dbHosts) }
            .applyToSslSettings { it.enabled(true) }
            .credential(MongoCredential.createCredential(DB_USER , DB_NAME, DB_PASS.toCharArray()))
            .build()
    )

    private val database: MongoDatabase = conn.getDatabase(DB_NAME)

    val collectionGeocoding: MongoCollection<Document> = database.getCollection(COLLECTION_GEOCODING_INFO)
    val collectionForecast: MongoCollection<Document> = database.getCollection(COLLECTION_FORECAST_INFO)

    fun closeConnection() = conn.close()

}