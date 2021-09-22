package com.willvargas.pokemonapi.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Result(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?

) : Serializable