package co.poligran.viralpaws

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.poligran.viralpaws.ui.screens.*
import co.poligran.viralpaws.ui.theme.ViralPawsTheme
import co.poligran.viralpaws.data.SampleData
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViralPawsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    var isLoggedIn by remember { mutableStateOf(false) }
                    
                    NavHost(
                        navController = navController,
                        startDestination = if (isLoggedIn) "main" else "login"
                    ) {
                        composable("login") {
                            LoginScreen(
                                onLoginSuccess = {
                                    isLoggedIn = true
                                    navController.navigate("main") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                },
                                onRegisterClick = {
                                    navController.navigate("register")
                                }
                            )
                        }
                        
                        composable("register") {
                            RegisterScreen(
                                onRegisterSuccess = {
                                    isLoggedIn = true
                                    navController.navigate("main") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                },
                                onBackToLogin = {
                                    navController.navigateUp()
                                }
                            )
                        }
                        
                        composable("main") {
                            ViralPawsApp(
                                onLogout = {
                                    isLoggedIn = false
                                    navController.navigate("login") {
                                        popUpTo("main") { inclusive = true }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViralPawsApp(onLogout: () -> Unit) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.app_name),
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineMedium
                )
                Divider()
                Spacer(modifier = Modifier.height(8.dp))
                
                listOf(
                    Screen.Home,
                    Screen.Explore,
                    Screen.Shop,
                    Screen.Adoption,
                    Screen.Veterinaries,
                    Screen.Messages,
                    Screen.Profile,
                    Screen.Health
                ).forEach { screen ->
                    NavigationDrawerItem(
                        icon = { 
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = null
                            )
                        },
                        label = { Text(screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        },
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
                
                Spacer(modifier = Modifier.weight(1f))
                Divider()
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Logout, contentDescription = null) },
                    label = { Text("Cerrar Sesión") },
                    selected = false,
                    onClick = onLogout,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(id = R.string.app_name)) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                        }
                    }
                )
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(Screen.Home.route) { HomeScreen() }
                composable(Screen.Explore.route) { ExploreScreen() }
                composable(Screen.Shop.route) { ShopScreen() }
                composable(Screen.Adoption.route) { 
                    AdoptionScreen(
                        onPetClick = { petId ->
                            navController.navigate(Screen.PetDetails.createRoute(petId))
                        }
                    )
                }
                composable(Screen.Messages.route) { MessagesScreen() }
                composable(Screen.Profile.route) { ProfileScreen(onLogout = onLogout) }
                composable(Screen.Health.route) { HealthScreen() }
                composable(Screen.Veterinaries.route) { 
                    VeterinariesScreen(
                        onVeterinaryClick = { veterinaryId ->
                            navController.navigate(Screen.VeterinaryDetails.createRoute(veterinaryId))
                        }
                    )
                }
                
                composable(
                    route = Screen.PetDetails.route,
                    arguments = listOf(navArgument("petId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val petId = backStackEntry.arguments?.getString("petId")
                    val pet = SampleData.sampleAdoptionPets.find { it.id == petId }
                    if (pet != null) {
                        PetDetailsScreen(
                            pet = pet,
                            onBackClick = { navController.navigateUp() },
                            onAdoptClick = { 
                                navController.navigate(Screen.AdoptionForm.createRoute(pet.id))
                            }
                        )
                    }
                }
                
                composable(
                    route = Screen.AdoptionForm.route,
                    arguments = listOf(navArgument("petId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val petId = backStackEntry.arguments?.getString("petId")
                    val pet = SampleData.sampleAdoptionPets.find { it.id == petId }
                    if (pet != null) {
                        AdoptionFormScreen(
                            pet = pet,
                            onSubmit = { form ->
                                // Aquí se procesaría el formulario
                                navController.navigate(Screen.AdoptionConfirmation.createRoute(pet.id))
                            },
                            onBackClick = { navController.navigateUp() }
                        )
                    }
                }
                
                composable(
                    route = Screen.AdoptionConfirmation.route,
                    arguments = listOf(navArgument("petId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val petId = backStackEntry.arguments?.getString("petId")
                    val pet = SampleData.sampleAdoptionPets.find { it.id == petId }
                    if (pet != null) {
                        AdoptionConfirmationScreen(
                            pet = pet,
                            onBackToHome = {
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(Screen.Home.route) { inclusive = true }
                                }
                            }
                        )
                    }
                }
                
                composable(
                    route = Screen.VeterinaryDetails.route,
                    arguments = listOf(navArgument("veterinaryId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val veterinaryId = backStackEntry.arguments?.getString("veterinaryId")
                    if (veterinaryId != null) {
                        VeterinaryDetailsScreen(
                            veterinaryId = veterinaryId,
                            onBackClick = { navController.navigateUp() }
                        )
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Login : Screen("login", "Iniciar Sesión", Icons.Default.Person)
    object Register : Screen("register", "Registrarse", Icons.Default.PersonAdd)
    object Home : Screen("home", "Inicio", Icons.Default.Home)
    object Explore : Screen("explore", "Explorar", Icons.Default.Search)
    object Shop : Screen("shop", "Tienda", Icons.Default.ShoppingCart)
    object Messages : Screen("messages", "Mensajes", Icons.Default.Message)
    object Profile : Screen("profile", "Perfil", Icons.Default.Person)
    object Adoption : Screen("adoption", "Adopción", Icons.Default.Pets)
    object PetDetails : Screen("pet_details/{petId}", "Detalles de la Mascota", Icons.Default.Pets)
    object AdoptionForm : Screen("adoption_form/{petId}", "Formulario de Adopción", Icons.Default.Edit)
    object AdoptionConfirmation : Screen("adoption_confirmation/{petId}", "Confirmación de Adopción", Icons.Default.CheckCircle)
    object Health : Screen("health", "Salud", Icons.Default.HealthAndSafety)
    object Veterinaries : Screen("veterinaries", "Veterinarias", Icons.Default.LocalHospital)
    object VeterinaryDetails : Screen("veterinary_details/{veterinaryId}", "Detalles de la Veterinaria", Icons.Default.LocalHospital)
}

fun Screen.createRoute(vararg args: String): String {
    return buildString {
        append(route)
        args.forEach { arg ->
            append("/$arg")
        }
    }
}