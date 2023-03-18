package com.example.familyrecipes.domain

import com.example.familyrecipes.domain.models.Category
import com.example.familyrecipes.domain.models.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeListRepository {

    suspend fun addRecipe(recipe: Recipe)

    suspend fun addCategory(name: String)

    suspend fun deleteRecipe(recipe: Recipe)

    suspend fun editRecipe(recipe: Recipe)

    fun getRecipe(id: Int): Flow<Recipe>

    fun getRecipeList(): Flow<List<Recipe>>

    fun getCategories(): Flow<List<Category>>
}