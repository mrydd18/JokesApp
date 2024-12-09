package com.example.jokeapp

import android.app.Application
import android.content.Context

class JokesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        lateinit var context: Context
    }

}