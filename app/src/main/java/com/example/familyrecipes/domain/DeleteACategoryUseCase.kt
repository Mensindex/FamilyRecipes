package com.example.familyrecipes.domain

class DeleteACategoryUseCase(private val recipeListRepository: RecipeListRepository) {

    suspend operator fun invoke(categoryId: Long) {
        recipeListRepository.deleteCategory(categoryId = categoryId)
    }
}