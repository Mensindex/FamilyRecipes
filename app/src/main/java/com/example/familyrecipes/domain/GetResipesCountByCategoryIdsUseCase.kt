package com.example.familyrecipes.domain

import kotlinx.coroutines.flow.Flow

class GetRecipesCountByCategoryIdsUseCase(private val recipeListRepository: RecipeListRepository) {
    operator fun invoke(categoryIds: List<Long>): Flow<List<Int>> {
        return recipeListRepository.getRecipeCountsByCategoryIds(categoryIds = categoryIds)
    }
}