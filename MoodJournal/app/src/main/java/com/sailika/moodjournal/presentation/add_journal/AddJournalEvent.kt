package com.sailika.moodjournal.presentation.add_journal

import androidx.compose.ui.focus.FocusState

sealed class AddJournalEvent {
    data class EnteredTitle(val newTitle: String) : AddJournalEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AddJournalEvent()

    data class EnteredContent(val newContent: String) : AddJournalEvent()
    data class ChangeContentFocus(val focusState: FocusState) : AddJournalEvent()

    data class ChangeColor(val color: Int) : AddJournalEvent()

    object SaveJournal : AddJournalEvent()
}