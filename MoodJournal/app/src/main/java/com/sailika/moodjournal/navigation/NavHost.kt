package com.sailika.moodjournal.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sailika.moodjournal.presentation.add_journal.AddJournalScreen
import com.sailika.moodjournal.presentation.view.MainView

@Composable
fun AppNavHost() {
    Surface(color = MaterialTheme.colorScheme.background) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = NavScreen.JournalsScreen.route
        ) {
            composable(route = NavScreen.JournalsScreen.route) {
                MainView(navController = navController, LocalContext.current)

            }

            composable(
                route = NavScreen.AddJournalEntryScreen.route,
                arguments = listOf(
                    navArgument(name = "journalId") {
                        type = NavType.IntType
                        defaultValue = -1
                    },

                    navArgument(name = "moodColor") {
                        type = NavType.IntType
                        defaultValue = -1
                    },
                )
            ) {
                val color = it.arguments?.getInt("moodColor") ?: -1
                AddJournalScreen(navController = navController, moodColor = color)
            }

        }
    }

}