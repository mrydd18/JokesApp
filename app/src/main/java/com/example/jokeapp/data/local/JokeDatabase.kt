package com.example.jokeapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jokeapp.data.local.entity.JokeDao
import com.example.jokeapp.data.local.entity.JokeDbo

@Database(entities = [JokeDbo::class], version = 1)
abstract class JokeDatabase : RoomDatabase() {

    abstract val jokeDao: JokeDao

    companion object {
        fun create(context: Context): JokeDatabase {
            return Room.databaseBuilder(
                context = context,
                JokeDatabase::class.java,
                "JokeDataBase"
            ).build()
        }
    }
}