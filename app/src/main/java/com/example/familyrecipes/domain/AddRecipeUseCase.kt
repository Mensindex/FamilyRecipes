package com.example.familyrecipes.domain

import com.example.familyrecipes.domain.models.Recipe

class AddRecipeUseCase(private val recipeListRepository: RecipeListRepository) {

    suspend fun addRecipe(recipe: Recipe) {
        recipeListRepository.addRecipe(recipe = recipe)
    }
}