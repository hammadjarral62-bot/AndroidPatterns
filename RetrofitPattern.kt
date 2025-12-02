// RETROFIT INSTANCE
object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://yourapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

// API SERVICE
interface ApiService {
    @GET("endpoint")
    suspend fun getData(): Response<List<YourModel>>
}

// CALL API
CoroutineScope(Dispatchers.IO).launch {
    val response = RetrofitInstance.api.getData()
    if (response.isSuccessful) {
        val data = response.body()
        withContext(Dispatchers.Main) {
            // update UI here
        }
    }
}
