package org.example.project.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import app.cash.sqldelight.driver.native.wrapConnection
import co.touchlab.sqliter.DatabaseConfiguration
import kotlinx.cinterop.ExperimentalForeignApi
import org.example.database.HafsDatabase
import platform.Foundation.NSBundle
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask

actual class DatabaseDriverFactory {

    actual suspend fun createDriver(): SqlDriver {
        copyDatabaseFromBundle()

        val documentsPath = NSSearchPathForDirectoriesInDomains(
            NSDocumentDirectory, NSUserDomainMask, true
        ).first() as String

        val databasePath = "$documentsPath/hafs_smart_v8.db"

        return NativeSqliteDriver(
            DatabaseConfiguration(
                name = databasePath,
                version = HafsDatabase.Schema.version.toInt(),
                create = { connection ->
                    wrapConnection(connection) { HafsDatabase.Schema.create(it) }
                },
                upgrade = { connection, oldVersion, newVersion ->
                    wrapConnection(connection) {
                        HafsDatabase.Schema.migrate(it, oldVersion.toLong(), newVersion.toLong())
                    }
                }
            )
        )
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun copyDatabaseFromBundle() {
        val documentsPath = NSSearchPathForDirectoriesInDomains(
            NSDocumentDirectory, NSUserDomainMask, true
        ).first() as String

        val databasePath = "$documentsPath/hafs_smart_v8.db"

        if (!NSFileManager.defaultManager.fileExistsAtPath(databasePath)) {
            val bundle = NSBundle.mainBundle
            val bundlePath = bundle.pathForResource("hafs_smart_v8", ofType = "db")

            bundlePath?.let { sourcePath ->
                NSFileManager.defaultManager.copyItemAtPath(
                    sourcePath,
                    toPath = databasePath,
                    error = null
                )
            }
        }
    }
}