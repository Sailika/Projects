package com.sailika.moodjournal.presentation.view

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sailika.moodjournal.model.Journal
import com.sailika.moodjournal.usecases.JournalUseCase
import com.sailika.moodjournal.utils.JOrder
import com.sailika.moodjournal.utils.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val journalUseCase: JournalUseCase
) : ViewModel() {
    //We need to have one state wrapper class that represents the current UI state of the main screen
    //State will have 4 things : current order,  list of journals, Restore journals and
    // order section visibility

    private val _state = mutableStateOf(JournalsState())
    val state: State<JournalsState> = _state

    private var recentDeletedEntry: Journal? = null

    /* We  get new instance of flow, when getJournalsJob is called, so we have to cancel the old coroutine
       observing our database  */

    private var getJournalsJob: Job? = null

    init {
        getJournals(JOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: Events) {
        when (event) {
            is Events.Order -> {
                //check whether the order is actually changed or not
                if (state.value.journalsOrder::class == event.jOrder::class &&
                    state.value.journalsOrder.orderType == event.jOrder.orderType) {
                    return
                }
                getJournals(event.jOrder)
            }

            is Events.DeleteJournalEntry -> {
                viewModelScope.launch {
                    journalUseCase.DeleteJournalEntry(event.journal)
                    recentDeletedEntry = event.journal
                }

            }

            is Events.RestoreJournal -> {
                //keep reference of last deleted journal
                viewModelScope.launch {
                    journalUseCase.AddJournalEntry(recentDeletedEntry ?: return@launch)

                    //avoid multiple calls
                    recentDeletedEntry = null
                }
            }

            is Events.ToggleOrderSection -> {
                _state.value =
                    state.value.copy(isOrderSectionVisible = !state.value.isOrderSectionVisible)
            }

        }
    }

    private fun getJournals(jOrder: JOrder) {
        getJournalsJob?.cancel()
        getJournalsJob = journalUseCase.GetIndividualJournal(jOrder).onEach { journals ->
            _state.value = state.value.copy(
                journals = journals,
                journalsOrder = jOrder
            )
        }.launchIn(viewModelScope)
    }
}