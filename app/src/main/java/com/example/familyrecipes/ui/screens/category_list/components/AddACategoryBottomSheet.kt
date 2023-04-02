package com.example.familyrecipes.ui.screens.category_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.familyrecipes.R
import com.example.familyrecipes.ui.screens.common.CustomInputField
import com.example.familyrecipes.ui.screens.common.StrokeButton
import com.example.familyrecipes.ui.theme.Alabaster
import com.example.familyrecipes.ui.theme.FadedOrange
import com.example.familyrecipes.ui.theme.Typography
import com.example.familyrecipes.ui.theme.Verdigris

@Preview(showBackground = true)
@Composable
fun AddACategoryBottomSheetPreview() {
    AddACategoryBottomSheet(
        onCancelClick = {},
        onAddClick = {},
    )
}

@Composable
fun AddACategoryBottomSheet(
    onCancelClick: () -> Unit,
    onAddClick: () -> Unit,
) {
    var categoryNameText by remember { mutableStateOf("") }

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
            androidx.compose.material3.Text(
                modifier = Modifier,
                text = stringResource(id = R.string.add_a_category),
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
                .padding(dimensionResource(id = R.dimen.dp16))
        ) {
            CustomInputField(
                text = categoryNameText,
                hintText = stringResource(id = R.string.category_name),
                onSuccess = {
                    categoryNameText = it
                }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.dp16),
                    end = dimensionResource(id = R.dimen.dp16),
                    bottom = dimensionResource(id = R.dimen.dp16),
                ),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp8))
        ) {
            StrokeButton(
                width = .5f,
                color = FadedOrange,
                label = stringResource(id = R.string.cancel),
                enable = true,
            ) {
                onCancelClick()
                categoryNameText = ""
            }
            StrokeButton(
                color = Verdigris,
                label = stringResource(id = R.string.add),
                enable = categoryNameText.isNotEmpty(),
            ) {
                onAddClick()
                categoryNameText = ""
            }
        }

    }
}