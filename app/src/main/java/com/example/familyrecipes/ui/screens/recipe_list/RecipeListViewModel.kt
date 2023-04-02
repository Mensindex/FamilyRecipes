package com.example.familyrecipes.ui.screens.recipe_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.familyrecipes.data.database.room.RecipeListRepositoryImpl
import com.example.familyrecipes.data.database.room.RecipeListRoomDao
import com.example.familyrecipes.domain.GetACategoryUseCase
import com.example.familyrecipes.domain.GetRecipesByCategoryUseCase
import com.example.familyrecipes.domain.models.Recipe
import com.example.familyrecipes.utils.asResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.familyrecipes.utils.Result
import kotlinx.coroutines.flow.update

class RecipeListViewModel(dao: RecipeListRoomDao) : ViewModel() {

    private val _recipeListUiState: MutableStateFlow<RecipeListUiState> =
        MutableStateFlow(value = RecipeListUiState())
    val recipeListUiState = _recipeListUiState.asStateFlow()
    private val getRecipesByCategoryUseCase =
        GetRecipesByCategoryUseCase(RecipeListRepositoryImpl(dao))
    private val getACategoryUseCase = GetACategoryUseCase(RecipeListRepositoryImpl(dao))


    fun getCategory(categoryId: Long) {
        viewModelScope.launch {
            getACategoryUseCase(categoryId = categoryId).asResult().collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _recipeListUiState.update { currentState ->
                            currentState.copy(
                                loading = true,
                                error = null
                            )
                        }
                    }
                    is Result.Error -> {
                        _recipeListUiState.update { currentState ->
                            currentState.copy(
                                error = result.exception?.message,
                                loading = false
                            )
                        }
                    }
                    is Result.Success -> {
                        _recipeListUiState.update { currentState ->
                            currentState.copy(
                                loading = false,
                                error = null,
                                categoryName = result.data.name
                            )
                        }
                    }
                }
            }
        }
    }

    fun getRecipesByCategory(categoryId: Long) {
        viewModelScope.launch {
            getRecipesByCategoryUseCase(categoryId = categoryId).asResult().collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _recipeListUiState.update { currentState ->
                            currentState.copy(
                                loading = true,
                                error = null
                            )
                        }
                    }
                    is Result.Error -> {
                        _recipeListUiState.update { currentState ->
                            currentState.copy(
                                error = result.exception?.message,
                                loading = false
                            )
                        }
                    }
                    is Result.Success -> {
                        _recipeListUiState.update { currentState ->
                            currentState.copy(
                                loading = false,
                                error = null,
                                recipeList = result.data
                            )
                        }
                    }
                }
            }
        }
    }

}

data class RecipeListUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val recipeList: List<Recipe> = emptyList(),
    val categoryName: String = "",
)