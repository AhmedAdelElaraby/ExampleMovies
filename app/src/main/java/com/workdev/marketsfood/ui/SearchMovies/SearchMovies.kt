package com.workdev.marketsfood.ui.SearchMovies

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mg_group.womniz.ResponseDataClass.SealedClass.ApiStateMovies
import com.workdev.marketsfood.R
import com.workdev.marketsfood.databinding.FragmentSearchMoviesBinding
import com.workdev.marketsfood.model.Database.ModelRoom
import com.workdev.marketsfood.model.Movies.Results
import com.workdev.marketsfood.ui.Controll.Utils.AdapterAllMovies
import com.workdev.marketsfood.ui.Favorites.ViewModel.FavoritesViewModel
import com.workdev.marketsfood.ui.HomeMovies.Utils.OnClickIdMovies
import com.workdev.marketsfood.utils.Const
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchMovies : Fragment(),OnClickIdMovies {
    lateinit var binding: FragmentSearchMoviesBinding
    var page = 1
    val viewModelMovies: SearchMoviesViewModel by viewModels()
    private val viewModel: FavoritesViewModel by viewModels()
    val array=ArrayList<Results>()
    val adapters = AdapterAllMovies(this)
    var textsearch: String = ""
    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchMoviesBinding.inflate(inflater, container, false)
//        viewModelMovies.SearchMovies(Const.TokenString,page)
        val layoutManager = GridLayoutManager(context, 2)

        binding.recyclerView.layoutManager=layoutManager
        binding.recyclerView.adapter = adapters


        binding.editTextSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Perform search with the entered query
                // Show results or perform actions based on the query
                // Toast.makeText(this@MainActivity, "Search submitted: $query", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Handle partial searches or filtering as user types

                    textsearch = newText
                    viewModelMovies.SearchMovies(Const.TokenString, page, newText)


                return false
            }
        })

        binding.nested.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            // on scroll change we are checking when users scroll as bottom.
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                // in this method we are incrementing page number,
                // making progress bar visible and calling get data method.
                page++
                viewModelMovies.SearchMovies(Const.TokenString, page, textsearch)


            }
        })



        viewModelMovies.SearchMoviesLiveData.observe(this@SearchMovies.requireActivity()) {
            when (it) {
                is ApiStateMovies.Loading -> {

                }

                is ApiStateMovies.Failure -> {

                    Toast.makeText(context, "" + it.e?.status_message, Toast.LENGTH_LONG).show()
                }

                is ApiStateMovies.Success -> {

                it.data.results!!.forEach {
                    array.add(it)
                }
                    adapters.differ.submitList(array)
                    adapters.notifyDataSetChanged()


                }

                is ApiStateMovies.Empty -> {


                }

            }


        }
        return binding.root
    }

    override fun savetodatabase(name: String, image: String, id: Int,iddatabase:Int,vote:Int) {
        val data= ModelRoom(name,image,id,iddatabase.toDouble(),vote)
        viewModel.insert(data)
        Toast.makeText(context,"Saved..",Toast.LENGTH_LONG).show()

    }

    override fun ShowDetilseMovies(id: Int) {
        val bind=Bundle()
        bind.putInt("movie_id",id)
        findNavController().navigate(R.id.action_searchMovies_to_detilseMovies,bind)
    }
}