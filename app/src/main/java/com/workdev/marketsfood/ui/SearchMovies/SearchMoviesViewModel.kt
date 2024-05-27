package com.workdev.marketsfood.ui.SearchMovies

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
class SearchMoviesViewModel @Inject constructor(private val mainRepository: MainRepository):ViewModel(){

    private var SearchMovies: MutableLiveData<ApiStateMovies> = MutableLiveData<ApiStateMovies>(
        ApiStateMovies.Empty)

    var SearchMoviesLiveData: LiveData<ApiStateMovies> = SearchMovies


    fun SearchMovies(Token: String, Page:Int,Search:String){
        viewModelScope.launch {
            SearchMovies.value = ApiStateMovies.Loading
            mainRepository.search (Token,Page,Search)
                .catch {e ->
                    SearchMovies.value =  when(e){
                        is HttpException -> ApiStateMovies.Failure(convertErrorBody(e))
                        else -> ApiStateMovies.Failure(ErrorResponse(0,e.message?:"",false))
                    }
                }.collect{data ->

                    SearchMovies.value = ApiStateMovies.Success(data)





                }
        }


    }
}