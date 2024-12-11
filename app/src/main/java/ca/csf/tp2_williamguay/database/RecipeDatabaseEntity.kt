package ca.csf.tp2_williamguay.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import ca.csf.tp2_williamguay.model.Ingredient
import ca.csf.tp2_williamguay.model.Instruction

@Entity(tableName = "favourite_recipe")
data class RecipeDatabaseEntity(
    val idRecipe: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}