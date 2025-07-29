package com.pawgram.app.data.repository

import com.pawgram.app.data.api.PawgramApiService
import com.pawgram.app.data.model.Pet
import com.pawgram.app.data.model.PetCareCenter
import com.pawgram.app.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PawgramRepository(private val apiService: PawgramApiService) {
    
    fun getPets(): Flow<List<Pet>> = flow {
        try {
            val pets = apiService.getPets()
            emit(pets)
        } catch (e: Exception) {
            // In a real app, you might want to emit cached data here
            emit(emptyList())
        }
    }
    
    fun getPetCareCenters(): Flow<List<PetCareCenter>> = flow {
        try {
            val centers = apiService.getPetCareCenters()
            emit(centers)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }
    
    fun getCurrentUser(): Flow<User?> = flow {
        try {
            val user = apiService.getCurrentUser()
            emit(user)
        } catch (e: Exception) {
            emit(null)
        }
    }
} 