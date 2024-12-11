package ca.csf.tp2_williamguay.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RecipeDatabaseEntity::class], version = 2)
abstract class RecipeDatabase : RoomDatabase(){
    abstract fun RecipeDao(): RecipeDao
}