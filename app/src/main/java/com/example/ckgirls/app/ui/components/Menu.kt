package com.example.ckgirls.app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

enum class Menu(val valor:String, val icon: ImageVector) {
    HOME("HOME", icon = Icons.Default.Home),
    ABOUT("ABOUT", icon = Icons.Default.Info),
    ACCOUNT("ACCOUNT", icon = Icons.Default.Person)

}