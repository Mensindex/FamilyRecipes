package com.example.familyrecipes.ui.screens.adding_a_recipe

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.familyrecipes.data.models.Category
import com.example.familyrecipes.data.models.Ingredient
import com.example.familyrecipes.data.models.MethodStep
import com.example.familyrecipes.data.models.RecipeImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddingARecipeViewModel : ViewModel() {
    private val _addingARecipeUIState = MutableStateFlow(AddingARecipeUIState())
    val addingARecipeUIState = _addingARecipeUIState.asStateFlow()

    fun addCategory(name: String) {
        _addingARecipeUIState.update { currentState ->
            currentState.copy(
                categories = currentState.categories.plus(Category(name))
                        as MutableList<Category>
            )
        }
    }

    fun addIngredient() {
        _addingARecipeUIState.update { currentState ->
            currentState.copy(
                ingredients = currentState.ingredients.plus(Ingredient())
                        as MutableList<Ingredient>
            )
        }
    }

    fun addMethod() {
        _addingARecipeUIState.update { currentState ->
            currentState.copy(
                method = currentState.method.plus(MethodStep()) as MutableList<MethodStep>
            )
        }
    }

    fun setRecipeName(name: String) {
        _addingARecipeUIState.update { currentState ->
            currentState.copy(recipeName = name)
        }
    }

    fun addRecipeImage(image: RecipeImage) {
        _addingARecipeUIState.update { currentState ->
            currentState.copy(recipeImage = image)
        }
    }
}

data class AddingARecipeUIState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val ingredients: MutableList<Ingredient> = mutableStateListOf(),
    val categories: MutableList<Category> = mutableStateListOf(),
    val method: MutableList<MethodStep> = mutableStateListOf(),
    val recipeName: String = "",
    val recipeImage: RecipeImage? = null,
)