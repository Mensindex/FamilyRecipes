package com.example.familyrecipes.ui.screens.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.familyrecipes.R
import com.example.familyrecipes.ui.theme.CatskillWhite
import com.example.familyrecipes.ui.theme.Shapes
import com.example.familyrecipes.ui.theme.Typography
import com.example.familyrecipes.ui.theme.Verdigris

@Preview(showBackground = true)
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
            verticalAlignment = Alignment.CenterVertically
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
                verticalAlignment = Alignment.CenterVertically
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






