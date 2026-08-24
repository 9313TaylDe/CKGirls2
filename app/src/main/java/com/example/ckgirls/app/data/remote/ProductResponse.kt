package com.example.ckgirls.app.data.remote

data class ProductResponse(
    val id: Int,
    val title: String,
    val slug: String,
    val price: Float,
    val description: String,
    val images: List<String>,
    val category: Category,
    val creationAt: String,
    val updatedAt: String
)

