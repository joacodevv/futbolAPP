package com.example.adad

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiClient {
    @GET("67abac6fad19ca34f8ffbec6")

    suspend fun getQuestions(): JsonResponse

    companion object {

        private const val BASE_URL = "https://api.jsonbin.io/v3/b/"

        fun getRetrofit(): ApiClient {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiClient::class.java)
        }
    }

}