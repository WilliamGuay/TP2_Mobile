package ca.csf.tp2_williamguay.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ca.csf.tp2_williamguay.R
import ca.csf.tp2_williamguay.model.Recipe

@Composable
fun RecipeInfo(recipe: Recipe){
    Row(modifier = Modifier.fillMaxWidth()){
        Column(modifier = Modifier.fillMaxWidth(0.5f)) {
            Row {
                if (recipe.isVegetarian) {
                    Icon(
                        painter = painterResource(R.drawable.leaf),
                        contentDescription = "Végétarien",
                        modifier =  Modifier.size(50.dp)
                    )
                }
                if (recipe.isDiaryFree) {
                    Icon(
                        painter = painterResource(R.drawable.droplet_slash),
                        contentDescription = "Sans lactose",
                        modifier =  Modifier.size(50.dp)
                    )
                }
                if (recipe.isGlutenFree) {
                    Icon(
                        painter = painterResource(R.drawable.wheat),
                        contentDescription = "Sans gluten",
                        modifier =  Modifier.size(50.dp)
                    )
                }
            }
        }
        Column(modifier = Modifier.fillMaxWidth(0.5f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.clock),
                    contentDescription = "temps de préparation",
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(recipe.readyInMinutes.toString())
            }
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.dish),
                    contentDescription = "portions",
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(recipe.servings.toString())
            }
        }
    }
}