package com.example.ckgirls.app.data.remote

data class Products(
    val price: Float,
    val slug:String,
    val id:Int,
    val description:String,
    val title:String,
    val images:List<String>,
    val category:Category
)
