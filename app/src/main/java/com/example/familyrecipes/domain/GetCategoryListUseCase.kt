package com.example.familyrecipes.domain

import com.example.familyrecipes.domain.models.Category
import kotlinx.coroutines.flow.Flow

class GetCategoryListUseCase(private val recipeListRepository: RecipeListRepository) {

    operator fun invoke(): Flow<List<Category>> {
        return recipeListRepository.getCategories()
    }
}