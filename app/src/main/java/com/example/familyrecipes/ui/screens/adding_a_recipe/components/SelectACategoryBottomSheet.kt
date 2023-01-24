package com.example.familyrecipes.ui.screens.adding_a_recipe.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.familyrecipes.R
import com.example.familyrecipes.data.models.Category
import com.example.familyrecipes.ui.screens.common.CreateACategoryField
import com.example.familyrecipes.ui.screens.common.StrokeButton
import com.example.familyrecipes.ui.theme.*

@Preview(showBackground = true)
@Composable
fun SelectACategoryPreview() {
    SelectACategoryBottomSheet(
        categoryList = remember {
            mutableStateListOf<Category>()
        },
        onSelectClick = {}
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SelectACategoryBottomSheet(
    onSelectClick: () -> Unit,
    categoryList: MutableList<Category>,
) {

    var isAddACategoryClicked by remember { mutableStateOf(false) }
    var categoryNameText by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .background(color = Color.White),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.dp62)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            androidx.compose.material3.Text(
                modifier = Modifier,
                text = stringResource(id = R.string.select_a_category),
                style = Typography.titleLarge,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Alabaster)
                .padding(dimensionResource(id = R.dimen.dp8)),
            contentAlignment = Alignment.CenterStart,
        ) {

            val animateRotate = animateFloatAsState(
                targetValue = if (isAddACategoryClicked) 45f else 0f,
                animationSpec = tween(
                    easing = EaseOutBack
                )
            )
            val animateColor = animateColorAsState(
                targetValue = if (isAddACategoryClicked) FadedOrange else Verdigris
            )
            Row(
                modifier = Modifier
                    .clickable(
                        onClick = {
                            if (isAddACategoryClicked) {
                                categoryNameText = ""
                            }
                            isAddACategoryClicked = !isAddACategoryClicked
                        },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp4)),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    modifier = Modifier.rotate(animateRotate.value),
                    painter = painterResource(id = R.drawable.filled_plus),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(animateColor.value)
                )
                AnimatedContent(
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dp4)),
                    targetState = isAddACategoryClicked
                ) {

                    androidx.compose.material3.Text(
                        text = if (it) stringResource(id = R.string.cancel) else stringResource(id = R.string.add_a_category),
                        style = Typography.labelLarge,
                        color = animateColor.value,
                    )
                }
            }
        }

        if (isAddACategoryClicked) {
            CreateACategoryField(
                text = categoryNameText,
                onAddClick = {
                    categoryList.add(
                        index = 0,
                        Category(name = categoryNameText)
                    )
                    categoryNameText = ""
                    isAddACategoryClicked = false
                },
                onValueChange = { categoryNameText = it },
                isAddEnabled = categoryNameText.isNotEmpty()
            )
        }

        LazyColumn(
            modifier = Modifier,
            content = {
                itemsIndexed(categoryList) { index, item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = dimensionResource(id = R.dimen.dp16),
                                vertical = dimensionResource(id = R.dimen.dp12),
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = item.name,
                            style = Typography.labelLarge,
                        )
                        Checkbox(
                            modifier = Modifier,
                            checked = item.isChecked.value,
                            onCheckedChange = {
                                item.isChecked.value = it
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Verdigris,
                                uncheckedColor = Heather,
                                checkmarkColor = Color.White,
                            )
                        )
                    }
                    if (index < categoryList.size - 1) {
                        Divider(color = CatskillWhite)
                    }

                }
            }
        )
        Box(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.dp16))
        ) {
            StrokeButton(
                enable = categoryList.any { it.isChecked.value },
                label = stringResource(id = R.string.select),
                onClick = { onSelectClick() }
            )
        }

    }
}

