package ca.csf.tp2_williamguay.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.csf.tp2_williamguay.database.RecipeDao
import ca.csf.tp2_williamguay.database.RecipeDatabase
import ca.csf.tp2_williamguay.database.RecipeDatabaseEntity
import ca.csf.tp2_williamguay.model.Recipe
import ca.csf.tp2_williamguay.service.SpoonacularService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeViewModel(db: RecipeDatabase): ViewModel() {
    var recipes: ArrayList<Recipe>? by mutableStateOf(null)
    var favouriteRecipes: ArrayList<Recipe>? by mutableStateOf(null)
    var searchResult: ArrayList<Recipe>? by mutableStateOf(null)

    val recipeDao = db.RecipeDao()

    var isLoading = mutableStateOf(false)

    init{
        refreshRandomRecipe()
        refreshFavouriteRecipes()
    }

    fun getRecipeFromIndex(index: Int): Recipe?{
        return recipes?.get(index)
    }

    fun addFavouriteRecipe(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            recipeDao.insert(RecipeDatabaseEntity(id))

            refreshFavouriteRecipes()
        }
    }

    fun removeFavouriteRecipe(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            recipeDao.delete(id)
            System.out.println("recipe removed")

            refreshFavouriteRecipes()
        }
    }

    fun refreshFavouriteRecipes(){
        isLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            if (favouriteRecipes == null){
                favouriteRecipes = ArrayList()
            }

            var favRecipesIds: List<RecipeDatabaseEntity>? = null
            recipeDao.getAll().collect{favRecipesIds = it}

            val ids = favRecipesIds!!.joinToString(",")

            val obtainedRecipes = SpoonacularService.fetchRecipesById(ids)
            favouriteRecipes!!.addAll(obtainedRecipes)

            isLoading.value = false
        }
    }

    fun refreshRandomRecipe(){
        isLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            if (recipes == null){
                recipes = ArrayList()
            }

            System.out.println("début de l'actualisation des recettes")

            val obtainedRecipes = SpoonacularService.fetchRandomRecepies()
            recipes!!.addAll(obtainedRecipes)

            isLoading.value = false
            System.out.println("ne charge plus")
        }
        System.out.println("recettes actualisées")
    }

    fun getRandomRecipes(): List<Recipe>{
        return  recipes!!
    }

    fun isFavourite(id: Int): Boolean{
        var isPresent = false

        viewModelScope.launch(Dispatchers.IO) {
            var favRecipes: List<RecipeDatabaseEntity>? = null
            recipeDao.getAll().collect{favRecipes = it}

            for (recipe: RecipeDatabaseEntity in favRecipes!!){
                if (recipe.idRecipe == id)
                    isPresent = true
            }
        }

        return isPresent
    }

    fun refreshSearchResult(ingredient: String){
        isLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            if (searchResult == null){
                searchResult = ArrayList()
            }

            val obtainedRecipes = SpoonacularService.fetchRecipeFromIngredient(ingredient)
            searchResult!!.addAll(obtainedRecipes)

            isLoading.value = false
        }
    }

}
