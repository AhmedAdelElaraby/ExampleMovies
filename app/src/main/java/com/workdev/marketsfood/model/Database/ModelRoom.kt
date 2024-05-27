package com.workdev.marketsfood.model.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "movies")
data class ModelRoom(
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "idmovies")
    var idmovies: Int,

    @ColumnInfo(name = "vote")
    var vote: Double ,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int=1


)
{



}
