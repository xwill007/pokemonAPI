package com.willvargas.pokemonapi.model.Pokemon


import com.google.gson.annotations.SerializedName

data class MoveLearnMethod(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)