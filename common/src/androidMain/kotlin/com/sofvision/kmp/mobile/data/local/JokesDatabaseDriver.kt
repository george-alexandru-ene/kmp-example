package com.sofvision.kmp.mobile.data.local

import android.content.Context
import com.softvision.kmp.JokesDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual object JokesDatabaseDriver {
    lateinit var context: Context

    actual fun getDriver(): SqlDriver {
        return AndroidSqliteDriver(JokesDatabase.Schema, context, "asdf.db")
    }
}

