package com.example.features.geocoding

import com.example.features.data.GeocodingInfo
import com.example.features.data.MongoClient
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.Document

class GeocodingRepository(clientMongo: MongoClient) {

    private val collection = clientMongo.collectionGeocoding

    fun addGeocoding(geocodingInfo: GeocodingInfo) {
        val document = Document.parse(Json.encodeToString(geocodingInfo))
        collection.insertOne(document)
    }

}