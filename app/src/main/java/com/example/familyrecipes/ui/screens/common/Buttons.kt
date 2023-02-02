package com.example.familyrecipes.ui.screens.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.familyrecipes.R
import com.example.familyrecipes.ui.theme.*

@Composable
fun LargeAddButton() {
    Button(
        onClick = {},
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.large_btn_height)),
        contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.dp16)),
        colors = ButtonDefaults.buttonColors(containerColor = Verdigris),
        shape = Shapes.small,
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp4)),
            verticalAlignment = CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = null,
                tint = Color.White,
            )
            Text(
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.dp2)),
                text = stringResource(id = R.string.add),
                style = Typography.labelLarge,
                color = Color.White,
            )
        }
    }
}

@Composable
fun SmallCategoriesButton() {
    OutlinedButton(
        onClick = {},
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.small_btn_height)),
        border = BorderStroke(
            width = dimensionResource(id = R.dimen.dp1),
            color = CatskillWhite
        ),
        contentPadding = PaddingValues(
            start = dimensionResource(id = R.dimen.dp12),
            end = dimensionResource(id = R.dimen.dp8),
        ),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        shape = Shapes.small,
        content = {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp4)),
                verticalAlignment = CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = dimensionResource(id = R.dimen.dp4)),
                    text = stringResource(id = R.string.categories),
                    style = Typography.labelLarge,
                    color = Verdigris,
                )
                Icon(
                    painter = painterResource(id = R.drawable.chevron_right),
                    contentDescription = null,
                    tint = Verdigris,
                )
            }
        }
    )
}

@Composable
fun CancelButton(onClick: () -> Unit) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    Box(
        modifier = Modifier
            .size(dimensionResource(id = R.dimen.large_btn_height))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            ),
        contentAlignment = Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.cancel24px),
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .indication(
                    interactionSource = interactionSource,
                    indication = rememberRipple(radius = dimensionResource(id = R.dimen.dp9)),
                ),
            contentDescription = null,
            contentScale = ContentScale.Fit,
        )
    }
}

@Composable
fun AddAnIngredientOrAStepButton(
    isIngredient: Boolean = true,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
) {
    val animatedColor = animateColorAsState(
        targetValue = if (isEnabled) Verdigris else Heather,
    )
    val stroke = Stroke(
        width = 4f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0.5f)
    )
    Box(
        modifier = Modifier
            .clip(Shapes.small)
            .height(dimensionResource(id = R.dimen.large_btn_height))
            .fillMaxWidth()
            .clickable(
                enabled = isEnabled,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = Verdigris),
                onClick = onClick,
            ),
        contentAlignment = Center,
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRoundRect(
                color = animatedColor.value,
                style = stroke,
                cornerRadius = CornerRadius(8.dp.toPx())
            )
        }
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp4)),
            verticalAlignment = CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.filled_plus),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(animatedColor.value),
            )
            Text(
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.dp2)),
                text = if (isIngredient) {
                    stringResource(id = R.string.add_an_ingredient)
                } else {
                    stringResource(id = R.string.add_a_step)
                },
                style = Typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                color = animatedColor.value,
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestPreview() {
    StrokeButton(label = "Select") {

    }
}


@Composable
fun StrokeButton(
    width: Float = 1f,
    color: Color = Verdigris,
    label:String,
    enable: Boolean = false,
    onClick: () -> Unit,
) {
    val animatedColor = animateColorAsState(
        targetValue = if (enable) color else Heather,
    )
    Box(
        modifier = Modifier
            .clip(Shapes.small)
            .height(dimensionResource(id = R.dimen.large_btn_height))
            .fillMaxWidth(fraction = width)
            .clickable(
                enabled = enable,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = color),
                onClick = onClick,
            ),
        contentAlignment = Center,
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRoundRect(
                color = animatedColor.value,
                style = Stroke(width = 4f),
                cornerRadius = CornerRadius(8.dp.toPx())
            )
        }
        Text(
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.dp2)),
            text = label,
            style = Typography.labelLarge,
            fontWeight = FontWeight.SemiBold,
            color = animatedColor.value,
        )
    }
}







