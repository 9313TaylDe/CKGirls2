package com.example.ckgirls.app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

enum class Menu(val valor:String, val icon: ImageVector, val rota:String?) {
    HOME("HOME", icon = Icons.Default.Home, rota = "home"),
    ABOUT("ABOUT", icon = Icons.Default.Info, rota = null),
    ACCOUNT("ACCOUNT", icon = Icons.Default.Person,rota = null),
    FILTERPRODUCTS("FILTER", icon = Icons.Default.FilterAlt,rota = null)
}