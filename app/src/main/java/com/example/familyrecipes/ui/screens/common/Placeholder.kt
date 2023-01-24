package com.example.familyrecipes.ui.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.familyrecipes.R
import com.example.familyrecipes.ui.theme.Heather
import com.example.familyrecipes.ui.theme.Shapes
import com.example.familyrecipes.ui.theme.SoftPeach
import com.example.familyrecipes.ui.theme.Typography

@Preview(showBackground = true)
@Composable
fun PlaceholderPreview() {
    Placeholder {  }
}

@Composable
fun Placeholder(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(Shapes.medium)
            .height(242.dp)
            .background(SoftPeach)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = R.drawable.placeholder),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.dp16))
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 195.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .background(SoftPeach)
                    .padding(dimensionResource(id = R.dimen.dp4)),
                horizontalArrangement = Arrangement
                    .spacedBy(dimensionResource(id = R.dimen.dp4))
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.dp6))
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = null,
                        tint = Heather,
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.dp16)),
                    )
                }

                Text(
                    text = stringResource(id = R.string.add_an_image),
                    style = Typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = Heather,
                )
            }
        }

    }
}