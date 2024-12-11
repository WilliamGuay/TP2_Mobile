package ca.csf.tp2_williamguay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import ca.csf.tp2_williamguay.composable.TopBar
import ca.csf.tp2_williamguay.database.RecipeDatabase
import ca.csf.tp2_williamguay.screens.FavouriteRecipesList
import ca.csf.tp2_williamguay.screens.RandomRecipeListHome
import ca.csf.tp2_williamguay.screens.RecipeDetails
import ca.csf.tp2_williamguay.screens.Screens
import ca.csf.tp2_williamguay.screens.SearchView
import ca.csf.tp2_williamguay.ui.theme.TP2_WilliamGuayTheme
import ca.csf.tp2_williamguay.viewModel.RecipeViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TP2_WilliamGuayTheme {

                val navigationController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = { TopBar(navigationController) }) { innerPadding ->
                    RecipeApp(innerPadding, navigationController)
                }
            }
        }
    }
}

@Composable
fun RecipeApp(innerPadding: PaddingValues, navController: NavHostController){
    val applicationContext = LocalContext.current

    val favouriteRecipesDb = Room.databaseBuilder(
        applicationContext,
        RecipeDatabase::class.java, "favourite_recipe"
    ).fallbackToDestructiveMigration().build()

    val viewModel = RecipeViewModel(favouriteRecipesDb)

    Navigation(navController, viewModel, innerPadding)
}

@Composable
fun Navigation(navigationController: NavHostController, viewModel: RecipeViewModel, innerPadding: PaddingValues){

    var activeRecipe by rememberSaveable { mutableStateOf(viewModel.recipes?.get(0)) }

    NavHost(
        navController = navigationController,
        startDestination = Screens.RandomRecipeListHome.name
    ) {
        composable(route = Screens.RandomRecipeListHome.name) {
            RandomRecipeListHome(viewModel, innerPadding, navigationController, {
                activeRecipe = it
                navigationController.navigate(route = Screens.RecipeDetails.name)
            })
        }

        composable(route = Screens.RecipeDetails.name) {
            RecipeDetails(activeRecipe, innerPadding, viewModel, navigationController)
        }

        composable(route = Screens.FavouritRecipesList.name){
            FavouriteRecipesList(viewModel, innerPadding, navigationController, {
                activeRecipe = it
                navigationController.navigate(route = Screens.RecipeDetails.name)
            })
        }

        composable(route = Screens.SearchRecipe.name) {
            SearchView(viewModel, innerPadding, navigationController, {
                activeRecipe = it
                navigationController.navigate(route = Screens.RecipeDetails.name)
            })
        }
    }
}