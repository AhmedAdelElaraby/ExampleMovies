package com.workdev.marketsfood.ui.HomeMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class HomeMoviesViewModel @Inject constructor(private val mainRepository: MainRepository):ViewModel(){
    //for testing purposes we make the following variable access modifier to public
    var Movies: MutableLiveData<ApiStateMovies> = MutableLiveData<ApiStateMovies>(
        ApiStateMovies.Empty)

    var MoviesLiveData: LiveData<ApiStateMovies> = Movies


    fun Movies(Token: String, Page:Int){
        viewModelScope.launch {
            Movies.value = ApiStateMovies.Loading
            mainRepository.Movies (Token,Page)
                .catch {e ->
                    Movies.value =  when(e){
                        is HttpException -> ApiStateMovies.Failure(convertErrorBody(e))
                        else -> ApiStateMovies.Failure(ErrorResponse(0,e.message?:"",false))
                    }
                }.collect{data ->

                    Movies.value = ApiStateMovies.Success(data)





                }
        }


    }
}