package ca.csf.tp2_williamguay.model

import org.json.JSONObject

class Ingredient(
    val ingredient: String
) {
    companion object{
        fun fromJson(json: String): Ingredient{

            val ingredientJson = JSONObject(json)
            val ingredientName = ingredientJson.getString("original")

            return Ingredient(ingredientName)
        }
    }
}