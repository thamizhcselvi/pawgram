package com.pawgram.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.pawgram.app.ui.components.PetCard
import com.pawgram.app.ui.components.PetCareCenterCard
import com.pawgram.app.ui.viewmodel.HomeUiState
import com.pawgram.app.ui.viewmodel.HomeViewModel
import androidx.compose.ui.res.painterResource
import com.pawgram.app.R
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.Person
import coil.compose.AsyncImagePainter
import androidx.compose.foundation.Image
import androidx.compose.material.icons.outlined.CircleNotifications
import androidx.compose.material3.CircularProgressIndicator

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        when(uiState) {
            is HomeUiState.Error -> {}
            is HomeUiState.Loaded -> HomeLoaded(uiState as HomeUiState.Loaded)
            is HomeUiState.Loading -> {}
        }
    }
}

@Composable
private fun HomeLoaded(uiState: HomeUiState.Loaded) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 20.dp,
            end = 20.dp,
            top = 20.dp,
            bottom = 100.dp // Extra space to avoid bottom navigation overlap
        ),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        item {
            HomeHeader(user = uiState.user)
        }

        item {
            PetsSection(pets = uiState.pets)
        }

        item {
            PetCareSection(petCareCenters = uiState.petCareCenters)
        }
    }
}

@Composable
private fun HomeHeader(
    user: com.pawgram.app.data.model.User?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            // User Profile Picture in Circle - Same as Profile Screen
            AsyncImage(
                model = "https://images.unsplash.com/photo-1583337130417-3346a1be7dee?w=400&h=400&fit=crop&crop=face",
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(
                        Color(0xFF4CAF50).copy(alpha = 0.2f),
                        CircleShape
                    ),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Welcome Message
            Row {
                Text(
                    text = "Welcome back, ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    fontSize = 16.sp
                )
                Text(
                    text = "${user?.name ?: "Phyllis"}!",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
        
        // Notification Icon
        IconButton(
            onClick = { /* TODO: Handle notifications */ }
        ) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "Notifications",
                tint = Color.White,
                modifier = Modifier.size(26.dp)
            )
        }
    }
}

@Composable
private fun PetsSection(
    pets: List<com.pawgram.app.data.model.Pet>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Your Pets",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            fontSize = 22.sp
        )
        
        Spacer(modifier = Modifier.height(20.dp))
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(pets) { pet ->
                PetCard(pet = pet)
            }
        }
    }
}

@Composable
private fun PetCareSection(
    petCareCenters: List<com.pawgram.app.data.model.PetCareCenter>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Pet Care Nearby",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                fontSize = 24.sp
            )
            
            TextButton(
                onClick = { /* TODO: Navigate to see more */ }
            ) {
                Text(
                    text = "See more",
                    color = Color(0xFF9E9E9E),
                    fontSize = 14.sp
                )
            }
        }
        
        Spacer(modifier = Modifier.height(20.dp))
        
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            petCareCenters.forEach { center ->
                PetCareCenterCard(center = center)
            }
        }
    }
} 