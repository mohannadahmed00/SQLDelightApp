package org.example.project.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.example.database.HafsDatabase
import java.io.FileOutputStream
import java.io.IOException

actual class DatabaseDriverFactory(private val context: Context) {

    actual suspend fun createDriver(): SqlDriver {
        copyDatabaseFromAssets()

        return AndroidSqliteDriver(
            schema = HafsDatabase.Schema,
            context = context,
            name = "hafs_smart_v8.db"
        )
    }

    private fun copyDatabaseFromAssets() {
        val dbFile = context.getDatabasePath("hafs_smart_v8.db")

        if (!dbFile.exists()) {
            dbFile.parentFile?.mkdirs()

            try {
                context.assets.open("databases/hafs_smart_v8.db").use { inputStream ->
                    FileOutputStream(dbFile).use { outputStream ->
                        inputStream.copyTo(outputStream)
                    }
                }
            } catch (e: IOException) {
                throw RuntimeException("Failed to copy database from assets", e)
            }
        }
    }
}