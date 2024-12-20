package com.example.features.forecast

import com.example.features.data.MongoClient
import com.example.features.forecast.data.ForecastResponse
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.Document

class ForecastRepository(clientMongo: MongoClient) {

    private val collection = clientMongo.collectionForecast

    fun addForecast(forecastResponse: ForecastResponse) {
        val document = Document.parse(Json.encodeToString(forecastResponse))
        collection.insertOne(document)
    }
}