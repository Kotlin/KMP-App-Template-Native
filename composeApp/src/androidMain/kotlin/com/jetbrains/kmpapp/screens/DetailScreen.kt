package com.jetbrains.kmpapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jetbrains.kmpapp.data.MuseumObject
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(objectId: Int, navigateBack: () -> Unit) {
    val viewModel: DetailViewModel = koinViewModel()
    val obj = viewModel.getObject(objectId).collectAsState(initial = null).value
    if (obj != null) {
        ObjectDetails(obj, navigateBack)
    }
}

@Composable
private fun ObjectDetails(
    obj: MuseumObject,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.White) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, "Back")
                }
            }
        },
        modifier = modifier,
    ) { paddingValues ->
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            AsyncImage(
                model = obj.primaryImageSmall,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
            )

            SelectionContainer {
                Column(Modifier.padding(12.dp)) {
                    Text(obj.title, style = MaterialTheme.typography.h5)
                    Text(obj.artistDisplayName, style = MaterialTheme.typography.h6)

                    Text(
                        obj.objectDate,
                        fontStyle = FontStyle.Italic,
                        style = MaterialTheme.typography.subtitle1
                    )

                    Spacer(Modifier.height(12.dp))

                    LabeledInfo("Dimensions", obj.dimensions)
                    LabeledInfo("Medium", obj.medium)
                    LabeledInfo("Department", obj.department)
                    LabeledInfo("Repository", obj.repository)
                    LabeledInfo("Credits", obj.creditLine)
                }
            }
        }
    }
}

@Composable
private fun LabeledInfo(
    label: String,
    data: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier.padding(vertical = 4.dp)) {
        Text(label, style = MaterialTheme.typography.subtitle2)
        Spacer(Modifier.height(2.dp))
        Text(data, style = MaterialTheme.typography.body1)
    }
}
