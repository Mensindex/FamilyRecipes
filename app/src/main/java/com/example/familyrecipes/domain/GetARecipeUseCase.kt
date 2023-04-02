package com.example.familyrecipes.domain

import com.example.familyrecipes.domain.models.Recipe
import kotlinx.coroutines.flow.Flow

class GetARecipeUseCase(private val recipeListRepository: RecipeListRepository) {

    operator fun invoke(id: Long):Flow<Recipe> {
        return recipeListRepository.getRecipe(id = id)
    }
}