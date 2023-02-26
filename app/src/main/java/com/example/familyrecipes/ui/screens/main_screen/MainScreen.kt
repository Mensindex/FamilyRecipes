package com.example.familyrecipes.ui.screens.main_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.familyrecipes.App
import com.example.familyrecipes.R
import com.example.familyrecipes.ui.navigation.NavRoute
import com.example.familyrecipes.ui.screens.common.CustomInputField
import com.example.familyrecipes.ui.screens.common.LargeAddButton
import com.example.familyrecipes.ui.screens.common.RecipeCard
import com.example.familyrecipes.ui.screens.common.SmallCategoriesButton
import com.example.familyrecipes.ui.theme.Heather
import com.example.familyrecipes.ui.theme.Typography
import com.example.familyrecipes.ui.theme.Verdigris

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(App.dao)),
) {
    val uiState by viewModel.mainViewModelUIState.collectAsState()

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                modifier = Modifier,
                title = { Text(text = stringResource(id = R.string.app_name)) },
                actions = {
                    Icon(
                        modifier = Modifier.clickable { },
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = stringResource(id = R.string.profile),
                        tint = Verdigris,
                    )
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.dp16),
                    end = dimensionResource(id = R.dimen.dp16),
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding(),
                )
                .fillMaxWidth(),
            content = {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp8))
                    )
                    {
                        LargeAddButton {
                            navController.navigate(route = NavRoute.AddingARecipeRoute.route)
                        }
                        CustomInputField(
                            modifier = Modifier.weight(0.7f),
                            hintText = stringResource(id = R.string.search_a_recipe___),
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.search),
                                    contentDescription = null,
                                    tint = Verdigris,
                                    modifier = Modifier
                                )
                            },
                            text = "",
                            onValueChange = {}
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.dp24))
                    )
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement
                            .spacedBy(dimensionResource(id = R.dimen.dp16)),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = stringResource(id = R.string.all_recipes),
                            style = Typography.labelLarge,
                            color = Heather,
                        )
                        SmallCategoriesButton()
                    }
                    Spacer(
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.dp16))
                    )
                }
                items(
                    items = uiState.listRecipe,
                    itemContent = { item ->
                        RecipeCard(
                            onClick = { navController.navigate(route = NavRoute.RecipeRoute.route) },
                            recipe = item
                        )
                        Spacer(
                            modifier = Modifier
                                .height(dimensionResource(id = R.dimen.dp12))
                        )
                    })
                item {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp16)))
                }

            }
        )
    }
}