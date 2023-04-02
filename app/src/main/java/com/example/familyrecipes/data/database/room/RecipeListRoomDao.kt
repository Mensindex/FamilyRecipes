package com.example.familyrecipes.data.database.room

import androidx.room.*
import com.example.familyrecipes.data.entities.*
import com.example.familyrecipes.utils.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeListRoomDao {

    @Query(value = "SELECT * FROM $RECIPE_TABLE_NAME")
    fun getRecipeList(): Flow<List<RecipeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipe(recipe: RecipeEntity): Long

    @Query(value = "SELECT * FROM $RECIPE_TABLE_NAME WHERE id=:$RECIPE_ID LIMIT 1")
    fun getRecipe(recipeId: Long): Flow<RecipeEntity>

    @Query(value = "SELECT * FROM $CATEGORY_TABLE_NAME WHERE id=:$CATEGORY_ID LIMIT 1")
    fun getCategory(categoryId: Long): Flow<CategoryEntity>

    @Update
    suspend fun updateRecipe(recipe: RecipeEntity)

    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)

    @Transaction
    @Query("SELECT * FROM $RECIPE_TABLE_NAME")
    fun getRecipesWithCategories(): Flow<List<RecipeWithCategoriesEntity>>

    @Query(
        """
        SELECT recipes_table.*
        FROM $RECIPE_TABLE_NAME
        INNER JOIN recipe_category ON recipes_table.id = recipe_category.recipeId
        WHERE recipe_category.categoryId = :categoryId
    """
    )
    fun getRecipesByCategoryId(categoryId: Long): Flow<List<RecipeEntity>>

    @Transaction
    @Query("SELECT * FROM $RECIPE_TABLE_NAME WHERE id = :recipeId")
    fun getRecipeWithCategoriesById(recipeId: Long): Flow<RecipeWithCategoriesEntity?>

    @Query(value = "SELECT * FROM $CATEGORY_TABLE_NAME")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeCategoriesCrossRef(recipeCategories: RecipeCategoryCrossRef)

    @Transaction
    suspend fun insertRecipeWithCategories(recipeWithCategoriesEntity: RecipeWithCategoriesEntity) {
        val recipeId = addRecipe(recipeWithCategoriesEntity.recipe)
        for (category in recipeWithCategoriesEntity.categories) {
            val recipeCategoryCrossRef = RecipeCategoryCrossRef(recipeId = recipeId, category.id)
            insertRecipeCategoriesCrossRef(recipeCategoryCrossRef)
        }
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCategory(category: CategoryEntity): Long

    @Update
    suspend fun updateCategory(category: CategoryEntity)

    @Query(value = "DELETE FROM $CATEGORY_TABLE_NAME WHERE id=:categoryId")
    suspend fun deleteCategory(categoryId: Long)

//    @Query(
//        value = "SELECT COUNT(*) FROM $RECIPE_TABLE_NAME WHERE id IN " +
//                "(SELECT $RECIPE_ID FROM $RECIPE_CATEGORY_TABLE_NAME WHERE $CATEGORY_ID = :categoryId)"
//    )
//    fun getRecipesCountByCategoryId(categoryId: Long): Flow<Long>

//    @Query(
//        "SELECT $CATEGORY_ID, COUNT(*) AS recipeCountInCategory FROM $RECIPE_CATEGORY_TABLE_NAME" +
//                " WHERE $CATEGORY_ID IN (:categoryIds) GROUP BY $CATEGORY_ID"
//    )
//    suspend fun getRecipeCountsByCategoryIds(categoryIds: List<Long>): List<RecipeCountInCategory>

    @Query("SELECT COUNT(*) FROM $RECIPE_CATEGORY_TABLE_NAME WHERE $CATEGORY_ID IN (:categoryIds) GROUP BY $CATEGORY_ID")
    fun getRecipeCountsByCategoryIds(categoryIds: List<Long>): Flow<List<Int>>
}