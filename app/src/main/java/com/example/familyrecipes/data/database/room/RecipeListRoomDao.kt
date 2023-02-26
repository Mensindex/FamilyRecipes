package com.example.familyrecipes.data.database.room

import androidx.room.*
import com.example.familyrecipes.data.entities.CategoryEntity
import com.example.familyrecipes.domain.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeListRoomDao {

    @Query(value = "SELECT * FROM recipes_table")
    fun getRecipeList(): Flow<List<RecipeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipe(recipe: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategory(category: CategoryEntity)

    @Query(value = "SELECT * FROM recipes_table WHERE id=:recipeId LIMIT 1")
    suspend fun getRecipe(recipeId: Int): RecipeEntity

    @Query(value = "SELECT * FROM categories_table")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Update
    suspend fun updateRecipe(recipe: RecipeEntity)

    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)
}