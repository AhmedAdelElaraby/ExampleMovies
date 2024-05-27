package com.mg_group.womniz.ResponseDataClass.SealedClass

import com.workdev.marketsfood.model.Error.ErrorResponse
import com.workdev.marketsfood.model.Movies.MoviesData


sealed class ApiStateMovies {
    object Loading : ApiStateMovies()
    class  Success(val data: MoviesData) : ApiStateMovies()
     class Failure(val e: ErrorResponse?) : ApiStateMovies()
   object Empty : ApiStateMovies()

}
