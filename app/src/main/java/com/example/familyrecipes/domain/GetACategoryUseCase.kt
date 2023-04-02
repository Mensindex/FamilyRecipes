package com.example.familyrecipes.domain

import com.example.familyrecipes.domain.models.Category
import kotlinx.coroutines.flow.Flow

class GetACategoryUseCase(private val recipeListRepository: RecipeListRepository) {

    operator fun invoke(categoryId: Long): Flow<Category> {
        return recipeListRepository.getCategoryById(categoryId = categoryId)
    }
}