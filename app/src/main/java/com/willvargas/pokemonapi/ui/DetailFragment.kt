package com.willvargas.pokemonapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.willvargas.pokemonapi.databinding.FragmentDetailBinding


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
                Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + item + ".png").into(binding.imageViewPokemon)
                Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/" + item + ".png").into(binding.imageView2)
                //Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + item + ".png").into(binding.imageView3)
                Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-viii/icons/" + item + ".png").into(binding.imageView3)
            }


        }

        return root
    }




}