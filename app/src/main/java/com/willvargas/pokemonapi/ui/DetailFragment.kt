package com.willvargas.pokemonapi.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.willvargas.pokemonapi.R
import com.willvargas.pokemonapi.databinding.FragmentDetailBinding
import com.willvargas.pokemonapi.model.Pokemon.Ability
import com.willvargas.pokemonapi.model.PokemonList
import com.willvargas.pokemonapi.model.Result
import com.willvargas.pokemonapi.server.PokemonListService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        val root: View= binding.root

        with(binding) {
            val pokemon = args.pokemonArg
            textViewNombre.text = pokemon.name
            textViewUrl.text = pokemon.url

            val urlPartes: Array<String> = pokemon.url.toString().split("/").toTypedArray()
            val item = urlPartes[urlPartes.size - 2].toString()

            if (pokemon.url != null) {
                Picasso.get()
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + item + ".png")
                    .into(binding.imageViewPokemon)
            }


        }

        return root
    }




}