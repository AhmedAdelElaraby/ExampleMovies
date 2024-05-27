package com.workdev.marketsfood.ui.HomeMovies

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mg_group.womniz.ResponseDataClass.SealedClass.ApiStateMovies
import com.workdev.marketsfood.R
import com.workdev.marketsfood.databinding.FragmentHomeMoviesBinding
import com.workdev.marketsfood.model.Database.ModelRoom
import com.workdev.marketsfood.model.Movies.Results
import com.workdev.marketsfood.ui.Controll.Utils.AdapterAllMovies
import com.workdev.marketsfood.ui.Favorites.ViewModel.FavoritesViewModel
import com.workdev.marketsfood.ui.HomeMovies.Utils.OnClickIdMovies
import com.workdev.marketsfood.utils.Const
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeMovies : Fragment() ,OnClickIdMovies {
    lateinit var binding: FragmentHomeMoviesBinding
    var page=1
    val viewModelMovies : HomeMoviesViewModel by viewModels()
    private val viewModel: FavoritesViewModel by viewModels()

    val adapters= AdapterAllMovies(this)
    val array=ArrayList<Results>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding=FragmentHomeMoviesBinding.inflate(inflater, container, false)
        viewModelMovies.Movies(Const.TokenString,page)
        val layoutManager=GridLayoutManager(context,2)
        binding.recyclerView.layoutManager=layoutManager
        binding.recyclerView.adapter=adapters

        binding.searchIcon.setOnClickListener {
            findNavController().navigate(R.id.action_homeMovies_to_searchMovies)
        }

        binding.saveIcon.setOnClickListener {
            findNavController().navigate(R.id.action_homeMovies_to_favorites)
        }




       binding.nested.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            // on scroll change we are checking when users scroll as bottom.
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                // in this method we are incrementing page number,
                // making progress bar visible and calling get data method.
                page++

                viewModelMovies.Movies(Const.TokenString,page)

            }
        })




    viewModelMovies.MoviesLiveData.observe(this@HomeMovies.requireActivity()){
            when(it){
                is ApiStateMovies.Loading ->{

                }
                is ApiStateMovies.Failure ->{

                    Toast.makeText(context,""+it.e?.status_message, Toast.LENGTH_LONG).show()
                }
                is ApiStateMovies.Success ->{
                    it.data.results!!.forEach{
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
        val data= ModelRoom(name,image,iddatabase,vote.toDouble(),id)
        viewModel.insert(data)
        Toast.makeText(context,"Saved..",Toast.LENGTH_LONG).show()

    }

    override fun ShowDetilseMovies(id: Int) {

        val bind=Bundle()
        bind.putInt("movie_id",id)
        findNavController().navigate(R.id.action_homeMovies_to_detilseMovies,bind)

    }
}