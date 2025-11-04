package com.example.exploreboston

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(onNavigateToCategories: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Welcome to Explore Boston")
            Spacer(Modifier.height(16.dp))
            Button(onClick = onNavigateToCategories) {
                Text("Start Tour")
            }
        }
    }
}

@Composable
fun CategoriesScreen(onCategorySelected: (String) -> Unit) {
    val categories = listOf("Museums", "Parks", "Restaurants")
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Select a Category", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        categories.forEach { category ->
            Text(
                text = category,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCategorySelected(category) }
                    .padding(12.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun ListScreen(category: String, onItemSelected: (Int) -> Unit) {
    val items = when (category) {
        "Museums" -> listOf("MIT Museum", "Museum of Fine Arts", "Children's Museum")
        "Parks" -> listOf("Boston Common", "Public Garden", "Rose Kennedy Greenway")
        "Restaurants" -> listOf("Legal Sea Foods", "Union Oyster House", "Neptune Oyster")
        else -> emptyList()
    }
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item {
            Text("All $category", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 16.dp))
        }
        items(items.indices.toList()) { index ->
            Text(
                text = items[index],
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemSelected(index) }
                    .padding(vertical = 12.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun DetailScreen(category: String, id: Int, onNavigateHome: () -> Unit) {
    val detailsMap = mapOf(
        "Museums" to listOf("MIT Museum: Technology and innovation exhibits.", "Museum of Fine Arts: Art from around the world.", "Children's Museum: Interactive exhibits for kids."),
        "Parks" to listOf("Boston Common: Historic central park.", "Public Garden: Beautiful botanical garden.", "Rose Kennedy Greenway: Urban park with art installations."),
        "Restaurants" to listOf("Legal Sea Foods: Fresh seafood classics.", "Union Oyster House: America's oldest restaurant.", "Neptune Oyster: Famous for oysters and seafood.")
    )
    val detailText = detailsMap[category]?.getOrNull(id) ?: "Details unavailable."
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(detailText, style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(24.dp))
        Button(onClick = onNavigateHome) {
            Text("Back to Home")
        }
    }
}
