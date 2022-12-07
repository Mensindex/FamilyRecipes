package com.example.familyrecipes.ui.screens.common

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.familyrecipes.R
import com.example.familyrecipes.ui.theme.Heather
import com.example.familyrecipes.ui.theme.Shapes
import com.example.familyrecipes.ui.theme.Typography

@Preview(showBackground = true)
@Composable
fun RecipeCardPreview(){
    RecipeCard()
}


@Composable
fun RecipeCard() {
    Card(
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.recipe_card_height))
            .fillMaxWidth()
            .graphicsLayer {  }
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
                    text = "Granny's cake",
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
                        text = "1h 40m",
                        style = Typography.bodyMedium,
                        color = Heather,
                    )
                }
            }

            Image(
                modifier = Modifier
                    .clip(Shapes.small)
                    .fillMaxHeight()
                    .width(72.dp),
                painter = painterResource(id = R.drawable.placeholder),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    }
}