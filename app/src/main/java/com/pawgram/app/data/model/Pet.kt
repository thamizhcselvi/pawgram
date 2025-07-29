package com.pawgram.app.data.model

import android.annotation.SuppressLint
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class Pet(
    val id: String,
    val name: String,
    val breed: String,
    val age: Int,
    @SerialName("age_unit") val ageUnit: String = "months",
    @SerialName("profile_picture") val profilePicture: String,
    val progress: Int = 0,
    @Transient val totalProgress: Int = 3
) {
    fun getAgeDisplay(): String {
        return when {
            age < 12 -> "$age months old"
            age == 12 -> "1 year old"
            else -> "${age / 12} years old"
        }
    }
    
    fun getProgressDisplay(): String {
        return "$progress/$totalProgress"
    }
} 