package ca.csf.tp2_williamguay.screens

import android.widget.Space
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
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ca.csf.tp2_williamguay.R
import ca.csf.tp2_williamguay.composable.FavouriteButton
import ca.csf.tp2_williamguay.composable.RecipeInfo
import ca.csf.tp2_williamguay.model.Ingredient
import ca.csf.tp2_williamguay.model.Instruction
import ca.csf.tp2_williamguay.model.Recipe
import ca.csf.tp2_williamguay.viewModel.RecipeViewModel
import coil.compose.AsyncImage

@Composable
fun RecipeDetails(recipe: Recipe?, innerPadding: PaddingValues, viewModel: RecipeViewModel, navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxSize().padding(innerPadding),
        bottomBar = { BottomAppBar { Button(onClick = {navController.popBackStack()}) { Text("Retour") } } }) { innerPadding->

        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(innerPadding)) {
            if (recipe != null) {
                Text(recipe.name)

                FavouriteButton(recipe.id, viewModel)

                AsyncImage(
                    model = recipe.img,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )

                RecipeInfo(recipe)

                Spacer(Modifier.padding(5.dp))

                Text("Ingrédients", fontSize = (25.sp))

                for (ingredient : Ingredient in recipe.ingredients){
                    Text(ingredient.ingredient)
                }

                Spacer(Modifier.padding(15.dp))

                Text("Préparation:", fontSize = 25.sp)

                for (instruction: Instruction in recipe.instructions){
                    Text(instruction.step)
                    Spacer(Modifier.padding(5.dp))
                }
            }
        }
    }
}