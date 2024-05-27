package com.workdev.marketsfood.ui.DetilseMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mg_group.womniz.ResponseDataClass.SealedClass.ApiStateDetilseMovie
import com.mg_group.womniz.ResponseDataClass.SealedClass.ApiStateMovies
import com.workdev.marketsfood.Repository.MainRepository
import com.workdev.marketsfood.Repository.convertErrorBody
import com.workdev.marketsfood.model.Error.ErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject
@HiltViewModel
class DetilseMoviesViewModel @Inject constructor(private val mainRepository: MainRepository):ViewModel(){

    private var DetilseMovie: MutableLiveData<ApiStateDetilseMovie> = MutableLiveData<ApiStateDetilseMovie>(
        ApiStateDetilseMovie.Empty)

    var DetilseMovieLiveData: LiveData<ApiStateDetilseMovie> = DetilseMovie


    fun DetilseMovie(Token: String, Path:Int){
        viewModelScope.launch {
            DetilseMovie.value = ApiStateDetilseMovie.Loading
            mainRepository.Detilse (Token,Path)
                .catch {e ->
                    DetilseMovie.value =  when(e){
                        is HttpException -> ApiStateDetilseMovie.Failure(convertErrorBody(e))
                        else -> ApiStateDetilseMovie.Failure(ErrorResponse(0,e.message?:"",false))
                    }
                }.collect{data ->

                    DetilseMovie.value = ApiStateDetilseMovie.Success(data)





                }
        }


    }




}