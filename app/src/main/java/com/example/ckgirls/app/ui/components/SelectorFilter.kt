package com.example.ckgirls.app.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.ckgirls.app.data.remote.Category
import com.example.ckgirls.app.data.remote.Products
import com.example.ckgirls.app.data.services.ViewModelService
import com.example.ckgirls.app.ui.colors.InputDark
import com.example.ckgirls.app.ui.colors.InputLight

@Composable
fun SelectorFiolter(
    viewModelService: ViewModelService = ViewModelService(),
    filtrarProductos: (Int) -> Unit
) {
    val products = viewModelService.categorys
    var selected by remember { mutableStateOf(false) }
    var nome by remember { mutableStateOf("") }
    val carregando = viewModelService.isloading
    val error = viewModelService.erros
    var selectedProducts by remember { mutableStateOf<Category?>(null) }
    val focusManager = LocalFocusManager.current

    Box() {

            IconButton(
                onClick = { selected = true },
            ) {
                Icon(
                    imageVector = Icons.Default.FilterAlt, "",
                    tint = InputLight,
                    modifier = Modifier.size(60.dp).zIndex(zIndex = 10f)
                )
            }

        DropdownMenu(
            expanded = selected, onDismissRequest = {
                selected = false
            }
        ) {

            DropdownMenuItem(
                text = { Text("Todos") }, onClick = {
                    nome = "Todos"
                    selectedProducts = null
                    filtrarProductos(0)
                    selected = false
                    focusManager.clearFocus()
                }
            )

            if (error != null) {
                DropdownMenuItem(text = { Text(error) }, onClick = { selected = false })
            }
            if (!carregando && error == null) {
                products.forEach { prods ->
                    DropdownMenuItem(text = { Text(prods.name) }, onClick = {
                        filtrarProductos(prods.id)
                        selectedProducts = prods
                        nome = prods.name
                        selected = false
                        focusManager.clearFocus()
                    })
                }
            }
        }
    }
}




