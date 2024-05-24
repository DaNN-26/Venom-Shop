package com.example.venomshop

import android.widget.Space
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.venomshop.data.DataSource.info

@Composable
fun InfoScreen(
    navController: NavController,
    selectedItem: Int
) {
    Scaffold(
        topBar = { TopBar(navController) },
        bottomBar = {
            NavBottomBar(
                navController = navController,
                selectedItem = selectedItem
            )
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = 18.dp
            ),
            modifier = Modifier.padding(it)
        ) {
            items(info) {
                InfoCard(
                    info = it,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InfoCard(
    info: com.example.venomshop.model.Info,
    modifier: Modifier = Modifier
) {
    var isInfoOpened by remember { mutableStateOf(false) }
    Card(
        elevation = 8.dp,
        modifier = modifier
            .fillMaxWidth()
    )
    {
        Column(
            modifier = Modifier.animateContentSize()
        ) {
            Row {
                Text(
                    text = stringResource(id = info.title),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 26.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(12.dp)
                )
                IconButton(onClick = { isInfoOpened = !isInfoOpened }) {
                    Icon(
                        imageVector = if (!isInfoOpened)
                            Icons.Filled.KeyboardArrowDown
                        else
                            Icons.Filled.KeyboardArrowUp,
                        contentDescription = null
                    )
                }
            }
            if (isInfoOpened) {
                Text(
                    text = stringResource(id = info.body),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Unspecified,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 8.dp)
                )
            }
        }
    }
}