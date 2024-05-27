package com.workdev.marketsfood.model.Movies

data class MoviesData(
    var page:Int? = null,
    var results:ArrayList<Results>?= ArrayList(),
    var total_pages:Int? =null,
    var total_results:Int?=null

)


data class Results(
    var adult:Boolean?=null,
    var backdrop_path:String?=null,
    var genre_ids:ArrayList<Int>? =null,
    var id:Int? =null ,
    var original_language:String? =null,
    var original_title:String? =null,
    var overview:String? =null,
    var popularity:Double? =null,
    var poster_path:String ? =null,
    var release_date: String?=null,
    var title:String? = null,
    var video :Boolean? =null,
    var vote_average:Double? =null,
    var vote_count:Int?=null
)

