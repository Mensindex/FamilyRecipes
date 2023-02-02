package com.example.familyrecipes.ui.screens.adding_a_recipe.components

import android.app.Activity
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.familyrecipes.R
import com.example.familyrecipes.data.models.RecipeImage
import com.example.familyrecipes.ui.theme.Heather
import com.example.familyrecipes.ui.theme.Shapes
import com.example.familyrecipes.ui.theme.SoftPeach
import com.example.familyrecipes.ui.theme.Typography

@Preview(showBackground = true)
@Composable
fun PlaceholderPreview() {
    RecipeImage({}, null)
}

@Composable
fun RecipeImage(
    addImageUriCallBack: (image: RecipeImage) -> Unit,
    image: RecipeImage?,
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri.let { uRi ->
            uRi?.toBitmap(
                contentResolver = (context as Activity).contentResolver
            )?.let { bitmap ->
                addImageUriCallBack(RecipeImage(uRi, bitmap))
            }
        }
    }
    Box(
        modifier = Modifier
            .clip(Shapes.medium)
            .height(242.dp)
            .fillMaxWidth()
            .background(SoftPeach)
            .clickable {
                launcher.launch("image/*")
            }
    ) {

        if (image == null) {
            Image(
                painter = painterResource(id = R.drawable.placeholder),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
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

        } else {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                bitmap = image.bitmap.asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

        }
    }
}