package ca.csf.tp2_williamguay.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ca.csf.tp2_williamguay.composable.RecipeCard
import ca.csf.tp2_williamguay.model.Recipe
import ca.csf.tp2_williamguay.viewModel.RecipeViewModel

@Composable
fun SearchView(viewModel: RecipeViewModel, innerPadding: PaddingValues, navController: NavController, onClick: (Recipe) -> Unit){
    var ingredient by rememberSaveable { mutableStateOf("") }
    val isLoading = viewModel.isLoading.value
    val searchResult = viewModel.searchResult

    Column (modifier = Modifier.padding(innerPadding).verticalScroll(rememberScrollState()).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(value = ingredient, onValueChange = {ingredient = it},
            trailingIcon = { IconButton(onClick = { viewModel.refreshSearchResult(ingredient) }){
                Icon(imageVector = Icons.Default.Search, contentDescription = "recherche")
            } })

        if (isLoading) CircularProgressIndicator()
        if(searchResult != null) {
            for (recipe in searchResult){
                RecipeCard(recipe, onClick, viewModel)
            }
        }

        Button(onClick = { navController.popBackStack() }) { Text("Retour") }
    }
}