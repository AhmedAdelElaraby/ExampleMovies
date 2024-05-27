package com.workdev.marketsfood.ui.DetilseMovies.Utils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import com.workdev.marketsfood.databinding.ItemMoviesBinding
import com.workdev.marketsfood.databinding.ItemTypeMovieBinding
import com.workdev.marketsfood.model.Database.ModelRoom
import com.workdev.marketsfood.model.DetilseMovie.Genre
import com.workdev.marketsfood.utils.Const

class AdapterType() : RecyclerView.Adapter<AdapterType.ItemMovies>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovies {
        return ItemMovies(ItemTypeMovieBinding.inflate( LayoutInflater.from(parent.context),parent,false ))

    }

    override fun onBindViewHolder(holder:ItemMovies, position: Int) {
        holder.binding.rate.text=differ.currentList[position].name





    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
     class ItemMovies(var binding: ItemTypeMovieBinding) : RecyclerView.ViewHolder(binding.root)

     val differCallback = object : DiffUtil.ItemCallback<Genre>(){
        override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return  oldItem.id == newItem.id
        }


        override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,differCallback)
}