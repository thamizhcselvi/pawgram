package com.pawgram.app.ui.screens

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 100.dp // Extra space to avoid bottom navigation overlap
            ),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                ExploreHeader()
            }
            
            item {
                FeaturedPetsSection()
            }
            
            item {
                DiscoverSection()
            }
        }
    }
}

@Composable
private fun ExploreHeader(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Explore,
            contentDescription = "Explore",
            modifier = Modifier.size(64.dp),
            tint = Color(0xFF4CAF50)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Discover Amazing Pets",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Connect with pet lovers from around the world and share your furry friends' adventures!",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFFB3B3B3),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun FeaturedPetsSection(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Featured Pets",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        val featuredPets = listOf(
            FeaturedPet(
                name = "Luna",
                breed = "Golden Retriever",
                image = "https://images.unsplash.com/photo-1552053831-71594a27632d?w=300&h=200&fit=crop",
                description = "Adventure-loving pup who loves hiking and swimming!"
            ),
            FeaturedPet(
                name = "Whiskers",
                breed = "Persian Cat",
                image = "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?w=300&h=200&fit=crop",
                description = "Elegant and graceful, loves sunny spots and gentle pets."
            ),
            FeaturedPet(
                name = "Buddy",
                breed = "Labrador",
                image = "https://images.unsplash.com/photo-1546527868-ccb7ee7dfa6a?w=300&h=200&fit=crop",
                description = "Playful and energetic, always ready for a game of fetch!"
            )
        )
        
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            featuredPets.forEach { pet ->
                FeaturedPetCard(pet = pet)
            }
        }
    }
}

@Composable
private fun DiscoverSection(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Pets,
                contentDescription = "Pets",
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Share Your Pet's Story",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Post photos, share moments, and connect with fellow pet enthusiasts. Your pet's journey could inspire others!",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = { /* TODO: Navigate to post creation */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Start Sharing")
            }
        }
    }
}

@Composable
private fun FeaturedPetCard(
    pet: FeaturedPet,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        // Frosted glass blurred background
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(12.dp))
                .graphicsLayer {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        renderEffect = RenderEffect
                            .createBlurEffect(20f, 20f, Shader.TileMode.CLAMP)
                            .asComposeRenderEffect()
                    }
                    clip = true
                }
                .background(
                    Color.Gray.copy(alpha = 0.10f),
                    RoundedCornerShape(12.dp)
                )
        )
        
        // Card content (sharp)
        Card(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = pet.image,
                    contentDescription = "${pet.name}'s photo",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = pet.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White.copy(alpha = 0.95f)
                    )
                    
                    Text(
                        text = pet.breed,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.75f)
                    )
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    Text(
                        text = pet.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}

private data class FeaturedPet(
    val name: String,
    val breed: String,
    val image: String,
    val description: String
) 