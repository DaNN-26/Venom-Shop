package com.example.venomshop

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ProfileScreen(
    navController: NavController,
    selectedItem: Int
) {
    var isOpened by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = { TopBar(navController = navController) },
        bottomBar = { NavBottomBar(navController = navController, selectedItem = selectedItem) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(it)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_icon2),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .shadow(4.dp, shape = CircleShape)
                    .background(Color(46, 134, 193, 100))
                    .size(200.dp),
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = stringResource(R.string.profile_unauthorized),
                fontSize = 20.sp,
            )
            Spacer(modifier = Modifier.height(10.dp))
            ProfileButton {
                isOpened = !isOpened
            }
            TextButton(onClick = {
                isOpened = !isOpened
            }) {
                Text(
                    text = stringResource(id = R.string.profile_enter),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }
            when (isOpened) {
                true -> ProfileErrorDialog { isOpened = false }
                else -> Unit
            }
        }
    }
}
@Composable
fun ProfileButton(
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = Color(60, 54, 230)),
        elevation = ButtonDefaults.buttonElevation(12.dp),
        shape = RoundedCornerShape(46.dp),
        modifier = Modifier.size(height = 50.dp, width = 180.dp),
        ) {
        Text(
            text = stringResource(id = R.string.profile_register),
            fontSize = 18.sp
        )
    }
}

@Composable
fun ProfileErrorDialog(
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
                        Button(
                            onClick = onDismissRequest,
                            colors = ButtonDefaults.buttonColors(Color(31, 97, 141))
                        ) {
                            Text(text = stringResource(id = R.string.close_button))
                        }
        },
        title = {
            Text(text = stringResource(R.string.profile_error_title))
        },
        text = {
            Text(text = stringResource(R.string.profile_error_body))
        }
    )
}