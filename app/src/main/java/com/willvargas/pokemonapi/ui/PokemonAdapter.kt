package com.willvargas.pokemonapi.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import com.willvargas.pokemonapi.R
import com.willvargas.pokemonapi.databinding.FragmentListItemBinding
import com.willvargas.pokemonapi.model.Result

class PokemonAdapter (
    private val onItemClicked: (Result)-> Unit,
    ): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private var listaPokemon: MutableList<Result> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonAdapter.ViewHolder, position: Int) {
        holder.bind(listaPokemon[position])
        holder.itemView.setOnClickListener{onItemClicked(listaPokemon[position])}
    }

    override fun getItemCount(): Int = listaPokemon.size

    fun appendItems(newItems: MutableList<Result>){
        listaPokemon.clear()
        listaPokemon.addAll(newItems)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = FragmentListItemBinding.bind(view)
        private val context: Context = binding.root.context
        fun bind(pokemon: Result){
            with(binding){
                titleTextView.text = pokemon.name

                val urlPartes: Array<String> = pokemon.url.toString().split("/").toTypedArray()
                val item = urlPartes[urlPartes.size - 2].toString()

                textViewItem.text = item

                if(pokemon.url != null) {
                    Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + item + ".png").into(binding.pictureImageView)
                }


            }
        }
    }
}