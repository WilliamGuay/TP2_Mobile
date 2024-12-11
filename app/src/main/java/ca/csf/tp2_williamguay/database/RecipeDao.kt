package ca.csf.tp2_williamguay.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM favourite_recipe")
    fun getAll(): Flow<List<RecipeDatabaseEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(recipe: RecipeDatabaseEntity)

    @Update
    suspend fun update(recipe: RecipeDatabaseEntity)

    @Query("DELETE FROM favourite_recipe WHERE idRecipe = :id")
    suspend fun delete(id: Int)
}