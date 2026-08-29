import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.ckgirls.app.data.remote.Products
import com.example.ckgirls.app.data.services.ViewModelService
import com.example.ckgirls.app.ui.components.MenuMobile
import androidx.compose.ui.window.PopupProperties
import com.example.ckgirls.app.ui.colors.InputLight
import com.example.ckgirls.app.ui.colors.MoneyGreen
import com.example.ckgirls.app.ui.components.SelectorFiolter

@Composable
fun HomeScreenUi(
    homeViewModel: ViewModelService = viewModel()
) {
    val products = homeViewModel.products
    var carregando = homeViewModel.isloading
    val categorias = homeViewModel.categorys
    val erros = homeViewModel.erros


    var nome by remember {
        mutableStateOf("")
    }
    var pesuisarProdutoDaCategoria by remember {
        mutableStateOf("")
    }

    var selectedProducts by remember {
        mutableStateOf<Products?>(null)
    }


    var showSugestions by remember {
        mutableStateOf(false)
    }

    val focusManager = LocalFocusManager.current
    val filtereds = products.filter { produto ->
        produto.title.contains(
            nome.trim(),
            ignoreCase = true
        )
    }
    var filteredActivate by remember { mutableStateOf(false) }

    var categoriaSelecionada by remember { mutableStateOf<Int?>(null) }

    val produtosPesquisados = if (categoriaSelecionada == null || categoriaSelecionada == 0) {
        products
    } else {
        products.filter { produto ->
            produto.category.id == categoriaSelecionada
        }
    }

    val pesquisaDeProdutosPelaCategoria = produtosPesquisados.filter { produto ->
        produto.title.contains(nome.trim(), ignoreCase = true)
    }

    LaunchedEffect(Unit) {
        homeViewModel.carregarTodosProdutos()
        homeViewModel.carregarTodasAsCategorias()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(10.dp)
    ) {

        if (carregando) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                CircularProgressIndicator()
            }

        } else {


            MenuMobile(filtered = {}) {

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.86f)
                        ) {
                            TextField(
                                value = nome,

                                onValueChange = { novoTexto ->
                                    nome = novoTexto
                                    selectedProducts = null
                                    showSugestions = novoTexto.isNotEmpty()

                                },

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),

                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = InputLight,
                                    unfocusedContainerColor = InputLight,

                                    focusedTextColor = Color.Gray,
                                    unfocusedTextColor = Color.Gray,

                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,

                                    focusedLabelColor = Color.Gray,
                                    unfocusedLabelColor = Color.Gray
                                ),


                                label = {
                                    Text("Pesquisar produto")
                                },

                                singleLine = true,

                                )


                            DropdownMenu(
                                expanded = showSugestions && produtosPesquisados.isNotEmpty(),

                                onDismissRequest = {
                                    showSugestions = false
                                },
                                properties = PopupProperties(
                                    focusable = false
                                ),

                                modifier = Modifier.fillMaxWidth()
                            ) {
                                produtosPesquisados
                                    .take(5)
                                    .forEach { produto ->

                                        DropdownMenuItem(
                                            text = {
                                                Text(produto.title)
                                            },

                                            onClick = {
                                                if (categoriaSelecionada == 0) {
                                                    nome = ""
                                                } else {
                                                    nome = produto.title
                                                }
                                                selectedProducts = produto
                                                showSugestions = false

                                                focusManager.clearFocus()
                                            }
                                        )
                                    }
                            }
                        }
                        SelectorFiolter(
                            viewModelService = homeViewModel,
                            filtrados = {
                                    pesquisaDeProdutosPelaCategoria
                            },
                            filtrarProductos = { filtered ->
                                categoriaSelecionada = filtered
                                selectedProducts = null
                                nome =""
                                showSugestions = false
                            })
                    }


                    val productosParaExbir = when {

                        selectedProducts != null -> {
                            listOf(selectedProducts!!)
                        }

                        nome.isNotEmpty() -> {
                            pesquisaDeProdutosPelaCategoria
                        }

                        else -> {
                            produtosPesquisados

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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = 180.dp, max = 300.dp)
                            ) {

                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {

                                    AsyncImage(
                                        model = prods.images.firstOrNull(),

                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(
                                                RoundedCornerShape(
                                                    topStart = 14.dp,
                                                    topEnd = 14.dp
                                                )
                                            ),

                                        contentDescription = prods.title
                                    )
                                }

                                Text(
                                    text = prods.title,
                                    maxLines = 2,
                                    minLines = 2,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(4.dp)
                                )

                                Text(
                                    text = "R$ %.2f".format(prods.price),
                                    maxLines = 1,
                                    minLines = 1,
                                    fontSize = 15.sp,
                                    color = MoneyGreen,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}