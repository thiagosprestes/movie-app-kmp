package com.example.database

import com.example.FavoritesDatabase

class Database(driverFactory: DriverFactory) {
    private val driver = driverFactory.createDriver()
    private val favoritesDatabase = FavoritesDatabase(driver)
    val databaseQueries = favoritesDatabase.favoritesDatabaseQueries
}