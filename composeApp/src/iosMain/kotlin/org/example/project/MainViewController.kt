package org.example.project

import androidx.compose.ui.window.ComposeUIViewController
import org.example.project.database.DatabaseDriverFactory
import org.example.project.database.DatabaseManager

fun MainViewController() = ComposeUIViewController {
    val databaseManager = DatabaseManager(DatabaseDriverFactory())
    App(DemoViewModel(databaseManager.getRepository()))
}