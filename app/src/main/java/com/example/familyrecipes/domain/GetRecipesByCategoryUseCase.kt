package com.example.familyrecipes.domain

import com.example.familyrecipes.domain.models.Recipe
import kotlinx.coroutines.flow.Flow

class GetRecipesByCategoryUseCase(private val recipeListRepository: RecipeListRepository) {

    operator fun invoke(categoryId: Long): Flow<List<Recipe>> {
        return recipeListRepository.getRecipeListByCategory(id = categoryId)
    }
}