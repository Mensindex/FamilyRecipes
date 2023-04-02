package com.example.familyrecipes.domain

import com.example.familyrecipes.domain.models.Recipe

class EditARecipeUseCase(private val recipeListRepository: RecipeListRepository) {

    suspend operator fun invoke(recipe: Recipe) {
        recipeListRepository.editRecipe(recipe = recipe)
    }
}