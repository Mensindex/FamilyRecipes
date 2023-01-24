package com.example.familyrecipes.ui.screens.adding_a_recipe

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.familyrecipes.R
import com.example.familyrecipes.data.models.Category
import com.example.familyrecipes.data.models.Ingredient
import com.example.familyrecipes.data.models.MethodStep
import com.example.familyrecipes.ui.screens.adding_a_recipe.components.SelectACategoryBottomSheet
import com.example.familyrecipes.ui.screens.common.*
import com.example.familyrecipes.ui.theme.Typography
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun AddingARecipePreview() {
    AddingARecipe(
        onBackClick = {},
        onCompleteClick = {},
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun AddingARecipe(
    onBackClick: () -> Unit,
    onCompleteClick: () -> Unit,
) {
    val ingredientsList = remember { mutableStateListOf<Ingredient>() }
    val methodList = remember { mutableStateListOf<MethodStep>() }
    val focusManager = LocalFocusManager.current
    //TopBar animation
    val scrollBehavior = TopAppBarDefaults
        .exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val collapsed = dimensionResource(id = R.dimen.collapsedTopAppBarTitleSize).value.toInt()
    val expanded = dimensionResource(id = R.dimen.expandedTopAppBarTitleSize).value.toInt()
    val topAppBarTextSize =
        (collapsed + (expanded - collapsed) * (1 - scrollBehavior.state.collapsedFraction)).sp
    //BottomSheet setting
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val coroutineScope = rememberCoroutineScope()
    BackHandler(
        enabled = sheetState.isVisible,
        onBack = { coroutineScope.launch { sheetState.hide() } }
    )
    val categoryList = remember {
        mutableStateListOf<Category>(
            Category(name = "Breakfast"),
            Category(name = "Lunch"),
            Category(name = "Dinner"),
        )
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            SelectACategoryBottomSheet(
                onSelectClick = { coroutineScope.launch { sheetState.hide() } },
                categoryList = categoryList
            )
        },
        modifier = Modifier.fillMaxSize(),
        sheetShape = RoundedCornerShape(
            topStart = dimensionResource(id = R.dimen.dp14),
            topEnd = dimensionResource(id = R.dimen.dp14)
        ),
        content = {
            Scaffold(
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = ({ focusManager.clearFocus() }),
                    )
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                containerColor = Color.White,
                topBar = {
                    LargeTopAppBar(
                        modifier = Modifier,
                        title = {
                            Text(
                                text = stringResource(id = R.string.adding_a_recipe),
                                style = Typography.headlineLarge,
                                fontSize = topAppBarTextSize,
                            )
                        },
                        actions = {
                            Image(
                                modifier = Modifier.clickable { onCompleteClick },
                                painter = painterResource(id = R.drawable.done),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                            )
                        },
                        navigationIcon = {
                            Image(
                                modifier = Modifier.clickable { onBackClick },
                                painter = painterResource(
                                    id = R.drawable.chevron_left
                                ),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                            )
                        },
                        scrollBehavior = scrollBehavior,
                        colors = TopAppBarDefaults.largeTopAppBarColors(
                            containerColor = Color.White,
                            scrolledContainerColor = Color.White,

                            )
                    )
                },
            ) { innerPadding ->
                LazyColumn(
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.dp16),
                        end = dimensionResource(id = R.dimen.dp16),
                        top = innerPadding.calculateTopPadding(),
                        bottom = innerPadding.calculateBottomPadding(),
                    )
                ) {
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Placeholder {}
                            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp24)))
                            Column(
                                modifier = Modifier,
                                verticalArrangement = Arrangement
                                    .spacedBy(dimensionResource(id = R.dimen.dp8)),
                            ) {
                                Text(
                                    text = stringResource(id = R.string.name),
                                    style = Typography.labelLarge
                                )
                                CustomInputField(
                                    text = "",
                                    onValueChange = {},
                                    hintText = stringResource(id = R.string.type_a_name),
                                )
                            }
                            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp24)))
                            Row(
                                modifier = Modifier,
                                horizontalArrangement = Arrangement
                                    .spacedBy(dimensionResource(id = R.dimen.dp8)),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Column(
                                    modifier = Modifier.weight(0.5f),
                                    verticalArrangement = Arrangement
                                        .spacedBy(dimensionResource(id = R.dimen.dp8)),
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.serving),
                                        style = Typography.labelLarge
                                    )
                                    CustomInputField(
                                        onValueChange = {},
                                    )
                                }
                                Column(
                                    modifier = Modifier.weight(0.5f),
                                    verticalArrangement = Arrangement
                                        .spacedBy(dimensionResource(id = R.dimen.dp8)),
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.preparation_time),
                                        style = Typography.labelLarge
                                    )
                                    CustomInputField(
                                        onValueChange = {},
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp24)))
                            Column(
                                modifier = Modifier,
                                verticalArrangement = Arrangement
                                    .spacedBy(dimensionResource(id = R.dimen.dp8)),
                            ) {
                                Text(
                                    text = stringResource(id = R.string.category),
                                    style = Typography.labelLarge
                                )
                                CustomSelectTextField(
                                    text = categoryList
                                        .filter { it.isChecked.value }
                                        .joinToString(
                                            separator = stringResource(id = R.string.category_separator)
                                        ) { it.name },
                                    modifier = Modifier,
                                    onClick = {
                                        coroutineScope.launch {
                                            sheetState.show()
                                        }
                                    },
                                )
                            }
                            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp24)))
                            Text(
                                text = stringResource(id = R.string.ingredients),
                                style = Typography.titleMedium
                            )
                            if (ingredientsList.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp16)))
                            } else {
                                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp8)))
                            }
                        }
                    }


                    itemsIndexed(ingredientsList) { index, item ->
                        Row(
                            modifier = Modifier
                                .padding(bottom = dimensionResource(id = R.dimen.dp8))
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp4)),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            CustomInputField(
                                modifier = Modifier.weight(1f),
                                text = item.name.value,
                                onValueChange = {
                                    item.name.value = it
                                }
                            )
                            CancelButton {
                                ingredientsList.removeAt(index)
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp8)))
                        AddAnIngredientOrAStepButton(
                            isEnabled = ingredientsList.lastOrNull()?.name?.value?.isNotEmpty() == true ||
                                    ingredientsList.size == 0
                        ) {
                            ingredientsList.add(Ingredient())
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp32)))
                        Text(
                            text = stringResource(id = R.string.method),
                            style = Typography.titleMedium
                        )
                        if (methodList.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp16)))
                        } else {
                            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp8)))
                        }
                    }

                    itemsIndexed(methodList) { index, item ->
                        Row(
                            modifier = Modifier
                                .padding(bottom = dimensionResource(id = R.dimen.dp8))
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp4)),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            CustomInputField(
                                modifier = Modifier.weight(1f),
                                text = item.step.value,
                                onValueChange = {
                                    item.step.value = it
                                }
                            )
                            CancelButton {
                                methodList.removeAt(index)
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp8)))
                        AddAnIngredientOrAStepButton(
                            isEnabled = methodList.lastOrNull()?.step?.value?.isNotEmpty() == true ||
                                    methodList.size == 0,
                            isIngredient = false,
                        ) {
                            methodList.add(MethodStep())
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp32)))
                    }
                }
            }
        }
    )

}