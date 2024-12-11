package ca.csf.tp2_williamguay.model

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class Recipe(
    val id: Int,
    val name: String,
    val img: String?,
    val ingredients: List<Ingredient>,
    val isVegetarian: Boolean,
    val isGlutenFree: Boolean,
    val isDiaryFree: Boolean,
    val instructions: List<Instruction>,
    val readyInMinutes: Int,
    val servings: Int
) {
    companion object {
        fun recipeFromJson(json: String): Recipe {

            val recipe = JSONObject(json)

            val recipeId = recipe.getInt("id")

            val recipeName = recipe.getString("title")

            var recipeImg:String?

            try {
                recipeImg = recipe.getString("image")
            } catch(_: JSONException){
                recipeImg = null
            }

            var recipeIsVegetarian: Boolean
            try {
                recipeIsVegetarian = recipe.getBoolean("vegetarian")
            } catch (_:JSONException){
                recipeIsVegetarian = false
            }

            var recipeIsGlutenFree: Boolean
            try{
                recipeIsGlutenFree = recipe.getBoolean("glutenFree")
            } catch(_:JSONException){
                recipeIsGlutenFree = false
            }

            var recipeIsDairyFree: Boolean
            try{
                recipeIsDairyFree = recipe.getBoolean("dairyFree")
            } catch(_:JSONException){
                recipeIsDairyFree = false
            }

            var recipeReadyInMinutes: Int
            try{
                recipeReadyInMinutes = recipe.getInt("readyInMinutes")
            } catch(_:JSONException){
                recipeReadyInMinutes = 0
            }

            var recipeServings: Int
            try{
                recipeServings = recipe.getInt("servings")
            } catch(_:JSONException){
                recipeServings = 0
            }


            val recipeIngredients = ArrayList<Ingredient>()
            val recipeIngredientsJSON = recipe.getJSONArray("extendedIngredients")

            for (j in 0..recipe.getJSONArray("extendedIngredients").length() - 1) {
                recipeIngredients.add(
                    Ingredient.fromJson(
                        recipeIngredientsJSON.getJSONObject(j).toString()
                    )
                )
            }

            val recipeInstructions = ArrayList<Instruction>()
            val recipeInstructionJSON = recipe.getJSONArray("analyzedInstructions").getJSONObject(0)
            val recipeStepsJSON = recipeInstructionJSON.getJSONArray("steps")

            for (j in 0..recipeStepsJSON.length() - 1) {
                recipeInstructions.add(
                    Instruction.fromJson(
                        recipeStepsJSON.getJSONObject(j).toString()
                    )
                )
            }

            return Recipe(
                recipeId, recipeName, recipeImg, recipeIngredients, recipeIsVegetarian, recipeIsGlutenFree,
                recipeIsDairyFree, recipeInstructions, recipeReadyInMinutes, recipeServings
            )
        }

        fun recipeFromSearchJson(json: String): Recipe{
            val recipe = JSONObject(json)

            val recipeId = recipe.getInt("id")

            val recipeName = recipe.getString("title")

            var recipeImg:String?

            try {
                recipeImg = recipe.getString("image")
            } catch(_: JSONException){
                recipeImg = null
            }

            var recipeIsVegetarian: Boolean
            try {
                recipeIsVegetarian = recipe.getBoolean("vegetarian")
            } catch (_:JSONException){
                recipeIsVegetarian = false
            }

            var recipeIsGlutenFree: Boolean
            try{
                recipeIsGlutenFree = recipe.getBoolean("glutenFree")
            } catch(_:JSONException){
                recipeIsGlutenFree = false
            }

            var recipeIsDairyFree: Boolean
            try{
                recipeIsDairyFree = recipe.getBoolean("dairyFree")
            } catch(_:JSONException){
                recipeIsDairyFree = false
            }

            var recipeReadyInMinutes: Int
            try{
                recipeReadyInMinutes = recipe.getInt("readyInMinutes")
            } catch(_:JSONException){
                recipeReadyInMinutes = 0
            }

            var recipeServings: Int
            try{
                recipeServings = recipe.getInt("servings")
            } catch(_:JSONException){
                recipeServings = 0
            }


            val recipeIngredients = ArrayList<Ingredient>()
            val recipeIngredientsJSON = recipe.getJSONArray("extendedIngredients")

            for (j in 0..recipe.getJSONArray("extendedIngredients").length() - 1) {
                recipeIngredients.add(
                    Ingredient.fromJson(
                        recipeIngredientsJSON.getJSONObject(j).toString()
                    )
                )
            }

            val recipeInstructions = ArrayList<Instruction>()
            val recipeInstructionJSON = recipe.getJSONArray("analyzedInstructions").getJSONObject(0)
            val recipeStepsJSON = recipeInstructionJSON.getJSONArray("steps")

            for (j in 0..recipeStepsJSON.length() - 1) {
                recipeInstructions.add(
                    Instruction.fromJson(
                        recipeStepsJSON.getJSONObject(j).toString()
                    )
                )
            }

            return Recipe(
                recipeId, recipeName, recipeImg, recipeIngredients, recipeIsVegetarian, recipeIsGlutenFree,
                recipeIsDairyFree, recipeInstructions, recipeReadyInMinutes, recipeServings
            )
        }
    }
}