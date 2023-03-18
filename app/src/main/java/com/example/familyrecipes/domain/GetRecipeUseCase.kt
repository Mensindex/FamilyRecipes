package com.example.familyrecipes.domain

import com.example.familyrecipes.domain.models.Recipe
import kotlinx.coroutines.flow.Flow

class GetRecipeUseCase(private val recipeListRepository: RecipeListRepository) {

    fun getRecipe(id: Int):Flow<Recipe> {
        return recipeListRepository.getRecipe(id = id)
    }
}