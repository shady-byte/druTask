package com.example.drutask.data.model.dataModel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "movies_table")
data class Movie(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val movieId: Long,

    @SerializedName("title")
    val name: String,

    @SerializedName("release_date")
    val productionDate: String,

    @SerializedName("vote_average")
    val rating: Double,

    @SerializedName("overview")
    val description: String,

    @SerializedName("poster_path")
    var imageUrl: String,

    var category: String? = null
): Serializable
