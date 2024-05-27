package com.workdev.marketsfood.ui.Favorites.View

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.workdev.marketsfood.R
import com.workdev.marketsfood.databinding.FragmentFavoritesBinding
import com.workdev.marketsfood.model.Database.ModelRoom
import com.workdev.marketsfood.ui.Favorites.ViewModel.FavoritesViewModel
import com.workdev.marketsfood.ui.Favorites.Utils.AdapterAllFavorites
import com.workdev.marketsfood.ui.Favorites.Utils.OnClickDetilse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint

class Favorites : Fragment() ,OnClickDetilse{

    lateinit var  binding:FragmentFavoritesBinding

    private val viewModel: FavoritesViewModel by viewModels()
    val adapters= AdapterAllFavorites(this)
        var array=ArrayList<ModelRoom>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
       binding=FragmentFavoritesBinding.inflate(inflater, container, false)

        binding.recyclerView.apply {
            layoutManager=GridLayoutManager(context,2)
            adapter=adapters
        }

        // Observe all items
        lifecycleScope.launch {
            viewModel.getAll().collect { items ->
                items.forEach {
                    array.add(it)

                }
                adapters.differ.submitList(array)

            }
        }

        binding.editTextSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Perform search with the entered query
                // Show results or perform actions based on the query
                // Toast.makeText(this@MainActivity, "Search submitted: $query", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Handle partial searches or filtering as user types

               finditem(newText)



                return false
            }
        })




        return binding.root
    }

    private fun finditem(newText: String) {
        if (newText!=null){
            val list=ArrayList<ModelRoom>()
            for (i in array){
                if (i.name.toLowerCase(Locale.ROOT).contains(newText)){
                    list.add(i)
                }
            }
            if (list.isEmpty()){
                Toast.makeText(context,"is Empty",Toast.LENGTH_LONG).show()
            }
            else{

                adapters.differ.submitList(list)
                adapters.notifyDataSetChanged()
            }

        }

    }

    override fun ShowDetilseMovies(id: Int) {
        val bind=Bundle()
        bind.putInt("movie_id",id)
        findNavController().navigate(R.id.action_favorites_to_detilseMovies,bind)

    }


}