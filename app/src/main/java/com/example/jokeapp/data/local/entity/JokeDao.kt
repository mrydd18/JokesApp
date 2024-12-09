package com.example.jokeapp.data.local.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface JokeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveJoke(joke: JokeDbo)

    @Delete
    suspend fun deleteJoke(joke: JokeDbo)

    @Query("SELECT * FROM jokes_table")
    suspend fun getAllJokes(): List<JokeDbo>

}