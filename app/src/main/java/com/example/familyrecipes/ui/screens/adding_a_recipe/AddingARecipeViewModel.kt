package com.example.familyrecipes.ui.screens.adding_a_recipe

import androidx.lifecycle.ViewModel
import com.example.familyrecipes.data.models.Category
import com.example.familyrecipes.data.models.Ingredient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddingARecipeViewModel : ViewModel() {
    private val _addingARecipeUIState =
        MutableStateFlow(AddingARecipeUIState())
    val addingARecipeUIState = _addingARecipeUIState.asStateFlow()

    fun addIngredient() {
        _addingARecipeUIState.update { currentState ->
            currentState.copy(ingredients = currentState.ingredients.plus(Ingredient()))
        }
    }

    fun addCategory(name: String) {
        _addingARecipeUIState.update { currentState ->
            currentState.copy(
                categories = currentState.categories.plus(Category(name))
            )
        }
    }

    fun test(r: String): AddingARecipeUIState {
        return AddingARecipeUIState(error = r)
    }
}

data class AddingARecipeUIState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val ingredients: List<Ingredient> = emptyList(),
    val categories: List<Category> = emptyList(),
)