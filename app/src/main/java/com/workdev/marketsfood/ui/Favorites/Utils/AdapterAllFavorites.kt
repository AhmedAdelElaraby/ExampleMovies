package com.workdev.marketsfood.ui.Favorites.Utils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import com.workdev.marketsfood.databinding.ItemMoviesBinding
import com.workdev.marketsfood.model.Database.ModelRoom
import com.workdev.marketsfood.utils.Const

class AdapterAllFavorites(private var onClickDetilse: OnClickDetilse) : RecyclerView.Adapter<AdapterAllFavorites.ItemMovies>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovies {
        return ItemMovies(ItemMoviesBinding.inflate( LayoutInflater.from(parent.context),parent,false ))

    }

    override fun onBindViewHolder(holder:ItemMovies, position: Int) {

        Picasso.get().load(Const.BASE_IMAGE+differ.currentList[position].image)
            .fit().centerCrop().into(holder.binding.image)




        holder.binding.name.text=differ.currentList[position].name
     holder.binding.rate.text=differ.currentList[position].vote.toString()

        holder.binding.root.setOnClickListener {
            onClickDetilse.ShowDetilseMovies(differ.currentList[position].idmovies)
        }


    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
     class ItemMovies(var binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root)

     val differCallback = object : DiffUtil.ItemCallback<ModelRoom>(){
        override fun areItemsTheSame(oldItem: ModelRoom, newItem: ModelRoom): Boolean {
            return  oldItem.id == newItem.id
        }


        override fun areContentsTheSame(oldItem: ModelRoom, newItem: ModelRoom): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,differCallback)
}