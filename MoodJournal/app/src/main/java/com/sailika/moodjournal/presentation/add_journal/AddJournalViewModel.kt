package com.sailika.moodjournal.presentation.add_journal

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sailika.moodjournal.model.InvalidJournalException
import com.sailika.moodjournal.model.Journal
import com.sailika.moodjournal.usecases.JournalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddJournalViewModel @Inject constructor(
    private val journalUseCase: JournalUseCase,
    savedStateHandle: SavedStateHandle //contains the nav arguments
) : ViewModel() {

    //exposing all the states required
    private val _journalTitle = mutableStateOf(JournalTextFieldState(hint = "Type in your mood"))
    val journalTitle: State<JournalTextFieldState> = _journalTitle

    private val _journalContent = mutableStateOf(JournalTextFieldState(hint = "Want to right your thoughts down??"))
    val journalContent: State<JournalTextFieldState> = _journalContent

    private val _journalColor = mutableIntStateOf(Journal.moodColors.random().toArgb())
    val journalColor: State<Int> = _journalColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentEntryID: Int? = null

    init {
        savedStateHandle.get<Int>("JournalId")?.let { journalId ->
            if (journalId != -1) {
                viewModelScope.launch {
                    journalUseCase.GetJournal(journalId)?.also { journal ->
                        currentEntryID = journal.id
                        _journalTitle.value = journalTitle.value.copy(
                            text = journal.title,
                            isHintVisible = false
                        )

                        _journalContent.value = journalContent.value.copy(
                            text = journal.content,
                            isHintVisible = false
                        )

                        _journalColor.value = journal.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddJournalEvent) {
        when (event) {
            //if title is entered, then only update it
            is AddJournalEvent.EnteredTitle -> {
                _journalTitle.value = journalTitle.value.copy(text = event.newTitle)
            }

            //when we are not focused on the text-field and the text is empty, we will show the hint
            is AddJournalEvent.ChangeTitleFocus -> {
                _journalTitle.value = journalTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && journalTitle.value.text.isBlank()
                )
            }

            is AddJournalEvent.EnteredContent -> {
                _journalContent.value = journalContent.value.copy(text = event.newContent)
            }

            is AddJournalEvent.ChangeContentFocus -> {
                _journalContent.value = journalContent.value.copy(
                    isHintVisible = !event.focusState.isFocused && journalContent.value.text.isBlank()
                )
            }

            is AddJournalEvent.ChangeColor -> {
                _journalColor.value = event.color
            }

            is AddJournalEvent.SaveJournal -> {
                viewModelScope.launch {
                    try {
                        journalUseCase.AddJournalEntry(
                            Journal(
                                title = journalTitle.value.text,
                                content = journalContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = journalColor.value,
                                id = currentEntryID
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveJournal)
                    } catch (e: InvalidJournalException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(message = e.message ?: "Couldn't Save Journal")
                        )
                    }
                }
            }

        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveJournal : UiEvent()
    }

}