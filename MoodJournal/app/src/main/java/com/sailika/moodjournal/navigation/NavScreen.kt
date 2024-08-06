package com.sailika.moodjournal.navigation

sealed class NavScreen(val route: String) {
    object JournalsScreen : NavScreen("journals_screen")
    object AddJournalEntryScreen : NavScreen("add_journal_screen")
}