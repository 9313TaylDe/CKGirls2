package com.example.ckgirls.app.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.launch
import com.example.ckgirls.app.ui.components.Menu

@Composable
fun MenuMobile(
    filtered: () -> Unit,
    content: @Composable () -> Unit,

    ) {
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Menu.entries.forEach { opt ->
                    NavigationDrawerItem(
                        label = { Text(opt.valor) },
                        selected = false,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                        }, icon = {
                            Icon(
                                imageVector = opt.icon, contentDescription = ""
                            )
                        }
                    )
                }
            }
        }
    ) {

        Column() {
            Row() {
                IconButton(
                    onClick = { scope.launch { drawerState.open() } },
                    modifier = Modifier
                        .statusBarsPadding()
                        .zIndex(10f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu, contentDescription = ""
                    )
                }
                IconButton(
                    onClick = { filtered() },
                    modifier = Modifier
                        .statusBarsPadding()
                        .zIndex(10f)
                ) {

                }
            }
            content()


        }
    }

}