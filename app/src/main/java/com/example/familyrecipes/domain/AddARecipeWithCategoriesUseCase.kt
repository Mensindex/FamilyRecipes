package com.example.familyrecipes.domain

import com.example.familyrecipes.domain.models.RecipeWithCategories

class AddARecipeWithCategoriesUseCase(private val recipeListRepository: RecipeListRepository) {

    suspend operator fun invoke(recipeWithCategories: RecipeWithCategories) {
        recipeListRepository.addRecipeWithCategories(recipe = recipeWithCategories)
    }
}