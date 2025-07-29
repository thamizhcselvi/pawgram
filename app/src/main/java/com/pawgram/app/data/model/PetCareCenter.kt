package com.pawgram.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PetCareCenter(
    val id: String,
    val name: String,
    val logo: String,
    val distance: Double,
    val rating: Double,
    val services: List<String>,
    val location: String
) {
    fun getDistanceDisplay(): String {
        return "${distance} km away"
    }
    
    fun getRatingDisplay(): String {
        return "$rating/5"
    }
    
    fun getServicesDisplay(): String {
        return if (services.size > 1) {
            "${services.first()} and ${services.size - 1}+ more"
        } else {
            services.firstOrNull() ?: ""
        }
    }
} 