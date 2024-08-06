package com.sailika.moodjournal.presentation.view

import com.sailika.moodjournal.model.Journal
import com.sailika.moodjournal.utils.JOrder

sealed class Events {
    data class Order(val jOrder: JOrder) : Events()
    data class DeleteJournalEntry(val journal: Journal) : Events()
    object RestoreJournal : Events()
    object ToggleOrderSection : Events()
}
