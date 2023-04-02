package com.example.familyrecipes.domain

import com.example.familyrecipes.domain.models.Recipe
import kotlinx.coroutines.flow.Flow

class GetRecipeListUseCase(private val recipeListRepository: RecipeListRepository) {

    operator fun invoke(): Flow<List<Recipe>> {
        return recipeListRepository.getRecipeList()
    }
}