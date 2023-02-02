package com.example.familyrecipes.ui.screens.common

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.familyrecipes.R
import com.example.familyrecipes.ui.theme.Heather
import com.example.familyrecipes.ui.theme.Shapes
import com.example.familyrecipes.ui.theme.SoftPeach
import com.example.familyrecipes.ui.theme.Typography
import java.time.LocalTime


@Composable
fun RecipeCard(
    recipeName: String,
    preparingTime: LocalTime,
    recipeImage: Bitmap?,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .clickable(
                onClick = onClick,
            )
            .height(dimensionResource(id = R.dimen.recipe_card_height))
            .fillMaxWidth()
            .graphicsLayer { }
            .shadow(
                elevation = dimensionResource(id = R.dimen.dp8),
                ambientColor = Color.Gray,
                spotColor = Color.Gray,
                shape = Shapes.medium
            ),
        shape = Shapes.medium,

        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = dimensionResource(id = R.dimen.dp16),
                    end = dimensionResource(id = R.dimen.dp8),
                    top = dimensionResource(id = R.dimen.dp8),
                    bottom = dimensionResource(id = R.dimen.dp8),
                ),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp16)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp8))
            ) {
                Text(
                    text = recipeName,
                    maxLines = 1,
                    style = Typography.bodyLarge,
                    color = Color.Black,
                )
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement
                        .spacedBy(dimensionResource(id = R.dimen.dp2)),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.time),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        modifier = Modifier
                            .padding(bottom = dimensionResource(id = R.dimen.dp2)),
                        text = "${preparingTime.hour}h ${preparingTime.minute}m",
                        style = Typography.labelMedium,
                        color = Heather,
                    )
                }
            }

            if (recipeImage != null) {
                Image(
                    modifier = Modifier
                        .clip(Shapes.small)
                        .fillMaxHeight()
                        .width(72.dp)
                        .background(SoftPeach),
                    bitmap = recipeImage.asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            } else {
                Image(
                    modifier = Modifier
                        .clip(Shapes.small)
                        .fillMaxHeight()
                        .width(72.dp)
                        .background(SoftPeach),
                    painter = painterResource(id = R.drawable.mini_placeholder),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}