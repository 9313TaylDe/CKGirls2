import androidx.lifecycle.ViewModel
import com.example.ckgirls.app.data.remote.ProductResponse
import com.example.ckgirls.app.data.remote.Products

class ProductsRepository {
    suspend fun carregarTodosProdutos(): List<Products> {
        return Api.apiResponse.getAllProducts()
    }
}