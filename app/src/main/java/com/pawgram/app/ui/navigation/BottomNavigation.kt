package com.pawgram.app.ui.navigation

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class NavigationItem(
    val route: String,
    val icon: ImageVector,
    val label: String
)

@Composable
fun BottomNavigation(
    currentRoute: String?,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val navigationItems = listOf(
        NavigationItem("home", Icons.Default.Home, "HOME"),
        NavigationItem("explore", Icons.Default.Explore, "EXPLORE"),
        NavigationItem("profile", Icons.Default.Person, "PROFILE")
    )

    // Floating navigation container with glass morphism effect
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        // Glass morphism blurred background
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .clip(RoundedCornerShape(50.dp))
                .graphicsLayer {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        renderEffect = RenderEffect
                            .createBlurEffect(25f, 25f, Shader.TileMode.CLAMP)
                            .asComposeRenderEffect()
                    }
                    clip = true
                }
                .background(
                    Color.Black.copy(alpha = 0.35f),
                    RoundedCornerShape(50.dp)
                )
        )
        
        // Foreground content with glass morphism background
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .clip(RoundedCornerShape(50.dp)),
            color = Color.Transparent,
            shadowElevation = 0.dp
        ) {
            Box(
                modifier = Modifier
                    .background(
                        Color.Black.copy(alpha = 0.50f),
                        RoundedCornerShape(50.dp)
                    )
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    navigationItems.forEach { item ->
                        NavigationItemComponent(
                            item = item,
                            isSelected = currentRoute == item.route,
                            onClick = { onNavigate(item.route) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NavigationItemComponent(
    item: NavigationItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .clickable { onClick() }
            .background(
                if(isSelected) {
                    Color.White
                } else {
                    Color.Transparent
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.label,
            tint = if (isSelected) Color.Black else Color.White.copy(alpha = 0.9f),
            modifier = Modifier.size(22.dp)
        )
    }
} 