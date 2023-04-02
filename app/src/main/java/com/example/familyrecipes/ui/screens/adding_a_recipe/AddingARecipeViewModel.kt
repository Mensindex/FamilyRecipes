package com.example.familyrecipes.ui.screens.adding_a_recipe

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.familyrecipes.data.database.room.RecipeListRepositoryImpl
import com.example.familyrecipes.data.database.room.RecipeListRoomDao
import com.example.familyrecipes.domain.AddACategoryUseCase
import com.example.familyrecipes.domain.AddARecipeWithCategoriesUseCase
import com.example.familyrecipes.domain.GetCategoryListUseCase
import com.example.familyrecipes.domain.models.*
import com.example.familyrecipes.utils.MIN_SERVING
import com.example.familyrecipes.utils.MY_LOG
import com.example.familyrecipes.utils.Result
import com.example.familyrecipes.utils.asResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalTime

class AddingARecipeViewModel(dao: RecipeListRoomDao) : ViewModel() {

    private val _addingARecipeUIState = MutableStateFlow(AddingARecipeUIState())
    val addingARecipeUIState = _addingARecipeUIState.asStateFlow()
    private val addACategoryUseCase = AddACategoryUseCase(RecipeListRepositoryImpl(dao))
    private val getCategoryListUseCase = GetCategoryListUseCase(RecipeListRepositoryImpl(dao))
    private val addARecipeWithCategoriesUseCase =
        AddARecipeWithCategoriesUseCase(RecipeListRepositoryImpl(dao))

    init {
        getCategories()
    }

    suspend fun addCategory(name: String) {
        addACategoryUseCase(name = name)
//        _addingARecipeUIState.update { currentState ->
//            currentState.copy(
//                categories = currentState.categories.plus(Category(name = name))
//                        as MutableList<Category>
//            )
//        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoryListUseCase().asResult().collect { result ->
                when (result) {
                    is Result.Error -> {}
                    is Result.Loading -> {}
                    is Result.Success -> {
                        _addingARecipeUIState.update { currentState ->
                            currentState.copy(categories = mutableStateOf(result.data))
                        }
                    }
                }
            }
        }
    }

    fun addIngredient(name: String) {
        _addingARecipeUIState.update { currentState ->
            currentState.copy(
                ingredients = currentState.ingredients.plus(Ingredient(name = name))
            )
        }
    }

    fun addMethodStep(step: String) {
        _addingARecipeUIState.update { currentState ->
            currentState.copy(
                method = currentState.method.plus(MethodStep(step = step))
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

    suspend fun addRecipeWithCategories(recipe: Recipe, categories: List<Category>) {
        Log.d(MY_LOG, "categories in viewModel: $categories")
        addARecipeWithCategoriesUseCase(
            RecipeWithCategories(
                recipe = recipe,
                categories = categories,
            )
        )
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
    val servings: Int = MIN_SERVING,
    val timeValue: MutableState<LocalTime> = mutableStateOf(LocalTime.MIDNIGHT),
    val ingredients: List<Ingredient> = emptyList(),
    val categories: MutableState<List<Category>> = mutableStateOf(emptyList()),
    val method: List<MethodStep> = emptyList(),
    val recipeName: String = "",
    val recipeImage: RecipeImage? = null,
    val ingredientFieldText: MutableState<String> = mutableStateOf(""),
    val methodFieldText: MutableState<String> = mutableStateOf(""),
)