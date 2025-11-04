package com.example.exploreboston

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import com.example.exploreboston.ui.theme.ExploreBostonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExploreBostonTheme {
                ExploreBostonApp()
            }
        }
    }
}

/**
 * Main app composable displaying Scaffold with TopAppBar and NavGraph.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreBostonApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: BostonRoutes.Home.route

    // Lock back button navigation when at Home screen after full navigation
    var homeLocked by remember { mutableStateOf(false) }
    LaunchedEffect(currentRoute) { homeLocked = currentRoute == BostonRoutes.Home.route }
    BackHandler(enabled = homeLocked) {
        // Consume back press to disable it at Home
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        when {
                            currentRoute.startsWith("categories") -> BostonRoutes.Categories.title
                            currentRoute.startsWith("list") -> BostonRoutes.List.title
                            currentRoute.startsWith("detail") -> BostonRoutes.Detail.title
                            else -> BostonRoutes.Home.title
                        }
                    )
                },
                navigationIcon = {
                    if (!homeLocked && currentRoute != BostonRoutes.Home.route) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(BostonRoutes.Home.route) {
                            popUpTo(BostonRoutes.Home.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    }) {
                        Icon(Icons.Filled.Home, contentDescription = "Home")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        }
    ) { innerPadding ->
        NavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            onNavigateHome = {
                navController.navigate(BostonRoutes.Home.route) {
                    popUpTo(BostonRoutes.Home.route) { inclusive = true }
                    launchSingleTop = true
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExploreBostonTheme {
        ExploreBostonApp()
    }
}