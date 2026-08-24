import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api{
    val apiResponse = Retrofit.Builder()
        .baseUrl("https://api.escuelajs.co/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiResponse::class.java)
}