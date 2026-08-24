import com.example.ckgirls.app.data.remote.Category
import com.example.ckgirls.app.data.remote.CategoryResponse
import com.example.ckgirls.app.data.remote.Products
import com.example.ckgirls.app.data.remote.ProductUpdateRequest
import com.example.ckgirls.app.data.remote.ProductsRequest
import retrofit2.http.Body
import retrofit2.http.Query
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiResponse {
    @GET("products")
    suspend fun getAllProducts(): List<Products>

    @GET("products/{id}")
    suspend fun getOneProductById(
        @Path("id") id: Int
    ): Products

    @GET("products/slug/{slug}")
    suspend fun getOneProductBySlug(
        @Path("slug") slug: String
    ): Products

    @POST("products")
    suspend fun createNewProduct(
        @Body productsRequest: ProductsRequest
    ): Products

    @GET("products")
    suspend fun getPagination(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 10
    ): List<Products>

    @GET("products/{id}/related")
    suspend fun getProductsRelatedById(
        @Path("id") id: Int,
    ): List<Products>

    @GET("products/{slug}/related")
    suspend fun getProductsRelatedBySlug(
        @Path("slug") slug: String,
    ): List<Products>

    @GET("products")
    suspend fun filterProductsByTitle(
        @Query("title") title: String
    ): List<Products>

    @GET("products")
    suspend fun filterProductsByPrice(
        @Query("price") price: Float
    ): List<Products>

    @GET("products")
    suspend fun filterProductsByPriceMinMax(
        @Query("price_min") priceMin: Float,
        @Query("price_max") priceMax: Float
    ): List<Products>

    @GET("products")
    suspend fun filterProductsByCategoryId(
        @Query("categoryId") categoryId: Int
    ): List<Products>

    @GET("products")
    suspend fun filterProductsByCategorySlug(
        @Query("categorySlug") categorySlug: String
    ): List<Products>

    @GET("products")
    suspend fun paginationProductsByCategoryPrice(
        @Query("price_min") priceMin: Float,
        @Query("price_max") priceMax: Float,
        @Query("categoryId") categoryId: Int,
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0,
    ): List<Products>

    @GET("products")
    suspend fun paginationProductsByPrice(
        @Query("price_min") priceMin: Float,
        @Query("price_max") priceMax: Float,
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0,
    ): List<Products>

    @GET("products")
    suspend fun filterProductsByAllMethods(

        @Query("title") title: String,
        @Query("price_min") priceMin: Float,
        @Query("price_max") priceMax: Float,
        @Query("categorySlug") categorySlug: String,
        @Query("categoryId") categoryId: Int,

        ): List<Products>


    @PUT("products/{id}")
    suspend fun updateOneProduct(
        @Path("id") productId: Int,
        @Body productUpdateRequest: ProductUpdateRequest
    ): Products

    @DELETE("products/{id}")
    suspend fun deleteOneProduct(
        @Path("id") productId: Int
    ): Products


//    CATEGORYS

    @GET("categories")
    suspend fun getAllCategorys(): List<Category>

    @GET("categories/{id}")
    suspend fun getCategoryById(
        @Path("id") id: Int
    ): Category

    @GET("categories/{slug}")
    suspend fun getCategoryBySlug(
        @Path("slug") slug: String
    ): Category

    @GET("categories/{id}/products")
    suspend fun getProductByCategoryId(
        @Path("id") id: Int

    ): List<Products>

    @POST("categories")
    suspend fun createNewCategory(
        @Body categoryResponse: CategoryResponse
    ): Category

    @PUT("categories/{id}")
    suspend fun updateOneCategory(
        @Path("id") id: Int,
        @Body categoryResponse: CategoryResponse
    ): Category


    @DELETE("categories/{id}")
    suspend fun deleteOneCategory(
        @Path("id") id: Int,
        @Body categoryResponse: CategoryResponse
    ): Category


}

