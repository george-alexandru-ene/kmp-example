package com.sofvision.kmp.mobile.data.local


import com.softvision.kmp.JokesDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual object JokesDatabaseDriver {
    actual fun getDriver(): SqlDriver = NativeSqliteDriver(JokesDatabase.Schema, "jokes.db")
}