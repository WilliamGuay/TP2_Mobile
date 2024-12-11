package ca.csf.tp2_williamguay.service

import ca.csf.tp2_williamguay.model.Recipe
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object SpoonacularService {
    private const val API_KEY = "db9d7cd9019c4e0c8ccedf36e87ab90a"

    private const val API_URL_RANDOM =
        "https://api.spoonacular.com/recipes/random?number=10&apiKey=$API_KEY"

    private const val API_URL_FAVOURITES =
        "https://api.spoonacular.com/recipes/informationBulk?apiKey=$API_KEY&ids="

    private const val API_URL_SEARCH_BY_INGREDIENT =
        "https://api.spoonacular.com/recipes/findByIngredients?apiKey=$API_KEY&number=10&ingredients="

    suspend fun fetchRandomRecepies(): List<Recipe> {
        val url = URL(API_URL_RANDOM)
        val connection = url.openConnection() as HttpURLConnection
        val data = connection.run {
            requestMethod = "GET"
            inputStream.bufferedReader().readText()
        }
        return getRecepiesFromJSONData(data)
    }

    suspend fun fetchRecipeFromIngredient(ingredient: String): List<Recipe>{
        val url = URL(API_URL_SEARCH_BY_INGREDIENT + ingredient.trim())
        val connection = url.openConnection() as HttpURLConnection
        val data = connection.run {
            requestMethod = "GET"
            inputStream.bufferedReader().readText()
        }
        return getRecepiesFromJSONData(data)
    }

    suspend fun fetchRecipesById(ids: String): List<Recipe>{
        val url = URL(API_URL_FAVOURITES + ids.trim())
        val connection = url.openConnection() as HttpURLConnection
        val data = connection.run {
            requestMethod = "GET"
            inputStream.bufferedReader().readText()
        }
        System.out.println(data);
        return getRecepiesFromJSONData(data)
    }

    suspend fun getRecepiesFromJSONData(data: String): List<Recipe>{
        val recipes = ArrayList<Recipe>()

        val recipesJson = JSONArray(data.removePrefix("{\"recipes\":").removeSuffix("}"))

        for (i in 0 .. recipesJson.length() - 1){
            recipes.add(Recipe.recipeFromJson(recipesJson.getJSONObject(i).toString()))
        }

        return recipes
    }

    suspend fun getRecipesFromSearchJSONData(data: String): List<Recipe>{
        val recipes = ArrayList<Recipe>()

        val recipesJson = JSONArray(data.removePrefix("{\"recipes\":").removeSuffix("}"))

        for (i in 0..recipesJson.length() - 1){
            recipes.add(Recipe.recipeFromSearchJson(recipesJson.getJSONObject(i).toString()))
        }

        return recipes
    }
}