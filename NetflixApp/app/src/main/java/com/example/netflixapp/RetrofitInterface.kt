package com.example.netflixapp

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitInterface {
    @GET(".")
    suspend fun getMovieList(): Response<ArrayList<MovieModel>>

    companion object {

        private val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor {
                    val builder = it.request().newBuilder()
                    builder.header("x-rapidapi-key", "4cb24d0259msh736502921b24d65p164047jsn96daf4504eed")
                    builder.header("x-rapidapi-host", "imdb-top-100-movies.p.rapidapi.com")

                    return@Interceptor it.proceed(builder.build())
                }
            )
        }.build()

        private const val BASE_URL= "https://imdb-top-100-movies.p.rapidapi.com/"

        fun getInstance():RetrofitInterface {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(RetrofitInterface::class.java)
        }

    }

}














