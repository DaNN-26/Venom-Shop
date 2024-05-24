package com.example.venomshop.data

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class Screen(
    val icon: ImageVector,
    @StringRes
    val title: Int,
)
