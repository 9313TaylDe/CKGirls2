package com.example.ckgirls.app.data.views

import ProductsRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ckgirls.app.data.remote.ProductResponse
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.ckgirls.app.data.remote.Products
import kotlinx.coroutines.delay

class HomeViewModel : ViewModel() {
    val repository = ProductsRepository()
    var products by mutableStateOf<List<Products>>(emptyList())
        private set
    var carregando by mutableStateOf(false)
        private set


    fun carregarTodosProdutos() {
        viewModelScope.launch {
            try {
                carregando = true
                products = repository.carregarTodosProdutos()
            } catch (e: Exception) {
                e.printStackTrace()
            }finally {
                carregando = false
            }
        }
    }
}