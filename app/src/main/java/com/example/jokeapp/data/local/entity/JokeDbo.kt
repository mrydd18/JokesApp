package com.example.jokeapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "jokes_table")
data class JokeDbo (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val setup: String,
    val punchline: String

)