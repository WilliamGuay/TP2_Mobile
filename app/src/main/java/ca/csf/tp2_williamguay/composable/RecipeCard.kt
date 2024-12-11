package ca.csf.tp2_williamguay.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ca.csf.tp2_williamguay.R
import ca.csf.tp2_williamguay.model.Recipe
import ca.csf.tp2_williamguay.viewModel.RecipeViewModel
import coil.compose.AsyncImage

@Composable
fun RecipeCard(recipe: Recipe, onClick: (Recipe) -> Unit, viewModel: RecipeViewModel){
    Card(modifier = Modifier.fillMaxWidth().padding(10.dp),
        onClick = {onClick(recipe)}) {
        Row(Modifier.padding(8.dp).fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxWidth(0.75f)) {
                Text(recipe.name)
            }
            FavouriteButton(recipe.id, viewModel)
        }
        Row(modifier = Modifier.fillMaxWidth()){
            Column {
                AsyncImage(
                    model = if (recipe.img == null) R.drawable.chef_310556 else recipe.img,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        RecipeInfo(recipe)
    }
}