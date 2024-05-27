package com.workdev.marketsfood.ui.Controll.Utils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.workdev.marketsfood.R

import com.workdev.marketsfood.databinding.ItemMoviesBinding

import com.workdev.marketsfood.model.Movies.Results
import com.workdev.marketsfood.ui.HomeMovies.Utils.OnClickIdMovies
import com.workdev.marketsfood.utils.Const

class AdapterAllMovies(private var onClickIdMovies: OnClickIdMovies) : RecyclerView.Adapter<AdapterAllMovies.ItemMovies>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovies {
        return ItemMovies(ItemMoviesBinding.inflate( LayoutInflater.from(parent.context),parent,false ))

    }

    override fun onBindViewHolder(holder:ItemMovies, position: Int) {
        Picasso.get().load(Const.BASE_IMAGE+differ.currentList[position].poster_path)
            .fit().centerCrop().into(holder.binding.image)



        holder.binding.name.text=differ.currentList[position].title
        holder.binding.rate.text=differ.currentList[position].vote_average.toString()


        holder.binding.save.setOnClickListener {
            onClickIdMovies.savetodatabase(differ.currentList[position].title!!,
                differ.currentList[position].poster_path!!,
                differ.currentList[position].id!!,position,differ.currentList[position].vote_average!!.toInt())
        }

        holder.binding.root.setOnClickListener {
            onClickIdMovies.ShowDetilseMovies(differ.currentList[position].id!!)
        }


    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
     class ItemMovies(var binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root)

     val differCallback = object : DiffUtil.ItemCallback<Results>(){
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return  oldItem.id == newItem.id
        }


        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,differCallback)
}