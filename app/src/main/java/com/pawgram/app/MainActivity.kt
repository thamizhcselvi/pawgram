package com.pawgram.app

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pawgram.app.data.api.MockPawgramApiService
import com.pawgram.app.data.repository.PawgramRepository
import com.pawgram.app.ui.navigation.BottomNavigation
import com.pawgram.app.ui.screens.ExploreScreen
import com.pawgram.app.ui.screens.HomeScreen
import com.pawgram.app.ui.screens.ProfileScreen
import com.pawgram.app.ui.theme.PawgramTheme
import com.pawgram.app.ui.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PawgramTheme {
                PawgramApp()
            }
        }
    }
}

@Composable
fun PawgramApp() {
    var currentRoute by remember { mutableStateOf("home") }
    
    // Initialize dependencies
    val apiService = remember { MockPawgramApiService() }
    val repository = remember { PawgramRepository(apiService) }
    
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Overall app glass morphism background
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        renderEffect = RenderEffect
                            .createBlurEffect(30f, 30f, Shader.TileMode.CLAMP)
                            .asComposeRenderEffect()
                    }
                }
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF1A1A1A),
                            Color(0xFF2D2D2D),
                            Color(0xFF1A1A1A)
                        ),
                        radius = 1000f
                    )
                )
        )
        
        // Main content area - full screen
        when (currentRoute) {
            "home" -> {
                val homeViewModel: HomeViewModel = viewModel {
                    HomeViewModel(repository)
                }
                HomeScreen(
                    viewModel = homeViewModel,
                    modifier = Modifier.fillMaxSize()
                )
            }
            "explore" -> ExploreScreen(
                modifier = Modifier.fillMaxSize()
            )
            "profile" -> ProfileScreen(
                modifier = Modifier.fillMaxSize()
            )
        }
        
        // Floating bottom navigation
        BottomNavigation(
            currentRoute = currentRoute,
            onNavigate = { route ->
                currentRoute = route
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
} 