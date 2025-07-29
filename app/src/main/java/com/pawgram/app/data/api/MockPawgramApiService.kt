package com.pawgram.app.data.api

import com.pawgram.app.data.model.Pet
import com.pawgram.app.data.model.PetCareCenter
import com.pawgram.app.data.model.User
import kotlinx.coroutines.delay

class MockPawgramApiService : PawgramApiService {
    
    override suspend fun getPets(): List<Pet> {
        delay(1000) // Simulate network delay
        return listOf(
            Pet(
                id = "1",
                name = "Brian",
                breed = "Bengal",
                age = 5,
                profilePicture = "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?w=200&h=200&fit=crop&crop=face",
                progress = 1,
                totalProgress = 3
            ),
            Pet(
                id = "2",
                name = "Soda",
                breed = "Dalmatian",
                age = 24,
                profilePicture = "https://images.unsplash.com/photo-1546527868-ccb7ee7dfa6a?w=200&h=200&fit=crop&crop=face",
                progress = 2,
                totalProgress = 3
            )
        )
    }
    
    override suspend fun getPetCareCenters(): List<PetCareCenter> {
        delay(1000) // Simulate network delay
        return listOf(
            PetCareCenter(
                id = "1",
                name = "Paws Up - Pet Care",
                logo = "https://images.unsplash.com/photo-1587764379873-97837921fd44?w=100&h=100&fit=crop",
                distance = 2.0,
                rating = 4.5,
                services = listOf("Grooming", "Vaccination", "Training", "Boarding", "Veterinary"),
                location = "Downtown"
            ),
            PetCareCenter(
                id = "2",
                name = "Inooko",
                logo = "https://images.unsplash.com/photo-1552053831-71594a27632d?w=100&h=100&fit=crop",
                distance = 4.2,
                rating = 4.8,
                services = listOf("Grooming", "Training", "Pet Sitting", "Emergency Care"),
                location = "Westside"
            ),
            PetCareCenter(
                id = "3",
                name = "Migu",
                logo = "https://images.unsplash.com/photo-1583337130417-3346a1be7dee?w=100&h=100&fit=crop",
                distance = 2.0,
                rating = 4.5,
                services = listOf("Grooming", "Boarding", "Pet Supplies", "Veterinary"),
                location = "Eastside"
            )
        )
    }
    
    override suspend fun getCurrentUser(): User {
        delay(500) // Simulate network delay
        return User(
            id = "1",
            name = "Phyllis",
            profilePicture = "https://images.unsplash.com/photo-1494790108755-2616b612b786?w=200&h=200&fit=crop&crop=face"
        )
    }
} 