package com.example.exploreboston

/**
 * Sealed class representing all navigation routes in the app.
 * Each object holds a route string and a user-friendly title.
 */
sealed class BostonRoutes(
    val route: String,
    val title: String,
) {
    object Home : BostonRoutes("home", "Home")
    object Categories : BostonRoutes("categories", "Categories")
    object List : BostonRoutes("list/{category}", "List") {
        /** Helper to create a route string with a category argument */
        fun createRoute(category: String) = "list/$category"
    }
    object Detail : BostonRoutes("detail/{category}/{id}", "Details") {
        /** Helper to create a route string with category and id arguments */
        fun createRoute(category: String, id: Int) = "detail/$category/$id"
    }
}
