package com.example.familyrecipes.ui.screens.recipe_list

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.familyrecipes.R
import com.example.familyrecipes.ui.navigation.NavRoute
import com.example.familyrecipes.ui.screens.common.RecipeCard
import com.example.familyrecipes.ui.theme.Typography
import com.example.familyrecipes.ui.theme.Verdigris

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeList(
    navController: NavHostController,
    viewModel: RecipeListViewModel,
    onBackClick: () -> Unit,
) {

    val uiState by viewModel.recipeListUiState.collectAsState()

    //Animation
    var isClicked by remember { mutableStateOf(false) }
    val pulseAnimation by animateFloatAsState(
        targetValue = if (isClicked) .7f else 1f,
        animationSpec = repeatable(
            iterations = 2,
            animation = tween(durationMillis = 70, easing = EaseOutBack),
            repeatMode = RepeatMode.Reverse
        ),
        finishedListener = {
            isClicked = false
        }
    )
    //TopBar animation
    val scrollBehavior = TopAppBarDefaults
        .exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val sizeOfCollapsedTopBar =
        dimensionResource(id = R.dimen.collapsedTopAppBarTitleSize).value.toInt()
    val sizeOfExpandedTopBar =
        dimensionResource(id = R.dimen.expandedTopAppBarTitleSize).value.toInt()
    val topAppBarTextSize =
        (sizeOfCollapsedTopBar + (sizeOfExpandedTopBar - sizeOfCollapsedTopBar) * (1 - scrollBehavior.state.collapsedFraction)).sp

    Scaffold(
        modifier = Modifier,
        containerColor = Color.White,
        topBar = {
            LargeTopAppBar(
                modifier = Modifier,
                title = {
                    Text(
                        text = if (uiState.loading) {
                            stringResource(id = R.string.empty_string)
                        } else if (uiState.error != null) {
                            "${uiState.error}"
                        } else {
                            uiState.categoryName
                        },
                        style = Typography.headlineLarge,
                        fontSize = topAppBarTextSize,
                    )
                },
                actions = {
                    Row(
                        modifier = Modifier
                            .padding(end = dimensionResource(id = R.dimen.dp16))
                            .clickable {
                                navController.navigate(NavRoute.AddingARecipeRoute.route)
                            },
                        horizontalArrangement = Arrangement
                            .spacedBy(dimensionResource(id = R.dimen.dp4)),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            modifier = Modifier
                                .graphicsLayer {
                                    scaleX = if (isClicked) pulseAnimation else 1f
                                    scaleY = if (isClicked) pulseAnimation else 1f
                                },
                            painter = painterResource(id = R.drawable.filled_plus),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                        )
                        Text(
                            text = stringResource(id = R.string.add_a_recipe),
                            style = Typography.labelLarge,
                            color = Verdigris,
                        )
                    }
                },
                navigationIcon = {
                    Image(
                        modifier = Modifier.clickable(
                            onClick = onBackClick
                        ),
                        painter = painterResource(
                            id = R.drawable.chevron_left
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                    )
                }
            )
        }
    ) { innerPadding ->
        if (uiState.loading) {

        } else if (uiState.error != null) {
            Text(text = uiState.error.orEmpty())
        } else {
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
                    items(
                        items = uiState.recipeList,
                        itemContent = { item ->
                            RecipeCard(
                                recipe = item,
                                onClick = {
                                    navController.navigate(
                                        route = NavRoute.RecipeRoute.route.plus(
                                            "/${item.id}"
                                        )
                                    )
                                },
                            )
                            Spacer(
                                modifier = Modifier
                                    .height(dimensionResource(id = R.dimen.dp12))
                            )
                        }
                    )
                })
        }
    }
}