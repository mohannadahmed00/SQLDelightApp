package org.example.project.database

import org.example.database.HafsDatabase

class DatabaseManager(private val driverFactory: DatabaseDriverFactory) {

    private var database: HafsDatabase? = null
    private var repository: AyatRepository? = null

    suspend fun initialize() {
        val driver = driverFactory.createDriver()
        database = HafsDatabase(driver)
        repository = AyaRepositoryImpl(database!!)
    }

    fun getRepository(): AyatRepository {
        return repository ?: throw IllegalStateException("Database not initialized")
    }
}