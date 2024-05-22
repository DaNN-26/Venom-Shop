package com.example.venomshop


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.venomshop.data.DataSource
import com.example.venomshop.data.DataSource.clothes
import com.example.venomshop.model.Clothes

enum class VenomShopScreen {
    Start,
    Clothing
}

@Composable
fun ShopScreen(
    shopViewModel: ShopViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val shopUiState by shopViewModel.uiState.collectAsState()
    NavHost(
        navController = navController,
        startDestination = VenomShopScreen.Start.name
        ) {
        composable(route = VenomShopScreen.Start.name) {
            ShopColumn(onClick = {
                shopViewModel.addClothing(it)
                navController.navigate(route = VenomShopScreen.Clothing.name)
            })
        }
        composable(route = VenomShopScreen.Clothing.name) {
            ClothingScreen(shopUiState.clothing)
        }
    }
}

@Composable
fun ShopColumn(
    onClick: (Clothes) -> Unit
) {
    Surface(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            //modifier = Modifier.background(Color(104, 136, 193, 90))
        ) {
            items(clothes) {
                ClothesCard(
                    clothes = it,
                    onClick = { onClick(it) })
            }
        }
    }
}

@Composable
fun ClothesCard(
    clothes : Clothes,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        elevation = CardDefaults.cardElevation(12.dp),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .clickable { onClick() }
    ) {
        Box(
//            modifier = Modifier.background(brush = Brush.horizontalGradient(
//                colors = listOf(
//                    Color.White,
//                    Color(184, 226, 255, 240),
//                )
//            ),
//                alpha = 0.9f
//            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .size(height = 200.dp, width = 200.dp)
            ) {
                Image(
                    painter = painterResource(id = clothes.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(135.dp)
                        //.shadow(6.dp)
                )
                Text(
                    text = stringResource(id = clothes.brand),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                )
                Text(
                    text = "${clothes.price} ₽",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier
                            .size(18.dp)
                            .padding(2.dp)
                    )
                    Text(
                        text = clothes.grade.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(end = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview (showSystemUi = false)
@Composable
fun ShopPreview() {
}