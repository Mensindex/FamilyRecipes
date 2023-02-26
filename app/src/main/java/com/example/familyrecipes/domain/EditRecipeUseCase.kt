package com.example.familyrecipes.domain

import com.example.familyrecipes.domain.models.Recipe

class EditRecipeUseCase(private val recipeListRepository: RecipeListRepository) {

    suspend fun editRecipe(recipe: Recipe) {
        recipeListRepository.editRecipe(recipe = recipe)
    }
}