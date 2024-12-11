package ca.csf.tp2_williamguay.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ca.csf.tp2_williamguay.composable.RecipeCard
import ca.csf.tp2_williamguay.model.Recipe
import ca.csf.tp2_williamguay.viewModel.RecipeViewModel

@Composable
fun FavouriteRecipesList(viewModel: RecipeViewModel, innerPadding: PaddingValues, navController: NavController, onClick: (Recipe) -> Unit){
    val isLoading = viewModel.isLoading.value
    val recipes = viewModel.favouriteRecipes

    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(innerPadding).verticalScroll(rememberScrollState()).fillMaxSize()) {
        if (isLoading) CircularProgressIndicator()
        if(recipes != null) {
            for (recipe in recipes){
                RecipeCard(recipe, onClick, viewModel)
            }
        }
    }
}