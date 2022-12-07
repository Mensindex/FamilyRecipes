package com.example.familyrecipes.ui.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.familyrecipes.R
import com.example.familyrecipes.ui.theme.*

@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview() {
    CustomInputField()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomInputField(
    modifier: Modifier = Modifier,
    hintText: String = stringResource(id = R.string.empty_string),
    icon: @Composable (() -> Unit)? = null,
) {
    var textState by remember { mutableStateOf(TextFieldValue()) }
    Row(
        modifier = modifier
            .height(dimensionResource(id = R.dimen.large_btn_height))
            .clip(shape = Shapes.small)
            .background(color = Alabaster)
            .padding(horizontal = dimensionResource(id = R.dimen.dp16)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp16)),
    ) {
        BasicTextField(
            modifier = Modifier
                .weight(1f),
            value = textState,
            onValueChange = {
                textState = it
            },
            textStyle = Typography.bodyMedium,
            singleLine = true,
            cursorBrush = SolidColor(Verdigris),
            decorationBox = { innerTextField ->
                TextFieldDefaults.TextFieldDecorationBox(
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        containerColor = Color.Transparent,
                        cursorColor = Verdigris,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    value = textState.text,
                    innerTextField = innerTextField,
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    contentPadding = PaddingValues(0.dp),
                    placeholder = {
                        Text(
                            text = hintText,
                            style = Typography.bodyMedium,
                            color = Heather,
                        )
                    }
                )
            })
        icon?.invoke()
    }
}