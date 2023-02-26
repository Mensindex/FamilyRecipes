package com.example.familyrecipes.ui.screens.adding_a_recipe

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.familyrecipes.data.database.room.RecipeListRepositoryImpl
import com.example.familyrecipes.data.database.room.RecipeListRoomDao
import com.example.familyrecipes.domain.AddRecipeUseCase
import com.example.familyrecipes.domain.models.Ingredient
import com.example.familyrecipes.domain.models.MethodStep
import com.example.familyrecipes.domain.models.Recipe
import com.example.familyrecipes.domain.models.RecipeImage
import com.example.familyrecipes.domain.models.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalTime

class AddingARecipeViewModel(dao: RecipeListRoomDao) : ViewModel() {
    private val _addingARecipeUIState = MutableStateFlow(AddingARecipeUIState())
    val addingARecipeUIState = _addingARecipeUIState.asStateFlow()
    private val addRecipe = AddRecipeUseCase(RecipeListRepositoryImpl(dao))

    fun addCategory(name: String) {
        _addingARecipeUIState.update { currentState ->
            currentState.copy(
                categories = currentState.categories.plus(Category(name = name))
                        as MutableList<Category>
            )
        }
    }

    fun addIngredient() {
        _addingARecipeUIState.update { currentState ->
            currentState.copy(
                ingredients = currentState.ingredients.plus(Ingredient())
            )
        }
    }

    fun addMethodStep() {
        _addingARecipeUIState.update { currentState ->
            currentState.copy(
                method = currentState.method.plus(MethodStep())
            )
        }
    }

    fun removeMethodStep(methodStep: MethodStep) {
        _addingARecipeUIState.update { currentState ->
            currentState.copy(
                method = currentState.method.minus(methodStep)
            )
        }
    }

    fun removeIngredient(ingredient: Ingredient) {
        _addingARecipeUIState.update { currentState ->
            currentState.copy(
                ingredients = currentState.ingredients.minus(ingredient)
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

    suspend fun addRecipe(recipe: Recipe) {
        addRecipe.addRecipe(recipe = recipe)
    }

    fun setServings(servings: Int) {
        _addingARecipeUIState.update { currentState ->
            currentState.copy(servings = servings)
        }
    }
}

data class AddingARecipeUIState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val servings: Int? = null,
    val timeValue: MutableState<LocalTime> = mutableStateOf(LocalTime.MIDNIGHT),
    val ingredients: List<Ingredient> = emptyList(),
    val categories: List<Category> = emptyList(),
    val method: List<MethodStep> = emptyList(),
    val recipeName: String = "",
    val recipeImage: RecipeImage? = null,
)