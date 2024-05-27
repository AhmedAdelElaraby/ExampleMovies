package com.workdev.marketsfood.ui.DetilseMovies

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mg_group.womniz.ResponseDataClass.SealedClass.ApiStateDetilseMovie
import com.mg_group.womniz.ResponseDataClass.SealedClass.ApiStateMovies
import com.workdev.marketsfood.R
import com.workdev.marketsfood.databinding.FragmentDetilseMoviesBinding
import com.workdev.marketsfood.ui.DetilseMovies.Utils.AdapterType
import com.workdev.marketsfood.utils.Const
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetilseMovies : Fragment() {
    lateinit var binding: FragmentDetilseMoviesBinding
    private val viewModel: DetilseMoviesViewModel by viewModels()
    val adapters=AdapterType()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentDetilseMoviesBinding.inflate(inflater, container, false)

         val args = arguments
        val movie_id=args?.getInt("movie_id")
         viewModel.DetilseMovie(Const.TokenString, movie_id ?:0)

        binding.rec.apply {
             layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

            adapter=adapters
        }

        viewModel.DetilseMovieLiveData.observe(this@DetilseMovies.requireActivity()){
            when(it){
                is ApiStateDetilseMovie.Loading ->{

                }
                is ApiStateDetilseMovie.Failure ->{

                    Toast.makeText(context,""+it.e?.status_message, Toast.LENGTH_LONG).show()
                }
                is ApiStateDetilseMovie.Success ->{
                    binding.DataDescription.text=it.data.overview
                    binding.DataRating.text=it.data.vote_average.toString()
                    binding.DataLanguage.text=it.data.original_language
                    binding.DataLength.text=" min "+it.data.runtime/60
                    adapters.differ.submitList(it.data.genres)
                    binding.nameapp.text=it.data.title
                    binding.rate.text=it.data.vote_average.toString()






                }

                is ApiStateDetilseMovie.Empty -> {



                }

            }






        }





        return binding.root
    }
}