package com.example.venomshop


import androidx.annotation.StringRes
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.venomshop.data.DataSource
import com.example.venomshop.data.DataSource.clothes
import com.example.venomshop.data.DataSource.info
import com.example.venomshop.model.Clothes

enum class VenomShopScreen(
    val icon: ImageVector,
    @StringRes val title: Int
) {
    Start(
        icon = Icons.Filled.ShoppingCart,
        title = R.string.goods
    ),
    Clothing(
        icon = Icons.Filled.Star,
        title = R.string.goods
    ),
    Info(
        icon = Icons.Filled.Info,
        title = R.string.Help
    ),
    Profile(
        icon = Icons.Filled.AccountCircle,
        title = R.string.Profile
    )
}

val items = listOf(
    VenomShopScreen.Start,
    VenomShopScreen.Info,
    VenomShopScreen.Profile
)

@Composable
fun ShopScreen(
    shopViewModel: ShopViewModel = viewModel(),
) {
    val navController = rememberNavController()
    val selectedItem by remember { mutableIntStateOf(0) }
        val shopUiState by shopViewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = VenomShopScreen.Start.name,
        ) {
            composable(route = VenomShopScreen.Start.name) {
                ShopColumn(
                    onClick = { clothing ->
                        shopViewModel.addClothing(clothing)
                    navController.navigate(route = VenomShopScreen.Clothing.name)
                    },
                    selectedItem = selectedItem,
                    navController = navController
                )
            }
            composable(route = VenomShopScreen.Clothing.name) {
                ClothingScreen(shopUiState.clothing, navController)
            }
            composable(route = VenomShopScreen.Info.name) {
                InfoScreen(
                    navController = navController,
                    selectedItem = selectedItem
                )
            }
            composable(route = VenomShopScreen.Profile.name) {
                ProfileScreen(
                    navController = navController,
                    selectedItem = selectedItem
                )
            }
        }
}
@Composable
fun NavBottomBar(
    navController: NavController,
    selectedItem: Int,
) {
    var selIt = selectedItem
    BottomNavigation(
        backgroundColor = Color.White,
        elevation = 16.dp
    ) {
        items.forEachIndexed { index, screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = null,
                        tint = if (navController.currentDestination?.route == screen.name)
                            Color(46, 134, 193)
                        else
                            Color.Black,
                        modifier = Modifier.size(24.dp)
                        )
                },
                label = {
                        Text(text = stringResource(id = screen.title))
                },
                selected = selIt == index,

                onClick = {
                    selIt = index
                    when(index){
                        0 -> navController.navigate(VenomShopScreen.Start.name)
                        1 -> navController.navigate(VenomShopScreen.Info.name)
                        2 -> navController.navigate(VenomShopScreen.Profile.name)
                    }
                }

            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                fontWeight = FontWeight.Light
                )
        },
        navigationIcon = {
            if (navController.currentDestination?.route == VenomShopScreen.Clothing.name) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = null)
                }
            }
        },
        modifier = Modifier.shadow(12.dp)
    )
}

@Composable
fun ShopColumn(
    onClick: (Clothes) -> Unit,
    selectedItem: Int,
    navController: NavController
) {
    Scaffold(
        topBar = { TopBar(navController) },
        bottomBar = {
            NavBottomBar(
                selectedItem = selectedItem,
                navController = navController
            )
        }
    ) {padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(padding)
                .padding(
                    horizontal = 16.dp
                )
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
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier
            .clickable { onClick() }
            .padding(vertical = 8.dp)
    ) {
        Box() {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .size(height = 200.dp, width = 200.dp)
            ) {
                Image(
                    painter = painterResource(id = clothes.images[0]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(135.dp)
                        .shadow(2.dp)
                )
                Text(
                    text = stringResource(id = clothes.brand),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp
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

@Preview (showSystemUi = true)
@Composable
fun ShopPreview() {
    ShopScreen()
}