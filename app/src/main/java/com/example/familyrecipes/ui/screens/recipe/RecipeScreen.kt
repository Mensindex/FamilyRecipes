package com.example.familyrecipes.ui.screens.recipe

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.familyrecipes.R
import com.example.familyrecipes.data.models.Recipe
import com.example.familyrecipes.ui.screens.recipe.components.RecipeOptionsBottomSheet
import com.example.familyrecipes.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen(
    navController: NavHostController,
    recipe: Recipe,
    onBackClick: () -> Unit,
) {

    //TopBar animation
    val scrollBehavior = TopAppBarDefaults
        .exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val sizeOfCollapsedTopBar =
        dimensionResource(id = R.dimen.collapsedTopAppBarTitleSize).value.toInt()
    val sizeOfExpandedTopBar =
        dimensionResource(id = R.dimen.expandedTopAppBarTitleSize).value.toInt()
    val topAppBarTextSize =
        (sizeOfCollapsedTopBar + (sizeOfExpandedTopBar - sizeOfCollapsedTopBar)
                * (1 - scrollBehavior.state.collapsedFraction)).sp

    //BottomSheet setting
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val coroutineScope = rememberCoroutineScope()
    BackHandler(
        enabled = modalBottomSheetState.isVisible,
        onBack = { coroutineScope.launch { modalBottomSheetState.hide() } }
    )

    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetState = modalBottomSheetState,
        sheetContent = {
            Spacer(modifier = Modifier.height(1.dp))
            RecipeOptionsBottomSheet(
                onEditClick = { },
                onAddToCategoryClick = { },
                onShareClick = { },
                onDeleteClick = { },
            )
        },
        sheetShape = RoundedCornerShape(
            topStart = dimensionResource(id = R.dimen.dp14),
            topEnd = dimensionResource(id = R.dimen.dp14)
        ),
        content = {
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                containerColor = Color.White,
                topBar = {
                    LargeTopAppBar(
                        modifier = Modifier,
                        title = {
                            Text(
                                text = recipe.name,
                                style = Typography.headlineLarge,
                                fontSize = topAppBarTextSize,
                            )
                        },
                        actions = {
                            Image(
                                modifier = Modifier
                                    .clip(Shapes.medium)
                                    .indication(
                                        interactionSource = remember {
                                            MutableInteractionSource()
                                        },
                                        indication = rememberRipple(
                                            radius = dimensionResource(id = R.dimen.dp20)
                                        )
                                    )
                                    .clickable {
                                        coroutineScope.launch { modalBottomSheetState.show() }
                                    },
                                painter = painterResource(id = R.drawable.dot_menu),
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
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = dimensionResource(id = R.dimen.dp16)),
                        ) {
                            //Recipe image
                            Box(
                                modifier = Modifier
                                    .clip(Shapes.medium)
                                    .height(242.dp)
                                    .fillMaxWidth()
                                    .background(SoftPeach)
                            ) {

                                if (recipe.image == null) {
                                    Image(
                                        painter = painterResource(id = R.drawable.placeholder),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxSize()
                                    )

                                } else {
                                    Image(
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        bitmap = recipe.image.asImageBitmap(),
                                        contentDescription = null,
                                        contentScale = ContentScale.FillWidth
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp16)))
                            //Time$Serving
                            Row(
                                modifier = Modifier
                                    .padding(bottom = dimensionResource(id = R.dimen.dp24)),
                                horizontalArrangement = Arrangement
                                    .spacedBy(dimensionResource(id = R.dimen.dp8)),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Row(
                                    modifier = Modifier,
                                    horizontalArrangement = Arrangement
                                        .spacedBy(dimensionResource(id = R.dimen.dp2)),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Image(
                                        modifier = Modifier.size(dimensionResource(id = R.dimen.dp18)),
                                        painter = painterResource(id = R.drawable.time),
                                        contentDescription = null,
                                        contentScale = ContentScale.Fit
                                    )
                                    Text(
                                        text = "${recipe.preparingTime.hour}h " +
                                                "${recipe.preparingTime.minute}m",
                                        style = Typography.bodyMedium,
                                        color = SecondaryText,
                                    )
                                }
                                Divider(
                                    color = CatskillWhite,
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(dimensionResource(id = R.dimen.dp1))
                                )
                                Row(
                                    modifier = Modifier,
                                    horizontalArrangement = Arrangement
                                        .spacedBy(dimensionResource(id = R.dimen.dp2)),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Image(
                                        modifier = Modifier.size(dimensionResource(id = R.dimen.dp18)),
                                        painter = painterResource(id = R.drawable.dish),
                                        contentDescription = null,
                                        contentScale = ContentScale.Fit
                                    )
                                    Text(
                                        text = "${recipe.servings} serving(s)",
                                        style = Typography.bodyMedium,
                                        color = SecondaryText,
                                    )
                                }
                            }
                            //Ingredients title
                            Text(
                                text = stringResource(id = R.string.ingredients),
                                style = Typography.titleMedium
                            )
                        }
                    }
                    items(recipe.ingredients) { ingredient ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(text = ingredient.name.value)
                        }
                    }
                    item {
                        Text(
                            modifier = Modifier
                                .padding(
                                    top = dimensionResource(id = R.dimen.dp24),
                                    bottom = dimensionResource(id = R.dimen.dp16),
                                ),
                            text = stringResource(id = R.string.method),
                            style = Typography.titleMedium,
                        )
                    }
                    itemsIndexed(recipe.method) { index, methodStep ->
                        Column(
                            modifier = Modifier
                                .padding(bottom = dimensionResource(id = R.dimen.dp16)),
                            verticalArrangement = Arrangement
                                .spacedBy(dimensionResource(id = R.dimen.dp8)),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Row(
                                modifier = Modifier
                                    .clip(Shapes.extraSmall)
                                    .background(AlphaVerdigris)
                                    .padding(
                                        horizontal = dimensionResource(id = R.dimen.dp8),
                                        vertical = dimensionResource(id = R.dimen.dp4)
                                    ),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = "Step ${index + 1}",
                                    style = Typography.labelSmall,
                                    color = Verdigris,
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(text = methodStep.step.value)
                            }
                        }

                    }

                }
            }
        })
}