package com.example.jokeapp.data.remote

import com.example.jokeapp.data.remote.service.JokesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {


    private const val BASE_URL = "https://official-joke-api.appspot.com/"


    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val jokesService: JokesService = retrofit.create(JokesService::class.java)

}