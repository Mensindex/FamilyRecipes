package com.example.familyrecipes.domain

class GetRecipeUseCase(private val recipeListRepository: RecipeListRepository) {

    suspend fun getRecipe(id: Int) {
        recipeListRepository.getRecipe(id = id)
    }
}