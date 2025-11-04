package com.example.exploreboston

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType

/**
 * Defines the navigation graph connecting routes to composable screens.
 *
 * @param navController Controller to manage navigation state.
 * @param modifier Modifier allowing styling and padding.
 * @param onNavigateHome Callback to navigate to Home, clearing backstack.
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onNavigateHome: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = BostonRoutes.Home.route,
        modifier = modifier
    ) {
        // Home screen route
        composable(BostonRoutes.Home.route) {
            HomeScreen(onNavigateToCategories = { navController.navigate(BostonRoutes.Categories.route) })
        }
        // Categories screen route
        composable(BostonRoutes.Categories.route) {
            CategoriesScreen { category ->
                navController.navigate(BostonRoutes.List.createRoute(category))
            }
        }
        // List screen route with category argument
        composable(
            BostonRoutes.List.route,
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category").orEmpty()
            ListScreen(category) { id ->
                navController.navigate(BostonRoutes.Detail.createRoute(category, id))
            }
        }
        // Detail screen route with category and id arguments
        composable(
            BostonRoutes.Detail.route,
            arguments = listOf(
                navArgument("category") { type = NavType.StringType },
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category").orEmpty()
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            DetailScreen(category, id, onNavigateHome)
        }
    }
}
