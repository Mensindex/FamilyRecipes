package com.example.familyrecipes.ui.screens.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.familyrecipes.R
import com.example.familyrecipes.ui.theme.Heather
import com.example.familyrecipes.ui.theme.Shapes
import com.example.familyrecipes.ui.theme.Typography

@Preview(showBackground = true)
@Composable
fun CategoryCardPreview() {
    CategoryCard("Breakfast")
}

@Composable
fun CategoryCard(categoryName: String) {
    Card(
        modifier = Modifier
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
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = dimensionResource(id = R.dimen.dp12)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = categoryName,
                style = Typography.titleLarge,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = Color.Black,
            )
            Text(
                text = "8 recipes",
                style = Typography.labelMedium,
                textAlign = TextAlign.Center,
                color = Heather,
            )
        }
    }
}