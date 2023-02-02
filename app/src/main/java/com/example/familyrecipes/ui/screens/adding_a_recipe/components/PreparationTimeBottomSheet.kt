package com.example.familyrecipes.ui.screens.adding_a_recipe.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.familyrecipes.R
import com.example.familyrecipes.ui.screens.common.CustomTimePicker
import com.example.familyrecipes.ui.screens.common.StrokeButton
import com.example.familyrecipes.ui.theme.Alabaster
import com.example.familyrecipes.ui.theme.Typography
import java.time.LocalTime

@Preview(showBackground = true)
@Composable
fun PreparationTimeBottomSheetPreview() {
    PreparationTimeBottomSheet(
        timeValue = remember {
            mutableStateOf(LocalTime.MIDNIGHT)
        },
        onSelectClick = {}

    )
}

@Composable
fun PreparationTimeBottomSheet(
    timeValue: MutableState<LocalTime>,
    onSelectClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
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
                text = stringResource(id = R.string.preparation_time),
                style = Typography.titleLarge,
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(id = R.dimen.dp16)),
            thickness = dimensionResource(id = R.dimen.dp6),
            color = Alabaster,
        )
        Column(
            modifier = Modifier
        ) {
            CustomTimePicker(startTime = timeValue.value) { snappedTime ->
                timeValue.value = snappedTime
            }
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp24)))
        Box(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.dp16))
        ) {
            StrokeButton(
                enable = true,
                label = stringResource(id = R.string.select),
                onClick = { onSelectClick() }
            )
        }
    }

}