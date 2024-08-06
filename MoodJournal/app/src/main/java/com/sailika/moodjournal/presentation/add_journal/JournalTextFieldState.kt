package com.sailika.moodjournal.presentation.add_journal

data class JournalTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)