package com.willvargas.pokemonapi.server

import com.willvargas.pokemonapi.model.PokemonList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PokemonListService {

    @GET("pokemon/?limit=1118&offset=0")
    fun getListaPokemon(): Call<PokemonList>

    companion object{
        val URL_API = "https://pokeapi.co/api/v2/"


        fun create(): PokemonListService{
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

            return retrofit.create(PokemonListService::class.java)
        }
    }
}