package com.example.venomshop

import android.graphics.drawable.shapes.Shape
import android.widget.Button
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.venomshop.data.DataSource
import com.example.venomshop.model.Clothes

@Composable
fun ClothingScreen(
    clothing: Clothes,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { TopBar(navController = navController ) }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Pager(
                painterList = clothing.images,
                modifier = Modifier.aspectRatio(1f)
                )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "${clothing.price} ₽",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(
                    start = 18.dp,
                    top = 8.dp
                )
            )
            Text(
                text = stringResource(id = clothing.brand),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 18.dp)
            )
            Text(
                text = stringResource(id = clothing.title),
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 18.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = modifier
                    .padding(start = 18.dp)
                    .background(
                        color = when (clothing.grade) {
                            in (3.9..5.0) -> Color(46, 204, 113)
                            in (2.5..3.8) -> Color(241, 196, 15)
                            else -> Color(231, 76, 60)
                        },
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(start = 4.dp, end = 24.dp)
            ) {
                Text(
                    text = clothing.grade.toString(),
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Start,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 18.dp)
                )
            }
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = stringResource(R.string.clothing_description),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 18.dp)
            )
            Text(
                text = stringResource(clothing.description),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp,
                modifier = Modifier.padding(18.dp)
            )
            BuyButton(
                modifier = modifier
                    .fillMaxWidth()
                    .size(height = 100.dp, width = 200.dp)
                    .padding(18.dp)
            )
        }
    }
}

@Composable
fun BuyButton(
    modifier: Modifier = Modifier
) {
    var openDialog by remember { mutableStateOf(false) }
    Button(
        onClick = { openDialog = true },
        colors = ButtonDefaults.buttonColors(containerColor = Color(46, 134, 193)),
        shape = RoundedCornerShape(10.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
        modifier = modifier
    ) {
        Text(
            stringResource(R.string.clothing_text_buy),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
    when(openDialog) {
        true -> ShopAlertDialog(
            onDismissRequest = { openDialog = false },
        )
        else -> Unit
    }
}

@Composable
fun ShopAlertDialog(
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = onDismissRequest) {
                Text(
                    text = stringResource(R.string.close_button),
                    color = Color.Black
                )
            }
        },
        title = {
            Text(text = stringResource(R.string.buy_button_unavaible))
        },
        text = {
            Text(text = stringResource(R.string.buy_button_unavaible_body))
        }
    )

}

@Preview
@Composable
fun ButtonPreview() {
}