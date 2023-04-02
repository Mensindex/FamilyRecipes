package com.example.familyrecipes.ui.screens.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.familyrecipes.data.database.room.RecipeListRepositoryImpl
import com.example.familyrecipes.data.database.room.RecipeListRoomDao
import com.example.familyrecipes.domain.GetARecipeUseCase
import com.example.familyrecipes.domain.models.Recipe
import com.example.familyrecipes.utils.Result
import com.example.familyrecipes.utils.asResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipeViewModel(dao: RecipeListRoomDao) : ViewModel() {

    private val _recipeViewModelUIState =
        MutableStateFlow<RecipeViewModelUIState>(RecipeViewModelUIState.Loading)
    val recipeViewModelUIState = _recipeViewModelUIState.asStateFlow()
    private val getARecipeUseCase = GetARecipeUseCase(RecipeListRepositoryImpl(dao))


    fun getRecipe(id: Long) {
        viewModelScope.launch {
            getARecipeUseCase(id).asResult().collect { result ->
                when (result) {
                    is Result.Error -> {
                        _recipeViewModelUIState.value =
                            RecipeViewModelUIState.Error(msg = result.exception.toString())
                    }

                    is Result.Loading -> {
                        _recipeViewModelUIState.value =
                            RecipeViewModelUIState.Loading
                    }

                    is Result.Success -> {
                        _recipeViewModelUIState.value =
                            RecipeViewModelUIState.RecipeUi(recipe = result.data)
                    }


                }
            }
        }
    }
}

sealed interface RecipeViewModelUIState {
    data class RecipeUi(val recipe: Recipe) : RecipeViewModelUIState
    object Loading : RecipeViewModelUIState
    data class Error(val msg: String) : RecipeViewModelUIState
}

