package ca.csf.tp2_williamguay.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import ca.csf.tp2_williamguay.R
import ca.csf.tp2_williamguay.screens.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navigationController: NavHostController){
    CenterAlignedTopAppBar(title = { Text("Ze Cuisine") }, navigationIcon = {
        IconButton(onClick = {
            navigationController.navigate(Screens.FavouritRecipesList.name)
        }) { Icon(painter = painterResource(R.drawable.star), contentDescription = "Vers les favoris") }
        }, actions = {
            IconButton(onClick = { navigationController.navigate(Screens.SearchRecipe.name) }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "recherche")
            }
    })
}