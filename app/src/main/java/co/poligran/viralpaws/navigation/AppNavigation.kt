package co.poligran.viralpaws.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.poligran.viralpaws.ui.screens.PetHealthScreen
import co.poligran.viralpaws.ui.screens.RegistrationScreen

sealed class Screen(val route: String) {
    object Registration : Screen("registration")
    object PetHealth : Screen("pet_health")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Registration.route
    ) {
        composable(Screen.Registration.route) {
            RegistrationScreen(
                onRegistrationComplete = {
                    navController.navigate(Screen.PetHealth.route)
                }
            )
        }
        
        composable(Screen.PetHealth.route) {
            PetHealthScreen()
        }
    }
} 