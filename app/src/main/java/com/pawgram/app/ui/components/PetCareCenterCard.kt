package com.pawgram.app.ui.components

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pawgram.app.data.model.PetCareCenter

@Composable
fun PetCareCenterCard(
    center: PetCareCenter,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        // Frosted glass blurred background
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
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Logo
                AsyncImage(
                    model = center.logo,
                    contentDescription = "${center.name} logo",
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(30.dp)),
                    contentScale = ContentScale.Crop
                )
                
                Spacer(modifier = Modifier.width(16.dp))
                
                // Center Details
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = center.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White.copy(alpha = 0.95f)
                    )
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    // Location
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Location",
                            modifier = Modifier.size(16.dp),
                            tint = Color.White.copy(alpha = 0.75f)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = center.getDistanceDisplay(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.75f)
                        )
                    }

                    Spacer(modifier = Modifier.height(2.dp))

                    // Rating
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            modifier = Modifier.size(16.dp),
                            tint = Color.White.copy(alpha = 0.75f)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = center.getRatingDisplay(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.75f)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Service Tags
                    Row {
                        center.services.take(2).forEach { service ->
                            Card(
                                modifier = Modifier.padding(end = 8.dp),
                                shape = RoundedCornerShape(8.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White.copy(alpha = 0.15f)
                                )
                            ) {
                                Text(
                                    text = service,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = Color.White.copy(alpha = 0.8f)
                                )
                            }
                        }
                        
                        if (center.services.size > 2) {
                            Card(
                                shape = RoundedCornerShape(8.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White.copy(alpha = 0.15f)
                                )
                            ) {
                                Text(
                                    text = "+${center.services.size - 1}",
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp).wrapContentWidth(),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = Color.White.copy(alpha = 0.8f)
                                )
                            }
                        }
                    }
                }
                
                // More options button
                IconButton(
                    onClick = { /* TODO: Show options menu */ },
                    modifier = Modifier.align(Alignment.Top)
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More options",
                        tint = Color.White.copy(alpha = 0.75f),
                    )
                }
            }
        }
    }
} 