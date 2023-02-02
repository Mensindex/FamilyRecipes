package com.example.familyrecipes.ui.screens.category_list

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.familyrecipes.R
import com.example.familyrecipes.ui.navigation.NavRoute
import com.example.familyrecipes.ui.screens.category_list.components.AddACategoryBottomSheet
import com.example.familyrecipes.ui.screens.common.CategoryCard
import com.example.familyrecipes.ui.theme.Typography
import com.example.familyrecipes.ui.theme.Verdigris
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun CategoryListScreen(
    navController: NavHostController,
) {
    val myCategoryList = mutableListOf("Breakfast", "Lunch", "Dinner")
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
    //BottomSheet setting
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
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
            AddACategoryBottomSheet(
                onCancelClick = { coroutineScope.launch { modalBottomSheetState.hide() } },
                onAddClick = { coroutineScope.launch { modalBottomSheetState.hide() } },
            )
        },
        sheetShape = RoundedCornerShape(
            topStart = dimensionResource(id = R.dimen.dp14),
            topEnd = dimensionResource(id = R.dimen.dp14)
        ),
        content = {
            Scaffold(
                modifier = Modifier,
                containerColor = Color.White,
                topBar = {
                    LargeTopAppBar(
                        modifier = Modifier,
                        title = {
                            Text(
                                text = stringResource(id = R.string.categories),
                                style = Typography.headlineLarge,
                                fontSize = topAppBarTextSize,
                            )
                        },
                        actions = {
                            Row(
                                modifier = Modifier
                                    .padding(end = dimensionResource(id = R.dimen.dp16))
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                        onClick = {
                                            isClicked = true
                                            coroutineScope.launch { modalBottomSheetState.show() }
                                        }
                                    ),
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
                                    text = stringResource(id = R.string.add_a_category),
                                    style = Typography.labelLarge,
                                    color = Verdigris,
                                )
                            }
                        },
                        navigationIcon = {
                            Image(
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
                            items = myCategoryList,
                            itemContent = { item ->
                                CategoryCard(
                                    categoryName = item,
                                    onClick = { navController.navigate(route = NavRoute.RecipeRoute.route) }
                                )
                                Spacer(
                                    modifier = Modifier
                                        .height(dimensionResource(id = R.dimen.dp12))
                                )
                            }
                        )
                    }
                )
            }
        })

}