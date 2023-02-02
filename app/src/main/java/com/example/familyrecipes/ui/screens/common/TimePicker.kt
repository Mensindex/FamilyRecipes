package com.example.familyrecipes.ui.screens.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import com.example.familyrecipes.ui.theme.FadedOrange
import com.example.familyrecipes.ui.theme.Shapes
import com.example.familyrecipes.ui.theme.Typography
import java.time.LocalTime


@Preview(showBackground = true)
@Composable
fun CustomTimePickerPreview() {
    CustomTimePicker(
        startTime = LocalTime.MIDNIGHT,
        onSnappedTime = {},
    )
}

@Composable
fun CustomTimePicker(
    startTime: LocalTime,
    onSnappedTime: (snappedTime: LocalTime) -> Unit,
) {
    WheelTimePicker(
        size = DpSize(width = 250.dp, height = 300.dp),
        minTime = LocalTime.MIDNIGHT,
        startTime = startTime,
        rowCount = 5,
        textColor = FadedOrange,
        textStyle = Typography.titleLarge,
        selectorProperties = WheelPickerDefaults.selectorProperties(
            color = FadedOrange.copy(alpha = 0.07f),
            border = BorderStroke(1.dp, FadedOrange.copy(alpha = 0.07f)),
            shape = Shapes.medium
        ),
        onSnappedTime = onSnappedTime,
    )
}