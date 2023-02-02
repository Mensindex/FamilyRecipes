package com.example.familyrecipes.ui.screens.recipe.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.familyrecipes.R
import com.example.familyrecipes.ui.theme.Alabaster
import com.example.familyrecipes.ui.theme.CatskillWhite
import com.example.familyrecipes.ui.theme.Typography

@Preview(showBackground = true)
@Composable
fun RecipeOptionsBottomSheetPreview() {
    RecipeOptionsBottomSheet(
        onEditClick = {},
        onAddToCategoryClick = {},
        onShareClick = {},
        onDeleteClick = {},
    )
}

@Composable
fun RecipeOptionsBottomSheet(
    onEditClick: () -> Unit,
    onAddToCategoryClick: () -> Unit,
    onShareClick: () -> Unit,
    onDeleteClick: () -> Unit,
) {

    var currentClick: OnIconClickType? by remember {
        mutableStateOf(null)
    }
    val pulseAnimation by animateFloatAsState(
        targetValue = if (currentClick != null) .7f else 1f,
        animationSpec = repeatable(
            iterations = 2,
            animation = tween(durationMillis = 70, easing = EaseOutBack),
            repeatMode = RepeatMode.Reverse
        ),
        finishedListener = {
            currentClick = null
        }
    )
    val animateRotate = animateFloatAsState(
        targetValue = if (currentClick != null) 8f else 0f,
        animationSpec = repeatable(
            iterations = 2,
            animation = tween(durationMillis = 100, easing = EaseOutBack),
            repeatMode = RepeatMode.Reverse
        ),
        finishedListener = {
            currentClick = null
        }
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(bottom = dimensionResource(id = R.dimen.dp16)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.dp62)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.recipe_options),
                style = Typography.titleLarge,
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth(),
            thickness = dimensionResource(id = R.dimen.dp6),
            color = Alabaster,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.dp16),
                    vertical = dimensionResource(id = R.dimen.dp16),
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        currentClick = OnIconClickType.EDIT
                        onEditClick()
                    },
                ),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp8)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = if (currentClick == OnIconClickType.EDIT) pulseAnimation else 1f
                        scaleY = if (currentClick == OnIconClickType.EDIT) pulseAnimation else 1f
                    }
                    .rotate(
                        if (currentClick == OnIconClickType.EDIT) animateRotate.value else 1f
                    ),
                painter = painterResource(id = R.drawable.edit),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            androidx.compose.material.Text(
                text = stringResource(id = R.string.edit),
                style = Typography.labelLarge,
            )
        }
        androidx.compose.material.Divider(color = CatskillWhite)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.dp16),
                    vertical = dimensionResource(id = R.dimen.dp16),
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        currentClick = OnIconClickType.ADD
                        onAddToCategoryClick()
                    },
                ),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp8)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = if (currentClick == OnIconClickType.ADD) pulseAnimation else 1f
                        scaleY = if (currentClick == OnIconClickType.ADD) pulseAnimation else 1f
                    }
                    .rotate(
                        if (currentClick == OnIconClickType.ADD) animateRotate.value else 1f
                    ),
                painter = painterResource(id = R.drawable.add_to_category),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            androidx.compose.material.Text(
                text = stringResource(id = R.string.add_to_category),
                style = Typography.labelLarge,
            )
        }
        androidx.compose.material.Divider(color = CatskillWhite)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.dp16),
                    vertical = dimensionResource(id = R.dimen.dp16),
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        currentClick = OnIconClickType.SHARE
                        onShareClick()
                    },
                ),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp8)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = if (currentClick == OnIconClickType.SHARE) pulseAnimation else 1f
                        scaleY = if (currentClick == OnIconClickType.SHARE) pulseAnimation else 1f
                    }
                    .rotate(
                        if (currentClick == OnIconClickType.SHARE) animateRotate.value else 1f
                    ),
                painter = painterResource(id = R.drawable.share),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            androidx.compose.material.Text(
                text = stringResource(id = R.string.share),
                style = Typography.labelLarge,
            )
        }
        androidx.compose.material.Divider(color = CatskillWhite)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.dp16),
                    vertical = dimensionResource(id = R.dimen.dp16),
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        currentClick = OnIconClickType.DELETE
                        onDeleteClick()
                    },
                ),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp8)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = if (currentClick == OnIconClickType.DELETE) pulseAnimation else 1f
                        scaleY = if (currentClick == OnIconClickType.DELETE) pulseAnimation else 1f
                    }
                    .rotate(
                        if (currentClick == OnIconClickType.DELETE) animateRotate.value else 1f
                    ),
                painter = painterResource(id = R.drawable.delete),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            androidx.compose.material.Text(
                text = stringResource(id = R.string.delete),
                style = Typography.labelLarge,
            )
        }
    }
}