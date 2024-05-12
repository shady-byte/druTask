package com.example.drutask.data.dataSources.localDataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.drutask.data.model.dataModel.Movie

@Database(entities = [Movie::class], version = 4, exportSchema = false)
abstract class MoviesDatabase: RoomDatabase() {
    abstract val databaseDao: DatabaseDao
}