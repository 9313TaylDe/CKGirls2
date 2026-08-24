import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.ckgirls.app.data.remote.Products
import com.example.ckgirls.app.data.views.HomeViewModel
import com.example.ckgirls.app.ui.components.MenuMobile

@Composable
fun HomeScreenUi(
    homeViewModel: HomeViewModel = viewModel()
) {
    val produtos = homeViewModel.products
    val carregando = homeViewModel.carregando

    var nome by remember {
        mutableStateOf("")
    }

    var selectedProducts by remember {
        mutableStateOf<Products?>(null)
    }

    var showSugestions by remember {
        mutableStateOf(false)
    }

    val focusManager = LocalFocusManager.current

    val filtereds = produtos.filter { produto ->
        produto.title.contains(
            nome,
            ignoreCase = true
        )
    }

    LaunchedEffect(Unit) {
        homeViewModel.carregarTodosProdutos()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        if (carregando) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                CircularProgressIndicator()
            }

        } else {

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                MenuMobile(filtered = {}) {

                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {

                      
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {

                            TextField(
                                value = nome,

                                onValueChange = { novoTexto ->

                                    nome = novoTexto

                                    // Ao digitar uma nova pesquisa,
                                    // remove o produto selecionado anteriormente
                                    selectedProducts = null

                                    showSugestions =
                                        novoTexto.isNotEmpty()
                                },

                                modifier = Modifier
                                    .fillMaxWidth(),

                                label = {
                                    Text("Pesquisar produto")
                                },

                                singleLine = true
                            )

                         
                            DropdownMenu(
                                expanded = showSugestions &&
                                        filtereds.isNotEmpty(),

                                onDismissRequest = {
                                    showSugestions = false
                                },

                                modifier = Modifier.fillMaxWidth()
                            ) {

                                filtereds
                                    .take(5)
                                    .forEach { produto ->

                                        DropdownMenuItem(

                                            text = {
                                                Text(
                                                    text = produto.title
                                                )
                                            },

                                            onClick = {

                                                nome = produto.title

                                                selectedProducts = produto

                                                showSugestions = false

                                                focusManager.clearFocus()
                                            }
                                        )
                                    }
                            }
                        }

                    
                        val productosParaExbir = when {

                            selectedProducts != null -> {
                                listOf(selectedProducts!!)
                            }

                            nome.isNotEmpty() -> {
                                filtereds
                            }

                            else -> {
                                produtos
                            }
                        }

                      
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),

                            horizontalArrangement = Arrangement.spacedBy(
                                8.dp
                            ),

                            verticalArrangement = Arrangement.spacedBy(
                                8.dp
                            ),

                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp)
                        ) {

                            items(productosParaExbir) { prods ->

                                Card(
                                    modifier = Modifier.fillMaxWidth()
                                ) {

                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {

                                        AsyncImage(
                                            model = prods.images.firstOrNull(),

                                            modifier = Modifier.size(120.dp),

                                            contentDescription = prods.title
                                        )
                                    }

                                    Text(
                                        text = prods.title,

                                        maxLines = 2,

                                        minLines = 2,

                                        fontSize = 10.sp,

                                        textAlign = TextAlign.Center,

                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(4.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}