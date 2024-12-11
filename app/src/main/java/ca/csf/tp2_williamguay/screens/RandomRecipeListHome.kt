package ca.csf.tp2_williamguay.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ca.csf.tp2_williamguay.R
import ca.csf.tp2_williamguay.composable.RecipeCard
import ca.csf.tp2_williamguay.composable.RecipeInfo
import ca.csf.tp2_williamguay.model.Recipe
import ca.csf.tp2_williamguay.viewModel.RecipeViewModel
import coil.compose.AsyncImage

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun RandomRecipeListHome(viewModel: RecipeViewModel, innerPadding: PaddingValues, navController: NavController, onClick: (Recipe) -> Unit){
    val isLoading = viewModel.isLoading.value
    val recipes = viewModel.recipes

    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(innerPadding).verticalScroll(rememberScrollState()).fillMaxSize()) {
        if (isLoading) CircularProgressIndicator()
        if(recipes != null) {
            for (recipe in recipes){
                RecipeCard(recipe, onClick, viewModel)
            }
        }
    }

    print("recettes affichées")
}