package com.pawgram.app.ui.components

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.pawgram.app.data.model.Pet

@Composable
fun PetCard(
    pet: Pet,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(160.dp)
            .height(200.dp)
    ) {
        // Frosted glass blurred background with rounded corners
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(40.dp))
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
                    RoundedCornerShape(40.dp)
                )
        )
        // Card content (sharp)
        Card(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(38.dp)),
            shape = RoundedCornerShape(38.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(14.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Pet Profile Picture
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(38.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = pet.profilePicture,
                            contentDescription = "${pet.name}'s profile picture",
                            modifier = Modifier
                                .size(70.dp)
                                .clip(RoundedCornerShape(30.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                // Pet Name - Enhanced for glass morphism
                Text(
                    text = pet.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White.copy(alpha = 0.95f),
                    fontSize = 18.sp
                )
                // Pet Details - Enhanced for glass morphism
                Text(
                    text = "${pet.breed}, ${pet.getAgeDisplay()}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.75f),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                // Progress Bar - Enhanced for glass morphism
                LinearProgressIndicator(
                    progress = pet.progress.toFloat() / pet.totalProgress.toFloat(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = Color(0xFF4CAF50).copy(alpha = 0.9f),
                    trackColor = Color.White.copy(alpha = 0.2f)
                )
                Spacer(modifier = Modifier.height(6.dp))
                // Progress Text - Enhanced for glass morphism
                Text(
                    text = pet.getProgressDisplay(),
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 12.sp
                )
            }
        }
    }
} 