package com.example.familyrecipes.domain

import com.example.familyrecipes.domain.models.Recipe

class DeleteRecipeUseCase(private val recipeListRepository: RecipeListRepository) {

    suspend fun deleteRecipe(recipe: Recipe) {
        recipeListRepository.deleteRecipe(recipe = recipe)
    }
}