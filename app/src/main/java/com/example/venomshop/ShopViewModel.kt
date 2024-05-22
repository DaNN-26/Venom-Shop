package com.example.venomshop

import androidx.lifecycle.ViewModel
import com.example.venomshop.data.DataSource.clothes
import com.example.venomshop.data.ShopState
import com.example.venomshop.model.Clothes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ShopViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ShopState(clothing = clothes[0]))
    val uiState: StateFlow<ShopState> = _uiState.asStateFlow()

    fun addClothing(currentClothing: Clothes) {
        _uiState.update {
            it.copy(
                clothing = currentClothing
            )
        }
    }
}