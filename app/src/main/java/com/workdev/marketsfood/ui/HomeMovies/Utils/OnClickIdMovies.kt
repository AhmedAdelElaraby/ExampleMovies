package com.workdev.marketsfood.ui.HomeMovies.Utils

import android.media.Image

interface OnClickIdMovies {
    fun savetodatabase(name:String,image:String,id:Int,iddatabase:Int,vote:Int)

    fun ShowDetilseMovies(id:Int)

}