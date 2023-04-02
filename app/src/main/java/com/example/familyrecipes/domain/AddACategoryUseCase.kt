package com.example.familyrecipes.domain

class AddACategoryUseCase(private val recipeListRepository: RecipeListRepository) {

    suspend operator fun invoke(name: String){
        recipeListRepository.addCategory(name = name)
    }
}