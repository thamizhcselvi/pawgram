package com.pawgram.app.data.api

import com.pawgram.app.data.model.Pet
import com.pawgram.app.data.model.PetCareCenter
import com.pawgram.app.data.model.User
import retrofit2.http.GET

interface PawgramApiService {
    @GET("pets")
    suspend fun getPets(): List<Pet>
    
    @GET("pet-care-centers")
    suspend fun getPetCareCenters(): List<PetCareCenter>
    
    @GET("user")
    suspend fun getCurrentUser(): User
} 