package com.mg_group.womniz.ResponseDataClass.SealedClass

import com.workdev.marketsfood.model.Database.ModelRoom


sealed class ApiStateDatabase {
    object Loading : ApiStateDatabase()
    class  Success(val data: List<ModelRoom>) : ApiStateDatabase()
     class Failure(val e: String?) : ApiStateDatabase()
   object Empty : ApiStateDatabase()

}
