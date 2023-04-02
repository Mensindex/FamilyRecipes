package com.example.familyrecipes.domain

import com.example.familyrecipes.domain.models.Category
import com.example.familyrecipes.domain.models.Recipe
import com.example.familyrecipes.domain.models.RecipeWithCategories
import kotlinx.coroutines.flow.Flow

interface RecipeListRepository {

    suspend fun addRecipeWithCategories(recipe: RecipeWithCategories)

    suspend fun deleteRecipe(recipe: Recipe)

    suspend fun editRecipe(recipe: Recipe)

    fun getRecipe(id: Long): Flow<Recipe>

    fun getRecipeListByCategory(id: Long): Flow<List<Recipe>>

    fun getRecipeList(): Flow<List<Recipe>>

    suspend fun addCategory(name: String)

    fun getCategoryById(categoryId: Long): Flow<Category>

    fun getCategories(): Flow<List<Category>>

    suspend fun deleteCategory(categoryId: Long)

//    fun getRecipesCountByCategoryId(categoryId: Long): Flow<Long>

    fun getRecipeCountsByCategoryIds(categoryIds: List<Long>): Flow<List<Int>>
}