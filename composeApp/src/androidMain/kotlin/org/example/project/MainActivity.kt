package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.example.project.database.DatabaseDriverFactory
import org.example.project.database.DatabaseManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val databaseManager = DatabaseManager(DatabaseDriverFactory(applicationContext))
        lifecycleScope.launch { databaseManager.initialize() }
        setContent {
            App(DemoViewModel(databaseManager.getRepository()))
        }
    }
}