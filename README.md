# Explore-Boston
CS501 (Asgn5-Q3)

---

## Explore Boston

Explore Boston is a multi-screen Android app built with Jetpack Compose demonstrating navigation patterns. It features:
- A **Home** screen as an intro  
- **Categories** screen listing museums, parks, and restaurants
- **List** screen showing items filtered by category
- **Detail** screen with detailed info on selected location

The app uses type-safe navigation routes passing String and Int arguments, and manages back stack to clear on home navigation.

---
## Features
- Jetpack Compose UI components
- Navigation with `NavController` and `NavHost`
- Type-safe sealed class for routes with route-building helpers
- Argument passing for category (String) and item ID (Int) in routes
- Back stack management with 'popUpTo(inclusive = true)' on home navigation
- Dynamic `TopAppBar` with back and home icons
- Back button disabled on Home screen to prevent accidental exits

---
## Architecture and Code Structure
- `BostonRoutes.kt`: Holds all navigation routes and titles.
- `NavGraph.kt`: Defines the composable navigation graph with all screens linked to routes.
- `MainActivity.kt`: Hosts Compose UI and scaffold with `TopAppBar`, calls NavGraph.
- `Screens.kt`: Contains composable functions for Home, Categories, List, and Detail screens.

---
## AI Documentation
- **AI used for:** Assisting with improving route arguments, particularly on how to disable the navigation back button when on the home screen, as well as enhancing the navigation graph and using it to improve the user experience by adding Icons.
- **I fixed:** Had to separately implement `implementation("androidx.navigation:navigation-compose:2.9.5")` in the Gradle app, and I also fixed the deprecated code while using the most recent imports (e.g. using `import androidx.compose.material.icons.automirrored.filled.ArrowBack` instead of `import androidx.compose.material.icons.filled.ArrowBack`)

---

## 🛠️ How to Run
```bash
git clone https://github.com/JerryBLT/Explore-Boston.git
```
---
## Contribution
Feel free to submit issues or pull requests for fixes or improvements!

---
## License
This project is licensed under the MIT License. See LICENSE for details.

---
## Contact
- `Name`: Jerry Teixeira
- `Email`: jerrybt@bu.edu

