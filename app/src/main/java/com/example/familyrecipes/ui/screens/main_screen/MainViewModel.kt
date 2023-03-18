package com.example.familyrecipes.ui.screens.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.familyrecipes.data.database.room.RecipeListRepositoryImpl
import com.example.familyrecipes.data.database.room.RecipeListRoomDao
import com.example.familyrecipes.domain.GetRecipeListUseCase
import com.example.familyrecipes.domain.models.Recipe
import com.example.familyrecipes.utils.Result
import com.example.familyrecipes.utils.asResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(dao: RecipeListRoomDao) : ViewModel() {
    private val _mainViewModelUIState = MutableStateFlow(MainViewModelUIState())
    val mainViewModelUIState = _mainViewModelUIState.asStateFlow()
    private val getRecipeListUseCase = GetRecipeListUseCase(RecipeListRepositoryImpl(dao))

    init {
        getAllRecipes()
    }

    private fun getAllRecipes() {
        viewModelScope.launch {
            getRecipeListUseCase.getRecipeList().asResult().collect { result ->
                when (result) {
                    is Result.Error -> {}
                    is Result.Loading -> {}
                    is Result.Success -> {
                        _mainViewModelUIState.update { currentState ->
                            currentState.copy(listRecipe = result.data)
                        }
                    }
                }
            }
        }
    }
}

data class MainViewModelUIState(
    val listRecipe: List<Recipe> = emptyList(),
)