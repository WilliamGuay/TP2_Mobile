package ca.csf.tp2_williamguay.composable

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ca.csf.tp2_williamguay.R
import ca.csf.tp2_williamguay.viewModel.RecipeViewModel

@Composable
fun FavouriteButton(recipeId: Int, viewModel: RecipeViewModel){

    var isFavourite = viewModel.isFavourite(recipeId)

    IconButton(onClick = {
        if (isFavourite) {
            isFavourite = false
            viewModel.removeFavouriteRecipe(recipeId)
        }
        else {
            isFavourite = true
            viewModel.addFavouriteRecipe(recipeId)
        }
    }){
        if (isFavourite)
            Icon(painter = painterResource(R.drawable.star), contentDescription = "favourite recipe")
        else
            Icon(painter = painterResource(R.drawable.star_outlined), contentDescription = "not favourite recipe")
    }
}