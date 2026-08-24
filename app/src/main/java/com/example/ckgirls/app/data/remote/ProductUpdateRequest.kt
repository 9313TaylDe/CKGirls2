package com.example.ckgirls.app.data.remote

data class ProductUpdateRequest(
    val title: String? = null,
    val price: Float? = null,
    val description: String? = null,
    val categoryId: Int? = null,
    val images: List<String>? = null
)
