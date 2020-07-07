package com.sofvision.kmp.mobile.data.local

import com.squareup.sqldelight.db.SqlDriver

expect object JokesDatabaseDriver {
    fun getDriver(): SqlDriver
}