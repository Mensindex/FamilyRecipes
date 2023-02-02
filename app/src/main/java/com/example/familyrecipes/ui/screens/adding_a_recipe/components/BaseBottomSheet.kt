package com.example.familyrecipes.ui.screens.adding_a_recipe.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.familyrecipes.data.models.Category
import java.time.LocalTime

@Composable
fun BaseBottomSheet(
    bottomSheetType: BottomSheetType,
    onSelectCategoryClick: (() -> Unit)?,
    categoryList: MutableList<Category> = mutableListOf(),
    timeValue: MutableState<LocalTime> = remember {mutableStateOf(LocalTime.MIDNIGHT)},
    onSelectTimeClick: (() -> Unit)?,
) {

    when (bottomSheetType) {
        BottomSheetType.TYPE1 ->
            onSelectTimeClick?.let {
                PreparationTimeBottomSheet(
                    timeValue = timeValue,
                    onSelectClick = it
                )
            }

        BottomSheetType.TYPE2 ->
            SelectACategoryBottomSheet(
                onSelectClick = onSelectCategoryClick,
                categoryList = categoryList
            )
    }
}