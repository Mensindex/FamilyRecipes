package com.example.familyrecipes.ui.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.familyrecipes.R
import com.example.familyrecipes.ui.theme.*

@Preview(showBackground = true)
@Composable
fun TesttPreview() {
    CustomSelectTextField() {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomInputField(
    modifier: Modifier = Modifier,
    text: String = stringResource(id = R.string.empty_string),
    hintText: String = stringResource(id = R.string.select___),
    icon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
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
                .weight(1f)
                .focusRequester(remember { FocusRequester() }),
            value = text,
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            onValueChange = onValueChange,
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
                    value = text,
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

@Composable
fun CustomSelectTextField(
    modifier: Modifier = Modifier,
    text: String = stringResource(id = R.string.empty_string),
    hintText: String = stringResource(id = R.string.select___),
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.large_btn_height))
            .clip(shape = Shapes.small)
            .background(color = Alabaster)
            .padding(horizontal = dimensionResource(id = R.dimen.dp16))
    ) {
        if (text.isBlank()) {
            Text(
                modifier = Modifier.align(CenterStart),
                text = hintText,
                style = Typography.bodyMedium,
                color = Heather,
                overflow = TextOverflow.Visible,
                maxLines = 1,
            )
        } else {
            Text(
                modifier = Modifier.align(CenterStart),
                text = text,
                style = Typography.bodyMedium,
                color = Color.Black,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateACategoryField(
    text: String = stringResource(id = R.string.empty_string),
    onAddClick: () -> Unit,
    isAddEnabled: Boolean,
    onValueChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    Row(
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.large_btn_height))
            .background(color = Alabaster)
            .padding(horizontal = dimensionResource(id = R.dimen.dp16)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp16)),
    ) {
        BasicTextField(
            modifier = Modifier
                .weight(1f)
                .focusRequester(remember { FocusRequester() }),
            value = text,
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            onValueChange = onValueChange,
            textStyle = Typography.bodyLarge,
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
                    value = text,
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
                            text = stringResource(id = R.string.category_name),
                            style = Typography.bodyLarge,
                            color = Heather,
                        )
                    }
                )
            })

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(dimensionResource(id = R.dimen.large_btn_height))
                .clickable(
                    enabled = isAddEnabled,
                    onClick = onAddClick
                ),
            contentAlignment = Center
        ) {
            Icon(
                modifier = Modifier.size(dimensionResource(id = R.dimen.dp24)),
                painter = painterResource(id = R.drawable.filled_plus24px),
                contentDescription = null,
                tint = if (text.isEmpty()) Heather else Verdigris,
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServingInputField(onSuccess: (String) -> Unit) {
    var servingText by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Row(
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.large_btn_height))
            .clip(shape = Shapes.small)
            .background(color = Alabaster)
            .padding(horizontal = dimensionResource(id = R.dimen.dp16)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp16)),
    ) {
        BasicTextField(
            modifier = Modifier
                .weight(1f)
                .focusRequester(remember { FocusRequester() }),
            value = servingText,
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                servingText = it
                onSuccess(it)
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
                    value = servingText,
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
                            text = stringResource(id = R.string.select___),
                            style = Typography.bodyMedium,
                            color = Heather,
                        )
                    }
                )
            })
    }
}