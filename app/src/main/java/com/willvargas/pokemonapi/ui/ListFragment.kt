package com.willvargas.pokemonapi.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.willvargas.pokemonapi.databinding.FragmentListBinding
import com.willvargas.pokemonapi.model.Result
import com.willvargas.pokemonapi.model.PokemonList
import com.willvargas.pokemonapi.server.PokemonListService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater,container,false)
        val root: View= binding.root

        pokemonAdapter= PokemonAdapter(onItemClicked = {onPokemonItemCliked(it)})

        binding.recyclerViewPokemon.apply{
          layoutManager = LinearLayoutManager(this@ListFragment.context)
          adapter = pokemonAdapter
            setHasFixedSize(false)
        }
        loadPokemon()
        return root
    }

    private fun loadPokemon() {
        val apikey = ""
        PokemonListService.create()
            .getListaPokemon()
            .enqueue(object: Callback<PokemonList> {
                override fun onFailure(call: Call<PokemonList>, t:Throwable){
                    Log.d("Error",t.message.toString())
                }

                override fun onResponse(call: Call<PokemonList>, response: Response<PokemonList>) {
                    if (response.isSuccessful) {
                        var listaPokemon: MutableList<Result> = response.body()?.results as MutableList<Result>
                        pokemonAdapter.appendItems(listaPokemon)
                    }
                }
            })
    }

    private fun onPokemonItemCliked(pokemon: Result) {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(pokemon))
    }
}