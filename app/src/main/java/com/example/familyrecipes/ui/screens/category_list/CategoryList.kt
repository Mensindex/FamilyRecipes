package com.example.familyrecipes.ui.screens.category_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.familyrecipes.R
import com.example.familyrecipes.ui.screens.common.CategoryCard
import com.example.familyrecipes.ui.theme.Typography
import com.example.familyrecipes.ui.theme.Verdigris

@Preview(showBackground = true)
@Composable
fun CategoryListTest() {
    CategoryList()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryList() {
    val myCategoryList = mutableListOf<String>("Breakfast", "Lunch", "Dinner")
    Scaffold(
        modifier = Modifier,
        containerColor = Color.White,
        topBar = {
            LargeTopAppBar(
                modifier = Modifier,
                title = {
                    Text(
                        text = stringResource(id = R.string.categories),
                        style = Typography.headlineLarge,
                    )
                },
                actions = {
                    Row(
                        modifier = Modifier
                            .padding(end = dimensionResource(id = R.dimen.dp16))
                            .clickable { },
                        horizontalArrangement = Arrangement
                            .spacedBy(dimensionResource(id = R.dimen.dp4)),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.filled_plus),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                        )
                        Text(
                            text = stringResource(id = R.string.add_a_category),
                            style = Typography.labelLarge,
                            color = Verdigris,
                        )
                    }
                },
                navigationIcon = {
                    Image(
                        painter = painterResource(
                            id = R.drawable.chevron_left),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                    )
                }
            )
        }
    ) { pad ->
        LazyColumn(
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.dp16),
                    end = dimensionResource(id = R.dimen.dp16),
                    top = pad.calculateTopPadding(),
                    bottom = pad.calculateBottomPadding(),
                )
                .fillMaxWidth(),
            content = {
                items(
                    items = myCategoryList,
                    itemContent = { item ->
                        CategoryCard(item)
                        Spacer(modifier = Modifier
                            .height(dimensionResource(id = R.dimen.dp12)))
                    }
                )
            }
        )
    }
}