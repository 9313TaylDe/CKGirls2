package com.example.ckgirls.app.data.remote

data class ProductsRequest(
    val title: String,
    val price: Float,
    val description: String,
    val categoryId: Int,
    val images: List<String>
)