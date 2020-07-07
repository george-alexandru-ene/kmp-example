package com.sofvision.kmp.presentation

import android.app.Application
import com.sofvision.kmp.mobile.data.local.JokesDatabaseDriver.context

class JokesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }
}