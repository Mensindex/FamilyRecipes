package com.example.familyrecipes.ui.screens.adding_a_recipe

import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.familyrecipes.App
import com.example.familyrecipes.R
import com.example.familyrecipes.data.entities.toCategoryEntity
import com.example.familyrecipes.domain.models.Recipe
import com.example.familyrecipes.ui.navigation.NavRoute
import com.example.familyrecipes.ui.screens.adding_a_recipe.components.BaseBottomSheet
import com.example.familyrecipes.ui.screens.adding_a_recipe.components.BottomSheetType
import com.example.familyrecipes.ui.screens.adding_a_recipe.components.RecipeImage
import com.example.familyrecipes.ui.screens.common.*
import com.example.familyrecipes.ui.theme.Typography
import com.example.familyrecipes.utils.MY_LOG
import kotlinx.coroutines.launch
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun AddingARecipe(
    navController: NavHostController,
    onBackClick: () -> Unit,
    viewModel: AddingARecipeViewModel = viewModel(factory = AddingARecipeViewModelFactory(App.dao)),
) {
    val context = LocalContext.current
    val uiState by viewModel.addingARecipeUIState.collectAsState()
    val focusManager = LocalFocusManager.current

    //TopBar animation
    val scrollBehavior = TopAppBarDefaults
        .exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val sizeOfCollapsedTopBar =
        dimensionResource(id = R.dimen.collapsedTopAppBarTitleSize).value.toInt()
    val sizeOfExpandedTopBar =
        dimensionResource(id = R.dimen.expandedTopAppBarTitleSize).value.toInt()
    val topAppBarTextSize =
        (sizeOfCollapsedTopBar + (sizeOfExpandedTopBar - sizeOfCollapsedTopBar) * (1 - scrollBehavior.state.collapsedFraction)).sp
    //BottomSheet setting
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    var currentBottomSheet: BottomSheetType? by remember {
        mutableStateOf(null)
    }
    val coroutineScope = rememberCoroutineScope()
    //BackHandler
    BackHandler(
        enabled = modalBottomSheetState.isVisible,
        onBack = { coroutineScope.launch { modalBottomSheetState.hide() } }
    )

    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetState = modalBottomSheetState,
        sheetContent = {
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp1)))
            currentBottomSheet?.let { bottomSheetType ->
                BaseBottomSheet(
                    bottomSheetType = bottomSheetType,
                    categoryList = uiState.categories,
                    timeValue = uiState.timeValue,
                    onSelectCategoryClick = { coroutineScope.launch { modalBottomSheetState.hide() } },
                    onSelectTimeClick = { coroutineScope.launch { modalBottomSheetState.hide() } },
                    onAddCategory = { viewModel.addCategory(it) }
                )
            }
        },
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
                                modifier = Modifier
                                    .clickable(
                                        onClick = {
                                            if (with(uiState) {
                                                    recipeName.isNotEmpty() &&
                                                            categories.isNotEmpty() &&
                                                            method.isNotEmpty() &&
                                                            ingredients.isNotEmpty()
                                                }) {
                                                viewModel.viewModelScope.launch {
                                                    viewModel.addRecipe(
                                                        Recipe(
                                                            name = uiState.recipeName,
                                                            image = uiState.recipeImage?.bitmap,
                                                            preparingTime = uiState.timeValue.value,
                                                            categories = uiState.categories.map { category -> category.toCategoryEntity() },
                                                            ingredients = uiState.ingredients,
                                                            method = uiState.method,
                                                            servings = uiState.servings
                                                        )
                                                    )
                                                }
                                                navController.navigate(route = NavRoute.MainRoute.route)
                                            } else Toast.makeText(
                                                context,
                                                "All fields must be filled",
                                                Toast.LENGTH_SHORT
                                            ).show()

                                        }
                                    ),
                                painter = painterResource(id = R.drawable.done),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                            )
                        },
                        navigationIcon = {
                            Image(
                                modifier = Modifier.clickable { onBackClick() },
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
                            RecipeImage(
                                addImageUriCallBack = { viewModel.addRecipeImage(it) },
                                image = uiState.recipeImage
                            )
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
                                    text = uiState.recipeName,
                                    onValueChange = {
                                        viewModel.setRecipeName(it)
                                    },
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
                                    ServingInputField {
                                        viewModel.setServings(it.toInt())
                                    }
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
                                    CustomSelectTextField(
                                        text =
                                        if (uiState.timeValue.value != LocalTime.MIDNIGHT) {
                                            "${uiState.timeValue.value.hour}h ${uiState.timeValue.value.minute}m"
                                        } else stringResource(id = R.string.empty_string),
                                        onClick = {
                                            currentBottomSheet = BottomSheetType.TYPE1
                                            coroutineScope.launch { modalBottomSheetState.show() }
                                        },
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
                                    text = uiState.categories
                                        .filter { it.isChecked.value }
                                        .joinToString(
                                            separator = stringResource(id = R.string.category_separator)
                                        ) { it.name },
                                    modifier = Modifier,
                                    onClick = {
                                        currentBottomSheet = BottomSheetType.TYPE2
                                        coroutineScope.launch {
                                            modalBottomSheetState.show()
                                        }
                                    },
                                )
                            }
                            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp24)))
                            Text(
                                text = stringResource(id = R.string.ingredients),
                                style = Typography.titleMedium
                            )
                            if (uiState.ingredients.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp16)))
                            } else {
                                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp8)))
                            }
                        }
                    }


                    itemsIndexed(uiState.ingredients) { index, item ->
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
                                viewModel.removeIngredient(uiState.ingredients[index])
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp8)))
                        AddAnIngredientOrAStepButton(
                            isEnabled = uiState.ingredients.lastOrNull()?.name?.value?.isNotEmpty() == true ||
                                    uiState.ingredients.isEmpty()
                        ) {
                            viewModel.addIngredient("")
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp32)))
                        Text(
                            text = stringResource(id = R.string.method),
                            style = Typography.titleMedium
                        )
                        if (uiState.method.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp16)))
                        } else {
                            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp8)))
                        }
                    }

                    itemsIndexed(uiState.method) { index, item ->
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
                                viewModel.removeMethodStep(uiState.method[index])
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp8)))
                        AddAnIngredientOrAStepButton(
                            isEnabled = uiState.method.lastOrNull()?.step?.value?.isNotEmpty() == true ||
                                    uiState.method.isEmpty(),
                            isIngredient = false,
                        ) {
                            viewModel.addMethodStep("")
                        }
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp32)))
                    }
                }
            }
        }
    )
}