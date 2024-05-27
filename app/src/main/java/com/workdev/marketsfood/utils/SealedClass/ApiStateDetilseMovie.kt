package com.mg_group.womniz.ResponseDataClass.SealedClass

import com.workdev.marketsfood.model.DetilseMovie.Detilse
import com.workdev.marketsfood.model.Error.ErrorResponse



sealed class ApiStateDetilseMovie {
    object Loading : ApiStateDetilseMovie()
    class  Success(val data: Detilse) : ApiStateDetilseMovie()
     class Failure(val e: ErrorResponse?) : ApiStateDetilseMovie()
   object Empty : ApiStateDetilseMovie()

}
